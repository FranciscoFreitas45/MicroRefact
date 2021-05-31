import java.io.Serializable;
import java.util.Date;
import javax.persistence;
@Entity
@Table(name = "ph_feedback")
public class PhFeedback implements Serializable{

 private  Long id;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  Long starNum;

 private  Long imgNum;

 private  Date updateTime;


@Column(name = "brand_logo", length = 200)
public String getBrandLogo(){
    return brandLogo;
}


public void setBrandLogo(String brandLogo){
    this.brandLogo = brandLogo;
}


@Column(name = "brand_name", length = 50)
public String getBrandName(){
    return brandName;
}


public void setBrandName(String brandName){
    this.brandName = brandName;
}


@Column(name = "star_num", length = 11)
public Long getStarNum(){
    return starNum;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setImgNum(Long imgNum){
    this.imgNum = imgNum;
}


@Column(name = "img_num", length = 11)
public Long getImgNum(){
    return imgNum;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "update_time")
public Date getUpdateTime(){
    return updateTime;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


public void setId(Long id){
    this.id = id;
}


public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
}


public void setStarNum(Long starNum){
    this.starNum = starNum;
}


}