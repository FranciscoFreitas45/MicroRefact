import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_show_image")
public class PhShowImage implements Serializable{

 private  Long id;

 private  String orderNo;

 private  String unionid;

 private  String position;

 private  String realName;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String isOffway;

 private  String showImage;

 private  String content;

 private  String url;

 private  String status;

 private  String checkName;

 private  Date checkTime;

 private  String checkContent;

 private  String starName;

 private  Date createTime;

 private  String remark;


public void setBrandLogo(String brandLogo){
    this.brandLogo = brandLogo;
}


public void setUnionid(String unionid){
    this.unionid = unionid;
}


@Column(name = "brand_name", length = 50)
public String getBrandName(){
    return brandName;
}


public void setStarName(String starName){
    this.starName = starName;
}


public void setRealName(String realName){
    this.realName = realName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


public void setContent(String content){
    this.content = content;
}


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


public void setCheckContent(String checkContent){
    this.checkContent = checkContent;
}


@Column(name = "content", length = 500)
public String getContent(){
    return content;
}


public void setShowImage(String showImage){
    this.showImage = showImage;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "check_content", length = 200)
public String getCheckContent(){
    return checkContent;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


public void setPosition(String position){
    this.position = position;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "check_time")
public Date getCheckTime(){
    return checkTime;
}


@Column(name = "check_name", length = 50)
public String getCheckName(){
    return checkName;
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


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
}


@Column(name = "show_image", length = 100)
public String getShowImage(){
    return showImage;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setStatus(String status){
    this.status = status;
}


public void setUrl(String url){
    this.url = url;
}


public void setCheckTime(Date checkTime){
    this.checkTime = checkTime;
}


public void setCheckName(String checkName){
    this.checkName = checkName;
}


@Column(name = "url", length = 500)
public String getUrl(){
    return url;
}


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "star_name", length = 200)
public String getStarName(){
    return starName;
}


}