import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_code")
public class PhCode implements Serializable{

 private  Long id;

 private  String code;

 private  String phone;

 private  String position;

 private  String realName;

 private  String status;

 private  Date createTime;

 private  String remark;


@Column(name = "phone", length = 11)
public String getPhone(){
    return phone;
}


public void setRealName(String realName){
    this.realName = realName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


public void setCode(String code){
    this.code = code;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setPosition(String position){
    this.position = position;
}


public void setStatus(String status){
    this.status = status;
}


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


@Column(name = "code", length = 10)
public String getCode(){
    return code;
}


}