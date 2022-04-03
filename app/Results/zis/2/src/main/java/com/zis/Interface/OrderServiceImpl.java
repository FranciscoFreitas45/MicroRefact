package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.OrderService;
public class OrderServiceImpl implements OrderService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Order createOrder(CreateTradeOrderDTO orderDTO){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createOrder"))
    .queryParam("orderDTO",orderDTO)
;  Order aux = restTemplate.getForObject(builder.toUriString(), Order.class);

 return aux;
}


public void payOrder(Integer shopId,String outOrderNumber,Double paymentAmount,Integer operator){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/payOrder"))
    .queryParam("shopId",shopId)
    .queryParam("outOrderNumber",outOrderNumber)
    .queryParam("paymentAmount",paymentAmount)
    .queryParam("operator",operator)
;
  restTemplate.put(builder.toUriString(), null);
}


public void arrangeOrderToRepo(Integer orderId,Integer operator,Integer repoId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/arrangeOrderToRepo"))
    .queryParam("orderId",orderId)
    .queryParam("operator",operator)
    .queryParam("repoId",repoId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void applyRefund(Integer shopId,String outOrderNumber,Integer operator,Date applyTime,String refundMemo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/applyRefund"))
    .queryParam("shopId",shopId)
    .queryParam("outOrderNumber",outOrderNumber)
    .queryParam("operator",operator)
    .queryParam("applyTime",applyTime)
    .queryParam("refundMemo",refundMemo)
;
  restTemplate.put(builder.toUriString(), null);
}


}