import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhCreditDetailService;
import cn.offway.athena.domain.PhCreditDetail;
import cn.offway.athena.domain.PhShowImage;
import cn.offway.athena.repository.PhCreditDetailRepository;
@Service
public class PhCreditDetailServiceImpl implements cn.offway.athena.service.PhCreditDetailService,PhCreditDetailService{

 private  Logger logger;

@Autowired
 private  PhCreditDetailRepository phCreditDetailRepository;


@Override
public PhCreditDetail save(PhCreditDetail phCreditDetail){
    return phCreditDetailRepository.save(phCreditDetail);
}


@Override
public PhCreditDetail findOne(Long id){
    return phCreditDetailRepository.findOne(id);
}


@Override
public Page<PhCreditDetail> findByPage(String orderNo,String unionid,String type,Pageable page){
    return phCreditDetailRepository.findAll(new Specification<PhCreditDetail>() {

        @Override
        public Predicate toPredicate(Root<PhCreditDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(orderNo)) {
                params.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
            }
            if (StringUtils.isNotBlank(unionid)) {
                params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
            }
            if (StringUtils.isNotBlank(type)) {
                params.add(criteriaBuilder.equal(root.get("type"), type));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public Predicate toPredicate(Root<PhCreditDetail> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(orderNo)) {
        params.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
    }
    if (StringUtils.isNotBlank(unionid)) {
        params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
    }
    if (StringUtils.isNotBlank(type)) {
        params.add(criteriaBuilder.equal(root.get("type"), type));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}