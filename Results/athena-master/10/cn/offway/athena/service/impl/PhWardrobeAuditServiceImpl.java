import cn.offway.athena.domain.PhWardrobeAudit;
import cn.offway.athena.repository.PhWardrobeAuditRepository;
import cn.offway.athena.service.PhWardrobeAuditService;
import org.apache.commons.lang.StringUtils;
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
public class PhWardrobeAuditServiceImpl implements cn.offway.athena.service.PhWardrobeAuditService,PhWardrobeAuditService{

 private  Logger logger;

@Autowired
 private  PhWardrobeAuditRepository phWardrobeAuditRepository;


@Override
public List<PhWardrobeAudit> save(List<PhWardrobeAudit> entities){
    return phWardrobeAuditRepository.save(entities);
}


@Override
public PhWardrobeAudit findOne(Long id){
    return phWardrobeAuditRepository.findOne(id);
}


@Override
public Page<PhWardrobeAudit> listAll(String brandId,String goodsName,String goodsId,String state,List<Long> brandIds,Pageable pageable){
    return phWardrobeAuditRepository.findAll(new Specification<PhWardrobeAudit>() {

        @Override
        public Predicate toPredicate(Root<PhWardrobeAudit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> params = new ArrayList<>();
            if (StringUtils.isNotBlank(brandId)) {
                params.add(cb.equal(root.get("brandId"), brandId));
            }
            if (StringUtils.isNotBlank(goodsName)) {
                params.add(cb.like(root.get("goodsName"), "%" + goodsName + "%"));
            }
            if (StringUtils.isNotBlank(goodsId)) {
                params.add(cb.equal(root.get("goodsId"), goodsId));
            }
            if (StringUtils.isNotBlank(state)) {
                params.add(cb.equal(root.get("state"), state));
            }
            if (brandIds != null) {
                CriteriaBuilder.In<Object> in = cb.in(root.get("brandId"));
                for (Object id : brandIds) {
                    in.value(id);
                }
                params.add(in);
            }
            Predicate[] predicates = new Predicate[params.size()];
            query.where(params.toArray(predicates));
            return null;
        }
    }, pageable);
}


@Override
public void delete(Long id){
    phWardrobeAuditRepository.delete(id);
}


@Override
public Predicate toPredicate(Root<PhWardrobeAudit> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> params = new ArrayList<>();
    if (StringUtils.isNotBlank(brandId)) {
        params.add(cb.equal(root.get("brandId"), brandId));
    }
    if (StringUtils.isNotBlank(goodsName)) {
        params.add(cb.like(root.get("goodsName"), "%" + goodsName + "%"));
    }
    if (StringUtils.isNotBlank(goodsId)) {
        params.add(cb.equal(root.get("goodsId"), goodsId));
    }
    if (StringUtils.isNotBlank(state)) {
        params.add(cb.equal(root.get("state"), state));
    }
    if (brandIds != null) {
        CriteriaBuilder.In<Object> in = cb.in(root.get("brandId"));
        for (Object id : brandIds) {
            in.value(id);
        }
        params.add(in);
    }
    Predicate[] predicates = new Predicate[params.size()];
    query.where(params.toArray(predicates));
    return null;
}


}