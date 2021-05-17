import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_offline_orders_goods")
public class PhOfflineOrdersGoods implements Serializable{

 private  Long id;

 private  String ordersNo;

 private  Long goodsId;

 private  String goodsName;

 private  String goodsImage;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String sku;

 private  String size;

 private  String color;

 private  Date createTime;

 private  String expressOrderNo;

 private  String way;

 private  String remark;

 private  String state;


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


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setExpressOrderNo(String expressOrderNo){
    this.expressOrderNo = expressOrderNo;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


@Column(name = "orders_no", length = 50)
public String getOrdersNo(){
    return ordersNo;
}


public void setSize(String size){
    this.size = size;
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


@Column(name = "express_order_no", length = 50)
public String getExpressOrderNo(){
    return expressOrderNo;
}


@Column(name = "goods_image", length = 100)
public String getGoodsImage(){
    return goodsImage;
}


@Column(name = "color", length = 10)
public String getColor(){
    return color;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setOrdersNo(String ordersNo){
    this.ordersNo = ordersNo;
}


@Column(name = "sku", length = 100)
public String getSku(){
    return sku;
}


public void setWay(String way){
    this.way = way;
}


@Column(name = "way", length = 2)
public String getWay(){
    return way;
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


public void setState(String state){
    this.state = state;
}


@Column(name = "goods_id", length = 11)
public Long getGoodsId(){
    return goodsId;
}


}