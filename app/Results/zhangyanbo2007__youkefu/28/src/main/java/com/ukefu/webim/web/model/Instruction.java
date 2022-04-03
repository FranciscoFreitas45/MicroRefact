package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_instruction")
@org.hibernate.annotations.Proxy(lazy = false)
public class Instruction {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String name;

 private  String keyword;

 private  String code;

 private  String plugin;

 private  String type;

 private  String scope;

 private  String parent;

 private  String memo;

 private  Date createtime;

 private  String snsid;

 private  String userid;

 private  String username;

 private  String matcherule;

 private  boolean tipdefault;

 private  String status;

 private  boolean userbind;

 private  String interfacetype;

 private  String interfaceurl;

 private  String interfaceparam;

 private  String adapter;

 private  String messagetype;

 private  String eventype;


public String getName(){
    return name;
}


public boolean isUserbind(){
    return userbind;
}


public String getInterfaceurl(){
    return interfaceurl;
}


public String getStatus(){
    return status;
}


public String getInterfacetype(){
    return interfacetype;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public void setId(String id){
    this.id = id;
}


public String getCode(){
    return code;
}


public void setTipdefault(boolean tipdefault){
    this.tipdefault = tipdefault;
}


public String getAdapter(){
    return adapter;
}


public String getEventype(){
    return eventype;
}


public void setPlugin(String plugin){
    this.plugin = plugin;
}


public void setAdapter(String adapter){
    this.adapter = adapter;
}


public void setCode(String code){
    this.code = code;
}


public String getMemo(){
    return memo;
}


public String getType(){
    return type;
}


public void setInterfacetype(String interfacetype){
    this.interfacetype = interfacetype;
}


public void setScope(String scope){
    this.scope = scope;
}


public void setUserbind(boolean userbind){
    this.userbind = userbind;
}


public String getScope(){
    return scope;
}


public void setName(String name){
    this.name = name;
}


public void setMessagetype(String messagetype){
    this.messagetype = messagetype;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public boolean isTipdefault(){
    return tipdefault;
}


@Transient
public String getTopic(){
    StringBuffer strb = new StringBuffer();
    strb.append(this.snsid);
    if (this.matcherule.equals("keyword")) {
        strb.append(".").append("text").append(".").append(this.getKeyword());
    } else if (this.matcherule.equals("message")) {
        strb.append(".").append(this.getMessagetype()).append(".").append(this.getMessagetype());
    } else if (this.matcherule.equals("event")) {
        strb.append(".").append("event").append(".").append(this.getEventype());
        if (!this.eventype.equals("subscribe")) {
            strb.append(".").append(this.getKeyword());
        }
    }
    return strb.toString();
}


public String getUsername(){
    return username;
}


public String getMessagetype(){
    return messagetype;
}


public Date getCreatetime(){
    return createtime;
}


public void setInterfaceurl(String interfaceurl){
    this.interfaceurl = interfaceurl;
}


public void setMatcherule(String matcherule){
    this.matcherule = matcherule;
}


public void setParent(String parent){
    this.parent = parent;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getParent(){
    return parent;
}


public void setUsername(String username){
    this.username = username;
}


public void setSnsid(String snsid){
    this.snsid = snsid;
}


public void setInterfaceparam(String interfaceparam){
    this.interfaceparam = interfaceparam;
}


public void setType(String type){
    this.type = type;
}


public void setStatus(String status){
    this.status = status;
}


public String getMatcherule(){
    return matcherule;
}


public String getInterfaceparam(){
    return interfaceparam;
}


public void setEventype(String eventype){
    this.eventype = eventype;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getKeyword(){
    return keyword;
}


public String getPlugin(){
    return plugin;
}


public String getOrgi(){
    return orgi;
}


public String getSnsid(){
    return snsid;
}


}