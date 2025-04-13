package com.lilium.register.search.query;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import com.lilium.register.search.SearchFilters;

public final class QueryBuilder {

    private QueryBuilder() {
    }

    public static SearchRequest buildSearchRequest(final SearchFilters filters, final SearchMeta meta) {
        SearchRequest.Builder builder = new SearchRequest.Builder();
        builder.index(meta.getIndex());

        Query.Builder queryBuilder = new Query.Builder();
        if (meta.getType() == QueryType.MATCH) {
            MatchQuery.Builder matchQuery = new MatchQuery.Builder();

            for (String field : meta.getFields()) {
                matchQuery.field(field);
            }
            matchQuery.query(filters.getTerm());
            queryBuilder.match(matchQuery.build());
        }

        builder.query(queryBuilder.build());

        return builder.build();
    }
}
