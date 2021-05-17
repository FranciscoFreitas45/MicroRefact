import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhOrderRemarkService;
import cn.offway.athena.domain.PhOrderRemark;
import cn.offway.athena.repository.PhOrderRemarkRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Service
public class PhOrderRemarkServiceImpl implements cn.offway.athena.service.PhOrderRemarkService,PhOrderRemarkService{

 private  Logger logger;

@Autowired
 private  PhOrderRemarkRepository phOrderRemarkRepository;


@Override
public List<PhOrderRemark> save(List<PhOrderRemark> entities){
    return phOrderRemarkRepository.save(entities);
}


@Override
public PhOrderRemark findOne(Long id){
    return phOrderRemarkRepository.findOne(id);
}


@Override
public Page<PhOrderRemark> findAllByPage(String id,Pageable page){
    return phOrderRemarkRepository.findAll(new Specification<PhOrderRemark>() {

        @Override
        public Predicate toPredicate(Root<PhOrderRemark> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(id)) {
                params.add(criteriaBuilder.equal(root.get("ordersId"), id));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public void delete(Long id){
    phOrderRemarkRepository.delete(id);
}


@Override
public List<PhOrderRemark> findAllByOrdersId(String id){
    return phOrderRemarkRepository.findAllByOrdersId(id);
}


@Override
public Predicate toPredicate(Root<PhOrderRemark> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(id)) {
        params.add(criteriaBuilder.equal(root.get("ordersId"), id));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}