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
    return account;
}


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
    return rules;
}


public void setRules(List<Rule> rules){
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
    return channels;
}


public void setAccount(Account account){
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