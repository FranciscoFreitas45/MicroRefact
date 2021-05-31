import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_goods_stock")
public class PhGoodsStock implements Serializable{

 private  Long id;

 private  Long goodsId;

 private  String goodsName;

 private  String goodsImage;

 private  String image;

 private  String sku;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String isOffway;

 private  String type;

 private  String category;

 private  String size;

 private  String color;

 private  Long stock;

 private  Date createTime;

 private  String remark;


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


public void setGoodsImage(String goodsImage){
    this.goodsImage = goodsImage;
}


@Column(name = "stock", length = 11)
public Long getStock(){
    return stock;
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


public void setSku(String sku){
    this.sku = sku;
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


public void setStock(Long stock){
    this.stock = stock;
}


@Column(name = "goods_image", length = 100)
public String getGoodsImage(){
    return goodsImage;
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


@Column(name = "sku", length = 100)
public String getSku(){
    return sku;
}


@Column(name = "size", length = 10)
public String getSize(){
    return size;
}


public void setColor(String color){
    this.color = color;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "type", length = 10)
public String getType(){
    return type;
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