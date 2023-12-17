package org.example.concurrencysynchronizedjpa.repository;

import org.example.concurrencysynchronizedjpa.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
