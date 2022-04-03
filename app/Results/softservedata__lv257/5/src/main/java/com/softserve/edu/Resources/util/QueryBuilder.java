package com.softserve.edu.Resources.util;
 import com.softserve.edu.Resources.dto.SearchDTO;
import com.softserve.edu.Resources.entity.ConstrainedProperty;
import com.softserve.edu.Resources.entity.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class QueryBuilder {

 static  Logger logger;


public String namedQueryForLookingByResourceId(String tableName,List<ConstrainedProperty> resourceProperties){
    StringBuilder createQuery = new StringBuilder();
    createQuery.append(formSelectAndFromPart(resourceProperties, tableName));
    createQuery.append(" WHERE gr.id=:id");
    return createQuery.toString();
}


public String namedQueryForLookingByResourcesIds(String tableName,List<ConstrainedProperty> resourceProperties){
    StringBuilder createQuery = new StringBuilder();
    createQuery.append(formSelectAndFromPart(resourceProperties, tableName));
    createQuery.append(" WHERE gr.id IN (:ids)");
    return createQuery.toString();
}


public String buildInsertResourceImplQuery(ResourceType resourceType,Map<String,String> propertiesAndValues){
    String insertClause = "INSERT INTO " + resourceType.getTableName();
    StringBuilder sb = new StringBuilder();
    sb.append(insertClause);
    // build columns to insert to
    Set<String> keys = propertiesAndValues.keySet();
    String columnNames = keys.stream().sorted().collect(Collectors.joining(","));
    sb.append(" (id,").append(columnNames).append(")");
    // build named parameters for the jdbc template
    String columnNamesAsParameters = keys.stream().sorted().map(key -> ":" + key).collect(Collectors.joining(","));
    sb.append(" VALUES(").append(":").append("id").append(",").append(columnNamesAsParameters).append(")");
    String readyInsertString = sb.toString();
    System.out.println(readyInsertString);
    return readyInsertString;
}


public String queryForJdbcTemplate(String tableName,Map<String,String> valuesToSearch,List<ConstrainedProperty> allResourceProperties){
    StringBuilder createQuery = new StringBuilder();
    createQuery.append(formSelectAndFromPart(allResourceProperties, tableName));
    if (!valuesToSearch.values().isEmpty() || !valuesToSearch.isEmpty()) {
        createQuery.append(" WHERE ");
        Iterator<Map.Entry<String, String>> entries = valuesToSearch.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            createQuery.append("gr." + entry.getKey() + " = ?");
            if (entries.hasNext()) {
                createQuery.append(" AND ");
            }
        }
    }
    return createQuery.toString();
}


public String buildQuery(SearchDTO searchDTO){
    logger.info("Building query to search");
    StringBuilder stringBuilder = new StringBuilder();
    String ownerType = searchDTO.getEntityType();
    logger.info("Searching this type of entity: " + ownerType);
    String ownerTypeFirstChar = ownerType.substring(0, 1).toLowerCase();
    Map<String, String> fieldsAndValues = searchDTO.getFieldsAndValues();
    // e.g. - "SELECT a FROM Address a"
    stringBuilder.append("SELECT " + ownerTypeFirstChar + " FROM " + ownerType + " " + ownerTypeFirstChar);
    Set<Map.Entry<String, String>> entries = fieldsAndValues.entrySet();
    // get rid of empty entry value
    // from not empty values, build where-clause
    String whereClause = entries.stream().filter(entry -> !entry.getValue().isEmpty()).map(entry -> entry.getKey() + "=\'" + entry.getValue() + "\'").collect(Collectors.joining(" AND "));
    // all fields are empty, return empty string
    // if not, append "WHERE" key-word and whereClause string
    if (whereClause.isEmpty()) {
        return "";
    } else {
        stringBuilder.append(" WHERE ");
        stringBuilder.append(whereClause);
    }
    String readyQuery = stringBuilder.toString();
    logger.info("Query to send to the DAO layer: " + readyQuery);
    return readyQuery;
}


public String formSelectAndFromPart(List<ConstrainedProperty> resourceProperties,String tableName){
    StringBuilder selectAndFromPart = new StringBuilder();
    selectAndFromPart.append("SELECT gr.id");
    selectAndFromPart.append(resourceProperties.isEmpty() ? " " : ", ");
    Iterator<ConstrainedProperty> iter = resourceProperties.iterator();
    while (iter.hasNext()) {
        selectAndFromPart.append("gr." + iter.next().getProperty().getColumnName() + "");
        if (iter.hasNext()) {
            selectAndFromPart.append(", ");
        } else {
            selectAndFromPart.append(" ");
        }
    }
    selectAndFromPart.append("FROM " + tableName + " gr");
    return selectAndFromPart.toString();
}


}