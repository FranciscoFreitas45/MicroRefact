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
import com.ushahidi.swiftriver.core.Request.AccountRequest;
import com.ushahidi.swiftriver.core.Request.Impl.AccountRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Account;
import com.ushahidi.swiftriver.core.Request.ChannelRequest;
import com.ushahidi.swiftriver.core.Request.Impl.ChannelRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Channel;
import com.ushahidi.swiftriver.core.Request.RuleRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RuleRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Rule;
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

 private long idXN57;

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


public void setId(Long id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


}