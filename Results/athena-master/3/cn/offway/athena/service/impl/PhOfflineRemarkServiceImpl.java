import cn.offway.athena.domain.PhOfflineRemark;
import cn.offway.athena.repository.PhOfflineRemarkRepository;
import cn.offway.athena.service.PhOfflineRemarkService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class PhOfflineRemarkServiceImpl implements PhOfflineRemarkService,cn.offway.athena.service.PhOfflineRemarkService{

 private  Logger logger;

@Autowired
 private  PhOfflineRemarkRepository phOfflineRemarkRepository;


@Override
public String findLatest(Long id){
    Object o = phOfflineRemarkRepository.findLatest(id);
    if (o == null) {
        return "";
    } else {
        return String.valueOf(o);
    }
}


@Override
public List<PhOfflineRemark> save(List<PhOfflineRemark> entities){
    return phOfflineRemarkRepository.save(entities);
}


@Override
public PhOfflineRemark findOne(Long id){
    return phOfflineRemarkRepository.findOne(id);
}


@Override
public Page<PhOfflineRemark> findAllByPage(String id,Pageable page){
    return phOfflineRemarkRepository.findAll(new Specification<PhOfflineRemark>() {

        @Override
        public Predicate toPredicate(Root<PhOfflineRemark> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
    phOfflineRemarkRepository.delete(id);
}


@Override
public Predicate toPredicate(Root<PhOfflineRemark> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(id)) {
        params.add(criteriaBuilder.equal(root.get("ordersId"), id));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}