import cn.offway.athena.domain.PhFeedbackDetail;
import cn.offway.athena.repository.PhFeedbackDetailRepository;
import cn.offway.athena.service.PhFeedbackDetailService;
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
import java.util.Date;
import java.util.List;
@Service
public class PhFeedbackDetailServiceImpl implements cn.offway.athena.service.PhFeedbackDetailService,PhFeedbackDetailService{

 private  Logger logger;

@Autowired
 private  PhFeedbackDetailRepository phFeedbackDetailRepository;


@Override
public Page<PhFeedbackDetail> findByPid(Long pid,String starName,Date sTime,Date eTime,Pageable pageable){
    return phFeedbackDetailRepository.findAll(new Specification<PhFeedbackDetail>() {

        @Override
        public Predicate toPredicate(Root<PhFeedbackDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> params = new ArrayList<Predicate>();
            params.add(cb.equal(root.get("pid"), pid));
            if (StringUtils.isNotBlank(starName)) {
                params.add(cb.like(root.get("starName"), "%" + starName + "%"));
            }
            if (sTime != null && eTime != null) {
                params.add(cb.between(root.get("backTime"), sTime, eTime));
            } else if (sTime != null) {
                params.add(cb.greaterThanOrEqualTo(root.get("backTime"), sTime));
            } else if (eTime != null) {
                params.add(cb.lessThanOrEqualTo(root.get("backTime"), eTime));
            }
            Predicate[] predicates = new Predicate[params.size()];
            query.where(params.toArray(predicates));
            return null;
        }
    }, pageable);
}


@Override
public List<PhFeedbackDetail> save(List<PhFeedbackDetail> entities){
    return phFeedbackDetailRepository.save(entities);
}


@Override
public PhFeedbackDetail findOne(Long id){
    return phFeedbackDetailRepository.findOne(id);
}


@Override
public void delByPid(Long pid){
    phFeedbackDetailRepository.deleteByPid(pid);
}


@Override
public void delete(Long id){
    phFeedbackDetailRepository.delete(id);
}


@Override
public Predicate toPredicate(Root<PhFeedbackDetail> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> params = new ArrayList<Predicate>();
    params.add(cb.equal(root.get("pid"), pid));
    params.add(cb.equal(root.get("starName"), starName));
    Predicate[] predicates = new Predicate[params.size()];
    query.where(params.toArray(predicates));
    return null;
}


@Override
public Long checkStarName(Long pid,String starName){
    return phFeedbackDetailRepository.count(new Specification<PhFeedbackDetail>() {

        @Override
        public Predicate toPredicate(Root<PhFeedbackDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> params = new ArrayList<Predicate>();
            params.add(cb.equal(root.get("pid"), pid));
            params.add(cb.equal(root.get("starName"), starName));
            Predicate[] predicates = new Predicate[params.size()];
            query.where(params.toArray(predicates));
            return null;
        }
    });
}


}