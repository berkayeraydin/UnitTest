package com.twounit.unittest.controller;

import com.twounit.unittest.dto.PersonDto;
import com.twounit.unittest.service.KisiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kisi")
@RequiredArgsConstructor
public class PersonController {

    private final KisiService kisiService;

    @PostMapping
    public ResponseEntity<PersonDto> kaydet(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(kisiService.save(personDto));
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> tumunuListele() {
        return ResponseEntity.ok(kisiService.getAll());
    }
}