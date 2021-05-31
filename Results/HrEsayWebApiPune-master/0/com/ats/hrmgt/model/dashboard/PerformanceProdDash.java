import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PerformanceProdDash {

@Id
 private  String uniKey;

 private  double prodDays;

 private  double prodAmt;


public double getProdAmt(){
    return prodAmt;
}


public void setUniKey(String uniKey){
    this.uniKey = uniKey;
}


public void setProdAmt(double prodAmt){
    this.prodAmt = prodAmt;
}


public String getUniKey(){
    return uniKey;
}


public void setProdDays(double prodDays){
    this.prodDays = prodDays;
}


@Override
public String toString(){
    return "PerformanceProdDash [uniKey=" + uniKey + ", prodDays=" + prodDays + ", prodAmt=" + prodAmt + "]";
}


public double getProdDays(){
    return prodDays;
}


}