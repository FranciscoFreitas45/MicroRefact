package com.circle.dao.order.impl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.circle.constant.CircleTable;
import com.circle.constant.SystemDict;
import com.circle.dao.order.IOrderDao;
import com.circle.pojo.order.Order;
import com.circle.pojo.order.OrderDetail;
import com.xwtec.xwserver.dao.common.ICommonDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.util.StringUtil;
@Repository
public class OrderDaoImpl implements IOrderDao{

 private  Logger logger;

 public  String QUERY_ORDER_LIST;

 public  String QUERY_ORDER_BY_ID;

 public  String QUERY_ORDER_BY_ORDERNO;

 public  String QUERY_ORDERDETAIL_BY_ORDERID;

 public  String QUERY_ORDERDETAIL_BY_ID;

 public  String SAVE_ORDER_SQL;

 public  String SAVE_ORDER_DETAIL_SQL;

 public  String QUERY_ORDER_LIST_SQL;

 public  String QUERY_ORDERDETAIL_LIST_SQL;

 public  String UPDATE_ORDER_PAY_STATUS;

 public  String RECIVEGOOD＿SQL;

@Resource
 private ICommonDao commonDao;


@Override
public boolean setOrderPayStatus(int payStatus,String orderId,String tradeNo){
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("payStatus", payStatus);
    paramsMap.put("payNo", tradeNo);
    paramsMap.put("orderNo", orderId);
    return commonDao.update(UPDATE_ORDER_PAY_STATUS, paramsMap) > 0 ? true : false;
}


@Override
public Order queryOrderByOrderNo(String orderNo){
    logger.debug("[OderDaoImpl.queryOrderById] start...");
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("orderNo", orderNo);
    Order order = commonDao.queryForObject(QUERY_ORDER_BY_ORDERNO, paramMap, Order.class);
    logger.debug("[OderDaoImpl.queryOrderById] end...");
    return order;
}


@Override
public boolean saveOrder(Order order){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("orderId", order.getOrderId());
    paramMap.put("circleId", order.getCircleId());
    paramMap.put("organizerId", order.getOrganizerId());
    paramMap.put("issueAddress", order.getIssueAddress());
    paramMap.put("issueTime", order.getIssueTime());
    paramMap.put("endTime", order.getEndTime());
    paramMap.put("goodsAmount", order.getGoodsAmount());
    paramMap.put("orderAmount", order.getOrderAmount());
    paramMap.put("remark", order.getRemark());
    paramMap.put("userId", order.getUserId());
    paramMap.put("orderNo", order.getOrderNo());
    paramMap.put("receiveName", order.getReceiveName());
    paramMap.put("receivePhone", order.getReceivePhone());
    paramMap.put("shipType", order.getShipType());
    paramMap.put("payType", order.getPayType());
    paramMap.put("batchId", order.getBatchId());
    boolean flag = commonDao.update(SAVE_ORDER_SQL, paramMap) > 0 ? true : false;
    return flag;
}


@Override
public boolean saveOrderDetail(OrderDetail orderDetail){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("orderId", orderDetail.getOrderId());
    paramMap.put("goodName", orderDetail.getGoodName());
    paramMap.put("goodTitle", orderDetail.getGoodTitle());
    paramMap.put("goodUnit", orderDetail.getGoodUnit());
    paramMap.put("goodId", orderDetail.getGoodId());
    paramMap.put("unitPrice", orderDetail.getUnitPrice());
    paramMap.put("buyNum", orderDetail.getBuyNum());
    paramMap.put("total", orderDetail.getTotal());
    boolean flag = commonDao.update(SAVE_ORDER_DETAIL_SQL, paramMap) > 0 ? true : false;
    // int orderId=commonDao.getLastId(CircleTable.ORDER.getTableName(), "order_id");
    // orderDetail.setOrderId(orderId);
    return flag;
}


public boolean reciveGood(String order_id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("shipStatus", SystemDict.SHIPPING_STATUS_SIGN);
    paramMap.put("status", SystemDict.ORDER_STATUS_COMPLETE);
    paramMap.put("orderId", order_id);
    return commonDao.update(RECIVEGOOD＿SQL, paramMap) > 0 ? true : false;
}


@Override
public List<Map<String,Object>> queryOrderList(Page page,int userId){
    StringBuilder sb = new StringBuilder(QUERY_ORDER_LIST_SQL);
    page.getSearchParam().put("userid", userId + "");
    if (page != null && page.getSearchParam() != null) {
        if (page.getSearchParam().containsKey("ship_status") && !StringUtil.isEmpty(page.getSearchParam().get("ship_status"))) {
            sb.append(" and o.ship_status=:ship_status ");
        }
        if (page.getSearchParam().containsKey("status") && !StringUtil.isEmpty(page.getSearchParam().get("status"))) {
            sb.append(" and o.status=:status ");
        }
        if (page.getSearchParam().containsKey("commentStatus") && !StringUtil.isEmpty(page.getSearchParam().get("commentStatus"))) {
            if (page.getSearchParam().get("commentStatus").equals("0")) {
                sb.append(" and o.comment_status is null ");
            } else {
                sb.append(" and o.comment_status=:commentStatus ");
            }
        }
    }
    sb.append(" order by orderTime desc ");
    return commonDao.queryForList(sb.toString(), page.getSearchParam(), page);
}


public Order queryOrderById(String id){
    logger.debug("[OderDaoImpl.queryOrderById] start...");
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("orderId", id);
    Order order = commonDao.queryForObject(QUERY_ORDER_BY_ID, paramMap, Order.class);
    logger.debug("[OderDaoImpl.queryOrderById] end...");
    return order;
}


@Override
public Map<String,Object> queryOrderDetailById(String id){
    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("id", id);
    return commonDao.queryForMap(QUERY_ORDERDETAIL_BY_ID, paramMap);
}


@Override
public List<Map<String,Object>> queryOrderDetailList(String orderId){
    if (StringUtil.isEmpty(orderId)) {
        return null;
    }
    Map<String, Object> paramsMap = new HashMap<String, Object>();
    paramsMap.put("orderId", orderId);
    StringBuilder sb = new StringBuilder(QUERY_ORDERDETAIL_LIST_SQL);
    sb.append(" and o.order_id in(" + orderId + ")");
    return commonDao.queryForList(sb.toString());
}


public List<OrderDetail> queryOrdersDetailById(String id,Page page){
    logger.debug("[OderDaoImpl.queryOrderDetailById] start...");
    Map<String, String> paramMap = new HashMap<String, String>();
    paramMap.put("orderId", id);
    page.setSearchParam(paramMap);
    List<OrderDetail> orderDetailList = commonDao.queryForList(QUERY_ORDERDETAIL_BY_ORDERID, page.getSearchParam(), page, OrderDetail.class);
    logger.debug("[OderDaoImpl.queryOrdersDetailById] end...");
    return orderDetailList;
}


public void batchSaveOrderDetail(List<OrderDetail> orderDetailList){
    List<Map<String, ?>> paramListMap = new ArrayList<Map<String, ?>>();
    for (OrderDetail orderDetail : orderDetailList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", orderDetail.getOrderId());
        paramMap.put("goodName", orderDetail.getGoodName());
        paramMap.put("goodTitle", orderDetail.getGoodTitle());
        paramMap.put("goodUnit", orderDetail.getGoodUnit());
        paramMap.put("goodId", orderDetail.getGoodId());
        paramMap.put("unitPrice", orderDetail.getUnitPrice());
        paramMap.put("buyNum", orderDetail.getBuyNum());
        paramMap.put("total", orderDetail.getTotal());
        paramListMap.add(paramMap);
    }
    commonDao.batchUpdate(SAVE_ORDER_DETAIL_SQL, paramListMap);
}


}