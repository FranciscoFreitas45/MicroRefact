import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_offline_remark")
public class PhOfflineRemark implements Serializable{

 private  Long id;

 private  String ordersNo;

 private  String ordersId;

 private  String content;

 private  String remark;

 private  Date createTime;

 private  String nickname;


public void setContent(String content){
    this.content = content;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


public void setOrdersId(String ordersId){
    this.ordersId = ordersId;
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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setOrdersNo(String ordersNo){
    this.ordersNo = ordersNo;
}


@Column(name = "orders_no", length = 50)
public String getOrdersNo(){
    return ordersNo;
}


@Column(name = "nickname", length = 255)
public String getNickname(){
    return nickname;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "remark", length = 255)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


@Column(name = "orders_id", length = 50)
public String getOrdersId(){
    return ordersId;
}


}