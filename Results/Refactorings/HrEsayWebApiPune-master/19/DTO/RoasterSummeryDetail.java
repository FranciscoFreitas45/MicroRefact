import javax.persistence;
public class RoasterSummeryDetail {

 private  int empId;

 private  String empCode;

 private  String firstName;

 private  String middleName;

 private  String surname;

 private  int offDayCount;

 private  int nightCount;

 private  int ffCount;

 private  int lateMark;

 private  int lateMin;

 private  int km;

 private  float incentive;


public int getFfCount(){
    return ffCount;
}


public int getKm(){
    return km;
}


public String getMiddleName(){
    return middleName;
}


public int getLateMin(){
    return lateMin;
}


public float getIncentive(){
    return incentive;
}


public int getNightCount(){
    return nightCount;
}


public int getOffDayCount(){
    return offDayCount;
}


public int getEmpId(){
    return empId;
}


public String getEmpCode(){
    return empCode;
}


public int getLateMark(){
    return lateMark;
}


public String getFirstName(){
    return firstName;
}


public String getSurname(){
    return surname;
}


}