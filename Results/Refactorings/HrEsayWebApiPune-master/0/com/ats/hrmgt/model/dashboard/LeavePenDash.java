import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LeavePenDash {

@Id
 private  String uniKey;

 private  int newApp;

 private  int finalPending;

 private  int ohPending;

 private  int newClaimApp;

 private  int finalClaimApp;


public void setUniKey(String uniKey){
    this.uniKey = uniKey;
}


public void setNewApp(int newApp){
    this.newApp = newApp;
}


public int getNewApp(){
    return newApp;
}


public void setOhPending(int ohPending){
    this.ohPending = ohPending;
}


public int getOhPending(){
    return ohPending;
}


public void setFinalPending(int finalPending){
    this.finalPending = finalPending;
}


public void setFinalClaimApp(int finalClaimApp){
    this.finalClaimApp = finalClaimApp;
}


public String getUniKey(){
    return uniKey;
}


public void setNewClaimApp(int newClaimApp){
    this.newClaimApp = newClaimApp;
}


@Override
public String toString(){
    return "LeavePenDash [uniKey=" + uniKey + ", newApp=" + newApp + ", finalPending=" + finalPending + ", ohPending=" + ohPending + ", newClaimApp=" + newClaimApp + ", finalClaimApp=" + finalClaimApp + "]";
}


public int getFinalPending(){
    return finalPending;
}


public int getNewClaimApp(){
    return newClaimApp;
}


public int getFinalClaimApp(){
    return finalClaimApp;
}


}