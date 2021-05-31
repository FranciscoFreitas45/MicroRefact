import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_banner")
public class PhBanner implements Serializable{

 private  Long id;

 private  String banner;

 private  String type;

 private  String redirectId;

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date beginTime;

@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date endTime;

 private  String status;

 private  Long sort;

 private  Date createTime;

 private  String remark;


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


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public void setSort(Long sort){
    this.sort = sort;
}


@Column(name = "sort", length = 11)
public Long getSort(){
    return sort;
}


@Column(name = "redirect_id", length = 5)
public String getRedirectId(){
    return redirectId;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "end_time")
public Date getEndTime(){
    return endTime;
}


public void setRemark(String remark){
    this.remark = remark;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


public void setRedirectId(String redirectId){
    this.redirectId = redirectId;
}


public void setBeginTime(Date beginTime){
    this.beginTime = beginTime;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "begin_time")
public Date getBeginTime(){
    return beginTime;
}


@Column(name = "banner", length = 100)
public String getBanner(){
    return banner;
}


public void setBanner(String banner){
    this.banner = banner;
}


}