import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PayRewardDedDash {

@Id
 private  String uniKey;

 private  int empCount;

 private  String tot;


public void setUniKey(String uniKey){
    this.uniKey = uniKey;
}


public void setTot(String tot){
    this.tot = tot;
}


public int getEmpCount(){
    return empCount;
}


public String getUniKey(){
    return uniKey;
}


public void setEmpCount(int empCount){
    this.empCount = empCount;
}


@Override
public String toString(){
    return "PayRewardDedDash [uniKey=" + uniKey + ", empCount=" + empCount + ", tot=" + tot + "]";
}


public String getTot(){
    return tot;
}


}