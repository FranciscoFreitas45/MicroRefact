package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
public class Contacts extends ESBean{

 private  long serialVersionUID;

 private  String id;

 private  String gender;

 private  String cusbirthday;

 private  String ctype;

 private  String ckind;

 private  String clevel;

 private  String ccode;

 private  String nickname;

 private  String sarea;

 private  String csource;

 private  String language;

 private  String organ;

 private  String marriage;

 private  String entcusid;

 private  String education;

 private  String identifytype;

 private  String identifynumber;

 private  String website;

 private  String email;

 private  String emailalt;

 private  String mobileno;

 private  String mobilealt;

 private  String phone;

 private  String extension;

 private  String phonealt;

 private  String extensionalt;

 private  String familyphone;

 private  String familyphonealt;

 private  String fax;

 private  String faxalt;

 private  String country;

 private  String province;

 private  String city;

 private  String address;

 private  String postcode;

 private  String enterpriseid;

 private  String company;

 private  String department;

 private  String duty;

 private  String deptpr;

 private  String validstatus;

 private  String weixin;

 private  String weixinname;

 private  String weixinid;

 private  String weibo;

 private  String weiboid;

 private  String qqcode;

 private  Date touchtime;

 private  boolean datastatus;

 private  String processid;

 private  String creater;

 private  String username;

 private  String updateuser;

 private  String memo;

 private  String updateusername;

 private  Date updatetime;

 private  String orgi;

 private  String compper;

 private  Date createtime;

 private  String name;

 private  String assignedto;

 private  String wfstatus;

 private  String shares;

 private  String owner;

 private  String datadept;

 private  String pinyin;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getPostcode(){
    return postcode;
}


public String getCompper(){
    return compper;
}


public String getOwner(){
    return owner;
}


public String getIdentifynumber(){
    return identifynumber;
}


public String getCtype(){
    return ctype;
}


public String getCcode(){
    return ccode;
}


public String getWeixinid(){
    return weixinid;
}


public String getGender(){
    return gender;
}


public String getDuty(){
    return duty;
}


public String getMemo(){
    return memo;
}


public String getCompany(){
    return company;
}


public String getProcessid(){
    return processid;
}


public String getEmailalt(){
    return emailalt;
}


public String getPhone(){
    return phone;
}


public String getUsername(){
    return username;
}


public String getUpdateusername(){
    return updateusername;
}


public String getShares(){
    return shares;
}


public String getQqcode(){
    return qqcode;
}


public Date getCreatetime(){
    return createtime;
}


public String getIdentifytype(){
    return identifytype;
}


public String getProvince(){
    return province;
}


public String getLanguage(){
    return language;
}


public String getExtension(){
    return extension;
}


public String getWeibo(){
    return weibo;
}


public String getFamilyphone(){
    return familyphone;
}


public String getCsource(){
    return csource;
}


public String getFax(){
    return fax;
}


public String getPhonealt(){
    return phonealt;
}


public String getDeptpr(){
    return deptpr;
}


public String getCkind(){
    return ckind;
}


public String getName(){
    return name;
}


public String getCountry(){
    return country;
}


public String getDepartment(){
    return department;
}


public String getMobileno(){
    return mobileno;
}


public String getCity(){
    return city;
}


public String getWebsite(){
    return website;
}


public String getUpdateuser(){
    return updateuser;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getEnterpriseid(){
    return enterpriseid;
}


public String getExtensionalt(){
    return extensionalt;
}


public String getPinyin(){
    return pinyin;
}


public String getWeixin(){
    return weixin;
}


public String getMobilealt(){
    return mobilealt;
}


public String getValidstatus(){
    return validstatus;
}


public String getOrgan(){
    return organ;
}


public String getMarriage(){
    return marriage;
}


public String getEmail(){
    return email;
}


public String getEntcusid(){
    return entcusid;
}


public String getFamilyphonealt(){
    return familyphonealt;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getFaxalt(){
    return faxalt;
}


public String getAssignedto(){
    return assignedto;
}


public String getAddress(){
    return address;
}


public String getCusbirthday(){
    return cusbirthday;
}


public String getCreater(){
    return creater;
}


public String getClevel(){
    return clevel;
}


public String getDatadept(){
    return datadept;
}


public String getSarea(){
    return sarea;
}


public String getEducation(){
    return education;
}


public String getWeiboid(){
    return weiboid;
}


public String getNickname(){
    return nickname;
}


public String getOrgi(){
    return orgi;
}


public String getWfstatus(){
    return wfstatus;
}


public String getWeixinname(){
    return weixinname;
}


public Date getTouchtime(){
    return touchtime;
}


public void setId(String id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPhone(String phone){
    this.phone = phone;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPhone"))

.queryParam("phone",phone)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmail(String email){
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmail"))

.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMemo(String memo){
    this.memo = memo;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMemo"))

.queryParam("memo",memo)
;
restTemplate.put(builder.toUriString(),null);
}


}