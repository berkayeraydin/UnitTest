package com.twounit.unittest.repo;

import com.twounit.unittest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
