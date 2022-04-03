package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
public class Apply extends Model<Apply>{

 private  long serialVersionUID;

 private  Integer id;

 private  Integer userid;

 private  Integer deptid;

 private  Date applytime;

 private  Integer agree;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public Integer getAgree(){
    return agree;
}


public Date getApplytime(){
    return applytime;
}


public Integer getDeptid(){
    return deptid;
}


public Integer getId(){
    return id;
}


public Integer getUserid(){
    return userid;
}


public void setUserid(Integer userid){
    this.userid = userid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserid"))

.queryParam("userid",userid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDeptid(Integer deptid){
    this.deptid = deptid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDeptid"))

.queryParam("deptid",deptid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setApplytime(Date applytime){
    this.applytime = applytime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setApplytime"))

.queryParam("applytime",applytime)
;
restTemplate.put(builder.toUriString(),null);
}


}