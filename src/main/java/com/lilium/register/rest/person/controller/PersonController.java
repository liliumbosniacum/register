package com.lilium.register.rest.person.controller;

import com.lilium.register.rest.person.dto.PersonDTO;
import com.lilium.register.search.SearchFilters;
import com.lilium.register.service.person.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @PostMapping
    public void save(@RequestBody final PersonDTO dto) {
        service.save(dto);
    }

    @PostMapping("/search")
    public List<PersonDTO> search(@RequestBody final SearchFilters filters) {
        return service.search(filters);
    }
}
