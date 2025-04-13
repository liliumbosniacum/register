package com.lilium.register.document.person;

import com.lilium.register.document.AbstractDocument;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

@Document(indexName = "person")
@Mapping(mappingPath = "static/person.json")
public class PersonDocument extends AbstractDocument {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
