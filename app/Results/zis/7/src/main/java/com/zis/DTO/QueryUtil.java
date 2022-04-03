package com.zis.DTO;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
public class QueryUtil {

 private  Logger logger;

 private  String EQ;

 private  String NOTEQ;

 private  String LIKE;

 private  String NE;

 private  String IN;

 private  String LT;

 private  String GE;

 private  String LE;

 private  String BETWEEN;

 private  String GROUP;

 private  List<Condition> conditions;

 private  List<Order> orderList;

 private  String name;

 private  Object[] vals;

 private  String condition;

 private  String name;

 private  boolean asc;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public Specification<T> getSpecification(){
    return new Specification<T>() {

        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<String> groupNames = null;
            List<Predicate> psList = new ArrayList<Predicate>();
            for (Condition c : conditions) {
                if (EQ.equals(c.condition)) {
                    Path<String> p = root.get(c.name);
                    psList.add(cb.equal(p, c.vals[0]));
                }
                if (NOTEQ.equals(c.condition)) {
                    Path<String> p = root.get(c.name);
                    psList.add(cb.notEqual(p, c.vals[0]));
                }
                if (LIKE.equals(c.condition)) {
                    String likeName = c.vals[0].toString();
                    Path<String> p = root.get(c.name);
                    psList.add(cb.like(p, likeName));
                }
                if (NE.equals(c.condition)) {
                    Path<String> p = root.get(c.name);
                    psList.add(cb.notEqual(p, c.vals[0]));
                }
                if (LT.equals(c.condition)) {
                    Object value = c.vals[0];
                    Path<String> p = root.get(c.name);
                    if (value instanceof Date) {
                        psList.add(cb.lessThan(p.as(Date.class), (Date) value));
                    } else if (value instanceof String) {
                        psList.add(cb.lessThan(p.as(String.class), value.toString()));
                    } else {
                        psList.add(cb.lt(p.as(Number.class), (Number) value));
                    }
                }
                if (IN.equals(c.condition)) {
                    psList.add(root.get(c.name).in(c.vals));
                }
                if (BETWEEN.equals(c.condition)) {
                    Path p = root.get(c.name);
                    Object max = c.vals[0];
                    Object min = c.vals[1];
                    psList.add(cb.between(p.as(Comparable.class), (Comparable) max, (Comparable) min));
                }
                if (GE.equals(c.condition)) {
                    Object value = c.vals[0];
                    Path<String> p = root.get(c.name);
                    if (value instanceof Date) {
                        psList.add(cb.greaterThanOrEqualTo(p.as(Date.class), (Date) value));
                    } else if (value instanceof String) {
                        psList.add(cb.greaterThanOrEqualTo(p.as(String.class), value.toString()));
                    } else {
                        psList.add(cb.ge(p.as(Number.class), (Number) value));
                    }
                }
                if (LE.equals(c.condition)) {
                    Object value = c.vals[0];
                    Path<String> p = root.get(c.name);
                    psList.add(cb.le(p.as(Number.class), (Number) value));
                }
                if (GROUP.equals(c.condition)) {
                    String name = c.name;
                    if (groupNames == null) {
                        groupNames = new ArrayList<String>();
                    }
                    groupNames.add(name);
                }
            }
            Predicate[] ps = new Predicate[psList.size()];
            query.where(psList.toArray(ps));
            for (Order order : orderList) {
                String orderName = order.name;
                if (order.asc) {
                    query.orderBy(cb.asc(root.get(orderName)));
                } else {
                    query.orderBy(cb.desc(root.get(orderName)));
                }
            }
            // 判断集合groupNames是否为空如果不为空则添加group条件
            if (groupNames != null && !groupNames.isEmpty()) {
                for (String s : groupNames) {
                    query.groupBy(root.get(s));
                }
            }
            return query.getRestriction();
        }
    };
}


public QueryUtil<T> eq(String name,Object value){
    isNotEmpty(EQ, value);
    conditions.add(new Condition(EQ, name, value));
    return this;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/eq"))

.queryParam("name",name)
.queryParam("value",value)
;
QueryUtil<T> aux = restTemplate.getForObject(builder.toUriString(),QueryUtil<T>.class);
return aux;
}


}