import cn.offway.athena.domain.PhFeedback;
import cn.offway.athena.domain.PhFeedbackDetail;
import cn.offway.athena.repository.PhFeedbackRepository;
import cn.offway.athena.service.PhFeedbackService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class PhFeedbackServiceImpl implements PhFeedbackService,cn.offway.athena.service.PhFeedbackService{

 private  Logger logger;

@Autowired
 private  PhFeedbackRepository phFeedbackRepository;


@Override
public PhFeedback findByBrandId(Long id){
    return phFeedbackRepository.findOne(new Specification<PhFeedback>() {

        @Override
        public Predicate toPredicate(Root<PhFeedback> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> params = new ArrayList<Predicate>();
            params.add(cb.equal(root.get("brandId"), id));
            Predicate[] predicates = new Predicate[params.size()];
            query.where(params.toArray(predicates));
            return null;
        }
    });
}


@Override
public List<PhFeedback> save(List<PhFeedback> entities){
    return phFeedbackRepository.save(entities);
}


@Override
public PhFeedback findOne(Long id){
    return phFeedbackRepository.findOne(id);
}


@Override
public void delete(Long id){
    phFeedbackRepository.delete(id);
}


@Override
public Page<PhFeedback> findAll(Pageable pageable,String brandId,List<Long> brandIds,String starName,Date sTime,Date eTime){
    return phFeedbackRepository.findAll(new Specification<PhFeedback>() {

        @Override
        public Predicate toPredicate(Root<PhFeedback> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(brandId)) {
                params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
            }
            if (StringUtils.isNotBlank(starName)) {
                Subquery<PhFeedbackDetail> subquery = criteriaQuery.subquery(PhFeedbackDetail.class);
                Root<PhFeedbackDetail> subRoot = subquery.from(PhFeedbackDetail.class);
                subquery.select(subRoot);
                subquery.where(criteriaBuilder.equal(root.get("id"), subRoot.get("pid")), criteriaBuilder.like(subRoot.get("starName"), "%" + starName + "%"));
                params.add(criteriaBuilder.exists(subquery));
            }
            if (brandIds != null) {
                CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("brandId"));
                for (Object brandId : brandIds) {
                    in.value(brandId);
                }
                params.add(in);
            }
            if (sTime != null && eTime != null) {
                params.add(criteriaBuilder.between(root.get("updateTime"), sTime, eTime));
            } else if (sTime != null) {
                params.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updateTime"), sTime));
            } else if (eTime != null) {
                params.add(criteriaBuilder.lessThanOrEqualTo(root.get("updateTime"), eTime));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, pageable);
}


@Override
public Predicate toPredicate(Root<PhFeedback> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> params = new ArrayList<Predicate>();
    params.add(cb.equal(root.get("brandId"), id));
    Predicate[] predicates = new Predicate[params.size()];
    query.where(params.toArray(predicates));
    return null;
}


}