package com.ushahidi.swiftriver.core.model;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.ushahidi.swiftriver.core.Request.IdentityRequest;
import com.ushahidi.swiftriver.core.Request.Impl.IdentityRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Identity;
import com.ushahidi.swiftriver.core.Request.LinkRequest;
import com.ushahidi.swiftriver.core.Request.Impl.LinkRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Link;
import com.ushahidi.swiftriver.core.Request.MediaRequest;
import com.ushahidi.swiftriver.core.Request.Impl.MediaRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Media;
import com.ushahidi.swiftriver.core.Request.TagRequest;
import com.ushahidi.swiftriver.core.Request.Impl.TagRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Tag;
import com.ushahidi.swiftriver.core.Request.PlaceRequest;
import com.ushahidi.swiftriver.core.Request.Impl.PlaceRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Place;
@Entity
@Table(name = "droplets")
public class Drop {

@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "Seq")
@TableGenerator(name = "Seq", table = "seq", pkColumnName = "name", valueColumnName = "id", pkColumnValue = "droplets")
 private  long id;

@Column(name = "droplet_hash", nullable = false)
 private  String hash;

@Column(name = "channel", nullable = false)
 private  String channel;

@Lob
@Column(name = "droplet_title", nullable = false)
 private  String title;

@Lob
@Column(name = "droplet_content", nullable = false)
 private  String content;

@Transient
 private  Identity identity;

@Column(name = "droplet_date_pub", nullable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date datePublished;

@Transient
 private  Link originalUrl;

@Column(name = "droplet_orig_id", nullable = false)
 private  String originalId;

@Column(name = "comment_count")
 private  int commentCount;

@Column(name = "droplet_type", nullable = false)
 private  String type;

@Column(name = "droplet_locale")
 private  String locale;

@Transient
 private  Media image;

@Column(name = "droplet_date_add")
@Temporal(TemporalType.TIMESTAMP)
 private  Date dateAdded;

@Transient
 private  List<Tag> tags;

@Transient
 private  List<Place> places;

@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name = "droplets_links", joinColumns = @JoinColumn(name = "droplet_id"), inverseJoinColumns = @JoinColumn(name = "link_id"))
 private  List<Link> links;

@OneToMany(cascade = CascadeType.ALL)
@JoinTable(name = "droplets_media", joinColumns = @JoinColumn(name = "droplet_id"), inverseJoinColumns = @JoinColumn(name = "media_id"))
 private  List<Media> media;

@Transient
 private  List<Bucket> buckets;

@Transient
 private  List<Long> riverIds;

@Transient
 private  Boolean read;

@SuppressWarnings("rawtypes")
@Transient
 private  List<DropForm> forms;

@Transient
 private  List<Long> markAsRead;

@Transient
 private  List<Long> bucketIds;

@Transient
 private  List<Long> channelIds;

@Transient
 private  Long trackingId;

@Column(name = "idYKYP")
 private long idYKYP;

@Transient
 private IdentityRequest identityrequest = new IdentityRequestImpl();;

@Column(name = "idLCK8")
 private long idLCK8;

@Transient
 private LinkRequest linkrequest = new LinkRequestImpl();;

@Column(name = "idBTYE")
 private long idBTYE;

@Transient
 private MediaRequest mediarequest = new MediaRequestImpl();;

@Transient
 private TagRequest tagrequest = new TagRequestImpl();;

@Transient
 private PlaceRequest placerequest = new PlaceRequestImpl();;

public Drop() {
}
public List<Long> getChannelIds(){
    return channelIds;
}


public String getHash(){
    return hash;
}


public Boolean getRead(){
    return read;
}


public List<Tag> getTags(){
  this.tags = tagrequest.getTags(this.id);
return this.tags;
}}



public void setChannel(String channel){
    this.channel = channel;
}


public void setDateAdded(Date dateAdded){
    this.dateAdded = dateAdded;
}


@SuppressWarnings("rawtypes")
public void setForms(List<DropForm> forms){
    this.forms = forms;
}


public String getTitle(){
    return title;
}


public String getChannel(){
    return channel;
}


@SuppressWarnings("rawtypes")
public List<DropForm> getForms(){
    return forms;
}


public List<Place> getPlaces(){
  this.places = placerequest.getPlaces(this.id);
return this.places;
}}



public void setDatePublished(Date dropletDatePub){
    this.datePublished = dropletDatePub;
}


public void setId(long id){
    this.id = id;
}


public Date getDatePublished(){
    return datePublished;
}


public Link getOriginalUrl(){
  this.originalUrl = linkrequest.getOriginalUrl(this.idLCK8);
return this.originalUrl;
}}



public void setTrackingId(Long trackingId){
    this.trackingId = trackingId;
}


public void setTitle(String title){
    this.title = title;
}


public void setBuckets(List<Bucket> buckets){
    this.buckets = buckets;
}


public List<Long> getRiverIds(){
    return riverIds;
}


public List<Bucket> getBuckets(){
    return buckets;
}


public int getCommentCount(){
    return commentCount;
}


public String getType(){
    return type;
}


public void setLinks(List<Link> links){
    this.links = links;
}


public String getOriginalId(){
    return originalId;
}


public void setIdentity(Identity identity){
this.idYKYP = identity.getIdentity() ;
identityrequest.setIdentity(identity,this.idYKYP);
 this.identity = identity;
}



public Media getImage(){
  this.image = mediarequest.getImage(this.idBTYE);
return this.image;
}}



public void setImage(Media image){
this.idBTYE = image.getImage() ;
mediarequest.setImage(image,this.idBTYE);
 this.image = image;
}



public void setContent(String content){
    this.content = content;
}


public List<Link> getLinks(){
    return links;
}


public void setPlaces(List<Place> places){
placerequest.setPlaces(places,this.id);
 this.places = places;
}



public Long getTrackingId(){
    return trackingId;
}


public String getContent(){
    return content;
}


public long getId(){
    return id;
}


public void setTags(List<Tag> tags){
tagrequest.setTags(tags,this.id);
 this.tags = tags;
}



public List<Long> getMarkAsRead(){
    return markAsRead;
}


public void setOriginalId(String originalId){
    this.originalId = originalId;
}


public Date getDateAdded(){
    return dateAdded;
}


public List<Long> getBucketIds(){
    return bucketIds;
}


public void setRead(Boolean read){
    this.read = read;
}


public void setHash(String hash){
    this.hash = hash;
}


public List<Media> getMedia(){
    return media;
}


public void setRiverIds(List<Long> riverIds){
    this.riverIds = riverIds;
}


public void setMarkAsRead(List<Long> markAsRead){
    this.markAsRead = markAsRead;
}


public void setCommentCount(int commentCount){
    this.commentCount = commentCount;
}


public void setType(String type){
    this.type = type;
}


public void setOriginalUrl(Link originalUrl){
this.idLCK8 = originalUrl.getOriginalurl() ;
linkrequest.setOriginalUrl(originalUrl,this.idLCK8);
 this.originalUrl = originalUrl;
}



public void setBucketIds(List<Long> bucketIds){
    this.bucketIds = bucketIds;
}


public Identity getIdentity(){
  this.identity = identityrequest.getIdentity(this.idYKYP);
return this.identity;
}}



public void setLocal(String locale){
    this.locale = locale;
}


public void setMedia(List<Media> media){
    this.media = media;
}


public void setChannelIds(List<Long> channelIds){
    this.channelIds = channelIds;
}


public String getLocale(){
    return locale;
}


}