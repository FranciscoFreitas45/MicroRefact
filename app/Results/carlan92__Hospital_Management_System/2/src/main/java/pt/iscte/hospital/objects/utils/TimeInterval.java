package pt.iscte.hospital.objects.utils;
 import java.time.LocalTime;
public class TimeInterval {

 private  LocalTime timeBegin;

 private  LocalTime timeEnd;

// Constructor
public TimeInterval(LocalTime timeBegin, LocalTime timeEnd) {
    if (timeEnd.isBefore(timeBegin)) {
        this.timeBegin = timeEnd;
        this.timeEnd = timeBegin;
    }
    this.timeBegin = timeBegin;
    this.timeEnd = timeEnd;
}
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