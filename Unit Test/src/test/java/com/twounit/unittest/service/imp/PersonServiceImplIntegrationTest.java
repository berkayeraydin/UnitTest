package com.twounit.unittest.service.imp;

import com.twounit.unittest.dto.PersonDto;
import com.twounit.unittest.entity.Address;
import com.twounit.unittest.repo.AddressRepository;
import com.twounit.unittest.service.KisiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonServiceImplIntegrationTest {

    @Autowired
    private KisiService kisiService;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testSave() {
        // Test edebilmemiz için bu method bizden bir dto istiyor.
        PersonDto personDto = new PersonDto();
        personDto.setName("Berkay");
        personDto.setLastName("Eraydiin");
        personDto.setAddresses(Arrays.asList("İstanbul"));

        PersonDto result = kisiService.save(personDto);
        List<Address> adresses = addressRepository.findAll();


        assertTrue(result.getId() > 0L);
        assertEquals(adresses.size() , 1);

    }
}
