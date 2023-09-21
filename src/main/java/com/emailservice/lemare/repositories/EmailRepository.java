package com.emailservice.lemare.repositories;

import com.emailservice.lemare.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
