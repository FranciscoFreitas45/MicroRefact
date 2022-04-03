package es.us.isa.ideas.app.DTO;
 import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;
public class Workspace extends DomainEntityimplements Serializable{

 private  Collection<Tag> workspaceTags;

 private  Workspace origin;

 private  Researcher owner;

 private  String description;

 private  Integer downloads;

 private  Integer launches;

 private  Date lastMod;

 private  String name;

 private  Integer wsVersion;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public Workspace() {
}
public String getName(){
    return name;
}


@ManyToMany(fetch = FetchType.LAZY)
@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE })
@JoinTable(name = "WorkspaceTags", joinColumns = @JoinColumn(name = "id_ws", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "id_tag", nullable = false, updatable = false))
public Collection<Tag> getWorkspaceTags(){
    return this.workspaceTags;
}


@ManyToOne(optional = true)
public Workspace getOrigin(){
    return origin;
}


@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
public Date getLastMod(){
    return lastMod;
}


@NotNull
@Valid
@ManyToOne(optional = false, cascade = CascadeType.REFRESH)
public Researcher getOwner(){
    return owner;
}


public String getDescription(){
    return description;
}


public Integer getLaunches(){
    return launches;
}


public Integer getWsVersion(){
    return wsVersion;
}


public Integer getDownloads(){
    return downloads;
}


public void setDescription(String description){
    this.description = description;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDescription"))

.queryParam("description",description)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDownloads(Integer downloads){
    this.downloads = downloads;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDownloads"))

.queryParam("downloads",downloads)
;
restTemplate.put(builder.toUriString(),null);
}


}