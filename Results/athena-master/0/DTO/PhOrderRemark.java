import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhOrderRemark implements Serializable{

 private  Long id;

 private  String ordersNo;

 private  String ordersId;

 private  String content;

 private  String remark;

 private  Date createTime;

 private  String nickname;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "content", length = 255)
public String getContent(){
    return content;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "orders_no", length = 50)
public String getOrdersNo(){
    return ordersNo;
}


@Column(name = "nickname", length = 255)
public String getNickname(){
    return nickname;
}


@Column(name = "remark", length = 255)
public String getRemark(){
    return remark;
}


@Column(name = "orders_id", length = 50)
public String getOrdersId(){
    return ordersId;
}


public void setContent(String content){
    this.content = content;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContent"));

.queryParam("content",content);
restTemplate.put(builder.toUriString(),null);
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateTime"));

.queryParam("createTime",createTime);
restTemplate.put(builder.toUriString(),null);
}


public void setOrdersNo(String ordersNo){
    this.ordersNo = ordersNo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrdersNo"));

.queryParam("ordersNo",ordersNo);
restTemplate.put(builder.toUriString(),null);
}


public void setOrdersId(String ordersId){
    this.ordersId = ordersId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrdersId"));

.queryParam("ordersId",ordersId);
restTemplate.put(builder.toUriString(),null);
}


}