import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_wardrobe")
public class PhWardrobe implements Serializable{

 private  Long id;

 private  String unionid;

 private  Long goodsId;

 private  String goodsName;

 private  String image;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String isOffway;

 private  String type;

 private  String category;

 private  String size;

 private  String color;

 private  Date useDate;

 private  Date createTime;

 private  String remark;

 private  String state;


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


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


public void setSize(String size){
    this.size = size;
}


public void setCategory(String category){
    this.category = category;
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


public void setGoodsId(Long goodsId){
    this.goodsId = goodsId;
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


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


@Column(name = "category", length = 20)
public String getCategory(){
    return category;
}


@Column(name = "color", length = 10)
public String getColor(){
    return color;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setType(String type){
    this.type = type;
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


@Column(name = "type", length = 10)
public String getType(){
    return type;
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