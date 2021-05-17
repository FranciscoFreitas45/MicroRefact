import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class BonusParam {

@Id
 private  String uid;

 private  String totalBasicCal;

 private  String totalAllowance;


public String getUid(){
    return uid;
}


public void setTotalAllowance(String totalAllowance){
    this.totalAllowance = totalAllowance;
}


public String getTotalAllowance(){
    return totalAllowance;
}


public String getTotalBasicCal(){
    return totalBasicCal;
}


@Override
public String toString(){
    return "BonusParam [uid=" + uid + ", totalBasicCal=" + totalBasicCal + ", totalAllowance=" + totalAllowance + "]";
}


public void setUid(String uid){
    this.uid = uid;
}


public void setTotalBasicCal(String totalBasicCal){
    this.totalBasicCal = totalBasicCal;
}


}