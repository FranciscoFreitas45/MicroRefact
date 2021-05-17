import java.io.Serializable;
import javax.persistence;
import java.util.Date;
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


@Column(name = "phone", length = 11)
public String getPhone(){
    return phone;
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


@Column(name = "idcard_positive", length = 50)
public String getIdcardPositive(){
    return idcardPositive;
}


@Column(name = "privilege", length = 200)
public String getPrivilege(){
    return privilege;
}


@Column(name = "headimgurl", length = 500)
public String getHeadimgurl(){
    return headimgurl;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


@Column(name = "province", length = 50)
public String getProvince(){
    return province;
}


@Column(name = "city", length = 50)
public String getCity(){
    return city;
}


@Column(name = "is_auth", length = 2)
public String getIsAuth(){
    return isAuth;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
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


}