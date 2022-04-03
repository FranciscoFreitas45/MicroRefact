package es.us.isa.ideas.app.entities;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
@Access(AccessType.PROPERTY)
public class SocialNetworkConfiguration extends DomainEntity{

 private  String service;

 private  Actor actor;

 private  boolean publishNewPublicExperimentCreated;

 private  boolean publishNewPublicExperimentExecutionStarted;

 private  boolean publishNewExperimentExecutionFinished;

 private  boolean publishExistingExperimentMadePublic;

 private  boolean notifyWhenExperimentExecutionFinished;


public void setActor(Actor actor){
    this.actor = actor;
}


public boolean isPublishNewExperimentExecutionFinished(){
    return publishNewExperimentExecutionFinished;
}


public void setPublishNewPublicExperimentExecutionStarted(boolean publishNewPublicExperimentExecutionStarted){
    this.publishNewPublicExperimentExecutionStarted = publishNewPublicExperimentExecutionStarted;
}


public void setPublishExistingExperimentMadePublic(boolean publishExistingExperimentMadePublic){
    this.publishExistingExperimentMadePublic = publishExistingExperimentMadePublic;
}


public void setPublishNewExperimentExecutionFinished(boolean publishNewExperimentExecutionFinished){
    this.publishNewExperimentExecutionFinished = publishNewExperimentExecutionFinished;
}


public void setPublishNewPublicExperimentCreated(boolean publishNewPublicExperimentCreated){
    this.publishNewPublicExperimentCreated = publishNewPublicExperimentCreated;
}


public String getService(){
    return service;
}


public void setService(String service){
    this.service = service;
}


public void setNotifyWhenExperimentExecutionFinished(boolean notifyWhenExperimentExecutionFinished){
    this.notifyWhenExperimentExecutionFinished = notifyWhenExperimentExecutionFinished;
}


public boolean isPublishExistingExperimentMadePublic(){
    return publishExistingExperimentMadePublic;
}


public boolean isPublishNewPublicExperimentExecutionStarted(){
    return publishNewPublicExperimentExecutionStarted;
}


public boolean isPublishNewPublicExperimentCreated(){
    return publishNewPublicExperimentCreated;
}


public boolean isNotifyWhenExperimentExecutionFinished(){
    return notifyWhenExperimentExecutionFinished;
}


@ManyToOne(optional = false)
public Actor getActor(){
    return actor;
}


}