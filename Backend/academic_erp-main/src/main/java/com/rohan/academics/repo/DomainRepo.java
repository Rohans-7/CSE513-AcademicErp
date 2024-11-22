package com.rohan.academics.repo;

import com.rohan.academics.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepo extends JpaRepository<Domain, Integer> {

}
