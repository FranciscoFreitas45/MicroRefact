package com.ushahidi.swiftriver.core.model;
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
import com.ushahidi.swiftriver.core.Request.UserRequest;
import com.ushahidi.swiftriver.core.Request.Impl.UserRequestImpl;
import com.ushahidi.swiftriver.core.DTO.User;
import com.ushahidi.swiftriver.core.Request.RiverRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverRequestImpl;
import com.ushahidi.swiftriver.core.DTO.River;
import com.ushahidi.swiftriver.core.Request.BucketRequest;
import com.ushahidi.swiftriver.core.Request.Impl.BucketRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Bucket;
import com.ushahidi.swiftriver.core.Request.FormRequest;
import com.ushahidi.swiftriver.core.Request.Impl.FormRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Form;
import com.ushahidi.swiftriver.core.Request.ClientRequest;
import com.ushahidi.swiftriver.core.Request.Impl.ClientRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Client;
import com.ushahidi.swiftriver.core.Request.RiverDropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverDropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.RiverDrop;
import com.ushahidi.swiftriver.core.Request.BucketDropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.BucketDropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.BucketDrop;
@Entity
@Table(name = "accounts")
public class Account {

@Id
@GeneratedValue
 private  long id;

@Column(name = "account_path")
 private  String accountPath;

@Column(name = "account_private")
 private  boolean accountPrivate;

@Column(name = "account_active")
 private  boolean active;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "account_date_add")
 private  Date dateAdded;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "account_date_modified")
 private  Date dateModified;

@Column(name = "river_quota_remaining")
 private  int riverQuotaRemaining;

@Transient
 private  User owner;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
 private  List<AccountFollower> followers;

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "follower_id")
 private  List<AccountFollower> following;

@Transient
 private  List<River> rivers;

@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name = "river_collaborators", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "river_id"))
 private  List<River> collaboratingRivers;

@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name = "river_followers", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "river_id"))
 private  List<River> followingRivers;

@Transient
 private  List<Bucket> buckets;

@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name = "bucket_followers", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "bucket_id"))
 private  List<Bucket> followingBuckets;

@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name = "bucket_collaborators", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "bucket_id"))
 private  List<Bucket> collaboratingBuckets;

@Transient
 private  List<Form> forms;

@Transient
 private  Set<Client> clients;

@Version
 private  long version;

@Transient
 private  List<RiverDrop> readRiverDrops;

@Transient
 private  List<BucketDrop> readBucketDrops;

@Column(name = "idHR3O")
 private long idHR3O;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Transient
 private RiverRequest riverrequest = new RiverRequestImpl();;

@Transient
 private BucketRequest bucketrequest = new BucketRequestImpl();;

@Transient
 private FormRequest formrequest = new FormRequestImpl();;

@Transient
 private ClientRequest clientrequest = new ClientRequestImpl();;

@Transient
 private RiverDropRequest riverdroprequest = new RiverDropRequestImpl();;

@Transient
 private BucketDropRequest bucketdroprequest = new BucketDropRequestImpl();;

public Account() {
}
public void setAccountPrivate(boolean accountPrivate){
    this.accountPrivate = accountPrivate;
}


public List<RiverDrop> getReadRiverDrops(){
  this.readRiverDrops = riverdroprequest.getReadRiverDrops(this.id);
return this.readRiverDrops;
}}



public List<River> getRivers(){
  this.rivers = riverrequest.getRivers(this.id);
return this.rivers;
}}



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
  this.clients = clientrequest.getClients(this.id);
return this.clients;
}}



public List<AccountFollower> getFollowers(){
    return followers;
}


public boolean isActive(){
    return active;
}


public User getOwner(){
  this.owner = userrequest.getOwner(this.idHR3O);
return this.owner;
}}



public void setClients(Set<Client> clients){
clientrequest.setClients(clients,this.id);
 this.clients = clients;
}



public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


public void setOwner(User owner){
this.idHR3O = owner.getOwner() ;
userrequest.setOwner(owner,this.idHR3O);
 this.owner = owner;
}



public List<AccountFollower> getFollowing(){
    return following;
}


public void setForms(List<Form> forms){
formrequest.setForms(forms,this.id);
 this.forms = forms;
}



public Date getDateAdded(){
    return dateAdded;
}


public List<Bucket> getCollaboratingBuckets(){
  this.buckets = bucketrequest.getCollaboratingBuckets(this.id);
return this.buckets;
}}



public List<Form> getForms(){
  this.forms = formrequest.getForms(this.id);
return this.forms;
}}



public List<BucketDrop> getReadBucketDrops(){
  this.readBucketDrops = bucketdroprequest.getReadBucketDrops(this.id);
return this.readBucketDrops;
}}



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
  this.buckets = bucketrequest.getFollowingBuckets(this.id);
return this.buckets;
}}



public void setFollowingRivers(List<River> followingRivers){
riverrequest.setFollowingRivers(followingRivers,this.id);
 this.followingRivers = followingRivers;
}



public void setCollaboratingBuckets(List<Bucket> collaboratingBuckets){
bucketrequest.setCollaboratingBuckets(collaboratingBuckets,this.id);
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
bucketrequest.setBuckets(buckets,this.id);
 this.buckets = buckets;
}



public void setReadRiverDrops(List<RiverDrop> readRiverDrops){
riverdroprequest.setReadRiverDrops(readRiverDrops,this.id);
 this.readRiverDrops = readRiverDrops;
}



public void setReadBucketDrops(List<BucketDrop> readBucketDrops){
bucketdroprequest.setReadBucketDrops(readBucketDrops,this.id);
 this.readBucketDrops = readBucketDrops;
}



public List<Bucket> getBuckets(){
  this.buckets = bucketrequest.getBuckets(this.id);
return this.buckets;
}}



public void setFollowingBuckets(List<Bucket> followingBuckets){
bucketrequest.setFollowingBuckets(followingBuckets,this.id);
 this.followingBuckets = followingBuckets;
}



public void setActive(boolean active){
    this.active = active;
}


public void setCollaboratingRivers(List<River> collaboratingRivers){
riverrequest.setCollaboratingRivers(collaboratingRivers,this.id);
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
riverrequest.setRivers(rivers,this.id);
 this.rivers = rivers;
}



public void setFollowers(List<AccountFollower> followers){
    this.followers = followers;
}


public Date getDateModified(){
    return dateModified;
}


public List<River> getCollaboratingRivers(){
  this.rivers = riverrequest.getCollaboratingRivers(this.id);
return this.rivers;
}}



public List<River> getFollowingRivers(){
  this.rivers = riverrequest.getFollowingRivers(this.id);
return this.rivers;
}}



}