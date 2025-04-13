package com.lilium.register.search.converter;

import com.lilium.register.document.AbstractDocument;
import com.lilium.register.rest.dto.BaseDTO;

public interface Converter<E extends AbstractDocument, T extends BaseDTO> {

    Class<E> getDocumentClass();

    T convertToDto(E document);

    E convertToDocument(T dto);
}
