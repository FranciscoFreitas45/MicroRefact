package com.ushahidi.swiftriver.core.model;
 import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@DiscriminatorValue("bucket")
public class BucketActivity extends Activity{

@ManyToOne
@JoinColumn(name = "action_on_id")
 private  Bucket actionOnObj;


public Bucket getActionOnObj(){
    return actionOnObj;
}


public void setActionOnObj(Bucket actionOnObj){
    this.actionOnObj = actionOnObj;
}


}