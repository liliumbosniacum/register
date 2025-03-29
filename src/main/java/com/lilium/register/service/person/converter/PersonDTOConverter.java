package com.lilium.register.service.person.converter;

import com.lilium.register.document.person.PersonDocument;
import com.lilium.register.rest.person.dto.PersonDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonDTOConverter {

    public PersonDocument convertToDocument(final PersonDTO dto) {
        if (dto == null) {
            return null;
        }

        final PersonDocument personDocument = new PersonDocument();
        personDocument.setId(dto.getId());
        personDocument.setName(dto.getName());

        return personDocument;
    }
}
