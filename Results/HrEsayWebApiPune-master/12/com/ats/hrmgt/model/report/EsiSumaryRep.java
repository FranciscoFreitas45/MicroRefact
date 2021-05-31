import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EsiSumaryRep {

@Id
 private  String keyNew;

 private  int noEmp;

 private  double empContribution;

 private  double emperContribution;

 private  double totalContribution;

 private  int calcMonth;

 private  int calcYear;


public double getEmpContribution(){
    return empContribution;
}


public String getKeyNew(){
    return keyNew;
}


public void setCalcMonth(int calcMonth){
    this.calcMonth = calcMonth;
}


public void setNoEmp(int noEmp){
    this.noEmp = noEmp;
}


public void setKeyNew(String keyNew){
    this.keyNew = keyNew;
}


public double getTotalContribution(){
    return totalContribution;
}


public void setEmpContribution(double empContribution){
    this.empContribution = empContribution;
}


public int getCalcMonth(){
    return calcMonth;
}


public int getNoEmp(){
    return noEmp;
}


public void setEmperContribution(double emperContribution){
    this.emperContribution = emperContribution;
}


public double getEmperContribution(){
    return emperContribution;
}


@Override
public String toString(){
    return "EsiSumaryRep [keyNew=" + keyNew + ", noEmp=" + noEmp + ", empContribution=" + empContribution + ", emperContribution=" + emperContribution + ", totalContribution=" + totalContribution + ", calcMonth=" + calcMonth + ", calcYear=" + calcYear + "]";
}


public int getCalcYear(){
    return calcYear;
}


public void setTotalContribution(double totalContribution){
    this.totalContribution = totalContribution;
}


public void setCalcYear(int calcYear){
    this.calcYear = calcYear;
}


}