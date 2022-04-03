package com.hmm.finance.roomOrder.domain;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.entity.EmployeeQueryDTO;
public class RoomOrderQueryDTO {


@SuppressWarnings("serial")
public Specification<RoomOrder> getWhereClause(RoomOrderQueryDTO roomOrderQueryDTO){
    return new Specification<RoomOrder>() {

        @Override
        public Predicate toPredicate(Root<RoomOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        }
    };
}


@Override
public Predicate toPredicate(Root<RoomOrder> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    Predicate[] pre = new Predicate[predicates.size()];
    return query.where(predicates.toArray(pre)).getRestriction();
}


}