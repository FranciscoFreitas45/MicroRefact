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


public void setChannel(String channel){
    this.channel = channel;
}


public void setType(String type){
    this.type = type;
}


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


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setCreateName(String createName){
    this.createName = createName;
}


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


}