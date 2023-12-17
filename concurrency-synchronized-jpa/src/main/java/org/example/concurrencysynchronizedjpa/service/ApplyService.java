package org.example.concurrencysynchronizedjpa.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.concurrencysynchronizedjpa.entity.Applicant;
import org.example.concurrencysynchronizedjpa.repository.ApplicantRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final ApplicantRepository applicantRepository;

    @Transactional
    public synchronized void apply() {
        final long count = applicantRepository.count(); // 신청자 수
        if (count < 30) {
            Applicant applicant = Applicant.builder()
                    .name("테스트")
                    .build();
            applicantRepository.save(applicant);
        }
    }
}
