package com.twounit.unittest.repo;

import com.twounit.unittest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
