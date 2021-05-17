import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_credit_detail")
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


public void setUnionid(String unionid){
    this.unionid = unionid;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUnionid"));

.queryParam("unionid",unionid);
restTemplate.put(builder.toUriString(),null);


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


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrderNo"));

.queryParam("orderNo",orderNo);
restTemplate.put(builder.toUriString(),null);


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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateTime"));

.queryParam("createTime",createTime);
restTemplate.put(builder.toUriString(),null);


public void setChannel(String channel){
    this.channel = channel;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChannel"));

.queryParam("channel",channel);
restTemplate.put(builder.toUriString(),null);


public void setType(String type){
    this.type = type;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"));

.queryParam("type",type);
restTemplate.put(builder.toUriString(),null);


@Column(name = "channel", length = 2)
public String getChannel(){
    return channel;
}


@Column(name = "type", length = 2)
public String getType(){
    return type;
}


public void setRemark(String remark){
    this.remark = remark;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRemark"));

.queryParam("remark",remark);
restTemplate.put(builder.toUriString(),null);


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setCreateName(String createName){
    this.createName = createName;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateName"));

.queryParam("createName",createName);
restTemplate.put(builder.toUriString(),null);


public void setId(Long id){
    this.id = id;
}


@Column(name = "score", length = 11)
public Long getScore(){
    return score;
}


public void setScore(Long score){
    this.score = score;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setScore"));

.queryParam("score",score);
restTemplate.put(builder.toUriString(),null);


}