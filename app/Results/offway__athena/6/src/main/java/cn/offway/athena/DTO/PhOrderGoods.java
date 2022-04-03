package cn.offway.athena.DTO;
 import java.io.Serializable;
import javax.persistence;
import java.util.Date;
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


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


@Column(name = "mailNo", length = 50)
public String getMailNo(){
    return mailNo;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setSku(String sku){
    this.sku = sku;
}


@Column(name = "brand_logo", length = 50)
public String getBrandLogo(){
    return brandLogo;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}


@Column(name = "order_no", length = 50)
public String getOrderNo(){
    return orderNo;
}


@Column(name = "color", length = 10)
public String getColor(){
    return color;
}


@Column(name = "returnMailNo", length = 50)
public String getReturnMailNo(){
    return returnMailNo;
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


@Column(name = "state", length = 2)
public String getState(){
    return state;
}


@Column(name = "batch", length = 5)
public Long getBatch(){
    return batch;
}


@Column(name = "image", length = 100)
public String getImage(){
    return image;
}


@Column(name = "goods_id", length = 11)
public Long getGoodsId(){
    return goodsId;
}


}