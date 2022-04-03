package de.metas.ui.web.dashboard;
 import java.time.Duration;
import java.time.Instant;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class TimeRange {

@JsonProperty("fromMillis")
 private  long fromMillis;

@JsonProperty("toMillis")
 private  long toMillis;

@JsonIgnore
 private  boolean mainTimeRange;

@JsonIgnore
 private  long offsetMillis;


public long getFromMillis(){
    return fromMillis;
}


public long getToMillis(){
    return toMillis;
}


public TimeRange offset(TimeRange mainRange,Duration offset){
    final boolean mainTimeRange = false;
    final long offsetMillis = offset.toMillis();
    final long fromMillis = mainRange.getFromMillis() + offsetMillis;
    final long toMillis = mainRange.getToMillis() + offsetMillis;
    return new TimeRange(mainTimeRange, fromMillis, toMillis, offsetMillis);
}


public TimeRange main(long fromMillis,long toMillis){
    final boolean mainTimeRange = true;
    final int offsetMillis = 0;
    return new TimeRange(mainTimeRange, fromMillis, toMillis, offsetMillis);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("from", Instant.ofEpochMilli(fromMillis)).add("to", Instant.ofEpochMilli(toMillis)).add("main", mainTimeRange).add("offset", Duration.ofMillis(offsetMillis)).toString();
}


public boolean isMainTimeRange(){
    return mainTimeRange;
}


public long offsetDate(long millis){
    return millis + offsetMillis;
}


public long subtractOffset(long millis){
    return millis - offsetMillis;
}


public long getOffsetMillis(){
    return offsetMillis;
}


}