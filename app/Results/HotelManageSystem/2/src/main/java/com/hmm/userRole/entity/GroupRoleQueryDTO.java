package com.hmm.userRole.entity;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
public class GroupRoleQueryDTO {

 private  String departmentName;


public void setDepartmentName(String departmentName){
    this.departmentName = departmentName;
}


public String getDepartmentName(){
    return departmentName;
}


public Specification<GroupRole> getWhereClause(GroupRoleQueryDTO groupRoleQueryDTO){
    return new Specification<GroupRole>() {

        @Override
        public Predicate toPredicate(Root<GroupRole> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(groupRoleQueryDTO.getDepartmentName())) {
                predicates.add(criteriaBuilder.equal(root.get("departmentName").as(String.class), groupRoleQueryDTO.getDepartmentName()));
            }
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        }
    };
}


@Override
public Predicate toPredicate(Root<GroupRole> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    if (StringUtils.isNoneBlank(groupRoleQueryDTO.getDepartmentName())) {
        predicates.add(criteriaBuilder.equal(root.get("departmentName").as(String.class), groupRoleQueryDTO.getDepartmentName()));
    }
    Predicate[] pre = new Predicate[predicates.size()];
    return query.where(predicates.toArray(pre)).getRestriction();
}


}