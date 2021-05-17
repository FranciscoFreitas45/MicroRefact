import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
public class DeptQueryDTO {

 private  String ID_;

 private  String NAME_;

 private  Integer is_parent;


public void setID_(String iD_){
    ID_ = iD_;
}


public String getNAME_(){
    return NAME_;
}


public String getID_(){
    return ID_;
}


public Integer getIs_parent(){
    return is_parent;
}


public void setIs_parent(Integer is_parent){
    this.is_parent = is_parent;
}


public void setNAME_(String nAME_){
    NAME_ = nAME_;
}


public Specification<Department> getWhereClause(DeptQueryDTO deptQueryDTO){
    return new Specification<Department>() {

        @Override
        public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(deptQueryDTO.getID_())) {
                predicates.add(criteriaBuilder.equal(root.get("ID_").as(String.class), deptQueryDTO.getID_()));
            }
            if (null != deptQueryDTO.getNAME_()) {
                predicates.add(criteriaBuilder.equal(root.get("NAME_").as(String.class), deptQueryDTO.getNAME_()));
            }
            if (null != deptQueryDTO.getIs_parent()) {
                predicates.add(criteriaBuilder.equal(root.get("is_parent").as(String.class), deptQueryDTO.getIs_parent()));
            }
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        }
    };
}


@Override
public Predicate toPredicate(Root<Department> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<>();
    if (StringUtils.isNoneBlank(deptQueryDTO.getID_())) {
        predicates.add(criteriaBuilder.equal(root.get("ID_").as(String.class), deptQueryDTO.getID_()));
    }
    if (null != deptQueryDTO.getNAME_()) {
        predicates.add(criteriaBuilder.equal(root.get("NAME_").as(String.class), deptQueryDTO.getNAME_()));
    }
    if (null != deptQueryDTO.getIs_parent()) {
        predicates.add(criteriaBuilder.equal(root.get("is_parent").as(String.class), deptQueryDTO.getIs_parent()));
    }
    Predicate[] pre = new Predicate[predicates.size()];
    return query.where(predicates.toArray(pre)).getRestriction();
}


}