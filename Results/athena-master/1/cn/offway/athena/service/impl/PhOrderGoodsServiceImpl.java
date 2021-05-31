import cn.offway.athena.domain.PhOrderGoods;
import cn.offway.athena.repository.PhOrderGoodsRepository;
import cn.offway.athena.service.PhOrderGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class PhOrderGoodsServiceImpl implements PhOrderGoodsService,cn.offway.athena.service.PhOrderGoodsService{

 private  Logger logger;

@Autowired
 private  PhOrderGoodsRepository phOrderGoodsRepository;


@Override
public int getMaxBatch(String oid){
    Object o = phOrderGoodsRepository.getMaxBatch(oid);
    if (o != null) {
        return Integer.valueOf(String.valueOf(o));
    } else {
        return -1;
    }
}


@Override
public List<PhOrderGoods> findByOrderNo(String orderNo,String batch){
    return phOrderGoodsRepository.findByOrderNoAndBatch(orderNo, Long.valueOf(batch));
}


@Override
public List<PhOrderGoods> findNormalByOrderNo(String orderNo){
    return phOrderGoodsRepository.findAll(new Specification<PhOrderGoods>() {

        @Override
        public Predicate toPredicate(Root<PhOrderGoods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> params = new ArrayList<Predicate>();
            params.add(cb.equal(root.get("orderNo"), orderNo));
            params.add(cb.notEqual(root.get("state"), 2));
            Predicate[] predicates = new Predicate[params.size()];
            query.where(params.toArray(predicates));
            return null;
        }
    }, new Sort("brandId"));
}


@Override
public Page<PhOrderGoods> findByBrandId(String brandId,Pageable page){
    return phOrderGoodsRepository.findAll(new Specification<PhOrderGoods>() {

        @Override
        public Predicate toPredicate(Root<PhOrderGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (!"".equals(brandId)) {
                params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public PhOrderGoods save(PhOrderGoods phOrderGoods){
    return phOrderGoodsRepository.save(phOrderGoods);
}


@Override
public PhOrderGoods findOne(Long id){
    return phOrderGoodsRepository.findOne(id);
}


@Override
public Predicate toPredicate(Root<PhOrderGoods> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (!"".equals(brandId)) {
        params.add(criteriaBuilder.equal(root.get("brandId"), brandId));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


@Override
public int getRest(String oid){
    Object o = phOrderGoodsRepository.getRest(oid);
    return Integer.valueOf(String.valueOf(o));
}


}