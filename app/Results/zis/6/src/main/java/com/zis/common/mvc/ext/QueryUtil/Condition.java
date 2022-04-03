package com.zis.common.mvc.ext.QueryUtil;
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
public class Condition {

 private  String name;

 private  Object[] vals;

 private  String condition;

public Condition(String condition, String name, Object... vals) {
    this.name = name;
    this.vals = vals;
    this.condition = condition;
}
}