import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_order_info")
public class PhOrderInfo implements Serializable{

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

 private  Date receiptTime;

 private  Date showTime;

 private  String remark;

 private  String position;

 private  String realName;

 private  String extra;

 private  Long addressId;


public void setUnionid(String unionid){
    this.unionid = unionid;
}


public void setBrandLogo(String brandLogo){
    this.brandLogo = brandLogo;
}


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


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


public void setReturnTime(Date returnTime){
    this.returnTime = returnTime;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "show_time")
public Date getShowTime(){
    return showTime;
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


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


public void setShowTime(Date showTime){
    this.showTime = showTime;
}


public void setAddressId(Long addressId){
    this.addressId = addressId;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
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


public void setIsOffway(String isOffway){
    this.isOffway = isOffway;
}


@Column(name = "is_offway", length = 2)
public String getIsOffway(){
    return isOffway;
}


@Column(name = "brand_logo", length = 50)
public String getBrandLogo(){
    return brandLogo;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


@Column(name = "order_no", length = 50)
public String getOrderNo(){
    return orderNo;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "receipt_time")
public Date getReceiptTime(){
    return receiptTime;
}


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "return_time")
public Date getReturnTime(){
    return returnTime;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "use_date")
public Date getUseDate(){
    return useDate;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setStatus(String status){
    this.status = status;
}


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "address_id", length = 11)
public Long getAddressId(){
    return addressId;
}


public void setUseDate(Date useDate){
    this.useDate = useDate;
}


public void setExtra(String extra){
    this.extra = extra;
}


@Column(name = "extra", length = 200)
public String getExtra(){
    return extra;
}


}