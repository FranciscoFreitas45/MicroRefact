import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_user_info")
public class PhUserInfo implements Serializable{

 private  Long id;

 private  String unionid;

 private  String miniopenid;

 private  String nickname;

 private  String sex;

 private  String province;

 private  String city;

 private  String country;

 private  String headimgurl;

 private  String privilege;

 private  String isAuth;

 private  String phone;

 private  String companyName;

 private  String position;

 private  String realName;

 private  String idcardPositive;

 private  String idcardObverse;

 private  String inCert;

 private  Long creditScore;

 private  Date createTime;

 private  String remark;


public void setUnionid(String unionid){
    this.unionid = unionid;
}


@Column(name = "phone", length = 11)
public String getPhone(){
    return phone;
}


public void setCountry(String country){
    this.country = country;
}


public void setRealName(String realName){
    this.realName = realName;
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


public void setProvince(String province){
    this.province = province;
}


@Column(name = "country", length = 200)
public String getCountry(){
    return country;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "idcard_obverse", length = 50)
public String getIdcardObverse(){
    return idcardObverse;
}


public void setInCert(String inCert){
    this.inCert = inCert;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setPosition(String position){
    this.position = position;
}


@Column(name = "idcard_positive", length = 50)
public String getIdcardPositive(){
    return idcardPositive;
}


public void setMiniopenid(String miniopenid){
    this.miniopenid = miniopenid;
}


@Column(name = "privilege", length = 200)
public String getPrivilege(){
    return privilege;
}


public void setPrivilege(String privilege){
    this.privilege = privilege;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "headimgurl", length = 500)
public String getHeadimgurl(){
    return headimgurl;
}


public void setIdcardObverse(String idcardObverse){
    this.idcardObverse = idcardObverse;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "province", length = 50)
public String getProvince(){
    return province;
}


public void setId(Long id){
    this.id = id;
}


public void setIsAuth(String isAuth){
    this.isAuth = isAuth;
}


@Column(name = "city", length = 50)
public String getCity(){
    return city;
}


@Column(name = "is_auth", length = 2)
public String getIsAuth(){
    return isAuth;
}


public void setCreditScore(Long creditScore){
    this.creditScore = creditScore;
}


public void setCity(String city){
    this.city = city;
}


public void setSex(String sex){
    this.sex = sex;
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


public void setIdcardPositive(String idcardPositive){
    this.idcardPositive = idcardPositive;
}


@Column(name = "in_cert", length = 50)
public String getInCert(){
    return inCert;
}


@Column(name = "credit_score", length = 11)
public Long getCreditScore(){
    return creditScore;
}


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


@Column(name = "nickname", length = 200)
public String getNickname(){
    return nickname;
}


public void setHeadimgurl(String headimgurl){
    this.headimgurl = headimgurl;
}


@Column(name = "company_name", length = 50)
public String getCompanyName(){
    return companyName;
}


@Column(name = "miniopenid", length = 50)
public String getMiniopenid(){
    return miniopenid;
}


@Column(name = "sex", length = 2)
public String getSex(){
    return sex;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


}