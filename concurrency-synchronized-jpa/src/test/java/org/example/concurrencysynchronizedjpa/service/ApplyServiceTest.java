package org.example.concurrencysynchronizedjpa.service;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.example.concurrencysynchronizedjpa.repository.ApplicantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private EntityManager entityManager;

    @AfterEach
    public void tearDown() {
        applicantRepository.deleteAllInBatch();
        entityManager
                .createNativeQuery("ALTER TABLE APPLICANT AUTO_INCREMENT=1;")
                .executeUpdate();
    }

    @Test
    @Transactional
    void 일반_신청_테스트() {
        long beforeCount = applicantRepository.count();
        for (int i = 0; i < 200; i++) {
            applyService.apply();
        }
        long afterCount = applicantRepository.count();

        applicantRepository.findAll().forEach(applicant -> log.info("applicant: {}", applicant));
        assertThat(beforeCount).isZero();
        assertThat(afterCount).isEqualTo(30);
    }

    @Transactional
    @RepeatedTest(10)
    void 멀티_쓰레드_신청_테스트() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(200);

        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> {
                try {
                    applyService.apply();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(500);
        long afterCount = applicantRepository.count();

        applicantRepository.findAll().forEach(applicant -> log.info("applicant: {}", applicant));
        assertThat(afterCount).isEqualTo(30);
    }
}