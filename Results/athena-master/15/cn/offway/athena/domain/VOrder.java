import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "v_order")
public class VOrder implements Serializable{

 private  Long id;

 private  String orderNo;

 private  Date useDate;

 private  String users;

 private  String unionid;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String isOffway;

 private  String status;

 private  String isUpload;

 private  Date createTime;

 private  Date returnTime;

 private  Date showTime;

 private  Date receiptTime;

 private  String remark;

 private  Long eId;

 private  String expressOrderNo;

 private  String fromCity;

 private  String fromContent;

 private  String fromCounty;

 private  String fromPhone;

 private  String fromProvince;

 private  String fromRealName;

 private  String mailNo;

 private  String eStatus;

 private  String toCity;

 private  String toContent;

 private  String toCounty;

 private  String toPhone;

 private  String toProvince;

 private  String toRealName;

 private  String position;

 private  String realName;


@Column(name = "brand_name", length = 50)
public String getBrandName(){
    return brandName;
}


public void setRealName(String realName){
    this.realName = realName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


public void setReturnTime(Date returnTime){
    this.returnTime = returnTime;
}


@Column(name = "from_city", length = 20)
public String getFromCity(){
    return fromCity;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


public void setShowTime(Date showTime){
    this.showTime = showTime;
}


public void setExpressOrderNo(String expressOrderNo){
    this.expressOrderNo = expressOrderNo;
}


public void setToRealName(String toRealName){
    this.toRealName = toRealName;
}


public void setMailNo(String mailNo){
    this.mailNo = mailNo;
}


public void setIsUpload(String isUpload){
    this.isUpload = isUpload;
}


public void setPosition(String position){
    this.position = position;
}


public void setReceiptTime(Date receiptTime){
    this.receiptTime = receiptTime;
}


public void setUsers(String users){
    this.users = users;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setFromPhone(String fromPhone){
    this.fromPhone = fromPhone;
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


public void seteStatus(String eStatus){
    this.eStatus = eStatus;
}


public void setIsOffway(String isOffway){
    this.isOffway = isOffway;
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


public void setBrandName(String brandName){
    this.brandName = brandName;
}


@Column(name = "express_order_no", length = 50)
public String getExpressOrderNo(){
    return expressOrderNo;
}


@Column(name = "order_no", length = 50)
public String getOrderNo(){
    return orderNo;
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


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setToCounty(String toCounty){
    this.toCounty = toCounty;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "to_city", length = 20)
public String getToCity(){
    return toCity;
}


public void seteId(Long eId){
    this.eId = eId;
}


@Column(name = "e_status", length = 2)
public String geteStatus(){
    return eStatus;
}


public void setUnionid(String unionid){
    this.unionid = unionid;
}


public void setBrandLogo(String brandLogo){
    this.brandLogo = brandLogo;
}


@Column(name = "e_id", length = 11)
public Long geteId(){
    return eId;
}


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


@Column(name = "from_county", length = 20)
public String getFromCounty(){
    return fromCounty;
}


@Column(name = "to_county", length = 20)
public String getToCounty(){
    return toCounty;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "show_time")
public Date getShowTime(){
    return showTime;
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


@Column(name = "is_upload", length = 2)
public String getIsUpload(){
    return isUpload;
}


@Column(name = "from_phone", length = 50)
public String getFromPhone(){
    return fromPhone;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


@Column(name = "mail_no", length = 50)
public String getMailNo(){
    return mailNo;
}


@Column(name = "is_offway", length = 2)
public String getIsOffway(){
    return isOffway;
}


@Column(name = "to_phone", length = 50)
public String getToPhone(){
    return toPhone;
}


@Column(name = "brand_logo", length = 200)
public String getBrandLogo(){
    return brandLogo;
}


public void setToPhone(String toPhone){
    this.toPhone = toPhone;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "receipt_time")
public Date getReceiptTime(){
    return receiptTime;
}


public void setFromCounty(String fromCounty){
    this.fromCounty = fromCounty;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "return_time")
public Date getReturnTime(){
    return returnTime;
}


public void setToContent(String toContent){
    this.toContent = toContent;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "use_date")
public Date getUseDate(){
    return useDate;
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


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


public void setUseDate(Date useDate){
    this.useDate = useDate;
}


@Column(name = "from_province", length = 20)
public String getFromProvince(){
    return fromProvince;
}


public void setFromRealName(String fromRealName){
    this.fromRealName = fromRealName;
}


}