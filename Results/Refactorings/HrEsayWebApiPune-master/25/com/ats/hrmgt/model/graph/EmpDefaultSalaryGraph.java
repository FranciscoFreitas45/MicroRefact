import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EmpDefaultSalaryGraph {

@Id
 private  String id;

 private  double defaultSalAmt;

 private  int month;

 private  int year;


public void setMonth(int month){
    this.month = month;
}


public int getYear(){
    return year;
}


public void setDefaultSalAmt(double defaultSalAmt){
    this.defaultSalAmt = defaultSalAmt;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


@Override
public String toString(){
    return "EmpDefaultSalaryGraph [id=" + id + ", defaultSalAmt=" + defaultSalAmt + ", month=" + month + ", year=" + year + "]";
}


public int getMonth(){
    return month;
}


public void setYear(int year){
    this.year = year;
}


public double getDefaultSalAmt(){
    return defaultSalAmt;
}


}