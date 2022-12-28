package com.twounit.unittest.service.imp;

import com.twounit.unittest.dto.KisiDto;
import com.twounit.unittest.entity.Adres;
import com.twounit.unittest.repo.AdresRepository;
import com.twounit.unittest.service.KisiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class KisiServiceImplIntegrationTest {

    @Autowired
    private KisiService kisiService;
    @Autowired
    private AdresRepository adresRepository;

    @Test
    public void testSave() {
        // Test edebilmemiz için bu method bizden bir dto istiyor.
        KisiDto kisiDto = new KisiDto();
        kisiDto.setAdi("Berkay");
        kisiDto.setSoyadi("Eraydiin");
        kisiDto.setAdresler(Arrays.asList("İstanbul"));

        KisiDto result = kisiService.save(kisiDto);
        List<Adres> adress = adresRepository.findAll();


        assertTrue(result.getId() > 0L);
        assertEquals(adress.size() , 1);

    }
}
