package kielce.tu.weaii.telelearn.views;
 import lombok.Value;
import java.time.Duration;
@Value
public class TimeVew {

 private long hours;

 private long minutes;


public TimeVew form(Duration duration){
    return new TimeVew(duration.toHours(), duration.minusHours(duration.toHours()).toMinutes());
}


}