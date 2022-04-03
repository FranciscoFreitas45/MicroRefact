package com.zis.trade.processor;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.zis.common.util.SequenceCreator;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopInfo.ShopInfoStatus;
import com.zis.shop.service.ShopService;
import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.trade.dto.CreateTradeOrderDTO.SubOrder;
import com.zis.trade.entity.Order;
import com.zis.trade.entity.OrderLog;
import com.zis.trade.entity.Order.ExpressStatus;
import com.zis.trade.entity.Order.OrderType;
import com.zis.trade.entity.Order.PayStatus;
import com.zis.trade.entity.Order.StorageStatus;
import com.zis.trade.entity.OrderDetail;
import com.zis.trade.entity.OrderDetail.DetailStatus;
import com.zis.trade.entity.OrderLog.OperateType;
import com.zis.trade.entity.OrderOuter;
import com.zis.trade.repository.OrderDao;
import com.zis.trade.repository.OrderDetailDao;
import com.zis.trade.repository.OrderLogDao;
import com.zis.trade.repository.OrderOuterDao;
import com.zis.Interface.ShopService;
@Component
public class OrderProcessorImpl implements OrderProcessor{

@Autowired
 private  OrderOuterDao orderOuterDao;

@Autowired
 private  OrderDao orderDao;

@Autowired
 private  OrderDetailDao orderDetailDao;

@Autowired
 private  OrderLogDao orderLogDao;

@Autowired
 private  ShopService shopService;

 private  String[] CANCELLED_ORDER;

 private  Logger logger;


@Override
@Transactional
public Order createOrder(CreateTradeOrderDTO orderDTO){
    logger.debug("创建订单, param={}", JSONObject.toJSONString(orderDTO));
    checkForCreateOrder(orderDTO);
    ShopInfo shop = shopService.findShopById(orderDTO.getShopId());
    checkShop(orderDTO.getShopId(), shop);
    // TODO 幂等性控制
    OrderOuter orderOuter = this.orderOuterDao.findByShopIdAndOutOrderNumber(orderDTO.getShopId(), orderDTO.getOutOrderNumber());
    if (orderOuter != null) {
        throw new RuntimeException("订单已存在");
    }
    // if (orderOuter != null) {
    // Order order =
    // this.orderDao.findByOrderGroupNumberAndPayStatusNotIn(orderOuter.getOrderGroupNumber(),
    // Arrays.asList(CANCELLED_ORDER));
    // if(order != null){
    // throw new RuntimeException("订单已存在");
    // }
    // }
    // 按照shopId+地址，查找已存在的订单
    List<Order> existOrders = orderDao.findByShopIdAndReceiverNameAndReceiverPhoneAndReceiverAddr(orderDTO.getShopId(), orderDTO.getReceiverName(), orderDTO.getReceiverPhone(), orderDTO.getReceiverAddr());
    boolean blockNewOrder = false;
    String orderGroupNumber = null;
    if (CollectionUtils.isEmpty(existOrders)) {
        // 判断是否可以合并
        boolean canCombined = false;
        for (Order existOrder : existOrders) {
            // 合并到之前的订单
            if (canCombined) {
                // TODO 合并订单
                break;
            } else // 不能合并的，标记为拦截订单，在出库环节由人工进行处理
            {
                blockNewOrder = true;
                existOrder.setBlockFlag(true);
                existOrder.setBlockReason(OrderHelper.BLOCK_REASON_COMBINED);
                this.orderDao.save(existOrder);
            }
        }
    }
    if (orderGroupNumber == null) {
        orderGroupNumber = generateOrderGroupNumber();
    }
    // 生成新订单
    Order order = new Order();
    BeanUtils.copyProperties(orderDTO, order);
    order.setShopName(shop.getShopName());
    order.setpName(shop.getpName());
    order.setBlockFlag(blockNewOrder);
    order.setBlockReason(blockNewOrder ? OrderHelper.BLOCK_REASON_COMBINED : null);
    order.setCompanyId(shop.getCompanyId());
    order.setExpressStatus(ExpressStatus.WAIT_FOR_PRINT.getValue());
    order.setOrderGroupNumber(orderGroupNumber);
    order.setPayStatus(PayStatus.UNPAID.getValue());
    order.setStorageStatus(StorageStatus.WAIT_ARRANGE.getValue());
    order.setCreateTime(new Date());
    order.setUpdateTime(new Date());
    order = this.orderDao.save(order);
    // 生成订单详情
    List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
    for (SubOrder subOrder : orderDTO.getSubOrders()) {
        OrderDetail detail = new OrderDetail();
        BeanUtils.copyProperties(subOrder, detail);
        detail.setOrder(order);
        detail.setStatus(DetailStatus.VALID.getValue());
        detail.setCreateTime(new Date());
        detail.setUpdateTime(new Date());
        orderDetailList.add(detail);
    }
    this.orderDetailDao.save(orderDetailList);
    logger.info("生成新订单, order={}", JSONObject.toJSONString(order));
    // 创建orderOuter
    OrderOuter outOrder = new OrderOuter();
    BeanUtils.copyProperties(orderDTO, outOrder);
    outOrder.setShopName(shop.getShopName());
    outOrder.setpName(shop.getpName());
    outOrder.setCreateTime(new Date());
    outOrder.setUpdateTime(new Date());
    outOrder.setOrderData(JSONObject.toJSONString(orderDTO.getSubOrders()));
    outOrder.setOrderGroupNumber(orderGroupNumber);
    orderOuterDao.save(outOrder);
    logger.info("生成网店订单, orderOuter={}", JSONObject.toJSONString(outOrder));
    OrderLog log = OrderHelper.createOrderLog(order, orderDTO.getOperator(), OperateType.CREATE, "");
    orderLogDao.save(log);
    return order;
}


public void checkForCreateOrder(CreateTradeOrderDTO orderDTO){
    Assert.notNull(orderDTO, "参数不能为空");
    Assert.notNull(orderDTO.getOrderMoney(), "订单金额不能为空");
    Assert.isTrue(OrderType.isDefined(orderDTO.getOrderType()), "订单类型非法:" + orderDTO.getOrderType());
    Assert.notNull(orderDTO.getOutOrderNumber(), "网店订单号不能为空");
    Assert.notNull(orderDTO.getReceiverAddr(), "收件人地址不能为空");
    Assert.notNull(orderDTO.getReceiverName(), "收件人姓名不能为空");
    Assert.notNull(orderDTO.getReceiverPhone(), "收件人电话不能为空");
    Assert.notNull(orderDTO.getShopId(), "shopId不能为空");
    Assert.isTrue(!CollectionUtils.isEmpty(orderDTO.getSubOrders()), "订单中没有任何商品");
}


public String generateOrderGroupNumber(){
    Integer seq = SequenceCreator.getSequence(SequenceCreator.SEQ_ORDER_GROUP_NUM);
    return ZisUtils.getDateString("yyyyMMddHHmmss") + seq;
}


public void checkShop(Integer shopId,ShopInfo shop){
    Assert.notNull(shop, "店铺不存在" + shopId);
    if (!ShopInfoStatus.NORMAL.getValue().equals(shop.getStatus())) {
        throw new RuntimeException("该店铺已停用, shopId=" + shopId);
    }
}


}