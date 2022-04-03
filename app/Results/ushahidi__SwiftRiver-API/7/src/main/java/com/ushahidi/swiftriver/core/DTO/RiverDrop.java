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

 private long idQKZH;


public River getRiver(){
    return river;
}


public List<RiverDropLink> getLinks(){
    return links;
}


public long getId(){
    return id;
}


public List<RiverDropTag> getTags(){
    return tags;
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


public List<RiverDropComment> getComments(){
    return comments;
}


public Date getDatePublished(){
    return datePublished;
}


public Drop getDrop(){
  this.drop = droprequest.getDrop(this.id2DXZ);
return this.drop;
}}



}