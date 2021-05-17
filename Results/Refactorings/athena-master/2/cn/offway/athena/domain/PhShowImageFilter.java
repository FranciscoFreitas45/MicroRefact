import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_show_image_filter")
public class PhShowImageFilter implements Serializable{

 private  Long id;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  Date useDate;

 private  String showImage;

 private  String starName;

 private  Date createTime;

 private  String remark;


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


public void setStarName(String starName){
    this.starName = starName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "show_image")
public String getShowImage(){
    return showImage;
}


public void setShowImage(String showImage){
    this.showImage = showImage;
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


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "use_date")
public Date getUseDate(){
    return useDate;
}


@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "star_name", length = 200)
public String getStarName(){
    return starName;
}


public void setUseDate(Date useDate){
    this.useDate = useDate;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


}