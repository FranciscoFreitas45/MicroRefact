package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OrderServiceController {

 private OrderService orderservice;


@GetMapping
("/lackPart")
public StorageIoDetail lackPart(@RequestParam(name = "ioDetailId") Integer ioDetailId,@RequestParam(name = "actualAmt") Integer actualAmt,@RequestParam(name = "operator") Integer operator){
  return orderservice.lackPart(ioDetailId,actualAmt,operator);
}


@GetMapping
("/lackAll")
public StorageIoDetail lackAll(@RequestParam(name = "ioDetailId") Integer ioDetailId,@RequestParam(name = "operator") Integer operator){
  return orderservice.lackAll(ioDetailId,operator);
}


@PutMapping
("/finishSend")
public void finishSend(@RequestParam(name = "repoId") Integer repoId,@RequestParam(name = "batchId") Integer batchId,@RequestParam(name = "operator") Integer operator){
orderservice.finishSend(repoId,batchId,operator);
}


@GetMapping
("/createOrder")
public Order createOrder(@RequestParam(name = "orderDTO") CreateTradeOrderDTO orderDTO){
  return orderservice.createOrder(orderDTO);
}


@PutMapping
("/payOrder")
public void payOrder(@RequestParam(name = "shopId") Integer shopId,@RequestParam(name = "outOrderNumber") String outOrderNumber,@RequestParam(name = "paymentAmount") Double paymentAmount,@RequestParam(name = "operator") Integer operator){
orderservice.payOrder(shopId,outOrderNumber,paymentAmount,operator);
}


@PutMapping
("/arrangeOrderToRepo")
public void arrangeOrderToRepo(@RequestParam(name = "orderId") Integer orderId,@RequestParam(name = "operator") Integer operator,@RequestParam(name = "repoId") Integer repoId){
orderservice.arrangeOrderToRepo(orderId,operator,repoId);
}


@PutMapping
("/applyRefund")
public void applyRefund(@RequestParam(name = "shopId") Integer shopId,@RequestParam(name = "outOrderNumber") String outOrderNumber,@RequestParam(name = "operator") Integer operator,@RequestParam(name = "applyTime") Date applyTime,@RequestParam(name = "refundMemo") String refundMemo){
orderservice.applyRefund(shopId,outOrderNumber,operator,applyTime,refundMemo);
}


}