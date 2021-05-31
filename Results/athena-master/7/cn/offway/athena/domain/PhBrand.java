import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_brand")
public class PhBrand implements Serializable{

 private  Long id;

 private  String name;

 private  String logo;

 private  String info;

 private  String background;

 private  String realName;

 private  String phone;

 private  String province;

 private  String city;

 private  String county;

 private  String content;

 private  Date createTime;

 private  String remark;

 private  String status;

 private  Long returnAddrId;

 private  Long addrId;


public void setName(String name){
    this.name = name;
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


@Column(name = "name", length = 100)
public String getName(){
    return name;
}


public void setCounty(String county){
    this.county = county;
}


public void setProvince(String province){
    this.province = province;
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


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


public void setBackground(String background){
    this.background = background;
}


public void setReturnAddrId(Long returnAddrId){
    this.returnAddrId = returnAddrId;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "return_addr_id", length = 200)
public Long getReturnAddrId(){
    return returnAddrId;
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


public void setLogo(String logo){
    this.logo = logo;
}


@Column(name = "logo", length = 200)
public String getLogo(){
    return logo;
}


@Column(name = "info")
public String getInfo(){
    return info;
}


public void setCity(String city){
    this.city = city;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setAddrId(Long addrId){
    this.addrId = addrId;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setStatus(String status){
    this.status = status;
}


@Column(name = "addr_id", length = 200)
public Long getAddrId(){
    return addrId;
}


public void setInfo(String info){
    this.info = info;
}


@Column(name = "background", length = 200)
public String getBackground(){
    return background;
}


}