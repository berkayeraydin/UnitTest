package com.twounit.unittest.controller;

import com.twounit.unittest.dto.PersonDto;
import com.twounit.unittest.service.PersonServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonServices personServices;

    @PostMapping
    public ResponseEntity<PersonDto> save(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personServices.save(personDto));
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> tumunuListele() {
        return ResponseEntity.ok(personServices.getAll());
    }
}