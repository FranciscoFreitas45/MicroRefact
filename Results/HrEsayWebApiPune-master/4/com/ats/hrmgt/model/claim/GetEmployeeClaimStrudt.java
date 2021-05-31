import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetEmployeeClaimStrudt {

@Id
 private  int clmStructDetailsId;

 private  int clmTypeId;

 private  float clmAmt;

 private  int clmStructHeadId;

 private  String claimStructName;

 private  String claimTypeTitle;

 private  String claimTypeTitleShort;


public void setClaimTypeTitleShort(String claimTypeTitleShort){
    this.claimTypeTitleShort = claimTypeTitleShort;
}


public void setClmTypeId(int clmTypeId){
    this.clmTypeId = clmTypeId;
}


public void setClmAmt(float clmAmt){
    this.clmAmt = clmAmt;
}


public void setClaimTypeTitle(String claimTypeTitle){
    this.claimTypeTitle = claimTypeTitle;
}


public float getClmAmt(){
    return clmAmt;
}


public void setClmStructHeadId(int clmStructHeadId){
    this.clmStructHeadId = clmStructHeadId;
}


public String getClaimStructName(){
    return claimStructName;
}


public int getClmStructDetailsId(){
    return clmStructDetailsId;
}


public int getClmStructHeadId(){
    return clmStructHeadId;
}


public void setClaimStructName(String claimStructName){
    this.claimStructName = claimStructName;
}


public String getClaimTypeTitle(){
    return claimTypeTitle;
}


public void setClmStructDetailsId(int clmStructDetailsId){
    this.clmStructDetailsId = clmStructDetailsId;
}


public String getClaimTypeTitleShort(){
    return claimTypeTitleShort;
}


@Override
public String toString(){
    return "GetEmployeeClaimStrudt [clmStructDetailsId=" + clmStructDetailsId + ", clmTypeId=" + clmTypeId + ", clmAmt=" + clmAmt + ", clmStructHeadId=" + clmStructHeadId + ", claimStructName=" + claimStructName + ", claimTypeTitle=" + claimTypeTitle + ", claimTypeTitleShort=" + claimTypeTitleShort + "]";
}


public int getClmTypeId(){
    return clmTypeId;
}


}