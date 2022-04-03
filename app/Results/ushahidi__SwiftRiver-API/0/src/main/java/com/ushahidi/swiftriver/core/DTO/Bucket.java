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
public class Bucket {

 private  long id;

 private  Account account;

 private  String name;

 private  String bucketNameCanonical;

 private  String description;

 private  boolean published;

 private  String defaultLayout;

 private  Date dateAdded;

 private  String publicToken;

 private  int dropCount;

 private  List<BucketCollaborator> collaborators;

 private  List<Account> followers;

 private  List<BucketComment> comments;


public void setName(String name){
    this.name = name;
}


public void setCollaborators(List<BucketCollaborator> collaborators){
    this.collaborators = collaborators;
}


public String getPublicToken(){
    return publicToken;
}


public String getName(){
    return name;
}


public void setDropCount(int dropCount){
    this.dropCount = dropCount;
}


public long getId(){
    return id;
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


public void setDefaultLayout(String defaultLayout){
    this.defaultLayout = defaultLayout;
}


public Date getDateAdded(){
    return dateAdded;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 31).append(account).append(bucketNameCanonical).toHashCode();
}


public Account getAccount(){
    return account;
}


public List<BucketComment> getComments(){
    return comments;
}


public void setId(long id){
    this.id = id;
}


public String getBucketNameCanonical(){
    return bucketNameCanonical;
}


public void setComments(List<BucketComment> comments){
    this.comments = comments;
}


public List<BucketCollaborator> getCollaborators(){
    return collaborators;
}


public String getDefaultLayout(){
    return defaultLayout;
}


public boolean isPublished(){
    return published;
}


public void setBucketNameCanonical(String bucketNameCanonical){
    this.bucketNameCanonical = bucketNameCanonical;
}


public void setPublished(boolean published){
    this.published = published;
}


public int getDropCount(){
    return dropCount;
}


public void setPublicToken(String publicToken){
    this.publicToken = publicToken;
}


@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (obj == this)
        return true;
    if (obj.getClass() != getClass())
        return false;
    Bucket other = (Bucket) obj;
    return new EqualsBuilder().append(account, other.account).append(bucketNameCanonical, other.bucketNameCanonical).isEquals();
}


public void setAccount(Account account){
    this.account = account;
}


public void setFollowers(List<Account> followers){
    this.followers = followers;
}


}