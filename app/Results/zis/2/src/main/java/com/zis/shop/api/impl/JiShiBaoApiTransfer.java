package com.zis.shop.api.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.jsb.rest.client.JSBClient;
import com.jsb.rest.comm.JSBRestException;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.Refund;
import com.taobao.api.domain.Shipping;
import com.taobao.api.domain.Trade;
import com.taobao.api.request.ItemUpdateListingRequest;
import com.taobao.api.request.ItemUpdateRequest;
import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.request.RefundsReceiveGetRequest;
import com.taobao.api.request.TradeGetRequest;
import com.taobao.api.request.TradesSoldIncrementGetRequest;
import com.taobao.api.response.ItemUpdateListingResponse;
import com.taobao.api.response.ItemUpdateResponse;
import com.taobao.api.response.LogisticsOfflineSendResponse;
import com.taobao.api.response.RefundsReceiveGetResponse;
import com.taobao.api.response.TradeGetResponse;
import com.taobao.api.response.TradesSoldIncrementGetResponse;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.dto.ApiAddItemDto;
import com.zis.shop.dto.ApiQueryItemsDto;
import com.zis.shop.dto.ApiUpdateItemDto;
import com.zis.shop.dto.ApplyRefundDTO;
import com.zis.shop.dto.LogisticsOfflineSendDTO;
import com.zis.shop.dto.LogisticsOfflineSendDTO.ExpressCompany;
import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.trade.dto.CreateTradeOrderDTO.SubOrder;
import com.zis.trade.entity.Order.OrderType;
public class JiShiBaoApiTransfer extends AbstractApiTransfer{

 private  Logger logger;

 private  Long DEFAULT_PAGE_SIZE;

 private  String ZIS;


@Override
public boolean logisticsOfflineSend(ShopInfo shop,LogisticsOfflineSendDTO dto){
    try {
        JSBClient client = getClient(shop);
        LogisticsOfflineSendRequest req = new LogisticsOfflineSendRequest();
        req.setTid(dto.getTid());
        req.setOutSid(dto.getExpressNumber());
        req.setCompanyCode(ExpressCompany.getValue(dto.getExpressCompany()));
        LogisticsOfflineSendResponse rsp = client.execute(req);
        if (!rsp.isSuccess()) {
            throw new RuntimeException(rsp.getMsg() + rsp.getSubMsg());
        }
        Shipping result = rsp.getShipping();
        return result.getIsSuccess();
    } catch (JSBRestException e) {
        String msg = String.format("%s %s", "通知发货失败 错误原因", e.getMessage());
        logger.error(msg, e);
        throw new RuntimeException(msg);
    }
}


@Override
public ApiQueryItemsDto queryItemsOnsale(ShopInfo shop,Long page){
    return null;
}


@Override
public ApiQueryItemsDto queryItemsInventory(ShopInfo shop,String type,Long page){
    return null;
}


public boolean uploadItem(ApiUpdateItemDto apiItemDto,ShopInfo shop){
    JSBClient client = getClient(shop);
    ItemUpdateListingRequest req = new ItemUpdateListingRequest();
    req.setNumIid(apiItemDto.getpItemId());
    req.setNum((long) apiItemDto.getAmount());
    ItemUpdateListingResponse rsp = null;
    try {
        rsp = client.execute(req);
        if (rsp.isSuccess()) {
            return true;
        } else {
            throw new RuntimeException("商品上传失败：" + rsp.getMsg() + rsp.getSubMsg());
        }
    } catch (JSBRestException e) {
        throw new RuntimeException("商品上传失败：" + e.getMessage());
    }
}


@Override
public boolean updateItem(ApiUpdateItemDto apiItemDto,ShopInfo shop){
    JSBClient client = getClient(shop);
    ItemUpdateRequest req = new ItemUpdateRequest();
    req.setNumIid(apiItemDto.getpItemId());
    req.setNum((long) apiItemDto.getAmount());
    ItemUpdateResponse rsp = null;
    try {
        rsp = client.execute(req);
        if (rsp.isSuccess()) {
            // 成功后强制上架
            uploadItem(apiItemDto, shop);
            return true;
        } else {
            throw new RuntimeException(rsp.getSubMsg());
        }
    } catch (JSBRestException e) {
        throw new RuntimeException("更新商品失败：" + e.getMessage());
    }
}


public JSBClient getClient(ShopInfo shop){
    return new JSBClient(shop.getAppId(), shop.getAppSecret());
}


public List<CreateTradeOrderDTO> queryTradeForDate(List<CreateTradeOrderDTO> oldlist,Long page,ShopInfo shop,Date startTime,Date endTime){
    try {
        if (page == null) {
            page = 1L;
        }
        JSBClient client = getClient(shop);
        TradesSoldIncrementGetRequest req = new TradesSoldIncrementGetRequest();
        req.setFields("tid,has_buyer_message,type,status,payment,orders,outer_iid,receiver_name,receiver_address,receiver_mobile,seller_rate");
        req.setStartModified(startTime);
        req.setEndModified(endTime);
        req.setStatus("WAIT_SELLER_SEND_GOODS");
        req.setPageNo(page);
        req.setPageSize(DEFAULT_PAGE_SIZE);
        req.setUseHasNext(true);
        TradesSoldIncrementGetResponse rsp = client.execute(req);
        if (!rsp.isSuccess()) {
            throw new RuntimeException(rsp.getMsg() + rsp.getSubMsg());
        }
        List<CreateTradeOrderDTO> newlist = buildCreateTradeOrderDTO(shop, oldlist, rsp.getTrades());
        if (rsp.getHasNext() != null && rsp.getHasNext()) {
            return queryTradeForDate(newlist, ++page, shop, startTime, endTime);
        } else {
            logger.info("抓取创建订单结果" + JSON.toJSONString(newlist));
            return newlist;
        }
    } catch (JSBRestException e) {
        String msg = String.format("%s %s %s %s %s %s", "获取订单失败 开始时间", dateToString(startTime), "结束时间", dateToString(endTime), "错误原因", e.getMessage());
        logger.error(msg, e);
        throw new RuntimeException(msg);
    }
}


@Override
public Long addItem(ApiAddItemDto apiAddItemDto,ShopInfo shop){
    return null;
}


public List<CreateTradeOrderDTO> buildCreateTradeOrderDTO(ShopInfo shop,List<CreateTradeOrderDTO> dtoList,List<Trade> tradeList){
    if (CollectionUtils.isEmpty(dtoList)) {
        dtoList = new ArrayList<>();
    }
    if (CollectionUtils.isEmpty(tradeList)) {
        return null;
    }
    for (Trade t : tradeList) {
        CreateTradeOrderDTO dto = new CreateTradeOrderDTO();
        dto.setOutOrderNumber(t.getTid().toString());
        dto.setOrderMoney(Double.parseDouble(t.getPayment()));
        dto.setOrderType(OrderType.SELF.getValue());
        dto.setShopId(shop.getShopId());
        dto.setReceiverAddr(t.getReceiverAddress());
        dto.setReceiverName(t.getReceiverName());
        dto.setReceiverPhone(t.getReceiverMobile());
        if ((t.getHasBuyerMessage() != null && t.getHasBuyerMessage()) || (t.getSellerRate() != null && t.getSellerRate()) || (t.getSellerCanRate() != null && t.getSellerCanRate())) {
            Trade tr = getTradeGet(shop);
            if (StringUtils.isNotBlank(tr.getSellerMemo())) {
                dto.setSalerRemark(ZIS + tr.getSellerMemo());
            } else {
                dto.setSalerRemark(ZIS);
            }
            if (StringUtils.isNotBlank(tr.getBuyerMessage())) {
                dto.setBuyerMessage(tr.getBuyerMessage());
            } else {
                dto.setBuyerMessage("");
            }
        } else {
            dto.setSalerRemark(ZIS);
            dto.setBuyerMessage("");
        }
        // 操作员是否能获取
        dto.setOperator(0);
        List<SubOrder> list = new ArrayList<CreateTradeOrderDTO.SubOrder>();
        for (Order o : t.getOrders()) {
            SubOrder s = new SubOrder();
            s.setItemCount(o.getNum().intValue());
            s.setItemId(o.getNumIid().intValue());
            // 这些在下次调用的service出要获取
            s.setItemName("");
            s.setItemOutNum(o.getOuterIid());
            s.setItemPrice(Double.parseDouble(o.getPrice()));
            s.setSkuId(null);
            list.add(s);
        }
        dto.setSubOrders(list);
        dtoList.add(dto);
    }
    return dtoList;
}


public Trade getTradeGet(ShopInfo shop){
    try {
        JSBClient client = getClient(shop);
        TradeGetRequest req = new TradeGetRequest();
        req.setTid(13731243034871210L);
        req.setFields("tid, buyer_message,buyer_memo");
        TradeGetResponse rsp = client.execute(req);
        Trade t = rsp.getTrade();
        if (!rsp.isSuccess()) {
            throw new RuntimeException(rsp.getMsg() + rsp.getSubMsg());
        }
        return t;
    } catch (JSBRestException e) {
        String msg = String.format("%s %s", "获取买家备注失败 错误原因", e.getMessage());
        logger.error(msg, e);
        throw new RuntimeException(msg);
    }
}


public List<ApplyRefundDTO> buildApplyRefundDTO(ShopInfo shop,List<ApplyRefundDTO> aRList,List<Refund> rlist){
    if (aRList == null || aRList.isEmpty()) {
        aRList = new ArrayList<ApplyRefundDTO>();
    }
    if (CollectionUtils.isEmpty(rlist)) {
        return null;
    }
    for (Refund r : rlist) {
        ApplyRefundDTO dto = new ApplyRefundDTO();
        dto.setApplyTime(r.getCreated());
        dto.setOperator(0);
        dto.setOutOrderNumber(r.getTid().toString());
        dto.setRefundMemo(r.getReason());
        dto.setShopId(shop.getShopId());
        aRList.add(dto);
    }
    return aRList;
}


public List<ApplyRefundDTO> queryApplyRefundForDate(Long page,List<ApplyRefundDTO> oldlist,ShopInfo shop,Date startTime,Date endTime){
    try {
        if (page == null) {
            page = 1L;
        }
        JSBClient client = getClient(shop);
        RefundsReceiveGetRequest req = new RefundsReceiveGetRequest();
        req.setFields("refund_id, tid, title, buyer_nick, seller_nick, total_fee, status, created, refund_fee, oid, good_status, company_name, sid, payment, reason, desc, has_good_return, modified, order_status,refund_phase");
        req.setStatus("WAIT_SELLER_AGREE,WAIT_BUYER_RETURN_GOODS,WAIT_SELLER_CONFIRM_GOODS,SELLER_REFUSE_BUYER,SUCCESS");
        req.setStartModified(startTime);
        req.setEndModified(endTime);
        req.setUseHasNext(true);
        req.setPageNo(page);
        req.setPageSize(DEFAULT_PAGE_SIZE);
        RefundsReceiveGetResponse rsp = client.execute(req);
        if (!rsp.isSuccess()) {
            throw new RuntimeException(rsp.getMsg() + rsp.getSubMsg());
        }
        List<ApplyRefundDTO> newlist = buildApplyRefundDTO(shop, oldlist, rsp.getRefunds());
        if (rsp.getHasNext() != null && rsp.getHasNext()) {
            return queryApplyRefundForDate(++page, newlist, shop, startTime, endTime);
        } else {
            logger.info("抓取退款结果" + JSON.toJSONString(newlist));
            return newlist;
        }
    } catch (JSBRestException e) {
        String msg = String.format("%s %s %s %s %s %s", "获取退款订单失败 开始时间", dateToString(startTime), "结束时间", dateToString(endTime), "错误原因", e.getMessage());
        logger.error(msg, e);
        throw new RuntimeException(msg);
    }
}


public String dateToString(Date date){
    return ZisUtils.getDateString("yyyy-MM-dd HH:mm:ss", date);
}


}