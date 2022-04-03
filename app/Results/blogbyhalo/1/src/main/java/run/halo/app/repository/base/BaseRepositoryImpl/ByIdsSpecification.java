package run.halo.app.repository.base.BaseRepositoryImpl;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import run.halo.app.annotation.SensitiveConceal;
public class ByIdsSpecification implements Specification<T>{

 private  long serialVersionUID;

 private  JpaEntityInformation<T,?> entityInformation;

@Nullable
 private ParameterExpression<Collection> parameter;

ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
    this.entityInformation = entityInformation;
}
@Override
public Predicate toPredicate(Root<T> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    Path<?> path = root.get(this.entityInformation.getIdAttribute());
    this.parameter = cb.parameter(Collection.class);
    return path.in(this.parameter);
}


}