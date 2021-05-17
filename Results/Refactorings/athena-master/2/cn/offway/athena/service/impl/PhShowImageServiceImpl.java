import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cn.offway.athena.domain.PhShowImage;
import cn.offway.athena.repository.PhShowImageRepository;
import cn.offway.athena.service.PhShowImageService;
@Service
public class PhShowImageServiceImpl implements cn.offway.athena.service.PhShowImageService,PhShowImageService{

 private  Logger logger;

@Autowired
 private  PhShowImageRepository phShowImageRepository;


@Override
public PhShowImage findByOrderNo(String orderNo){
    return phShowImageRepository.findByOrderNo(orderNo);
}


@Override
public PhShowImage save(PhShowImage phShowImage){
    return phShowImageRepository.save(phShowImage);
}


@Override
public PhShowImage findOne(Long id){
    return phShowImageRepository.findOne(id);
}


@Override
public Page<PhShowImage> findByPage(String realName,String position,String orderNo,String unionid,String status,Long brandId,String isOffway,List<Long> brandIds,Pageable page){
    return phShowImageRepository.findAll(new Specification<PhShowImage>() {

        @Override
        public Predicate toPredicate(Root<PhShowImage> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(orderNo)) {
                params.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
            }
            if (StringUtils.isNotBlank(realName)) {
                params.add(criteriaBuilder.like(root.get("realName"), "%" + realName + "%"));
            }
            if (StringUtils.isNotBlank(position)) {
                params.add(criteriaBuilder.like(root.get("position"), "%" + position + "%"));
            }
            if (StringUtils.isNotBlank(unionid)) {
                params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
            }
            if (StringUtils.isNotBlank(status)) {
                params.add(criteriaBuilder.equal(root.get("status"), status));
            }
            if (null != brandId) {
                params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
            }
            if (StringUtils.isNotBlank(isOffway)) {
                params.add(criteriaBuilder.equal(root.get("isOffway"), isOffway));
            }
            if (CollectionUtils.isNotEmpty(brandIds)) {
                In<Object> in = criteriaBuilder.in(root.get("brandId"));
                for (Object brandId : brandIds) {
                    in.value(brandId);
                }
                params.add(in);
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public Predicate toPredicate(Root<PhShowImage> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(orderNo)) {
        params.add(criteriaBuilder.equal(root.get("orderNo"), orderNo));
    }
    if (StringUtils.isNotBlank(realName)) {
        params.add(criteriaBuilder.like(root.get("realName"), "%" + realName + "%"));
    }
    if (StringUtils.isNotBlank(position)) {
        params.add(criteriaBuilder.like(root.get("position"), "%" + position + "%"));
    }
    if (StringUtils.isNotBlank(unionid)) {
        params.add(criteriaBuilder.equal(root.get("unionid"), unionid));
    }
    if (StringUtils.isNotBlank(status)) {
        params.add(criteriaBuilder.equal(root.get("status"), status));
    }
    if (null != brandId) {
        params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
    }
    if (StringUtils.isNotBlank(isOffway)) {
        params.add(criteriaBuilder.equal(root.get("isOffway"), isOffway));
    }
    if (CollectionUtils.isNotEmpty(brandIds)) {
        In<Object> in = criteriaBuilder.in(root.get("brandId"));
        for (Object brandId : brandIds) {
            in.value(brandId);
        }
        params.add(in);
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}