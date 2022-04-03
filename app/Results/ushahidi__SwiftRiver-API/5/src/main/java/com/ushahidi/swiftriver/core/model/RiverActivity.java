package com.ushahidi.swiftriver.core.model;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.ushahidi.swiftriver.core.Request.RiverRequest;
import com.ushahidi.swiftriver.core.Request.Impl.RiverRequestImpl;
import com.ushahidi.swiftriver.core.DTO.River;
@Entity
@DiscriminatorValue("river")
public class RiverActivity extends Activity{

@Transient
 private  River actionOnObj;

@Column(name = "id37M8")
 private Long id37M8;

@Transient
 private RiverRequest riverrequest = new RiverRequestImpl();;


public River getActionOnObj(){
  this.actionOnObj = riverrequest.getActionOnObj(this.id37M8);
return this.actionOnObj;
}}



public void setActionOnObj(River actionOnObj){
this.id37M8 = actionOnObj.getActiononobj() ;
riverrequest.setActionOnObj(actionOnObj,this.id37M8);
 this.actionOnObj = actionOnObj;
}



}