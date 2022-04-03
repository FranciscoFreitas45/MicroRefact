package com.softserve.edu.Resources.util;
 import com.softserve.edu.Resources.dto.SearchDTO;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component
public class CriteriaQueryBuilder {


public CriteriaQuery<?> createCriteriaQuery(CriteriaBuilder criteriaBuilder,SearchDTO searchDTO){
    String entityType = searchDTO.getEntityType();
    Class<?> ownerClass = null;
    try {
        ownerClass = Class.forName("com.softserve.edu.Resources.entity." + entityType);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    List<Predicate> predicates = new ArrayList<>();
    CriteriaQuery<?> query = criteriaBuilder.createQuery(ownerClass);
    Root<?> root = query.from(ownerClass);
    Map<String, String> fieldsAndValues = searchDTO.getFieldsAndValues();
    fieldsAndValues.forEach((key, value) -> {
        if (!value.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get(key), value));
        }
    });
    query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
    return query;
}


}