import com.ats.hrmgt.model.HolidayMaster;
import java.util.List;
public class BirthHoliDash {

 private  int loginUserBirthDay;

 private  List<HolidayMaster> holiList;

 private  List<GetBirthDaysForDash> birthListUpcoming;

 private  List<GetBirthDaysForDash> birthListToday;


public void setBirthListToday(List<GetBirthDaysForDash> birthListToday){
    this.birthListToday = birthListToday;
}


public void setHoliList(List<HolidayMaster> holiList){
    this.holiList = holiList;
}


public List<GetBirthDaysForDash> getBirthListToday(){
    return birthListToday;
}


public List<GetBirthDaysForDash> getBirthListUpcoming(){
    return birthListUpcoming;
}


public int getLoginUserBirthDay(){
    return loginUserBirthDay;
}


public List<HolidayMaster> getHoliList(){
    return holiList;
}


@Override
public String toString(){
    return "BirthHoliDash [loginUserBirthDay=" + loginUserBirthDay + ", holiList=" + holiList + ", birthListUpcoming=" + birthListUpcoming + ", birthListToday=" + birthListToday + "]";
}


public void setBirthListUpcoming(List<GetBirthDaysForDash> birthListUpcoming){
    this.birthListUpcoming = birthListUpcoming;
}


public void setLoginUserBirthDay(int loginUserBirthDay){
    this.loginUserBirthDay = loginUserBirthDay;
}


}