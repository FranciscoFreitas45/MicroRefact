import cn.offway.athena.domain.PhOrderExpressInfo;
import cn.offway.athena.domain.PhOrderGoods;
import cn.offway.athena.domain.PhOrderInfo;
import cn.offway.athena.dto.sf.ReqAddOrder;
import cn.offway.athena.repository.PhOrderGoodsRepository;
import cn.offway.athena.repository.PhOrderInfoRepository;
import cn.offway.athena.service.PhOrderExpressInfoService;
import cn.offway.athena.service.PhOrderGoodsService;
import cn.offway.athena.service.PhOrderInfoService;
import cn.offway.athena.service.SfExpressService;
import cn.offway.athena.utils.JsonResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria;
import javax.persistence.criteria.CriteriaBuilder.In;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class PhOrderInfoServiceImpl implements cn.offway.athena.service.PhOrderInfoService,PhOrderInfoService{

 private  Logger logger;

@Autowired
 private  PhOrderInfoRepository phOrderInfoRepository;

@Autowired
 private  SfExpressService sfExpressService;

@Autowired
 private  PhOrderExpressInfoService phOrderExpressInfoService;

@Autowired
 private  PhOrderGoodsRepository phOrderGoodsRepository;

@Autowired
 private  PhOrderGoodsService phOrderGoodsService;

@Value("${is-prd}")
 private  boolean isPrd;


@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
@Override
public void cancel(String orderNo){
    PhOrderInfo phOrderInfo = findByOrderNo(orderNo);
    phOrderInfo.setStatus("4");
    save(phOrderInfo);
    phOrderGoodsRepository.updateStock(orderNo);
}


@Override
public PhOrderInfo findByOrderNo(String orderNo){
    return phOrderInfoRepository.findByOrderNo(orderNo);
}


@Deprecated
public JsonResult saveOrder(String orderNo,String[] ids){
    /*
         * 1.修改订单状态
         * 2.快递预约上门
         */
    PhOrderInfo phOrderInfo = findByOrderNo(orderNo);
    if ("1".equals(phOrderInfo.getStatus())) {
        return new JsonResult("500", "订单已发货！", null);
    }
    PhOrderExpressInfo phOrderExpressInfo = phOrderExpressInfoService.findByOrderNoAndType(orderNo, "0");
    phOrderExpressInfo.setExpressOrderNo(generateOrderNo("SF"));
    ReqAddOrder addOrder = new ReqAddOrder();
    addOrder.setD_address(phOrderExpressInfo.getToContent());
    addOrder.setD_contact(phOrderExpressInfo.getToRealName());
    addOrder.setD_tel(phOrderExpressInfo.getToPhone());
    addOrder.setJ_province(phOrderExpressInfo.getFromProvince());
    addOrder.setJ_city(phOrderExpressInfo.getFromCity());
    addOrder.setJ_county(phOrderExpressInfo.getFromCounty());
    addOrder.setJ_address(phOrderExpressInfo.getFromContent());
    addOrder.setJ_contact(phOrderExpressInfo.getFromRealName());
    addOrder.setJ_tel(phOrderExpressInfo.getFromPhone());
    addOrder.setOrder_source("OFFWAY");
    addOrder.setOrder_id(phOrderExpressInfo.getExpressOrderNo());
    // 付款方式：1:寄方付2:收方付3:第三方付
    addOrder.setPay_method("2");
    addOrder.setRemark("");
    addOrder.setSendstarttime("");
    JsonResult result;
    if (isPrd) {
        result = sfExpressService.addOrder(addOrder);
    } else {
        result = new JsonResult("200", "", "1234567890");
    }
    if ("200".equals(result.getCode())) {
        long batch = -2;
        String mailNo = String.valueOf(result.getData());
        for (String id : ids) {
            PhOrderGoods orderGoods = phOrderGoodsService.findOne(Long.valueOf(id));
            if (orderGoods != null) {
                if (batch == -2) {
                    batch = phOrderGoodsService.getMaxBatch(orderGoods.getOrderNo());
                }
                orderGoods.setMailNo(mailNo);
                orderGoods.setBatch(batch + 1);
                orderGoods.setRemark("平台发货");
                phOrderGoodsService.save(orderGoods);
            }
        }
        // phOrderExpressInfo.setBatch(batch + 1);
        // phOrderExpressInfo.setMailNo(mailNo);
        // 状态[0-已下单,1-已发货,2-已寄回,3-已收货,4-已取消,5-已部分收货,6-审核,7-部分寄出]
        if (phOrderGoodsService.getRest(orderNo) == 0) {
            phOrderInfo.setStatus("1");
            // 已下单
            phOrderExpressInfo.setStatus("1");
        } else {
            phOrderInfo.setStatus("7");
            // 已部分下单
            phOrderExpressInfo.setStatus("5");
        }
        phOrderExpressInfoService.save(phOrderExpressInfo);
        save(phOrderInfo);
    }
    return result;
}


@Override
public PhOrderInfo save(PhOrderInfo phOrderInfo){
    return phOrderInfoRepository.save(phOrderInfo);
}


@Override
public PhOrderInfo findOne(Long id){
    return phOrderInfoRepository.findOne(id);
}


@Override
public Page<PhOrderInfo> findByPage(String realName,String position,String orderNo,String unionid,Long brandId,String isOffway,Pageable page){
    return phOrderInfoRepository.findAll(new Specification<PhOrderInfo>() {

        @Override
        public Predicate toPredicate(Root<PhOrderInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> params = new ArrayList<Predicate>();
            if (StringUtils.isNotBlank(orderNo)) {
                params.add(cb.equal(root.get("orderNo"), orderNo));
            }
            if (StringUtils.isNotBlank(realName)) {
                params.add(cb.like(root.get("realName"), "%" + realName + "%"));
            }
            if (StringUtils.isNotBlank(position)) {
                params.add(cb.like(root.get("position"), "%" + position + "%"));
            }
            if (StringUtils.isNotBlank(unionid)) {
                params.add(cb.equal(root.get("unionid"), unionid));
            }
            if (null != brandId) {
                params.add(cb.equal(root.get("brandId"), brandId));
            }
            if (StringUtils.isNotBlank(isOffway)) {
                params.add(cb.equal(root.get("isOffway"), isOffway));
            }
            In<Object> in = cb.in(root.get("status"));
            String[] statusArr = new String[] { "0", "7" };
            for (Object status : statusArr) {
                in.value(status);
            }
            params.add(in);
            Predicate[] predicates = new Predicate[params.size()];
            query.where(params.toArray(predicates));
            return null;
        }
    }, page);
}


@Override
public Predicate toPredicate(Root<PhOrderInfo> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> params = new ArrayList<Predicate>();
    if (StringUtils.isNotBlank(orderNo)) {
        params.add(cb.equal(root.get("orderNo"), orderNo));
    }
    if (StringUtils.isNotBlank(realName)) {
        params.add(cb.like(root.get("realName"), "%" + realName + "%"));
    }
    if (StringUtils.isNotBlank(position)) {
        params.add(cb.like(root.get("position"), "%" + position + "%"));
    }
    if (StringUtils.isNotBlank(unionid)) {
        params.add(cb.equal(root.get("unionid"), unionid));
    }
    if (null != brandId) {
        params.add(cb.equal(root.get("brandId"), brandId));
    }
    if (StringUtils.isNotBlank(isOffway)) {
        params.add(cb.equal(root.get("isOffway"), isOffway));
    }
    In<Object> in = cb.in(root.get("status"));
    String[] statusArr = new String[] { "0", "7" };
    for (Object status : statusArr) {
        in.value(status);
    }
    params.add(in);
    Predicate[] predicates = new Predicate[params.size()];
    query.where(params.toArray(predicates));
    return null;
}


@Override
public String generateOrderNo(String prefix){
    int count = phOrderInfoRepository.hasOrder(prefix);
    if (count == 0) {
        phOrderInfoRepository.insert(prefix);
    }
    return phOrderInfoRepository.generateOrderNo(prefix);
}


}