import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AdvaceAmtGraph {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  String id;

 private  float advTot;

 private  String month;


public void setMonth(String month){
    this.month = month;
}


public void setAdvTot(float advTot){
    this.advTot = advTot;
}


public void setId(String id){
    this.id = id;
}


public float getAdvTot(){
    return advTot;
}


public String getId(){
    return id;
}


@Override
public String toString(){
    return "AdvaceAmtGraph [id=" + id + ", advTot=" + advTot + ", month=" + month + "]";
}


public String getMonth(){
    return month;
}


}