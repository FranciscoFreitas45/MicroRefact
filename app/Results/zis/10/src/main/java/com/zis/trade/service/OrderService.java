package com.zis.trade.service;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.zis.storage.entity.StorageIoDetail;
import com.zis.trade.dto.ChangeAddressDTO;
import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.trade.dto.ExpressNumberDTO;
import com.zis.trade.dto.OrderAddressImportDTO;
import com.zis.trade.dto.OrderQueryCondition;
import com.zis.trade.dto.OrderVO;
import com.zis.trade.entity.Order;
import com.zis.trade.entity.Order.ExpressStatus;
import com.zis.trade.entity.Order.PayStatus;
import com.zis.trade.entity.Order.StorageStatus;
public interface OrderService {


public Order createOrder(CreateTradeOrderDTO orderDTO)
;

public OrderVO changeOrderAddress(Integer orderId,Integer operator,ChangeAddressDTO newAddress)
;

public void agreeRefund(Integer orderId,Integer operator,String memo)
;

public void cancelOrder(Integer shopId,String outOrderNumber,Integer operator)
;

public StorageIoDetail lackPart(Integer ioDetailId,Integer actualAmt,Integer operator)
;

public List<String> importReceiverAddr(List<OrderAddressImportDTO> addrs)
;

public Order findByOrderIdAndCompanyId(Integer orderId,Integer companyId)
;

public OrderVO blockOrder(Integer orderId,Integer operator,String blockReason)
;

public void payOrder(Integer shopId,String outOrderNumber,Double paymentAmount,Integer operator)
;

public StorageIoDetail lackAll(Integer ioDetailId,Integer operator)
;

public boolean existByOutOrderNumber(Integer shopId,String outOrderNumber)
;

public OrderVO printExpress(Integer orderId,Integer operator)
;

public void cancelRefund(Integer orderId,Integer operator,String memo)
;

public OrderVO unblockOrder(Integer orderId,Integer operator,String unblockReason)
;

public void fillExpressNumber(Integer orderId,String expressNumber,String expressCompany,Integer operator)
;

public void finishSend(Integer repoId,Integer batchId,Integer operator)
;

public Page<OrderVO> findOrdersByStatus(Integer companyId,PayStatus payStatus,ExpressStatus expressStatus,StorageStatus storageStatus,Pageable page)
;

public void changeItems()
;

public List<OrderVO> printExpressList(List<Integer> orderIds,Integer operator)
;

public void fillExpressNumbers(List<ExpressNumberDTO> numbers,Integer operator)
;

public OrderVO sendOut(Integer repoId,String expressNumber,Integer operator)
;

public void cancelArrangeOrder(Integer orderId,Integer operator,String memo)
;

public int arrangeOrderToPos(Integer repoId,List<Integer> orderIds,Integer operator)
;

public Page<OrderVO> findOrdersByCondition(Integer companyId,Pageable page)
;

public void applyRefund(Integer shopId,String outOrderNumber,Integer operator,Date applyTime,String refundMemo)
;

public String appendSellerRemark(Integer orderId,Integer operator,String remark)
;

public void arrangeOrderToRepo(Integer orderId,Integer operator,Integer repoId)
;

}