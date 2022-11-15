package com.twounit.unittest.repo;

import com.twounit.unittest.entity.Adres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepository extends JpaRepository<Adres, Long> {

}
