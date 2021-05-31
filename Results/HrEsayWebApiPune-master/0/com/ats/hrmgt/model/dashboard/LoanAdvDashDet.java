import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class LoanAdvDashDet {

@Id
 private  String uniKey;

 private  String emp;

 private  String skipId;

 private  String skipTott;

 private  String advTot;


public void setSkipId(String skipId){
    this.skipId = skipId;
}


public void setAdvTot(String advTot){
    this.advTot = advTot;
}


public void setUniKey(String uniKey){
    this.uniKey = uniKey;
}


public void setEmp(String emp){
    this.emp = emp;
}


public String getUniKey(){
    return uniKey;
}


public String getSkipId(){
    return skipId;
}


public String getAdvTot(){
    return advTot;
}


public String getEmp(){
    return emp;
}


@Override
public String toString(){
    return "LoanAdvDashDet [uniKey=" + uniKey + ", emp=" + emp + ", skipId=" + skipId + ", skipTott=" + skipTott + ", advTot=" + advTot + "]";
}


public String getSkipTott(){
    return skipTott;
}


public void setSkipTott(String skipTott){
    this.skipTott = skipTott;
}


}