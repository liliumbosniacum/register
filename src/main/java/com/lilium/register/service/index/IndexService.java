package com.lilium.register.service.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class IndexService {
    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);

    private final ElasticsearchClient client;

    public IndexService(ElasticsearchClient client) {
        this.client = client;
    }

    public void createIndices() {
        final List<IndexInfo> indexInformation = getIndexInformation();
        if (CollectionUtils.isEmpty(indexInformation)) {
            return;
        }

        for (final IndexInfo indexInfo : indexInformation) {
            delete(indexInfo);
            create(indexInfo);
        }
    }

    private void create(IndexInfo indexInfo) {
        try {
            client.indices().create(c -> c.index(indexInfo.indexName()));
        } catch (Exception e) {
            LOG.error("{}", e.getMessage(), e);
        }
    }

    private void delete(IndexInfo indexInfo) {
        try {
            final BooleanResponse exists = client.indices().exists(e -> e.index(indexInfo.indexName()));
            if (!exists.value()) {
                return;
            }

            client.indices().delete(d -> d.index(indexInfo.indexName()));
        } catch (Exception e) {
            LOG.error("{}", e.getMessage(), e);
        }
    }

    private List<IndexInfo> getIndexInformation() {
        final var scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Document.class));

        final Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents(
                "com.lilium.register"
        );

        return beanDefinitions.stream()
                .map(IndexService::getIndexName)
                .filter(Objects::nonNull)
                .map(IndexInfo::new)
                .toList();
    }

    private static String getIndexName(final BeanDefinition definition) {
        try {
            final Class<?> documentClass = Class.forName(definition.getBeanClassName());

            final Document annotation = documentClass.getAnnotation(Document.class);
            return annotation.indexName();
        } catch (final ClassNotFoundException e) {
            LOG.error("{}", e.getMessage(), e);
            return null;
        }
    }
}
