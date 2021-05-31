import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_banner_history")
public class PhBannerHistory implements Serializable{

 private  Long id;

 private  String banner;

 private  String bannerId;

 private  String bannerImg;

 private  Date createTime;

 private  Date beginTime;

 private  Date endTime;


@Column(name = "banner_id", length = 5)
public String getBannerId(){
    return bannerId;
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


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "end_time")
public Date getEndTime(){
    return endTime;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setId(Long id){
    this.id = id;
}


@Column(name = "banner_img", length = 200)
public String getBannerImg(){
    return bannerImg;
}


public void setBannerImg(String bannerImg){
    this.bannerImg = bannerImg;
}


public void setBeginTime(Date beginTime){
    this.beginTime = beginTime;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "begin_time")
public Date getBeginTime(){
    return beginTime;
}


public void setBannerId(String bannerId){
    this.bannerId = bannerId;
}


@Column(name = "banner", length = 100)
public String getBanner(){
    return banner;
}


public void setBanner(String banner){
    this.banner = banner;
}


}