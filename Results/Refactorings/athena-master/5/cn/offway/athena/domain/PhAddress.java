import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_address")
public class PhAddress implements Serializable{

 private  Long id;

 private  String unionid;

 private  String realName;

 private  String phone;

 private  String province;

 private  String city;

 private  String county;

 private  String content;

 private  Date createTime;

 private  String remark;


public void setUnionid(String unionid){
    this.unionid = unionid;
}


@Column(name = "phone", length = 20)
public String getPhone(){
    return phone;
}


public void setRealName(String realName){
    this.realName = realName;
}


public void setContent(String content){
    this.content = content;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


public void setCounty(String county){
    this.county = county;
}


public void setProvince(String province){
    this.province = province;
}


public void setCity(String city){
    this.city = city;
}


@Column(name = "content", length = 200)
public String getContent(){
    return content;
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


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "province", length = 20)
public String getProvince(){
    return province;
}


public void setId(Long id){
    this.id = id;
}


@Column(name = "county", length = 20)
public String getCounty(){
    return county;
}


@Column(name = "city", length = 20)
public String getCity(){
    return city;
}


}