import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_goods_image")
public class PhGoodsImage implements Serializable{

 private  Long id;

 private  Long goodsId;

 private  String goodsName;

 private  String imageUrl;

 private  String type;

 private  Long sort;

 private  Date createTime;

 private  String remark;


public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setImageUrl"));

.queryParam("imageUrl",imageUrl);
restTemplate.put(builder.toUriString(),null);


@Column(name = "goods_name", length = 100)
public String getGoodsName(){
    return goodsName;
}


public void setGoodsName(String goodsName){
    this.goodsName = goodsName;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGoodsName"));

.queryParam("goodsName",goodsName);
restTemplate.put(builder.toUriString(),null);


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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateTime"));

.queryParam("createTime",createTime);
restTemplate.put(builder.toUriString(),null);


public void setType(String type){
    this.type = type;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setType"));

.queryParam("type",type);
restTemplate.put(builder.toUriString(),null);


public void setSort(Long sort){
    this.sort = sort;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSort"));

.queryParam("sort",sort);
restTemplate.put(builder.toUriString(),null);


@Column(name = "sort", length = 11)
public Long getSort(){
    return sort;
}


@Column(name = "type", length = 2)
public String getType(){
    return type;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "image_url", length = 100)
public String getImageUrl(){
    return imageUrl;
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

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setGoodsId"));

.queryParam("goodsId",goodsId);
restTemplate.put(builder.toUriString(),null);


@Column(name = "goods_id", length = 11)
public Long getGoodsId(){
    return goodsId;
}


}