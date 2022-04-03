package de.metas.ui.web.dashboard;
 import java.time.Duration;
import de.metas.printing.esb.base.util.Check;
import de.metas.util.lang.CoalesceUtil;
import de.metas.util.time.SystemTime;
import lombok.Builder;
import lombok.Value;
@Builder
@Value
public class KPITimeRangeDefaults {

 public  KPITimeRangeDefaults DEFAULT;

 private  Duration defaultTimeRange;

 private  Duration defaultTimeRangeEndOffset;


public KPITimeRangeDefaultsBuilder defaultTimeRangeFromString(String defaultTimeRangeStr){
    return defaultTimeRange(parseDurationOrNull(defaultTimeRangeStr));
}


public Duration parseDurationOrNull(String durationStr){
    return Check.isEmpty(durationStr, true) ? null : Duration.parse(durationStr);
}


public long calculateToMillis(){
    long toMillis = SystemTime.millis();
    final Duration defaultTimeRangeEndOffset = getDefaultTimeRangeEndOffset();
    if (defaultTimeRangeEndOffset != null) {
        toMillis += defaultTimeRangeEndOffset.toMillis();
    }
    return toMillis;
}


public KPITimeRangeDefaults compose(KPITimeRangeDefaults fallback){
    return builder().defaultTimeRange(CoalesceUtil.coalesce(getDefaultTimeRange(), fallback.getDefaultTimeRange())).defaultTimeRangeEndOffset(CoalesceUtil.coalesce(getDefaultTimeRangeEndOffset(), fallback.getDefaultTimeRangeEndOffset())).build();
}


public KPITimeRangeDefaultsBuilder defaultTimeRangeEndOffsetFromString(String defaultTimeRangeEndOffsetStr){
    return defaultTimeRangeEndOffset(parseDurationOrNull(defaultTimeRangeEndOffsetStr));
}


public long calculateFromMillis(long toMillis){
    final Duration defaultTimeRange = getDefaultTimeRange();
    if (defaultTimeRange == null || defaultTimeRange.isZero()) {
        return 0;
    } else {
        return toMillis - defaultTimeRange.abs().toMillis();
    }
}


public TimeRange createTimeRange(java.util.Date dateFrom,java.util.Date dateTo){
    final long fromMillis = dateFrom == null ? -1 : dateFrom.getTime();
    final long toMillis = dateTo == null ? -1 : dateTo.getTime();
    return createTimeRange(fromMillis, toMillis);
}


}