package com.sobey.framework.utils;
 import java.util.Collection;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import com.google.common.collect.Lists;
public class DynamicSpecifications {


public Specification<T> bySearchFilter(Collection<SearchFilter> filters,Class<T> clazz){
    return new Specification<T>() {

        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
            if (Collections3.isNotEmpty(filters)) {
                List<Predicate> predicates = Lists.newArrayList();
                for (SearchFilter filter : filters) {
                    // nested path translate, 如Task的名为"user.name"的filedName,
                    // 转换为Task.user.name属性
                    String[] names = StringUtils.split(filter.fieldName, ".");
                    Path expression = root.get(names[0]);
                    for (int i = 1; i < names.length; i++) {
                        expression = expression.get(names[i]);
                    }
                    // logic operator
                    switch(filter.operator) {
                        case EQ:
                            predicates.add(builder.equal(expression, filter.value));
                            break;
                        case LIKE:
                            predicates.add(builder.like(expression, "%" + filter.value + "%"));
                            break;
                        case GT:
                            predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                            break;
                        case LT:
                            predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                            break;
                        case GTE:
                            predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                            break;
                        case LTE:
                            predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                            break;
                        case NOT:
                            predicates.add(builder.notEqual(expression, (Comparable) filter.value));
                            break;
                        case IsNull:
                            predicates.add(builder.isNull(expression));
                            break;
                        case NotNull:
                            predicates.add(builder.isNotNull(expression));
                            break;
                    }
                }
                // 将所有条件用 and 联合起来
                if (predicates.size() > 0) {
                    return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            }
            return builder.conjunction();
        }
    };
}


@SuppressWarnings({ "rawtypes", "unchecked" })
@Override
public Predicate toPredicate(Root<T> root,CriteriaQuery<?> query,CriteriaBuilder builder){
    if (Collections3.isNotEmpty(filters)) {
        List<Predicate> predicates = Lists.newArrayList();
        for (SearchFilter filter : filters) {
            // nested path translate, 如Task的名为"user.name"的filedName,
            // 转换为Task.user.name属性
            String[] names = StringUtils.split(filter.fieldName, ".");
            Path expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
            // logic operator
            switch(filter.operator) {
                case EQ:
                    predicates.add(builder.equal(expression, filter.value));
                    break;
                case LIKE:
                    predicates.add(builder.like(expression, "%" + filter.value + "%"));
                    break;
                case GT:
                    predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                    break;
                case LT:
                    predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                    break;
                case GTE:
                    predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                    break;
                case LTE:
                    predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                    break;
                case NOT:
                    predicates.add(builder.notEqual(expression, (Comparable) filter.value));
                    break;
                case IsNull:
                    predicates.add(builder.isNull(expression));
                    break;
                case NotNull:
                    predicates.add(builder.isNotNull(expression));
                    break;
            }
        }
        // 将所有条件用 and 联合起来
        if (predicates.size() > 0) {
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
    return builder.conjunction();
}


}