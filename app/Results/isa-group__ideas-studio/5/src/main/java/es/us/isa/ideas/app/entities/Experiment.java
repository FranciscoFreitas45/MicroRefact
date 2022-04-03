package es.us.isa.ideas.app.entities;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
public class Experiment extends DomainEntity{

 private  String experimentId;

 private  String experimentName;

 private  String description;

 private  boolean publicExperiment;

 private  Researcher owner;


@NotBlank
public String getExperimentName(){
    return experimentName;
}


public void setExperimentId(String experimentId){
    this.experimentId = experimentId;
}


public boolean getPublicExperiment(){
    return publicExperiment;
}


@NotBlank
public String getExperimentId(){
    return experimentId;
}


public void setDescription(String description){
    this.description = description;
}


@NotNull
@Valid
@ManyToOne(optional = false)
public Researcher getOwner(){
    return owner;
}


@NotNull
public String getDescription(){
    return description;
}


public void setPublicExperiment(boolean publicExperiment){
    this.publicExperiment = publicExperiment;
}


public void setExperimentName(String experimentName){
    this.experimentName = experimentName;
}


public void setOwner(Researcher owner){
    this.owner = owner;
}


}