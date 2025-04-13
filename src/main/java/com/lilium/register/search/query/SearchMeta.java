package com.lilium.register.search.query;

import java.util.List;

public class SearchMeta {
    private final List<String> fields;
    private final String index;
    private final QueryType type;

    public SearchMeta(List<String> fields, String index, QueryType type) {
        this.fields = fields;
        this.index = index;
        this.type = type;
    }

    public List<String> getFields() {
        return fields;
    }

    public String getIndex() {
        return index;
    }

    public QueryType getType() {
        return type;
    }
}
