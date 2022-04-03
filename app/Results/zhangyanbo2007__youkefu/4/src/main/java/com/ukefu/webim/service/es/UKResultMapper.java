package com.ukefu.webim.service.es;
 import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.ElasticsearchException;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.ScriptedField;
import org.springframework.data.elasticsearch.core.AbstractResultMapper;
import org.springframework.data.elasticsearch.core.DefaultEntityMapper;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
public class UKResultMapper extends AbstractResultMapper{

 private  MappingContext<? extends ElasticsearchPersistentEntity<?>,ElasticsearchPersistentProperty> mappingContext;

public UKResultMapper() {
    super(new DefaultEntityMapper());
}public UKResultMapper(MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext) {
    super(new DefaultEntityMapper());
    this.mappingContext = mappingContext;
}public UKResultMapper(EntityMapper entityMapper) {
    super(entityMapper);
}public UKResultMapper(MappingContext<? extends ElasticsearchPersistentEntity<?>, ElasticsearchPersistentProperty> mappingContext, EntityMapper entityMapper) {
    super(entityMapper);
    this.mappingContext = mappingContext;
}
@Override
public LinkedList<T> mapResults(MultiGetResponse responses,Class<T> clazz){
    LinkedList<T> list = new LinkedList<T>();
    for (MultiGetItemResponse response : responses.getResponses()) {
        if (!response.isFailed() && response.getResponse().isExists()) {
            T result = mapEntity(response.getResponse().getSourceAsString(), clazz);
            setPersistentEntityId(result, response.getResponse().getId(), clazz);
            list.add(result);
        }
    }
    return list;
}


public String buildJSONFromFields(Collection<SearchHitField> values){
    JsonFactory nodeFactory = new JsonFactory();
    try {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        JsonGenerator generator = nodeFactory.createGenerator(stream, JsonEncoding.UTF8);
        generator.writeStartObject();
        for (SearchHitField value : values) {
            if (value.getValues().size() > 1) {
                generator.writeArrayFieldStart(value.getName());
                for (Object val : value.getValues()) {
                    generator.writeObject(val);
                }
                generator.writeEndArray();
            } else {
                generator.writeObjectField(value.getName(), value.getValue());
            }
        }
        generator.writeEndObject();
        generator.flush();
        return new String(stream.toByteArray(), Charset.forName("UTF-8"));
    } catch (IOException e) {
        return null;
    }
}


public void setPersistentEntityId(T result,String id,Class<T> clazz){
    if (mappingContext != null && clazz.isAnnotationPresent(Document.class)) {
        ElasticsearchPersistentEntity<?> persistentEntity = mappingContext.getPersistentEntity(clazz);
        PersistentProperty<?> idProperty = persistentEntity.getIdProperty();
        // Only deal with String because ES generated Ids are strings !
        if (idProperty != null && idProperty.getType().isAssignableFrom(String.class)) {
            persistentEntity.getPropertyAccessor(result).setProperty(idProperty, id);
        }
    }
}


@Override
public T mapResult(GetResponse response,Class<T> clazz){
    T result = mapEntity(response.getSourceAsString(), clazz);
    if (result != null) {
        setPersistentEntityId(result, response.getId(), clazz);
    }
    return result;
}


public T mapEntity(Collection<SearchHitField> values,SearchHit hit,Class<T> clazz){
    return mapEntity(buildJSONFromFields(values), hit, clazz);
}


public void populateScriptFields(T result,SearchHit hit){
    if (hit.getFields() != null && !hit.getFields().isEmpty() && result != null) {
        for (java.lang.reflect.Field field : result.getClass().getDeclaredFields()) {
            ScriptedField scriptedField = field.getAnnotation(ScriptedField.class);
            if (scriptedField != null) {
                String name = scriptedField.name().isEmpty() ? field.getName() : scriptedField.name();
                SearchHitField searchHitField = hit.getFields().get(name);
                if (searchHitField != null) {
                    field.setAccessible(true);
                    try {
                        if (name.equals("title") && hit.getHighlightFields().get("title") != null) {
                            field.set(result, hit.getHighlightFields().get("title").fragments()[0].string());
                        } else {
                            field.set(result, searchHitField.getValue());
                        }
                    } catch (IllegalArgumentException e) {
                        throw new ElasticsearchException("failed to set scripted field: " + name + " with value: " + searchHitField.getValue(), e);
                    } catch (IllegalAccessException e) {
                        throw new ElasticsearchException("failed to access scripted field: " + name, e);
                    }
                }
            }
        }
    }
}


}