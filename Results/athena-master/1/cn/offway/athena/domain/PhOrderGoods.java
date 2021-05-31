import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_order_goods")
public class PhOrderGoods implements Serializable{

 private  Long id;

 private  Long orderId;

 private  String orderNo;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  Long goodsId;

 private  String goodsName;

 private  String image;

 private  String size;

 private  String color;

 private  Date createTime;

 private  String remark;

 private  String sku;

 private  String state;

 private  Long batch;

 private  String mailNo;

 private  String returnMailNo;


public void setBrandLogo(String brandLogo){
    this.brandLogo = brandLogo;
}


@Column(name = "brand_name", length = 50)
public String getBrandName(){
    return brandName;
}


@Column(name = "goods_name", length = 100)
public String getGoodsName(){
    return goodsName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


public void setBatch(Long batch){
    this.batch = batch;
}


@Column(name = "order_id", length = 11)
public Long getOrderId(){
    return orderId;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setOrderId(Long orderId){
    this.orderId = orderId;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


public void setMailNo(String mailNo){
    this.mailNo = mailNo;
}


@Column(name = "mailNo", length = 50)
public String getMailNo(){
    return mailNo;
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


@Column(name = "brand_logo", length = 50)
public String getBrandLogo(){
    return brandLogo;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


@Column(name = "order_no", length = 50)
public String getOrderNo(){
    return orderNo;
}


public void setOrderNo(String orderNo){
    this.orderNo = orderNo;
}


@Column(name = "color", length = 10)
public String getColor(){
    return color;
}


@Column(name = "returnMailNo", length = 50)
public String getReturnMailNo(){
    return returnMailNo;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setReturnMailNo(String returnMailNo){
    this.returnMailNo = returnMailNo;
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


@Column(name = "state", length = 2)
public String getState(){
    return state;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "batch", length = 5)
public Long getBatch(){
    return batch;
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