package com.twounit.unittest.service.imp;

import com.oneunit.unittest.Grader;
import com.twounit.unittest.dto.KisiDto;
import com.twounit.unittest.entity.Kisi;
import com.twounit.unittest.repo.AdresRepository;
import com.twounit.unittest.repo.KisiRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class KisiServiceImplTest {
    // Ben bu nesnenin instancesini yaratmam gerekecek. Mockito burada işimize yarayacak.
    @InjectMocks
    private KisiServiceImpl kisiService; // Bunun bir instancesini yaratır.
    // Hatta bunun ihtiyaç duyduğu altında alt nesneler varsa bunları mocklar.
    // KisiRepository  ve AdresRepository gibi.
    // Lakin bunları inject edebilmesi için @Mock ile belirtilmiş olması gerek.
    @Mock
    private KisiRepository kisiRepository;
    @Mock  // Gerçek olmayan birer kopyasını yartıyoruz.
    private AdresRepository adresRepository;

    // whenSave...ThenHata
    @Test
    public void testSave() {
        // Test edebilmemiz için bu method bizden bir dto istiyor.
        KisiDto kisiDto = new KisiDto();
        kisiDto.setAdi("Berkay");
        kisiDto.setSoyadi("Eraydiin");
        kisiDto.setAdresler(Arrays.asList("İstanbul"));
        Kisi kisiMock = Mockito.mock(Kisi.class);

        Mockito.when(kisiMock.getId()).thenReturn(1L);
        // Yukarıda ki satırda kisiMock.getId olursa -> şu olsun anlamı taşır.
        // kisiRepository çaprılınca -> kisiMock dönsün
        Mockito.when(kisiRepository.save(any(Kisi.class))).thenReturn(kisiMock);

        KisiDto result = kisiService.save(kisiDto);

        // Dto nun içinde id var mı ? Validate edeceğiz.
        assertEquals(result.getAdi(),kisiDto.getAdi());
        assertEquals(result.getSoyadi(),kisiDto.getSoyadi());

        // Yukarıda 1L tanımladık onun kontrolü
        assertEquals(result.getId(),1L);
    }

    @Test()
    void nullAdiShouldReturnNullPointerException() {
        KisiDto kisiDto = new KisiDto();
        kisiDto.setAdi(null);
        assertThrows(NullPointerException.class, () -> {kisiService.save(kisiDto);});
    }

    @Test()
    void nullObjectsShouldReturnNullPointerException() {
        KisiDto kisiDto = null;
        assertThrows(NullPointerException.class, () -> {kisiService.save(kisiDto);});
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGetAll() {
    }

    @Test
    public void testGetAllPageable() {
    }
}