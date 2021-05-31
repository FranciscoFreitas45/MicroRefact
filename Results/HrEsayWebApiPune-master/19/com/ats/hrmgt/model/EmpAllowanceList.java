public class EmpAllowanceList {

 private  int allowanceId;

 private  String allowanceName;

 private  double value;

 private  String shortName;


public String getAllowanceName(){
    return allowanceName;
}


public double getValue(){
    return value;
}


public void setAllowanceName(String allowanceName){
    this.allowanceName = allowanceName;
}


public void setValue(double value){
    this.value = value;
}


public String getShortName(){
    return shortName;
}


public void setShortName(String shortName){
    this.shortName = shortName;
}


public void setAllowanceId(int allowanceId){
    this.allowanceId = allowanceId;
}


@Override
public String toString(){
    return "EmpAllowanceList [allowanceId=" + allowanceId + ", allowanceName=" + allowanceName + ", value=" + value + ", shortName=" + shortName + "]";
}


public int getAllowanceId(){
    return allowanceId;
}


}