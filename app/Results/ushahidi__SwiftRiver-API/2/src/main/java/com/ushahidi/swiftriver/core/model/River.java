package com.ushahidi.swiftriver.core.model;
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
@Entity
@Table(name = "rivers")
public class River {

@Id
@GeneratedValue
 private  Long id;

@Transient
 private  Account account;

@Column(name = "river_name", nullable = false)
 private  String riverName;

@Column(name = "river_name_canonical", nullable = false)
 private  String riverNameCanonical;

 private  String description;

@Column(name = "river_active")
 private  Boolean active;

@Column(name = "river_public", nullable = false)
 private  Boolean riverPublic;

@Column(name = "default_layout")
 private  String defaultLayout;

@Column(name = "river_date_add")
@Temporal(TemporalType.TIMESTAMP)
 private  Date dateAdded;

@Column(name = "max_drop_id")
 private  Long maxDropId;

@Column(name = "drop_count")
 private  Integer dropCount;

@Column(name = "river_date_expiry")
@Temporal(TemporalType.TIMESTAMP)
 private  Date expiryDate;

@Column(name = "river_expired")
 private  Boolean expired;

@Column(name = "extension_count")
 private  Integer extensionCount;

@Column(name = "expiry_notification_sent")
 private  Boolean expiryNotificationSent;

@Column(name = "public_token")
 private  String publicToken;

@Column(name = "drop_quota")
 private  Integer dropQuota;

@Column(name = "river_full")
 private  Boolean full;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "river")
 private  List<RiverCollaborator> collaborators;

@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name = "river_followers", joinColumns = @JoinColumn(name = "river_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
 private  List<Account> followers;

@Transient
 private  List<Channel> channels;

@Transient
 private  List<Rule> rules;

@Column(name = "idXN57")
 private long idXN57;

@Transient
 private AccountRequest accountrequest = new AccountRequestImpl();;

@Transient
 private ChannelRequest channelrequest = new ChannelRequestImpl();;

@Transient
 private RuleRequest rulerequest = new RuleRequestImpl();;

public River() {
}
public void setCollaborators(List<RiverCollaborator> collaborators){
    this.collaborators = collaborators;
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


public void setDropCount(Integer dropCount){
    this.dropCount = dropCount;
}


public Long getId(){
    return id;
}


public void setMaxDropId(Long maxDropId){
    this.maxDropId = maxDropId;
}


public void setDescription(String description){
    this.description = description;
}


public List<Account> getFollowers(){
    return followers;
}


public String getDescription(){
    return description;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setRiverName(String riverName){
    this.riverName = riverName;
}


public void setDefaultLayout(String defaultLayout){
    this.defaultLayout = defaultLayout;
}


public Date getDateAdded(){
    return dateAdded;
}


public void setExpired(Boolean expired){
    this.expired = expired;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 31).append(account).append(riverNameCanonical).toHashCode();
}


public Account getAccount(){
  this.account = accountrequest.getAccount(this.idXN57);
return this.account;
}}



public Integer getExtensionCount(){
    return extensionCount;
}


public void setId(Long id){
    this.id = id;
}


public void setExtensionCount(Integer extensionCount){
    this.extensionCount = extensionCount;
}


public void setFull(Boolean full){
    this.full = full;
}


public List<Rule> getRules(){
  this.rules = rulerequest.getRules(this.id);
return this.rules;
}}



public void setRules(List<Rule> rules){
rulerequest.setRules(rules,this.id);
 this.rules = rules;
}



public void setRiverNameCanonical(String riverNameCanonical){
    this.riverNameCanonical = riverNameCanonical;
}


public Integer getDropQuota(){
    return dropQuota;
}


public List<RiverCollaborator> getCollaborators(){
    return collaborators;
}


public void setChannels(List<Channel> channels){
channelrequest.setChannels(channels,this.id);
 this.channels = channels;
}



public String getDefaultLayout(){
    return defaultLayout;
}


public Date getExpiryDate(){
    return expiryDate;
}


public void setDropQuota(Integer dropQuota){
    this.dropQuota = dropQuota;
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


public void setRiverPublic(Boolean riverPublic){
    this.riverPublic = riverPublic;
}


public Integer getDropCount(){
    return dropCount;
}


public void setExpiryNotificationSent(Boolean expiryNotificationSent){
    this.expiryNotificationSent = expiryNotificationSent;
}


public void setActive(Boolean active){
    this.active = active;
}


public void setPublicToken(String publicToken){
    this.publicToken = publicToken;
}


public void setExpiryDate(Date expiryDate){
    this.expiryDate = expiryDate;
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
}


public List<Channel> getChannels(){
  this.channels = channelrequest.getChannels(this.id);
return this.channels;
}}



public void setAccount(Account account){
this.idXN57 = account.getAccount() ;
accountrequest.setAccount(account,this.idXN57);
 this.account = account;
}



public Boolean getActive(){
    return active;
}


public Boolean getFull(){
    return full;
}


public void setFollowers(List<Account> followers){
    this.followers = followers;
}


}