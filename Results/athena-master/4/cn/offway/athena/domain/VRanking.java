import java.io.Serializable;
import javax.persistence;
@Entity
@Table(name = "v_ranking")
public class VRanking implements Serializable{

 private  Long brandId;

 private  String brandLogo;

 private  String brandName;

 private  Long sumgoods;

 private  Long upload;


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


public void setUpload(Long upload){
    this.upload = upload;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


@Column(name = "sumgoods", length = 21)
public Long getSumgoods(){
    return sumgoods;
}


@Column(name = "upload", length = 21)
public Long getUpload(){
    return upload;
}


@Id
@Column(name = "brand_id", length = 11)
public Long getBrandId(){
    return brandId;
}


public void setSumgoods(Long sumgoods){
    this.sumgoods = sumgoods;
}


}