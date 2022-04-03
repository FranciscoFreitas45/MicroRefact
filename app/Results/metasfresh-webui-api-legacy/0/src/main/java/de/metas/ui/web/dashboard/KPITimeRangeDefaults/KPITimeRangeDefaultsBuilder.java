package de.metas.ui.web.dashboard.KPITimeRangeDefaults;
 import java.time.Duration;
import de.metas.printing.esb.base.util.Check;
import de.metas.util.lang.CoalesceUtil;
import de.metas.util.time.SystemTime;
import lombok.Builder;
import lombok.Value;
public class KPITimeRangeDefaultsBuilder {


public KPITimeRangeDefaultsBuilder defaultTimeRangeFromString(String defaultTimeRangeStr){
    return defaultTimeRange(parseDurationOrNull(defaultTimeRangeStr));
}


public Duration parseDurationOrNull(String durationStr){
    return Check.isEmpty(durationStr, true) ? null : Duration.parse(durationStr);
}


public KPITimeRangeDefaultsBuilder defaultTimeRangeEndOffsetFromString(String defaultTimeRangeEndOffsetStr){
    return defaultTimeRangeEndOffset(parseDurationOrNull(defaultTimeRangeEndOffsetStr));
}


}