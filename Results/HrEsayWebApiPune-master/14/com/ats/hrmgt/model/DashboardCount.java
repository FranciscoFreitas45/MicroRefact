import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class DashboardCount {

@Id
 private  int pendingRequest;

 private  int myLeave;

 private  int info;

 private  int isAuthorized;

 private  int pendingClaim;

 private  int myClaim;

 private  int infoClaim;

 private  int isAuthorizedClaim;


public void setPendingRequest(int pendingRequest){
    this.pendingRequest = pendingRequest;
}


public void setPendingClaim(int pendingClaim){
    this.pendingClaim = pendingClaim;
}


public int getIsAuthorizedClaim(){
    return isAuthorizedClaim;
}


public void setIsAuthorizedClaim(int isAuthorizedClaim){
    this.isAuthorizedClaim = isAuthorizedClaim;
}


public int getInfo(){
    return info;
}


public int getPendingClaim(){
    return pendingClaim;
}


public int getPendingRequest(){
    return pendingRequest;
}


public int getMyClaim(){
    return myClaim;
}


public void setMyClaim(int myClaim){
    this.myClaim = myClaim;
}


public int getInfoClaim(){
    return infoClaim;
}


public void setInfoClaim(int infoClaim){
    this.infoClaim = infoClaim;
}


public int getIsAuthorized(){
    return isAuthorized;
}


public void setIsAuthorized(int isAuthorized){
    this.isAuthorized = isAuthorized;
}


public void setMyLeave(int myLeave){
    this.myLeave = myLeave;
}


public int getMyLeave(){
    return myLeave;
}


@Override
public String toString(){
    return "DashboardCount [pendingRequest=" + pendingRequest + ", myLeave=" + myLeave + ", info=" + info + ", isAuthorized=" + isAuthorized + ", pendingClaim=" + pendingClaim + ", myClaim=" + myClaim + ", infoClaim=" + infoClaim + ", isAuthorizedClaim=" + isAuthorizedClaim + "]";
}


public void setInfo(int info){
    this.info = info;
}


}