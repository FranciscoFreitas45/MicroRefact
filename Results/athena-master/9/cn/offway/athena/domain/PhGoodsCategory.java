import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_goods_category")
public class PhGoodsCategory implements Serializable{

 private  Long id;

 private  Long goodsType;

 private  String goodsTypeName;

 private  String name;

 private  String image;

 private  Long sort;

 private  Date createTime;

 private  String remark;


public void setName(String name){
    this.name = name;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "name", length = 100)
public String getName(){
    return name;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setGoodsTypeName(String goodsTypeName){
    this.goodsTypeName = goodsTypeName;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setGoodsType(Long goodsType){
    this.goodsType = goodsType;
}


public void setSort(Long sort){
    this.sort = sort;
}


@Column(name = "sort", length = 11)
public Long getSort(){
    return sort;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "goods_type", length = 11)
public Long getGoodsType(){
    return goodsType;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "goods_type_name", length = 100)
public String getGoodsTypeName(){
    return goodsTypeName;
}


public void setId(Long id){
    this.id = id;
}


@Column(name = "image", length = 100)
public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
}


}