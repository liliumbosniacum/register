package com.lilium.register.service.person;

import com.lilium.register.document.person.PersonDocument;
import com.lilium.register.document.person.PersonRepository;
import com.lilium.register.rest.person.dto.PersonDTO;
import com.lilium.register.service.person.converter.PersonDTOConverter;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonDTOConverter converter;

    public PersonService(final PersonRepository repository,
                         final PersonDTOConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public void save(final PersonDTO dto) {
        final PersonDocument document = converter.convertToDocument(dto);
        if (document == null) {
            return;
        }

        repository.save(document);
    }
}
