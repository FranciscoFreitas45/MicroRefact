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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.ushahidi.swiftriver.core.Request.DropRequest;
import com.ushahidi.swiftriver.core.Request.Impl.DropRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Drop;
import com.ushahidi.swiftriver.core.Request.ChannelRequest;
import com.ushahidi.swiftriver.core.Request.Impl.ChannelRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Channel;
@Entity
@Table(name = "rivers_droplets")
public class RiverDrop {

@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "Seq")
@TableGenerator(name = "Seq", table = "seq", pkColumnName = "name", valueColumnName = "id", pkColumnValue = "rivers_droplets")
 private  long id;

@Transient
 private  Drop drop;

@ManyToOne
 private  River river;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "droplet_date_pub")
 private  Date datePublished;

@Transient
 private  Channel channel;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "riverDrop")
 private  List<RiverDropLink> links;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "riverDrop")
 private  List<RiverDropPlace> places;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "riverDrop")
 private  List<RiverDropTag> tags;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "riverDrop")
 private  List<RiverDropComment> comments;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "drop")
 private  List<RiverDropForm> forms;

@Column(name = "id2DXZ")
 private long id2DXZ;

@Transient
 private DropRequest droprequest = new DropRequestImpl();;

@Column(name = "idQKZH")
 private long idQKZH;

@Transient
 private ChannelRequest channelrequest = new ChannelRequestImpl();;


public River getRiver(){
    return river;
}


public List<RiverDropLink> getLinks(){
    return links;
}


public void setPlaces(List<RiverDropPlace> places){
    this.places = places;
}


public void setRiver(River river){
    this.river = river;
}


public long getId(){
    return id;
}


public List<RiverDropTag> getTags(){
    return tags;
}


public void setChannel(Channel channel){
this.idQKZH = channel.getChannel() ;
channelrequest.setChannel(channel,this.idQKZH);
 this.channel = channel;
}



public void setTags(List<RiverDropTag> tags){
    this.tags = tags;
}


public void setForms(List<RiverDropForm> forms){
    this.forms = forms;
}


public Channel getChannel(){
  this.channel = channelrequest.getChannel(this.idQKZH);
return this.channel;
}}



public List<RiverDropForm> getForms(){
    return forms;
}


public List<RiverDropPlace> getPlaces(){
    return places;
}


public void setDrop(Drop drop){
this.id2DXZ = drop.getDrop() ;
droprequest.setDrop(drop,this.id2DXZ);
 this.drop = drop;
}



public void setDatePublished(Date datePublished){
    this.datePublished = datePublished;
}


public void setLinks(List<RiverDropLink> links){
    this.links = links;
}


public List<RiverDropComment> getComments(){
    return comments;
}


public void setId(long id){
    this.id = id;
}


public Date getDatePublished(){
    return datePublished;
}


public Drop getDrop(){
  this.drop = droprequest.getDrop(this.id2DXZ);
return this.drop;
}}



public void setComments(List<RiverDropComment> comments){
    this.comments = comments;
}


}