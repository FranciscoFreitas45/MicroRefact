import java.util.List;
public class InfoForCompOffList {

 private  boolean isError;

 private  String msg;

 private  List<DailyRecordForCompOff> dailyrecordlistforcompoff;


public List<DailyRecordForCompOff> getDailyrecordlistforcompoff(){
    return dailyrecordlistforcompoff;
}


public boolean isError(){
    return isError;
}


public String getMsg(){
    return msg;
}


public void setError(boolean isError){
    this.isError = isError;
}


public void setDailyrecordlistforcompoff(List<DailyRecordForCompOff> dailyrecordlistforcompoff){
    this.dailyrecordlistforcompoff = dailyrecordlistforcompoff;
}


@Override
public String toString(){
    return "InfoForCompOffList [isError=" + isError + ", msg=" + msg + ", dailyrecordlistforcompoff=" + dailyrecordlistforcompoff + "]";
}


public void setMsg(String msg){
    this.msg = msg;
}


}