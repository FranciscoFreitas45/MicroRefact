package com.ushahidi.swiftriver.core.model;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.ushahidi.swiftriver.core.Request.FormRequest;
import com.ushahidi.swiftriver.core.Request.Impl.FormRequestImpl;
import com.ushahidi.swiftriver.core.DTO.Form;
@Entity
@DiscriminatorValue("form")
public class FormActivity extends Activity{

@Transient
 private  Form actionOnObj;

@Column(name = "idVMYL")
 private Long idVMYL;

@Transient
 private FormRequest formrequest = new FormRequestImpl();;


public Form getActionOnObj(){
  this.actionOnObj = formrequest.getActionOnObj(this.idVMYL);
return this.actionOnObj;
}}



public void setActionOnObj(Form actionOnObj){
this.idVMYL = actionOnObj.getActiononobj() ;
formrequest.setActionOnObj(actionOnObj,this.idVMYL);
 this.actionOnObj = actionOnObj;
}



}