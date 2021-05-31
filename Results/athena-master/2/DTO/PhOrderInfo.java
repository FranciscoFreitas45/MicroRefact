import java.io.Serializable;
import javax.persistence;
import java.util.Date;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Column(name = "brand_name", length = 50)
public String getBrandName(){
    return brandName;
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


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


@Column(name = "users", length = 50)
public String getUsers(){
    return users;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "is_offway", length = 2)
public String getIsOffway(){
    return isOffway;
}


@Column(name = "brand_logo", length = 50)
public String getBrandLogo(){
    return brandLogo;
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


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "return_time")
public Date getReturnTime(){
    return returnTime;
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


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


@Column(name = "address_id", length = 11)
public Long getAddressId(){
    return addressId;
}


@Column(name = "extra", length = 200)
public String getExtra(){
    return extra;
}


public void setIsUpload(String isUpload){
    this.isUpload = isUpload;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsUpload"));

.queryParam("isUpload",isUpload);
restTemplate.put(builder.toUriString(),null);
}


}