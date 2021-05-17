import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_feedback_detail")
public class PhFeedbackDetail implements Serializable{

 private  Long id;

 private  Long pid;

 private  Long brandId;

 private  String brandName;

 private  String brandLogo;

 private  String starName;

 private  Long imgNum;

 private  String weibo;

 private  String remark;

 private  Date backTime;

 private  String imgUrl;

 private  String goodsId;


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


public void setImgUrl(String imgUrl){
    this.imgUrl = imgUrl;
}


@Column(name = "weibo", length = 200)
public String getWeibo(){
    return weibo;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setPid(Long pid){
    this.pid = pid;
}


public void setBackTime(Date backTime){
    this.backTime = backTime;
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


public void setWeibo(String weibo){
    this.weibo = weibo;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "back_time")
public Date getBackTime(){
    return backTime;
}


public void setBrandId(Long brandId){
    this.brandId = brandId;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "star_name", length = 50)
public String getStarName(){
    return starName;
}


@Column(name = "img_url", length = 1000)
public String getImgUrl(){
    return imgUrl;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setGoodsId(String goodsId){
    this.goodsId = goodsId;
}


@Column(name = "pid", length = 11)
public Long getPid(){
    return pid;
}


@Column(name = "goods_id", length = 1000)
public String getGoodsId(){
    return goodsId;
}


}