package com.twounit.unittest.repo;

import com.twounit.unittest.entity.Kisi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KisiRepository extends JpaRepository<Kisi, Long> {
}
