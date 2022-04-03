package com.ushahidi.swiftriver.core.DTO;
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
public class RiverDrop {

 private  long id;

 private  Drop drop;

 private  River river;

 private  Date datePublished;

 private  Channel channel;

 private  List<RiverDropLink> links;

 private  List<RiverDropPlace> places;

 private  List<RiverDropTag> tags;

 private  List<RiverDropComment> comments;

 private  List<RiverDropForm> forms;

 private long id2DXZ;

 private DropRequest droprequest = new DropRequestImpl();;

 private long idQKZH;

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