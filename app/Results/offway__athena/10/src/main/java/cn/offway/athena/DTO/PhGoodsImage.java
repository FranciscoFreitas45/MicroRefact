package cn.offway.athena.DTO;
 import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhGoodsImage implements Serializable{

 private  Long id;

 private  Long goodsId;

 private  String goodsName;

 private  String imageUrl;

 private  String type;

 private  Long sort;

 private  Date createTime;

 private  String remark;


@Column(name = "goods_name", length = 100)
public String getGoodsName(){
    return goodsName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


@Column(name = "sort", length = 11)
public Long getSort(){
    return sort;
}


@Column(name = "type", length = 2)
public String getType(){
    return type;
}


@Column(name = "image_url", length = 100)
public String getImageUrl(){
    return imageUrl;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setGoodsId(Long goodsId){
    this.goodsId = goodsId;
}


@Column(name = "goods_id", length = 11)
public Long getGoodsId(){
    return goodsId;
}


}