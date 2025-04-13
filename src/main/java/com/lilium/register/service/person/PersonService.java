package com.lilium.register.service.person;

import com.lilium.register.document.person.PersonDocument;
import com.lilium.register.document.person.PersonRepository;
import com.lilium.register.rest.person.dto.PersonDTO;
import com.lilium.register.search.ElasticsearchProxy;
import com.lilium.register.search.query.QueryType;
import com.lilium.register.search.SearchFilters;
import com.lilium.register.search.query.SearchMeta;
import com.lilium.register.service.person.converter.PersonDTOConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final PersonDTOConverter converter;
    private final ElasticsearchProxy<PersonDocument, PersonDTO> client;

    public PersonService(final PersonRepository repository,
                         final PersonDTOConverter converter,
                         ElasticsearchProxy<PersonDocument, PersonDTO> client) {
        this.repository = repository;
        this.converter = converter;
        this.client = client;
    }

    public void save(final PersonDTO dto) {
        final PersonDocument document = converter.convertToDocument(dto);
        if (document == null) {
            return;
        }

        repository.save(document);
    }

    public List<PersonDTO> search(SearchFilters filters) {
        return client.search(
                filters,
                new SearchMeta(List.of("name"), "person", QueryType.MATCH),
                PersonDocument.class
        );
    }
}
