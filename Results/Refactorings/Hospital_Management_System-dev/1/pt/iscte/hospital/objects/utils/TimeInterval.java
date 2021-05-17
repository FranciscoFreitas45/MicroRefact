import java.time.LocalTime;
public class TimeInterval {

 private  LocalTime timeBegin;

 private  LocalTime timeEnd;


public LocalTime getTimeEnd(){
    return timeEnd;
}


@Override
public String toString(){
    return "TimeInterval{" + "timeBegin=" + timeBegin + ", timeEnd=" + timeEnd + '}';
}


public LocalTime getTimeBegin(){
    return timeBegin;
}


}