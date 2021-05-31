import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhCreditDetail implements Serializable{

 private  Long id;

 private  String unionid;

 private  String channel;

 private  String type;

 private  Long score;

 private  Date createTime;

 private  String remark;

 private  String orderNo;

 private  String createName;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


@Column(name = "order_no", length = 50)
public String getOrderNo(){
    return orderNo;
}


@Column(name = "create_name", length = 50)
public String getCreateName(){
    return createName;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "channel", length = 2)
public String getChannel(){
    return channel;
}


@Column(name = "type", length = 2)
public String getType(){
    return type;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "score", length = 11)
public Long getScore(){
    return score;
}


public void setChannel(String channel){
    this.channel = channel;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChannel"));

.queryParam("channel",channel);
restTemplate.put(builder.toUriString(),null);
}


public void setCreateName(String createName){
    this.createName = createName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateName"));

.queryParam("createName",createName);
restTemplate.put(builder.toUriString(),null);
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateTime"));

.queryParam("createTime",createTime);
restTemplate.put(builder.toUriString(),null);
}


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrderNo"));

.queryParam("orderNo",orderNo);
restTemplate.put(builder.toUriString(),null);
}


public void setRemark(String remark){
    this.remark = remark;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRemark"));

.queryParam("remark",remark);
restTemplate.put(builder.toUriString(),null);
}


public void setScore(Long score){
    this.score = score;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setScore"));

.queryParam("score",score);
restTemplate.put(builder.toUriString(),null);
}


public void setType(String type){
    this.type = type;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"));

.queryParam("type",type);
restTemplate.put(builder.toUriString(),null);
}


public void setUnionid(String unionid){
    this.unionid = unionid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUnionid"));

.queryParam("unionid",unionid);
restTemplate.put(builder.toUriString(),null);
}


}