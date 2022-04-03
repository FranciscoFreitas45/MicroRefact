package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_contacts")
@Entity
@Table(name = "uk_contacts")
@org.hibernate.annotations.Proxy(lazy = false)
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


public String getPostcode(){
    return postcode;
}


public void setProvince(String province){
    this.province = province;
}


public void setShares(String shares){
    this.shares = shares;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public void setCompper(String compper){
    this.compper = compper;
}


public void setEducation(String education){
    this.education = education;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getCompper(){
    return compper;
}


public String getOwner(){
    return owner;
}


public void setCtype(String ctype){
    this.ctype = ctype;
}


public void setOwner(String owner){
    this.owner = owner;
}


public void setGender(String gender){
    this.gender = gender;
}


public void setDuty(String duty){
    this.duty = duty;
}


public void setUpdateusername(String updateusername){
    this.updateusername = updateusername;
}


public void setIdentifynumber(String identifynumber){
    this.identifynumber = identifynumber;
}


public void setMobileno(String mobileno){
    this.mobileno = mobileno;
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


public void setTouchtime(Date touchtime){
    this.touchtime = touchtime;
}


public String getProcessid(){
    return processid;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getEmailalt(){
    return emailalt;
}


public String getPhone(){
    return phone;
}


public void setName(String name){
    this.name = name;
}


public void setCcode(String ccode){
    this.ccode = ccode;
}


public void setCusbirthday(String cusbirthday){
    this.cusbirthday = cusbirthday;
}


public void setAssignedto(String assignedto){
    this.assignedto = assignedto;
}


public void setDatadept(String datadept){
    this.datadept = datadept;
}


public void setWeibo(String weibo){
    this.weibo = weibo;
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


public void setFax(String fax){
    this.fax = fax;
}


public void setValidstatus(String validstatus){
    this.validstatus = validstatus;
}


public void setClevel(String clevel){
    this.clevel = clevel;
}


public void setCompany(String company){
    this.company = company;
}


public void setEntcusid(String entcusid){
    this.entcusid = entcusid;
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


public void setFamilyphone(String familyphone){
    this.familyphone = familyphone;
}


public String getCsource(){
    return csource;
}


public String getFax(){
    return fax;
}


public void setMobilealt(String mobilealt){
    this.mobilealt = mobilealt;
}


public void setWeixinid(String weixinid){
    this.weixinid = weixinid;
}


public void setWeixin(String weixin){
    this.weixin = weixin;
}


public String getPhonealt(){
    return phonealt;
}


public void setCountry(String country){
    this.country = country;
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


public void setFaxalt(String faxalt){
    this.faxalt = faxalt;
}


public void setQqcode(String qqcode){
    this.qqcode = qqcode;
}


public void setSarea(String sarea){
    this.sarea = sarea;
}


public void setId(String id){
    this.id = id;
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


public void setCity(String city){
    this.city = city;
}


public String getUpdateuser(){
    return updateuser;
}


public void setDepartment(String department){
    this.department = department;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getEnterpriseid(){
    return enterpriseid;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setCsource(String csource){
    this.csource = csource;
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


public void setOrgan(String organ){
    this.organ = organ;
}


public String getValidstatus(){
    return validstatus;
}


public void setEmail(String email){
    this.email = email;
}


public String getOrgan(){
    return organ;
}


public String getMarriage(){
    return marriage;
}


public void setPostcode(String postcode){
    this.postcode = postcode;
}


public void setPhonealt(String phonealt){
    this.phonealt = phonealt;
}


public String getEmail(){
    return email;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDeptpr(String deptpr){
    this.deptpr = deptpr;
}


public String getEntcusid(){
    return entcusid;
}


public void setLanguage(String language){
    this.language = language;
}


public void setExtension(String extension){
    this.extension = extension;
}


public void setWfstatus(String wfstatus){
    this.wfstatus = wfstatus;
}


public String getFamilyphonealt(){
    return familyphonealt;
}


public void setUpdateuser(String updateuser){
    this.updateuser = updateuser;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setPinyin(String pinyin){
    this.pinyin = pinyin;
}


public void setWeixinname(String weixinname){
    this.weixinname = weixinname;
}


public void setWeiboid(String weiboid){
    this.weiboid = weiboid;
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


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setEnterpriseid(String enterpriseid){
    this.enterpriseid = enterpriseid;
}


public String getClevel(){
    return clevel;
}


public void setWebsite(String website){
    this.website = website;
}


public String getDatadept(){
    return datadept;
}


public void setMarriage(String marriage){
    this.marriage = marriage;
}


public void setCkind(String ckind){
    this.ckind = ckind;
}


public void setAddress(String address){
    this.address = address;
}


public void setUsername(String username){
    this.username = username;
}


public String getSarea(){
    return sarea;
}


public String getEducation(){
    return education;
}


public void setFamilyphonealt(String familyphonealt){
    this.familyphonealt = familyphonealt;
}


public String getWeiboid(){
    return weiboid;
}


public void setMemo(String memo){
    this.memo = memo;
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


public void setExtensionalt(String extensionalt){
    this.extensionalt = extensionalt;
}


public void setIdentifytype(String identifytype){
    this.identifytype = identifytype;
}


public Date getTouchtime(){
    return touchtime;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setEmailalt(String emailalt){
    this.emailalt = emailalt;
}


}