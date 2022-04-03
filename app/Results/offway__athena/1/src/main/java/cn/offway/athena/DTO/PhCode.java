package cn.offway.athena.DTO;
 import java.io.Serializable;
import javax.persistence;
import java.util.Date;
public class PhCode implements Serializable{

 private  Long id;

 private  String code;

 private  String phone;

 private  String position;

 private  String realName;

 private  String status;

 private  Date createTime;

 private  String remark;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://16";


@Column(name = "phone", length = 11)
public String getPhone(){
    return phone;
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


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "code", length = 10)
public String getCode(){
    return code;
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStatus"))

.queryParam("status",status)
;
restTemplate.put(builder.toUriString(),null);
}


}