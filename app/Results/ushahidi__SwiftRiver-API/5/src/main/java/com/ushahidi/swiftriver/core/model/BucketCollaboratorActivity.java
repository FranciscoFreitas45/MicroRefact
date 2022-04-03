package com.ushahidi.swiftriver.core.model;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@DiscriminatorValue("bucket_collaborator")
public class BucketCollaboratorActivity extends Activity{

@ManyToOne
@JoinColumn(name = "action_on_id")
 private  BucketCollaborator actionOnObj;


public BucketCollaborator getActionOnObj(){
    return actionOnObj;
}


public void setActionOnObj(BucketCollaborator actionOnObj){
    this.actionOnObj = actionOnObj;
}


}