package cn.offway.athena.DTO;
 import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhOrderExpressInfo implements Serializable{

 private  Long id;

 private  Long orderId;

 private  String orderNo;

 private  String expressOrderNo;

 private  String type;

 private  String mailNo;

 private  String fromPhone;

 private  String fromRealName;

 private  String fromProvince;

 private  String fromCity;

 private  String fromCounty;

 private  String fromContent;

 private  String toPhone;

 private  String toRealName;

 private  String toProvince;

 private  String toCity;

 private  String toCounty;

 private  String toContent;

 private  String status;

 private  Date createTime;

 private  String remark;

 private  String exPhone;

 private  String lastTime;

 private  Long batch;

 private  Long returnId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "order_id", length = 11)
public Long getOrderId(){
    return orderId;
}


@Column(name = "from_city", length = 20)
public String getFromCity(){
    return fromCity;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "from_content", length = 200)
public String getFromContent(){
    return fromContent;
}


@Column(name = "to_real_name", length = 50)
public String getToRealName(){
    return toRealName;
}


@Column(name = "express_order_no", length = 50)
public String getExpressOrderNo(){
    return expressOrderNo;
}


@Column(name = "order_no", length = 50)
public String getOrderNo(){
    return orderNo;
}


@Column(name = "last_time", length = 50)
public String getLastTime(){
    return lastTime;
}


@Column(name = "from_real_name", length = 50)
public String getFromRealName(){
    return fromRealName;
}


@Column(name = "batch", length = 5)
public Long getBatch(){
    return batch;
}


@Column(name = "type", length = 2)
public String getType(){
    return type;
}


@Column(name = "to_city", length = 20)
public String getToCity(){
    return toCity;
}


@Column(name = "from_county", length = 20)
public String getFromCounty(){
    return fromCounty;
}


@Column(name = "to_county", length = 20)
public String getToCounty(){
    return toCounty;
}


@Column(name = "ex_phone", length = 20)
public String getExPhone(){
    return exPhone;
}


@Column(name = "to_content", length = 200)
public String getToContent(){
    return toContent;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "from_phone", length = 50)
public String getFromPhone(){
    return fromPhone;
}


@Column(name = "mail_no", length = 50)
public String getMailNo(){
    return mailNo;
}


@Column(name = "to_phone", length = 50)
public String getToPhone(){
    return toPhone;
}


@Column(name = "return_id", length = 11)
public Long getReturnId(){
    return returnId;
}


@Column(name = "to_province", length = 20)
public String getToProvince(){
    return toProvince;
}


@Column(name = "from_province", length = 20)
public String getFromProvince(){
    return fromProvince;
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExPhone(String exPhone){
    this.exPhone = exPhone;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExPhone"))

.queryParam("exPhone",exPhone)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLastTime(String lastTime){
    this.lastTime = lastTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLastTime"))

.queryParam("lastTime",lastTime)
;
restTemplate.put(builder.toUriString(),null);
}


}