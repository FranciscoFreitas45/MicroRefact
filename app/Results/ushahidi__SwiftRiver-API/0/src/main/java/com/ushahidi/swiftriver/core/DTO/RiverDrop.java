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
    this.channel = channel;
}


public void setTags(List<RiverDropTag> tags){
    this.tags = tags;
}


public void setForms(List<RiverDropForm> forms){
    this.forms = forms;
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


public void setDrop(Drop drop){
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
    return drop;
}


public void setComments(List<RiverDropComment> comments){
    this.comments = comments;
}


}