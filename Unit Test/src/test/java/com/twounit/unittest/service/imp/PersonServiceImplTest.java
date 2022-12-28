package com.twounit.unittest.service.imp;

import com.twounit.unittest.dto.PersonDto;
import com.twounit.unittest.entity.Address;
import com.twounit.unittest.entity.Person;
import com.twounit.unittest.repo.AddressRepository;
import com.twounit.unittest.repo.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
    // Ben bu nesnenin instancesini yaratmam gerekecek. Mockito burada işimize yarayacak.
    @InjectMocks
    private PersonServicesImpl kisiService; // Bunun bir instancesini yaratır.
    // Hatta bunun ihtiyaç duyduğu altında alt nesneler varsa bunları mocklar.
    // KisiRepository  ve AdresRepository gibi.
    // Lakin bunları inject edebilmesi için @Mock ile belirtilmiş olması gerek.
    @Mock
    private PersonRepository personRepository;
    @Mock  // Gerçek olmayan birer kopyasını yartıyoruz.
    private AddressRepository addressRepository;

    // whenSave...ThenHata
    @Test
    public void testSave() {
        // Test edebilmemiz için bu method bizden bir dto istiyor.
        PersonDto personDto = new PersonDto();
        personDto.setName("Berkay");
        personDto.setLastName("Eraydiin");
        personDto.setAddresses(Arrays.asList("İstanbul"));
        Person personMock = Mockito.mock(Person.class);

        when(personMock.getId()).thenReturn(1L);
        // Yukarıda ki satırda kisiMock.getId olursa -> şu olsun anlamı taşır.
        // kisiRepository çaprılınca -> kisiMock dönsün
        when(personRepository.save(any(Person.class))).thenReturn(personMock);

        PersonDto result = kisiService.save(personDto);

        // Dto nun içinde id var mı ? Validate edeceğiz.
        assertEquals(result.getName(), personDto.getName());
        assertEquals(result.getLastName(), personDto.getLastName());

        // Yukarıda 1L tanımladık onun kontrolü
        assertEquals(result.getId(),1L);
    }

    @Test
    public void testSaveException() {
        // Test edebilmemiz için bu method bizden bir dto istiyor.
        PersonDto personDto = new PersonDto();
        //kisiDto.setAdi("Berkay");
        personDto.setLastName("Eraydiin");
        personDto.setAddresses(Arrays.asList("İstanbul"));

        assertThrows(IllegalArgumentException.class, () -> {
            kisiService.save(personDto);
        });
    }

    @Test()
    void nullObjectsShouldReturnNullPointerException() {
        PersonDto personDto = null;
        assertThrows(NullPointerException.class, () -> {kisiService.save(personDto);});
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGetAll() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Berkay");
        person.setLastName("Eraydın");

        when(personRepository.findAll()).thenReturn(Collections.singletonList(person));
        List<PersonDto> personDtos = kisiService.getAll();

        assertEquals(personDtos.size(), 1);
        assertEquals(personDtos.get(0) , PersonDto.builder().id(1L).build());
        assertEquals(personDtos.get(0).getName(), "Berkay");
    }

    @Test
    public void testGetAllWithAddress() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Berkay");
        person.setLastName("Eraydın");

        Address address = new Address();
        address.setAddressType(Address.AddressType.OTHER);
        address.setAddress("Berkay Adres");
        person.setAddresses((Collections.singletonList(address)));

        when(personRepository.findAll()).thenReturn(Collections.singletonList(person));
        List<PersonDto> personDtos = kisiService.getAll();

        assertEquals(personDtos.size(), 1);
        assertEquals(personDtos.get(0).getAddresses().size(),1);
    }

    @Test
    public void testGetAllPageable() {
    }
}