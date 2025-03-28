package com.lilium.register.document.person;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "person")
public class PersonDocument {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
