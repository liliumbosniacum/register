package com.lilium.register.rest.person.dto;

import com.lilium.register.rest.dto.BaseDTO;

public class PersonDTO extends BaseDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
