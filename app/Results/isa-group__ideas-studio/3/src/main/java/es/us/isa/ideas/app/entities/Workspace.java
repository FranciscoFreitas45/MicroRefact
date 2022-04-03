package es.us.isa.ideas.app.entities;
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
@Entity
@Access(AccessType.PROPERTY)
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

public Workspace() {
}
public void setName(String name){
    this.name = name;
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


public void setOrigin(Workspace origin){
    this.origin = origin;
}


@javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
public Date getLastMod(){
    return lastMod;
}


public void setDescription(String description){
    this.description = description;
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


public void setOwner(Researcher owner){
    this.owner = owner;
}


public Integer getLaunches(){
    return launches;
}


public void setLaunches(Integer launches){
    this.launches = launches;
}


public void setDownloads(Integer downloads){
    this.downloads = downloads;
}


public Integer getWsVersion(){
    return wsVersion;
}


public void setLastMod(Date lastMod){
    this.lastMod = lastMod;
}


public Integer getDownloads(){
    return downloads;
}


public void setWorkspaceTags(Collection<Tag> tags){
    this.workspaceTags = tags;
}


public void setWsVersion(Integer wsVersion){
    this.wsVersion = wsVersion;
}


}