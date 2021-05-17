import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_order_express_info")
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


public void setOrderId(Long orderId){
    this.orderId = orderId;
}


public void setExpressOrderNo(String expressOrderNo){
    this.expressOrderNo = expressOrderNo;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


public void setToRealName(String toRealName){
    this.toRealName = toRealName;
}


public void setMailNo(String mailNo){
    this.mailNo = mailNo;
}


public void setLastTime(String lastTime){
    this.lastTime = lastTime;
}


public void setFromPhone(String fromPhone){
    this.fromPhone = fromPhone;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setToProvince(String toProvince){
    this.toProvince = toProvince;
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


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
}


public void setFromContent(String fromContent){
    this.fromContent = fromContent;
}


public void setFromProvince(String fromProvince){
    this.fromProvince = fromProvince;
}


@Column(name = "from_real_name", length = 50)
public String getFromRealName(){
    return fromRealName;
}


public void setReturnId(Long returnId){
    this.returnId = returnId;
}


public void setToCounty(String toCounty){
    this.toCounty = toCounty;
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


public void setBatch(Long batch){
    this.batch = batch;
}


public void setExPhone(String exPhone){
    this.exPhone = exPhone;
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


public void setToPhone(String toPhone){
    this.toPhone = toPhone;
}


public void setFromCounty(String fromCounty){
    this.fromCounty = fromCounty;
}


public void setToContent(String toContent){
    this.toContent = toContent;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setType(String type){
    this.type = type;
}


@Column(name = "return_id", length = 11)
public Long getReturnId(){
    return returnId;
}


public void setFromCity(String fromCity){
    this.fromCity = fromCity;
}


public void setStatus(String status){
    this.status = status;
}


public void setToCity(String toCity){
    this.toCity = toCity;
}


@Column(name = "to_province", length = 20)
public String getToProvince(){
    return toProvince;
}


@Column(name = "from_province", length = 20)
public String getFromProvince(){
    return fromProvince;
}


public void setFromRealName(String fromRealName){
    this.fromRealName = fromRealName;
}


}