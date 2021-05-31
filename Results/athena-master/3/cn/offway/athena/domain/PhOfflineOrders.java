import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_offline_orders")
public class PhOfflineOrders implements Serializable{

 private  Long id;

 private  String ordersNo;

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date startTime;

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date returnTime;

 private  String realName;

 private  String position;

 private  String users;

 private  Long goodsCount;

 private  Date createTime;

 private  String state;

 private  String remark;

 private  String message;


public void setRealName(String realName){
    this.realName = realName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "goods_count", length = 11)
public Long getGoodsCount(){
    return goodsCount;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "return_time")
public Date getReturnTime(){
    return returnTime;
}


public void setReturnTime(Date returnTime){
    this.returnTime = returnTime;
}


@Column(name = "message", length = 2)
public String getMessage(){
    return message;
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


public void setGoodsCount(Long goodsCount){
    this.goodsCount = goodsCount;
}


public void setOrdersNo(String ordersNo){
    this.ordersNo = ordersNo;
}


public void setMessage(String message){
    this.message = message;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setPosition(String position){
    this.position = position;
}


@Column(name = "orders_no", length = 50)
public String getOrdersNo(){
    return ordersNo;
}


@Column(name = "position", length = 100)
public String getPosition(){
    return position;
}


@Column(name = "state", length = 2)
public String getState(){
    return state;
}


public void setUsers(String users){
    this.users = users;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "users", length = 50)
public String getUsers(){
    return users;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setState(String state){
    this.state = state;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "start_time")
public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


}