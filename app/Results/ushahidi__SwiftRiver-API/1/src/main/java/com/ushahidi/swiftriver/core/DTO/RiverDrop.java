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
    return channel;
}


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
    return drop;
}


}