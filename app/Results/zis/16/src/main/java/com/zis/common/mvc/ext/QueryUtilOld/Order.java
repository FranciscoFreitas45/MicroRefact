package com.zis.common.mvc.ext.QueryUtilOld;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
public class Order {

 private  String name;

 private  boolean asc;

public Order(String name, boolean asc) {
    super();
    this.name = name;
    this.asc = asc;
}
}