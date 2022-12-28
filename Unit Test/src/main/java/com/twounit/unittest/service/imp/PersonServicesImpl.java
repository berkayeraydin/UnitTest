package com.twounit.unittest.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.twounit.unittest.dto.PersonDto;
import com.twounit.unittest.entity.Address;
import com.twounit.unittest.entity.Person;
import com.twounit.unittest.repo.AddressRepository;
import com.twounit.unittest.repo.PersonRepository;
import com.twounit.unittest.service.PersonServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class PersonServicesImpl implements PersonServices {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
        Assert.notNull(personDto.getName(), "Adi alani zorunludur!");

        if (Objects.isNull(personDto)) {
            throw new NullPointerException();
        }

        Person person = new Person();
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        final Person personDb = personRepository.save(person);

        List<Address> liste = new ArrayList<>();
        personDto.getAddresses().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setAddressType(Address.AddressType.OTHER);
            address.setActive(true);
            address.setPerson(personDb);
            liste.add(address);
        });
        addressRepository.saveAll(liste);
        personDto.setId(personDb.getId());
        return personDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> kisiler = personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();

        kisiler.forEach(it -> {
            PersonDto personDto =new PersonDto();
            personDto.setId(it.getId());
            personDto.setName(it.getName());
            personDto.setLastName(it.getLastName());
            personDto.setAddresses(
                    it.getAddresses() != null ?
                            it.getAddresses().stream().map(Address::getAddress).collect(Collectors.toList())
                            : null);
            personDtos.add(personDto);
        });
        return personDtos;
    }

    @Override
    public Page<PersonDto> getAll(Pageable pageable) {
        return null;
    }
}