import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_goods")
public class PhGoods implements Serializable{

 private  Long id;

 private  String name;

 private  String image;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String isOffway;

 private  String type;

 private  String category;

 private  String isRelease;

 private  String status;

 private  Date createTime;

 private  String remark;


public void setName(String name){
    this.name = name;
}


public void setBrandLogo(String brandLogo){
    this.brandLogo = brandLogo;
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


@Column(name = "name", length = 100)
public String getName(){
    return name;
}


public void setIsRelease(String isRelease){
    this.isRelease = isRelease;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
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


@Column(name = "is_release", length = 2)
public String getIsRelease(){
    return isRelease;
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


@Column(name = "brand_logo", length = 200)
public String getBrandLogo(){
    return brandLogo;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


@Column(name = "category", length = 20)
public String getCategory(){
    return category;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setType(String type){
    this.type = type;
}


public void setStatus(String status){
    this.status = status;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "type", length = 20)
public String getType(){
    return type;
}


@Column(name = "image", length = 100)
public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
}


}