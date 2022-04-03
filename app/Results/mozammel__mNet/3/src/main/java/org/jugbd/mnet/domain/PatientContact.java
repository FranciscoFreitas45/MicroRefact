package org.jugbd.mnet.domain;
 import org.jugbd.mnet.domain.enums.Relationship;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import java.io.Serializable;
@Embeddable
public class PatientContact implements Serializable{

@Size(max = 100)
@Column(length = 100)
 private  String contactPerson;

@Column(length = 10)
@Enumerated(EnumType.STRING)
 private  Relationship relationship;

@Size(max = 100)
 private  String comments;

@Size(max = 32)
@Column(length = 32)
 private  String emergencyContactNumber;


public void setContactPerson(String contactPerson){
    this.contactPerson = contactPerson;
}


public String getEmergencyContactNumber(){
    return emergencyContactNumber;
}


public String getContactPerson(){
    return contactPerson;
}


public String getComments(){
    return comments;
}


public Relationship getRelationship(){
    return relationship;
}


public void setRelationship(Relationship relationship){
    this.relationship = relationship;
}


public void setEmergencyContactNumber(String emergencyContactNumber){
    this.emergencyContactNumber = emergencyContactNumber;
}


public void setComments(String comments){
    this.comments = comments;
}


}