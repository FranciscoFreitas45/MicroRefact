import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_wardrobe_audit")
public class PhWardrobeAudit implements Serializable{

 private  Long id;

 private  Long wardrobeId;

 private  String unionid;

 private  String useName;

 private  String content;

 private  Long goodsId;

 private  String goodsName;

 private  String image;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String size;

 private  String color;

 private  Date photoDate;

 private  Date useDate;

 private  Date createTime;

 private  String state;

 private  String reason;

 private  String remark;

 private  Date returnDate;

 private  String isDel;


public void setUnionid(String unionid){
    this.unionid = unionid;
}


public void setBrandLogo(String brandLogo){
    this.brandLogo = brandLogo;
}


@Column(name = "goods_name", length = 100)
public String getGoodsName(){
    return goodsName;
}


@Column(name = "brand_name", length = 50)
public String getBrandName(){
    return brandName;
}


public void setContent(String content){
    this.content = content;
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


public void setWardrobeId(Long wardrobeId){
    this.wardrobeId = wardrobeId;
}


@Column(name = "content", length = 200)
public String getContent(){
    return content;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "use_name", length = 200)
public String getUseName(){
    return useName;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "photo_date")
public Date getPhotoDate(){
    return photoDate;
}


public void setPhotoDate(Date photoDate){
    this.photoDate = photoDate;
}


public void setReturnDate(Date returnDate){
    this.returnDate = returnDate;
}


@Column(name = "is_del", length = 2)
public String getIsDel(){
    return isDel;
}


public void setSize(String size){
    this.size = size;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setReason(String reason){
    this.reason = reason;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setIsDel(String isDel){
    this.isDel = isDel;
}


public void setId(Long id){
    this.id = id;
}


public void setUseName(String useName){
    this.useName = useName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "return_date")
public Date getReturnDate(){
    return returnDate;
}


public void setGoodsId(Long goodsId){
    this.goodsId = goodsId;
}


@Column(name = "brand_logo", length = 200)
public String getBrandLogo(){
    return brandLogo;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


@Column(name = "reason", length = 200)
public String getReason(){
    return reason;
}


@Column(name = "color", length = 10)
public String getColor(){
    return color;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "use_date")
public Date getUseDate(){
    return useDate;
}


@Column(name = "size", length = 10)
public String getSize(){
    return size;
}


public void setColor(String color){
    this.color = color;
}


@Column(name = "state", length = 2)
public String getState(){
    return state;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "wardrobe_id", length = 11)
public Long getWardrobeId(){
    return wardrobeId;
}


public void setUseDate(Date useDate){
    this.useDate = useDate;
}


public void setState(String state){
    this.state = state;
}


@Column(name = "image", length = 100)
public String getImage(){
    return image;
}


@Column(name = "goods_id", length = 11)
public Long getGoodsId(){
    return goodsId;
}


public void setImage(String image){
    this.image = image;
}


}