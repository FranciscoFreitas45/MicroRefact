public class AllowanceWithDifferenceForArear {

 private  int allowanceId;

 private  String name;

 private  String shortName;

 private  double allowanceValue;

 private  double allowanceValueCal;

 private  double allowanceDifference;

 private  double arearCal;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setAllowanceValue(double allowanceValue){
    this.allowanceValue = allowanceValue;
}


public void setAllowanceDifference(double allowanceDifference){
    this.allowanceDifference = allowanceDifference;
}


public double getArearCal(){
    return arearCal;
}


public double getAllowanceValue(){
    return allowanceValue;
}


public void setAllowanceValueCal(double allowanceValueCal){
    this.allowanceValueCal = allowanceValueCal;
}


public double getAllowanceDifference(){
    return allowanceDifference;
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


public double getAllowanceValueCal(){
    return allowanceValueCal;
}


@Override
public String toString(){
    return "AllowanceWithDifferenceForArear [allowanceId=" + allowanceId + ", name=" + name + ", shortName=" + shortName + ", allowanceValue=" + allowanceValue + ", allowanceValueCal=" + allowanceValueCal + ", allowanceDifference=" + allowanceDifference + ", arearCal=" + arearCal + "]";
}


public void setArearCal(double arearCal){
    this.arearCal = arearCal;
}


public int getAllowanceId(){
    return allowanceId;
}


}