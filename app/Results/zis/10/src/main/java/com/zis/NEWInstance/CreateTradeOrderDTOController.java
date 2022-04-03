package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CreateTradeOrderDTOController {

 private CreateTradeOrderDTO createtradeorderdto;

 private CreateTradeOrderDTO createtradeorderdto;


@PutMapping
("/setOutOrderNumber")
public void setOutOrderNumber(@RequestParam(name = "outOrderNumber") String outOrderNumber){
createtradeorderdto.setOutOrderNumber(outOrderNumber);
}


@PutMapping
("/setOrderMoney")
public void setOrderMoney(@RequestParam(name = "orderMoney") Double orderMoney){
createtradeorderdto.setOrderMoney(orderMoney);
}


@PutMapping
("/setOrderType")
public void setOrderType(@RequestParam(name = "orderType") String orderType){
createtradeorderdto.setOrderType(orderType);
}


@PutMapping
("/setShopId")
public void setShopId(@RequestParam(name = "shopId") Integer shopId){
createtradeorderdto.setShopId(shopId);
}


@PutMapping
("/setReceiverAddr")
public void setReceiverAddr(@RequestParam(name = "receiverAddr") String receiverAddr){
createtradeorderdto.setReceiverAddr(receiverAddr);
}


@PutMapping
("/setReceiverName")
public void setReceiverName(@RequestParam(name = "receiverName") String receiverName){
createtradeorderdto.setReceiverName(receiverName);
}


@PutMapping
("/setReceiverPhone")
public void setReceiverPhone(@RequestParam(name = "receiverPhone") String receiverPhone){
createtradeorderdto.setReceiverPhone(receiverPhone);
}


@PutMapping
("/setSalerRemark")
public void setSalerRemark(@RequestParam(name = "salerRemark") String salerRemark){
createtradeorderdto.setSalerRemark(salerRemark);
}


@PutMapping
("/setBuyerMessage")
public void setBuyerMessage(@RequestParam(name = "buyerMessage") String buyerMessage){
createtradeorderdto.setBuyerMessage(buyerMessage);
}


@PutMapping
("/setOperator")
public void setOperator(@RequestParam(name = "operator") Integer operator){
createtradeorderdto.setOperator(operator);
}


@PutMapping
("/setSubOrders")
public void setSubOrders(@RequestParam(name = "subOrders") List<SubOrder> subOrders){
createtradeorderdto.setSubOrders(subOrders);
}


}