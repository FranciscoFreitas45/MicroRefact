package com.ushahidi.swiftriver.core.DTO;
 import java.util.Date;
import java.util.List;
import java.util.Set;
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
import javax.persistence.Version;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Account {

 private  long id;

 private  String accountPath;

 private  boolean accountPrivate;

 private  boolean active;

 private  Date dateAdded;

 private  Date dateModified;

 private  int riverQuotaRemaining;

 private  User owner;

 private  List<AccountFollower> followers;

 private  List<AccountFollower> following;

 private  List<River> rivers;

 private  List<River> collaboratingRivers;

 private  List<River> followingRivers;

 private  List<Bucket> buckets;

 private  List<Bucket> followingBuckets;

 private  List<Bucket> collaboratingBuckets;

 private  List<Form> forms;

 private  Set<Client> clients;

 private  long version;

 private  List<RiverDrop> readRiverDrops;

 private  List<BucketDrop> readBucketDrops;

public Account() {
}
public void setAccountPrivate(boolean accountPrivate){
    this.accountPrivate = accountPrivate;
}


public List<RiverDrop> getReadRiverDrops(){
    return readRiverDrops;
}


public List<River> getRivers(){
    return rivers;
}


public void setRiverQuotaRemaining(int riverQuotaRemaining){
    this.riverQuotaRemaining = riverQuotaRemaining;
}


public String getAccountPath(){
    return accountPath;
}


public long getId(){
    return id;
}


public Set<Client> getClients(){
    return clients;
}


public List<AccountFollower> getFollowers(){
    return followers;
}


public boolean isActive(){
    return active;
}


public User getOwner(){
    return owner;
}


public void setClients(Set<Client> clients){
    this.clients = clients;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setOwner(User owner){
    this.owner = owner;
}


public List<AccountFollower> getFollowing(){
    return following;
}


public void setForms(List<Form> forms){
    this.forms = forms;
}


public Date getDateAdded(){
    return dateAdded;
}


public List<Bucket> getCollaboratingBuckets(){
    return collaboratingBuckets;
}


public List<Form> getForms(){
    return forms;
}


public List<BucketDrop> getReadBucketDrops(){
    return readBucketDrops;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 31).append(accountPath).toHashCode();
}


public boolean isAccountPrivate(){
    return accountPrivate;
}


public void setId(long id){
    this.id = id;
}


public int getRiverQuotaRemaining(){
    return riverQuotaRemaining;
}


public void setDateModified(Date dateModified){
    this.dateModified = dateModified;
}


public long getVersion(){
    return version;
}


public List<Bucket> getFollowingBuckets(){
    return followingBuckets;
}


public void setFollowingRivers(List<River> followingRivers){
    this.followingRivers = followingRivers;
}


public void setCollaboratingBuckets(List<Bucket> collaboratingBuckets){
    this.collaboratingBuckets = collaboratingBuckets;
}


public void setVersion(long version){
    this.version = version;
}


public void setFollowing(List<AccountFollower> following){
    this.following = following;
}


public void setAccountPath(String accountPath){
    this.accountPath = accountPath;
}


public void setBuckets(List<Bucket> buckets){
    this.buckets = buckets;
}


public void setReadRiverDrops(List<RiverDrop> readRiverDrops){
    this.readRiverDrops = readRiverDrops;
}


public void setReadBucketDrops(List<BucketDrop> readBucketDrops){
    this.readBucketDrops = readBucketDrops;
}


public List<Bucket> getBuckets(){
    return buckets;
}


public void setFollowingBuckets(List<Bucket> followingBuckets){
    this.followingBuckets = followingBuckets;
}


public void setActive(boolean active){
    this.active = active;
}


public void setCollaboratingRivers(List<River> collaboratingRivers){
    this.collaboratingRivers = collaboratingRivers;
}


@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (obj == this)
        return true;
    if (obj.getClass() != getClass())
        return false;
    Account other = (Account) obj;
    return new EqualsBuilder().append(accountPath, other.accountPath).isEquals();
}


public void setRivers(List<River> rivers){
    this.rivers = rivers;
}


public void setFollowers(List<AccountFollower> followers){
    this.followers = followers;
}


public Date getDateModified(){
    return dateModified;
}


public List<River> getCollaboratingRivers(){
    return collaboratingRivers;
}


public List<River> getFollowingRivers(){
    return followingRivers;
}


}