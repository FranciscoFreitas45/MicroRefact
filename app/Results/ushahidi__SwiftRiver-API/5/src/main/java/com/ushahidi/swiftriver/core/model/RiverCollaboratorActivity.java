package com.ushahidi.swiftriver.core.model;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.ushahidi.swiftriver.core.Request.RiverCollaboratorRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverCollaboratorRequestImpl;
import com.ushahidi.swiftriver.core.DTO.RiverCollaborator;
@Entity
@DiscriminatorValue("river_collaborator")
public class RiverCollaboratorActivity extends Activity{

@Transient
 private  RiverCollaborator actionOnObj;

@Column(name = "idD9W3")
 private Long idD9W3;

@Transient
 private RiverCollaboratorRequest rivercollaboratorrequest = new RiverCollaboratorRequestImpl();;


public RiverCollaborator getActionOnObj(){
  this.actionOnObj = rivercollaboratorrequest.getActionOnObj(this.idD9W3);
return this.actionOnObj;
}}



public void setActionOnObj(RiverCollaborator actionOnObj){
this.idD9W3 = actionOnObj.getActiononobj() ;
rivercollaboratorrequest.setActionOnObj(actionOnObj,this.idD9W3);
 this.actionOnObj = actionOnObj;
}



}