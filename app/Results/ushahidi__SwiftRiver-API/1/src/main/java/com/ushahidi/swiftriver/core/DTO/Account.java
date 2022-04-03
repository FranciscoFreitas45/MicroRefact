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

 private long idHR3O;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

public Account() {
}
public List<RiverDrop> getReadRiverDrops(){
    return readRiverDrops;
}


public List<River> getRivers(){
    return rivers;
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


public User getOwner(){
    return owner;
}


public List<AccountFollower> getFollowing(){
    return following;
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


public int getRiverQuotaRemaining(){
    return riverQuotaRemaining;
}


public long getVersion(){
    return version;
}


public List<Bucket> getFollowingBuckets(){
    return followingBuckets;
}


public List<Bucket> getBuckets(){
    return buckets;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/equals"))

.queryParam("obj",obj)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}