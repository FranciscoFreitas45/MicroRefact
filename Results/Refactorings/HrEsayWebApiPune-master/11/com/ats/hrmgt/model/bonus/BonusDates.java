import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class BonusDates {

@Id
 private  String uid;

 private  int totalMonth;

 private  int monthFrom;

 private  int monthTo;

 private  String yearFrom;

 private  String yearTo;


public String getYearTo(){
    return yearTo;
}


public int getTotalMonth(){
    return totalMonth;
}


public void setTotalMonth(int totalMonth){
    this.totalMonth = totalMonth;
}


public int getMonthTo(){
    return monthTo;
}


public void setMonthFrom(int monthFrom){
    this.monthFrom = monthFrom;
}


public void setUid(String uid){
    this.uid = uid;
}


public void setMonthTo(int monthTo){
    this.monthTo = monthTo;
}


public void setYearFrom(String yearFrom){
    this.yearFrom = yearFrom;
}


public String getUid(){
    return uid;
}


public String getYearFrom(){
    return yearFrom;
}


public int getMonthFrom(){
    return monthFrom;
}


public void setYearTo(String yearTo){
    this.yearTo = yearTo;
}


@Override
public String toString(){
    return "BonusDates [uid=" + uid + ", totalMonth=" + totalMonth + ", monthFrom=" + monthFrom + ", monthTo=" + monthTo + ", yearFrom=" + yearFrom + ", yearTo=" + yearTo + "]";
}


}