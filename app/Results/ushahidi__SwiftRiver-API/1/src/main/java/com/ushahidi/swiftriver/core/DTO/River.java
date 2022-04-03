package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class River {

 private  Long id;

 private  Account account;

 private  String riverName;

 private  String riverNameCanonical;

 private  String description;

 private  Boolean active;

 private  Boolean riverPublic;

 private  String defaultLayout;

 private  Date dateAdded;

 private  Long maxDropId;

 private  Integer dropCount;

 private  Date expiryDate;

 private  Boolean expired;

 private  Integer extensionCount;

 private  Boolean expiryNotificationSent;

 private  String publicToken;

 private  Integer dropQuota;

 private  Boolean full;

 private  List<RiverCollaborator> collaborators;

 private  List<Account> followers;

 private  List<Channel> channels;

 private  List<Rule> rules;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

public River() {
}
public String getPublicToken(){
    return publicToken;
}


public String getRiverNameCanonical(){
    return riverNameCanonical;
}


public Boolean getRiverPublic(){
    return riverPublic;
}


public Long getId(){
    return id;
}


public List<Account> getFollowers(){
    return followers;
}


public String getDescription(){
    return description;
}


public Date getDateAdded(){
    return dateAdded;
}


public Account getAccount(){
    return account;
}


public Integer getExtensionCount(){
    return extensionCount;
}


public List<Rule> getRules(){
    return rules;
}


public Integer getDropQuota(){
    return dropQuota;
}


public List<RiverCollaborator> getCollaborators(){
    return collaborators;
}


public String getDefaultLayout(){
    return defaultLayout;
}


public Date getExpiryDate(){
    return expiryDate;
}


public String getRiverName(){
    return riverName;
}


public Long getMaxDropId(){
    return maxDropId;
}


public Boolean getExpiryNotificationSent(){
    return expiryNotificationSent;
}


public Boolean getExpired(){
    return expired;
}


public Integer getDropCount(){
    return dropCount;
}


public List<Channel> getChannels(){
    return channels;
}


public Boolean getActive(){
    return active;
}


public Boolean getFull(){
    return full;
}


public void setDropCount(Integer dropCount){
    this.dropCount = dropCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDropCount"))

.queryParam("dropCount",dropCount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAccount(Account account){
this.idXN57 = account.getAccount() ;
accountrequest.setAccount(account,this.idXN57);
 this.account = account;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setAccount"))

.queryParam("account",account)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActive(Boolean active){
    this.active = active;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setActive"))

.queryParam("active",active)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDropQuota(Integer dropQuota){
    this.dropQuota = dropQuota;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDropQuota"))

.queryParam("dropQuota",dropQuota)
;
restTemplate.put(builder.toUriString(),null);
}


@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (obj == this)
        return true;
    if (obj.getClass() != getClass())
        return false;
    River other = (River) obj;
    return new EqualsBuilder().append(account, other.account).append(riverNameCanonical, other.riverNameCanonical).isEquals();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/equals"))

.queryParam("obj",obj)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}