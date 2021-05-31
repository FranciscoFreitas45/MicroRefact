import cn.offway.athena.domain.PhOfflineOrders;
import cn.offway.athena.domain.PhOfflineOrdersGoods;
import cn.offway.athena.repository.PhOfflineOrdersRepository;
import cn.offway.athena.service.PhOfflineOrdersGoodsService;
import cn.offway.athena.service.PhOfflineOrdersService;
import javax.persistence.criteria.CriteriaBuilder.In;
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
public class PhOfflineOrdersServiceImpl implements PhOfflineOrdersService,cn.offway.athena.service.PhOfflineOrdersService{

 private  Logger logger;

@Autowired
 private  PhOfflineOrdersRepository phOfflineOrdersRepository;

@Autowired
 private  PhOfflineOrdersGoodsService phOfflineOrdersGoodsService;


@Override
public List<PhOfflineOrders> save(List<PhOfflineOrders> entities){
    return phOfflineOrdersRepository.save(entities);
}


@Override
public PhOfflineOrders findOne(Long id){
    return phOfflineOrdersRepository.findOne(id);
}


@Override
public Page<PhOfflineOrders> findByPage(String realName,String users,String state,String ordersNo,Date sTime,Date eTime,String brandName,Pageable page){
    return phOfflineOrdersRepository.findAll(new Specification<PhOfflineOrders>() {

        @Override
        public Predicate toPredicate(Root<PhOfflineOrders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(realName)) {
                params.add(criteriaBuilder.equal(root.get("realName"), realName));
            }
            if (StringUtils.isNotBlank(users)) {
                params.add(criteriaBuilder.equal(root.get("users"), users));
            }
            if (StringUtils.isNotBlank(state)) {
                params.add(criteriaBuilder.equal(root.get("state"), state));
            }
            if (StringUtils.isNotBlank(ordersNo)) {
                params.add(criteriaBuilder.equal(root.get("ordersNo"), ordersNo));
            }
            if (StringUtils.isNotBlank(brandName)) {
                List<PhOfflineOrdersGoods> offlineOrdersGoods = phOfflineOrdersGoodsService.findByBrandName(brandName);
                if (offlineOrdersGoods.size() > 0) {
                    List<String> orderNos = new ArrayList<>();
                    for (PhOfflineOrdersGoods offlineOrdersGood : offlineOrdersGoods) {
                        orderNos.add(offlineOrdersGood.getOrdersNo());
                    }
                    In<Object> in = criteriaBuilder.in(root.get("ordersNo"));
                    for (Object orderNo : orderNos) {
                        in.value(orderNo);
                    }
                    params.add(in);
                }
            }
            if (sTime != null && eTime != null) {
                params.add(criteriaBuilder.between(root.get("createTime"), sTime, eTime));
            } else if (sTime != null) {
                params.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), sTime));
            } else if (eTime != null) {
                params.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), eTime));
            }
            Predicate[] predicates = new Predicate[params.size()];
            criteriaQuery.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public void delete(Long id){
    phOfflineOrdersRepository.delete(id);
}


@Override
public Predicate toPredicate(Root<PhOfflineOrders> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(realName)) {
        params.add(criteriaBuilder.equal(root.get("realName"), realName));
    }
    if (StringUtils.isNotBlank(users)) {
        params.add(criteriaBuilder.equal(root.get("users"), users));
    }
    if (StringUtils.isNotBlank(state)) {
        params.add(criteriaBuilder.equal(root.get("state"), state));
    }
    if (StringUtils.isNotBlank(ordersNo)) {
        params.add(criteriaBuilder.equal(root.get("ordersNo"), ordersNo));
    }
    if (StringUtils.isNotBlank(brandName)) {
        List<PhOfflineOrdersGoods> offlineOrdersGoods = phOfflineOrdersGoodsService.findByBrandName(brandName);
        if (offlineOrdersGoods.size() > 0) {
            List<String> orderNos = new ArrayList<>();
            for (PhOfflineOrdersGoods offlineOrdersGood : offlineOrdersGoods) {
                orderNos.add(offlineOrdersGood.getOrdersNo());
            }
            In<Object> in = criteriaBuilder.in(root.get("ordersNo"));
            for (Object orderNo : orderNos) {
                in.value(orderNo);
            }
            params.add(in);
        }
    }
    if (sTime != null && eTime != null) {
        params.add(criteriaBuilder.between(root.get("createTime"), sTime, eTime));
    } else if (sTime != null) {
        params.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), sTime));
    } else if (eTime != null) {
        params.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), eTime));
    }
    Predicate[] predicates = new Predicate[params.size()];
    criteriaQuery.where(params.toArray(predicates));
    return null;
}


}