import java.util.ArrayList;
import java.util.List;
import cn.offway.athena.domain.PhOfflineOrders;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhOfflineOrdersGoodsService;
import cn.offway.athena.domain.PhOfflineOrdersGoods;
import cn.offway.athena.repository.PhOfflineOrdersGoodsRepository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Service
public class PhOfflineOrdersGoodsServiceImpl implements cn.offway.athena.service.PhOfflineOrdersGoodsService,PhOfflineOrdersGoodsService{

 private  Logger logger;

@Autowired
 private  PhOfflineOrdersGoodsRepository phOfflineOrdersGoodsRepository;


@Override
public List<PhOfflineOrdersGoods> findByordersNo(String ordersNo){
    return phOfflineOrdersGoodsRepository.findByOrdersNo(ordersNo);
}


@Override
public List<PhOfflineOrdersGoods> findByBrandName(String brandName){
    return phOfflineOrdersGoodsRepository.findByBrandName(brandName);
}


@Override
public List<PhOfflineOrdersGoods> save(List<PhOfflineOrdersGoods> entities){
    return phOfflineOrdersGoodsRepository.save(entities);
}


@Override
public PhOfflineOrdersGoods findOne(Long id){
    return phOfflineOrdersGoodsRepository.findOne(id);
}


@Override
public void delbyOrdersNo(String ordersNo){
    phOfflineOrdersGoodsRepository.deleteByOrdersNo(ordersNo);
}


@Override
public Page<PhOfflineOrdersGoods> findByPage(String ordersNo,Pageable page){
    return phOfflineOrdersGoodsRepository.findAll(new Specification<PhOfflineOrdersGoods>() {

        @Override
        public Predicate toPredicate(Root<PhOfflineOrdersGoods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(ordersNo)) {
                params.add(criteriaBuilder.equal(root.get("ordersNo"), ordersNo));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public void delete(Long id){
    phOfflineOrdersGoodsRepository.delete(id);
}


@Override
public Predicate toPredicate(Root<PhOfflineOrdersGoods> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(ordersNo)) {
        params.add(criteriaBuilder.equal(root.get("ordersNo"), ordersNo));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}