package com.zis.trade.service;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.util.StringUtil;
import com.zis.shiro.bean.User;
import com.zis.shiro.service.SysService;
import com.zis.storage.dto.CreateOrderDTO;
import com.zis.storage.dto.CreateOrderDTO.CreateOrderDetail;
import com.zis.storage.dto.StorageLacknessOpDTO;
import com.zis.storage.entity.StorageIoDetail;
import com.zis.storage.entity.StorageOrder;
import com.zis.storage.service.StorageService;
import com.zis.trade.dto.ChangeAddressDTO;
import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.trade.dto.ExpressNumberDTO;
import com.zis.trade.dto.OrderAddressImportDTO;
import com.zis.trade.dto.OrderQueryCondition;
import com.zis.trade.dto.OrderVO;
import com.zis.trade.dto.OrderVO.OrderDetailVO;
import com.zis.trade.entity.Order;
import com.zis.trade.entity.Order.ExpressStatus;
import com.zis.trade.entity.Order.PayStatus;
import com.zis.trade.entity.Order.StorageStatus;
import com.zis.trade.entity.OrderDetail;
import com.zis.trade.entity.OrderDetail.DetailStatus;
import com.zis.trade.entity.OrderLog;
import com.zis.trade.entity.OrderLog.OperateType;
import com.zis.trade.entity.OrderOuter;
import com.zis.trade.processor.OrderHelper;
import com.zis.trade.processor.OrderProcessor;
import com.zis.trade.repository.OrderDao;
import com.zis.trade.repository.OrderDetailDao;
import com.zis.trade.repository.OrderLogDao;
import com.zis.trade.repository.OrderOuterDao;
import com.zis.Interface.BookService;
import com.zis.Interface.SysService;
import com.zis.Interface.StorageService;
import com.zis.DTO.StorageService;
import com.zis.DTO.StorageLacknessOpDTO;
import com.zis.DTO.StorageService;
import com.zis.DTO.User;
@Service
public class OrderServiceImpl implements OrderService{

@Autowired
 private  OrderProcessor processor;

@Autowired
 private  OrderDao orderDao;

@Autowired
 private  OrderDetailDao orderDetailDao;

@Autowired
 private  OrderOuterDao orderOuterDao;

@Autowired
 private  OrderLogDao orderLogDao;

@Autowired
 private  BookService bookService;

@Autowired
 private  SysService sysService;

@Autowired
 private  StorageService storageService;

 private  Logger logger;

 private  String[] STORAGE_WAIT_ARRANGES;

 private  String[] CANCELLED_ORDER;

 private  String[] PICKUP_ORDER;

 private  String NEW_LINE;


@Override
public Order createOrder(CreateTradeOrderDTO orderDTO){
    return processor.createOrder(orderDTO);
}


@Override
@Transactional
public OrderVO changeOrderAddress(Integer orderId,Integer operator,ChangeAddressDTO newAddress){
    logger.info("??????????????????, orderId={}, operator={}, newAddress={}", orderId, operator, JSONObject.toJSONString(newAddress));
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(operator, "operator????????????");
    Assert.notNull(newAddress, "newAddress????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!OrderHelper.canChangeOrderAddress(order)) {
        throw new RuntimeException("????????????????????????????????????????????????");
    }
    String message = getChangeAddrMessage(newAddress, order);
    BeanUtils.copyProperties(newAddress, order);
    this.orderDao.save(order);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.CHANGE_ADDR, message);
    orderLogDao.save(log);
    return createOrderVO(order);
}


@Override
@Transactional
public void agreeRefund(Integer orderId,Integer operator,String memo){
    logger.info("????????????, orderId={}, operator={}, memo={}", orderId, operator, memo);
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(operator, "operator????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!OrderHelper.canAgreeRefund(order)) {
        throw new RuntimeException("????????????????????????????????????????????????");
    }
    order.setPayStatus(PayStatus.REFUND_FINISH.getValue());
    order.setRefundFinishTime(new Date());
    this.orderDao.save(order);
    this.orderDetailDao.updateStatusToInvalidByOrderId(orderId);
    // ???????????????????????????????????????
    this.storageService.cancelOrder(order.getRepoId(), orderId);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.AGREE_REFUND, memo);
    orderLogDao.save(log);
}


@Override
@Transactional
public void cancelOrder(Integer shopId,String outOrderNumber,Integer operator){
    logger.info("????????????, shopId={}, outOrderNumber={}, operator={}", shopId, outOrderNumber, operator);
    List<Order> orders = findOrdersByShopIdAndOutOrderNumber(shopId, outOrderNumber);
    if (CollectionUtils.isEmpty(orders)) {
        throw new RuntimeException("???????????????");
    }
    for (Order od : orders) {
        cancelOrder(od, operator);
    }
}


@Override
public StorageIoDetail lackPart(Integer ioDetailId,Integer actualAmt,Integer operator){
    Assert.notNull(ioDetailId, "ioDetailId????????????");
    Assert.notNull(operator, "operator????????????");
    StorageLacknessOpDTO lacknessDTO = storageService.lackPart(ioDetailId, operator, actualAmt);
    if (!lacknessDTO.getLacknessMatchNewPos()) {
        // ?????????????????????
        doOrderLacknesss(lacknessDTO.getLackOutOrderId(), operator);
    }
    if (lacknessDTO.isHasNext()) {
        return (StorageIoDetail) lacknessDTO;
    } else {
        return null;
    }
}


@Override
public List<String> importReceiverAddr(List<OrderAddressImportDTO> addrs){
    if (CollectionUtils.isEmpty(addrs)) {
        throw new RuntimeException("?????????????????????????????????");
    }
    List<String> list = new ArrayList<>();
    for (OrderAddressImportDTO addr : addrs) {
        List<String> outNumbers = orderOuterDao.findOrderGroupNumberByOutOrderNumber(addr.getOutOrderNumber());
        if (outNumbers.isEmpty()) {
            list.add("???????????????" + addr.getOutOrderNumber() + " ?????????");
            continue;
        }
        List<Order> orders = orderDao.findByOrderGroupNumberIn(outNumbers);
        for (Order order : orders) {
            order.setReceiverAddr(addr.getReceiverAddr());
            order.setReceiverName(addr.getReceiverName());
            order.setReceiverPhone(addr.getReceiverPhone());
        }
        orderDao.save(orders);
    }
    return list;
}


public void doOrderLacknesss(Integer orderId,Integer operator){
    Order order = this.orderDao.findOne(orderId);
    if (!OrderHelper.canLackness(order)) {
        throw new RuntimeException("???????????????????????????????????????");
    }
    order.setStorageStatus(StorageStatus.WAIT_ARRANGE_BY_LACKNESS.getValue());
    order.setRepoId(null);
    this.orderDao.save(order);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.LACKNESS, "");
    this.orderLogDao.save(log);
}


@Override
public Order findByOrderIdAndCompanyId(Integer orderId,Integer companyId){
    return this.orderDao.findByIdAndCompanyId(orderId, companyId);
}


@SuppressWarnings("unchecked")
public List<Order> findOrdersByShopIdAndOutOrderNumber(Integer shopId,String outOrderNumber){
    OrderOuter outOrder = orderOuterDao.findByShopIdAndOutOrderNumber(shopId, outOrderNumber);
    if (outOrder == null) {
        return Collections.EMPTY_LIST;
    }
    return orderDao.findByShopIdAndOrderGroupNumber(outOrder.getShopId(), outOrder.getOrderGroupNumber());
}


@Override
@Transactional
public OrderVO blockOrder(Integer orderId,Integer operator,String blockReason){
    logger.info("????????????, orderId={}, operator={}, blockReason={}", orderId, operator, blockReason);
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(operator, "operator????????????");
    Assert.notNull(blockReason, "blockReason????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!OrderHelper.canBlock(order)) {
        throw new RuntimeException("??????????????????????????????????????????");
    }
    order.setBlockFlag(true);
    String reason = String.format("%s%s%s", order.getBlockReason(), NEW_LINE, blockReason);
    order.setBlockReason(reason);
    this.orderDao.save(order);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.BLOCK, blockReason);
    orderLogDao.save(log);
    return createOrderVO(order);
}


@Override
@Transactional
public void payOrder(Integer shopId,String outOrderNumber,Double paymentAmount,Integer operator){
    logger.info("????????????, shopId={}, outOrderNumber={}, paymentAmount={}, operator={}", shopId, outOrderNumber, paymentAmount, operator);
    List<Order> orders = findOrdersByShopIdAndOutOrderNumber(shopId, outOrderNumber);
    if (CollectionUtils.isEmpty(orders)) {
        throw new RuntimeException("???????????????");
    }
    for (Order od : orders) {
        payOrder(od, paymentAmount, operator);
    }
}


@Override
@Transactional
public StorageIoDetail lackAll(Integer ioDetailId,Integer operator){
    Assert.notNull(ioDetailId, "ioDetailId????????????");
    Assert.notNull(operator, "operator????????????");
    StorageLacknessOpDTO lacknessDTO = storageService.lackAll(ioDetailId, operator);
    if (!lacknessDTO.getLacknessMatchNewPos()) {
        // ?????????????????????
        doOrderLacknesss(lacknessDTO.getLackOutOrderId(), operator);
    }
    if (lacknessDTO.isHasNext()) {
        return (StorageIoDetail) lacknessDTO;
    } else {
        return null;
    }
}


@Override
public boolean existByOutOrderNumber(Integer shopId,String outOrderNumber){
    OrderOuter outOrder = orderOuterDao.findByShopIdAndOutOrderNumber(shopId, outOrderNumber);
    // if (outOrder != null) {
    // Order order = this.orderDao.findByOrderGroupNumberAndPayStatusNotIn(outOrder.getOrderGroupNumber(),
    // Arrays.asList(CANCELLED_ORDER));
    // return order != null;
    // }
    return outOrder != null;
}


@Override
@Transactional
public OrderVO printExpress(Integer orderId,Integer operator){
    List<Integer> orderIds = new ArrayList<Integer>();
    orderIds.add(orderId);
    List<OrderVO> rs = printExpressList(orderIds, operator);
    return rs.get(0);
}


public void checkForFindOrdersByStatus(Integer companyId,PayStatus payStatus,ExpressStatus expressStatus,StorageStatus storageStatus){
    Assert.notNull(companyId, "companyId????????????");
    int statusCount = 0;
    if (payStatus != null)
        statusCount++;
    if (expressStatus != null)
        statusCount++;
    if (storageStatus != null)
        statusCount++;
    switch(statusCount) {
        case 0:
            throw new IllegalArgumentException("??????????????????");
        case 1:
            return;
        case 2:
        case 3:
        default:
            throw new IllegalArgumentException("payStatus???expressStatus???storageStatus??????????????????");
    }
}


public CreateOrderDTO buildStorageCreateOrderDTO(Order order){
    CreateOrderDTO request = new CreateOrderDTO();
    request.setBuyerName(order.getReceiverName());
    // XXX ????????????????????????
    request.setOrderType(StorageOrder.OrderType.SELF);
    request.setOutOrderId(order.getId());
    request.setRepoId(order.getRepoId());
    request.setShopId(order.getShopId());
    List<OrderDetail> details = this.orderDetailDao.findByOrderIdAndStatus(order.getId(), DetailStatus.VALID.getValue());
    List<CreateOrderDetail> detailList = new ArrayList<CreateOrderDetail>();
    for (OrderDetail orderDetail : details) {
        CreateOrderDetail record = new CreateOrderDetail();
        record.setAmount(orderDetail.getItemCount());
        record.setSkuId(orderDetail.getSkuId());
        detailList.add(record);
    }
    request.setDetailList(detailList);
    return request;
}


@Override
@Transactional
public void cancelRefund(Integer orderId,Integer operator,String memo){
    logger.info("????????????, orderId={}, operator={}, memo={}", orderId, operator, memo);
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(operator, "operator????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!OrderHelper.canCancelRefund(order)) {
        throw new RuntimeException("????????????????????????????????????????????????");
    }
    order.setPayStatus(PayStatus.PAID.getValue());
    this.orderDao.save(order);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.CANCEL_REFUND, memo);
    orderLogDao.save(log);
}


@Override
@Transactional
public OrderVO unblockOrder(Integer orderId,Integer operator,String unblockReason){
    logger.info("??????????????????, orderId={}, operator={}, unblockReason={}", orderId, operator, unblockReason);
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(operator, "operator????????????");
    Assert.notNull(unblockReason, "unblockReason????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!order.getBlockFlag()) {
        throw new RuntimeException("??????????????????????????????????????????");
    }
    order.setBlockFlag(false);
    this.orderDao.save(order);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.UNBLOCK, unblockReason);
    orderLogDao.save(log);
    return createOrderVO(order);
}


public void fillExpressNumber(Integer orderId,String receiverName,String expressNumber,String expressCompany,Integer operator){
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!OrderHelper.canFillExpressNumber(order)) {
        throw new RuntimeException("????????????????????????????????????????????????");
    }
    if (StringUtils.isNotBlank(receiverName) && !order.getReceiverName().equals(receiverName)) {
        throw new RuntimeException("????????????????????????????????????");
    }
    if (isInvalidAddr(order)) {
        throw new RuntimeException("????????????????????????????????????????????????????????????????????????");
    }
    order.setExpressCompany(expressCompany);
    order.setExpressNumber(expressNumber);
    order.setExpressStatus(ExpressStatus.FILLED_EX_NUM.getValue());
    this.orderDao.save(order);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.FILL_EX_NUM, expressCompany + expressNumber);
    orderLogDao.save(log);
}


@Override
@Transactional
public void finishSend(Integer repoId,Integer batchId,Integer operator){
    logger.info("????????????, batchId={}, operator={}", batchId, operator);
    List<Integer> list = this.storageService.finishBatchSend(batchId);
    // ?????????????????????????????????
    this.orderDao.updateStorageStatusToFinishByIds(list);
}


public String getChangeAddrMessage(ChangeAddressDTO newAddress,Order order){
    String oldAddr = StringUtil.conjunction(",", order.getReceiverName(), order.getReceiverPhone(), order.getReceiverAddr());
    String newAddr = StringUtil.conjunction(",", newAddress.getReceiverName(), newAddress.getReceiverPhone(), newAddress.getReceiverAddr());
    StringBuilder builder = new StringBuilder(100);
    builder.append("????????????").append(oldAddr).append("???????????????").append(newAddr);
    return builder.toString();
}


@Override
public Page<OrderVO> findOrdersByStatus(Integer companyId,PayStatus payStatus,ExpressStatus expressStatus,StorageStatus storageStatus,Pageable page){
    List<String> storageWaitList = Arrays.asList(STORAGE_WAIT_ARRANGES);
    List<String> cancelledList = Arrays.asList(CANCELLED_ORDER);
    // arg check
    checkForFindOrdersByStatus(companyId, payStatus, expressStatus, storageStatus);
    Page<Order> rs = null;
    if (payStatus != null) {
        rs = this.orderDao.findByCompanyIdAndPayStatusOrderByUpdateTimeDesc(companyId, payStatus.getValue(), page);
    }
    if (expressStatus != null) {
        // ?????????????????????????????????????????????????????????????????????,??????????????????????????????????????????
        if (expressStatus.getValue().equals(ExpressStatus.WAIT_FOR_PRINT.getValue())) {
            rs = this.orderDao.findByCompanyIdAndExpressStatusAndStorageStatusInAndPayStatusNotInOrderByUpdateTimeDesc(companyId, expressStatus.getValue(), Arrays.asList(PICKUP_ORDER), cancelledList, page);
        } else {
            rs = this.orderDao.findByCompanyIdAndExpressStatusAndStorageStatusNotInAndPayStatusNotInOrderByUpdateTimeDesc(companyId, expressStatus.getValue(), storageWaitList, cancelledList, page);
        }
    }
    if (storageStatus != null) {
        if (storageStatus.getValue().equals(StorageStatus.WAIT_ARRANGE.getValue())) {
            rs = this.orderDao.findByCompanyIdAndStorageStatusInAndPayStatusNotInOrderByUpdateTimeDesc(companyId, storageWaitList, cancelledList, page);
        } else {
            rs = this.orderDao.findByCompanyIdAndStorageStatusAndPayStatusNotInOrderByUpdateTimeDesc(companyId, storageStatus.getValue(), cancelledList, page);
        }
    }
    if (CollectionUtils.isEmpty(rs.getContent())) {
        return emptyOrderVOPage(page);
    }
    List<OrderVO> list = new ArrayList<OrderVO>((int) rs.getContent().size());
    for (Order order : rs.getContent()) {
        OrderVO vo = buildOrderVO(order);
        list.add(vo);
    }
    return new PageImpl<OrderVO>(list, page, rs.getTotalElements());
}


@Override
@Transactional
public void changeItems(){
// TODO Auto-generated method stub
}


@Override
@Transactional
public List<OrderVO> printExpressList(List<Integer> orderIds,Integer operator){
    logger.info("???????????????, orderIds={}, operator={}", orderIds, operator);
    Assert.notEmpty(orderIds, "orderIds????????????");
    Assert.notNull(operator, "operator????????????");
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (Integer orderId : orderIds) {
        Order order = orderDao.findOne(orderId);
        if (order != null) {
            if (!OrderHelper.canPrint(order)) {
                throw new RuntimeException("??????????????????????????????????????????orderId=" + orderId);
            }
            if (isInvalidAddr(order)) {
                throw new RuntimeException("????????????????????????????????????????????????????????????????????????");
            }
            order.setExpressStatus(ExpressStatus.PRINTED.getValue());
            orderDao.save(order);
            // ????????????
            OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.PRINT, "");
            orderLogDao.save(log);
            // ??????????????????
            list.add(createOrderVO(order));
        }
    }
    return list;
}


public OrderVO createOrderVO(Order order){
    List<String> outOrderNumbers = orderOuterDao.findOutOrderNumbersByOrderId(order.getOrderGroupNumber());
    List<OrderDetail> details = orderDetailDao.findByOrderIdAndStatus(order.getId(), DetailStatus.VALID.getValue());
    List<OrderDetailVO> orderDetails = new ArrayList<OrderDetailVO>();
    for (OrderDetail detail : details) {
        OrderDetailVO dvo = new OrderDetailVO();
        Bookinfo book = this.bookService.findBookById(detail.getSkuId());
        if (book == null) {
            throw new RuntimeException("??????????????????bookId=" + detail.getSkuId());
        }
        BeanUtils.copyProperties(book, dvo);
        BeanUtils.copyProperties(detail, dvo);
        orderDetails.add(dvo);
    }
    OrderVO vo = new OrderVO();
    BeanUtils.copyProperties(order, vo);
    String expressStatus = ExpressStatus.getEnum(order.getExpressStatus()).getDisplay();
    vo.setExpressStatusDisplay(expressStatus);
    String payStatus = PayStatus.getEnum(order.getPayStatus()).getDisplay();
    vo.setPayStatusDisplay(payStatus);
    String storageStatus = StorageStatus.getEnum(order.getStorageStatus()).getDisplay();
    vo.setStorageStatusDisplay(storageStatus);
    vo.setOutOrderNumbers(outOrderNumbers);
    vo.setOrderDetailVOs(orderDetails);
    return vo;
}


@Override
@Transactional
public void fillExpressNumbers(List<ExpressNumberDTO> numbers,Integer operator){
    logger.info("??????????????????, numbers={}, operator={}", JSONObject.toJSONString(numbers), operator);
    Assert.notEmpty(numbers, "numbers????????????");
    Assert.notNull(operator, "operator????????????");
    for (ExpressNumberDTO ex : numbers) {
        fillExpressNumber(ex.getOrderId(), ex.getReceiverName(), ex.getExpressNumber(), ex.getExpressCompany(), operator);
    }
}


@SuppressWarnings("unchecked")
public Page<OrderVO> emptyOrderVOPage(Pageable page){
    return new PageImpl<OrderVO>(Collections.EMPTY_LIST, page, 0);
}


@Override
@Transactional
public OrderVO sendOut(Integer repoId,String expressNumber,Integer operator){
    logger.info("????????????, repoId={}, expressNumber={}, operator={}", repoId, expressNumber, operator);
    Assert.notNull(repoId, "repoId????????????");
    Assert.notNull(operator, "operator????????????");
    Assert.notNull(expressNumber, "expressNumber????????????");
    // ???????????????????????????????????????????????????????????????????????????
    List<Order> orders = orderDao.findByRepoIdAndExpressNumber(repoId, expressNumber);
    if (orders.isEmpty()) {
        throw new RuntimeException("???????????????");
    }
    if (orders.size() > 1) {
        throw new RuntimeException("?????????????????????????????????????????????");
    }
    if (ExpressStatus.SEND_OUT.getValue().equals(orders.get(0).getExpressStatus())) {
        throw new RuntimeException("???????????????????????????????????????");
    // return createOrderVO(orders.get(0));
    }
    if (!ExpressStatus.FILLED_EX_NUM.getValue().equals(orders.get(0).getExpressStatus())) {
        throw new RuntimeException("????????????????????????????????????");
    }
    if (CollectionUtils.isEmpty(orders)) {
        throw new RuntimeException("???????????????????????????");
    }
    if (orders.size() > 1) {
        throw new RuntimeException("??????????????????????????????");
    }
    Order order = orders.get(0);
    String msg = OrderHelper.canSendOutWithMessage(order);
    if (msg != null) {
        throw new RuntimeException("???????????????" + msg);
    }
    order.setExpressStatus(ExpressStatus.SEND_OUT.getValue());
    order.setExpressTime(new Date());
    this.orderDao.save(order);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.SEND_OUT, "");
    orderLogDao.save(log);
    return createOrderVO(order);
}


public boolean isInvalidAddr(Order order){
    return order.getReceiverName().contains("*") && order.getReceiverPhone().contains("*") && order.getReceiverAddr().contains("*");
}


public void checkForFindOrdersByCondition(Integer companyId,OrderQueryCondition cond){
    Assert.notNull(companyId, "companyId????????????");
    Assert.notNull(cond, "OrderQueryCondition????????????");
    // ?????????????????????????????????????????????????????????????????????????????????????????????????????????
    int argCount = 0;
    if (cond.getOrderId() != null)
        argCount++;
    if (StringUtils.isNotBlank(cond.getExpressNumber()))
        argCount++;
    if (StringUtils.isNotBlank(cond.getReceiverName()))
        argCount++;
    if (StringUtils.isNotBlank(cond.getReceiverPhone()))
        argCount++;
    if (StringUtils.isNotBlank(cond.getOutOrderNumber())) {
        if (argCount > 0) {
            throw new IllegalArgumentException("??????????????????????????????????????????????????????");
        }
    } else {
        if (argCount == 0) {
            throw new IllegalArgumentException("?????????????????????");
        }
    }
}


@Override
@Transactional
public void cancelArrangeOrder(Integer orderId,Integer operator,String memo){
    logger.info("??????????????????, orderId={}, operator={}, memo={}", orderId, operator, memo);
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(operator, "operator????????????");
    Assert.notNull(memo, "memo????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!OrderHelper.canCancelArrangeOrder(order)) {
        throw new RuntimeException("????????????????????????????????????????????????");
    }
    order.setStorageStatus(StorageStatus.WAIT_ARRANGE_BY_MANUAL.getValue());
    orderDao.save(order);
    // ???????????????????????????????????????
    storageService.cancelOrder(order.getRepoId(), order.getId());
    // ????????????
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.CANCEL_ARRANGE, memo);
    orderLogDao.save(log);
}


@Override
@Transactional
public int arrangeOrderToPos(Integer repoId,List<Integer> orderIds,Integer operator){
    logger.info("????????????, repoId={}, orderIds={}, operator={}", repoId, orderIds, operator);
    Assert.notNull(orderIds, "orderIds????????????");
    Assert.notNull(operator, "operator????????????");
    Assert.notNull(repoId, "repoId????????????");
    List<Integer> outTradeNos = new ArrayList<Integer>(orderIds.size());
    for (Integer orderId : orderIds) {
        Order order = this.orderDao.findOne(orderId);
        if (order != null) {
            if (!OrderHelper.canArrangeOrderToPos(order)) {
                throw new RuntimeException("?????????????????????????????????????????????orderId=" + orderId);
            }
            order.setStorageStatus(StorageStatus.PICKUP.getValue());
            this.orderDao.save(order);
            // ????????????
            OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.ARRANGE_TO_POS, "");
            orderLogDao.save(log);
            outTradeNos.add(order.getId());
        }
    }
    // ??????????????????????????????
    int batchId = storageService.arrangeOrder(repoId, outTradeNos, operator);
    logger.info("????????????, repoId={}, orderIds={}, operator={}, batchId={}", repoId, orderIds, operator, batchId);
    return batchId;
}


@Override
public Page<OrderVO> findOrdersByCondition(Integer companyId,Pageable page){
    Page<Order> orders = this.orderDao.findByCompanyIdOrderByUpdateTimeDesc(companyId, page);
    if (CollectionUtils.isEmpty(orders.getContent())) {
        return emptyOrderVOPage(page);
    }
    List<OrderVO> list = new ArrayList<OrderVO>(orders.getContent().size());
    for (Order order : orders) {
        OrderVO vo = buildOrderVO(order);
        list.add(vo);
    }
    return new PageImpl<OrderVO>(list, page, orders.getTotalElements());
}


@Override
@Transactional
public void applyRefund(Integer shopId,String outOrderNumber,Integer operator,Date applyTime,String refundMemo){
    logger.info("??????????????????, shopId={}, outOrderNumber={}, operator={}, applyTime={}, refundMemo={}", shopId, outOrderNumber, operator, applyTime, refundMemo);
    List<Order> orders = findOrdersByShopIdAndOutOrderNumber(shopId, outOrderNumber);
    if (CollectionUtils.isEmpty(orders)) {
        throw new RuntimeException("???????????????");
    }
    for (Order od : orders) {
        applyRefund(od, operator, applyTime, refundMemo);
    }
}


@Override
public String appendSellerRemark(Integer orderId,Integer operator,String remark){
    logger.info("????????????, orderId={}, remark={}, operator={}", orderId, remark, operator);
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(remark, "remark????????????");
    Assert.notNull(operator, "operator????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    User user = sysService.findtUserById(operator);
    String newRemark = String.format("%s???%s%s", user == null ? "" : user.getRealName(), remark, NEW_LINE);
    order.setSalerRemark(order.getSalerRemark() + newRemark);
    orderDao.save(order);
    return order.getSalerRemark();
}


@Override
@Transactional
public void arrangeOrderToRepo(Integer orderId,Integer operator,Integer repoId){
    logger.info("?????????????????????, orderId={}, operator={}, repoId={}", orderId, operator, repoId);
    Assert.notNull(orderId, "orderId????????????");
    Assert.notNull(operator, "operator????????????");
    Assert.notNull(repoId, "repoId????????????");
    Order order = orderDao.findOne(orderId);
    Assert.notNull(order, "???????????????orderId=" + orderId);
    if (!OrderHelper.canArrangeOrderToRepo(order)) {
        throw new RuntimeException("????????????????????????????????????????????????");
    }
    order.setStorageStatus(StorageStatus.ARRANGED.getValue());
    order.setArrangeTime(new Date());
    order.setRepoId(repoId);
    this.orderDao.save(order);
    CreateOrderDTO request = buildStorageCreateOrderDTO(order);
    storageService.createOrder(request);
    OrderLog log = OrderHelper.createOrderLog(order, operator, OperateType.ARRANGE_TO_REPO, "");
    orderLogDao.save(log);
}


public OrderVO buildOrderVO(Order order){
    OrderVO vo = new OrderVO();
    BeanUtils.copyProperties(order, vo);
    vo.setPayStatusDisplay(PayStatus.getEnum(vo.getPayStatus()).getDisplay());
    vo.setExpressStatusDisplay(ExpressStatus.getEnum(vo.getExpressStatus()).getDisplay());
    vo.setStorageStatusDisplay(StorageStatus.getEnum(vo.getStorageStatus()).getDisplay());
    List<String> outOrderNumbers = this.orderOuterDao.findOutOrderNumbersByOrderId(vo.getOrderGroupNumber());
    vo.setOutOrderNumbers(outOrderNumbers);
    List<OrderDetailVO> odvos = new ArrayList<OrderVO.OrderDetailVO>(order.getOrderDetails().size());
    for (OrderDetail od : order.getOrderDetails()) {
        OrderDetailVO odvo = new OrderDetailVO();
        Bookinfo book = this.bookService.findBookById(od.getSkuId());
        if (book != null) {
            BeanUtils.copyProperties(book, odvo);
        }
        BeanUtils.copyProperties(od, odvo);
        odvos.add(odvo);
    }
    vo.setOrderDetailVOs(odvos);
    return vo;
}


}