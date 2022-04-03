package de.metas.ui.web.window.datatypes.json;
 import com.google.common.annotations.VisibleForTesting;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
@UtilityClass
public class DateTimeConverters {

 private  Logger logger;


public Object fromObject(Object valueObj,DocumentFieldWidgetType widgetType){
    if (widgetType == DocumentFieldWidgetType.LocalDate) {
        return fromObjectToLocalDate(valueObj);
    } else if (widgetType == DocumentFieldWidgetType.LocalTime) {
        return fromObjectToLocalTime(valueObj);
    } else if (widgetType == DocumentFieldWidgetType.ZonedDateTime) {
        return fromObjectToZonedDateTime(valueObj);
    } else if (widgetType == DocumentFieldWidgetType.Timestamp) {
        return fromObjectToInstant(valueObj);
    } else {
        throw new AdempiereException("Invalid date/time type: " + widgetType);
    }
}


public LocalTime fromJsonToLocalTime(String valueStr){
    final JSONDateConfig config = getConfig();
    return LocalTime.parse(valueStr, config.getLocalTimeFormatter());
}


public LocalDate fromObjectToLocalDate(Object valueObj){
    return fromObjectTo(valueObj, LocalDate.class, DateTimeConverters::fromJsonToLocalDate, TimeUtil::asLocalDate);
}


public LocalDate fromJsonToLocalDate(String valueStr){
    final JSONDateConfig config = getConfig();
    return LocalDate.parse(valueStr, config.getLocalDateFormatter());
}


public Instant fromJsonToInstant(String valueStr){
    final JSONDateConfig config = getConfig();
    return ZonedDateTime.parse(valueStr, config.getTimestampFormatter()).toInstant();
}


public JSONDateConfig getConfig(){
    return JSONDateConfig.DEFAULT;
}


public ZonedDateTime fromJsonToZonedDateTime(String valueStr){
    final JSONDateConfig config = getConfig();
    return ZonedDateTime.parse(valueStr, config.getZonedDateTimeFormatter());
}


public String toJson(ZoneId zoneId){
    return zoneId.getId();
}


public ZonedDateTime fromObjectToZonedDateTime(Object valueObj){
    return fromObjectTo(valueObj, ZonedDateTime.class, DateTimeConverters::fromJsonToZonedDateTime, TimeUtil::asZonedDateTime);
}


public LocalTime fromObjectToLocalTime(Object valueObj){
    return fromObjectTo(valueObj, LocalTime.class, DateTimeConverters::fromJsonToLocalTime, TimeUtil::asLocalTime);
}


@NonNull
public Timestamp fromPossibleJdbcTimestamp(String s){
    return Timestamp.valueOf(s);
}


public Object fromJson(String valueStr,DocumentFieldWidgetType widgetType){
    return fromObject(valueStr, widgetType);
}


public Instant fromObjectToInstant(Object valueObj){
    return fromObjectTo(valueObj, Instant.class, DateTimeConverters::fromJsonToInstant, TimeUtil::asInstant);
}


public boolean isPossibleJdbcTimestamp(String s){
    return s.length() == 21 && s.charAt(10) == ' ';
}


@Nullable
public T fromObjectTo(Object valueObj,Class<T> type,Function<String,T> fromJsonConverer,Function<Object,T> fromObjectConverter){
    if (valueObj == null || JSONNullValue.isNull(valueObj)) {
        return null;
    } else if (type.isInstance(valueObj)) {
        return type.cast(valueObj);
    } else if (valueObj instanceof CharSequence) {
        final String json = valueObj.toString().trim();
        if (json.isEmpty()) {
            return null;
        }
        if (isPossibleJdbcTimestamp(json)) {
            try {
                final Timestamp timestamp = fromPossibleJdbcTimestamp(json);
                return fromObjectConverter.apply(timestamp);
            } catch (final Exception e) {
                logger.warn("Error while converting possible JDBC Timestamp `{}` to java.sql.Timestamp", json, e);
                return fromJsonConverer.apply(json);
            }
        } else {
            return fromJsonConverer.apply(json);
        }
    } else if (valueObj instanceof StringLookupValue) {
        final String key = ((StringLookupValue) valueObj).getIdAsString();
        if (Check.isEmpty(key)) {
            return null;
        } else {
            return fromJsonConverer.apply(key);
        }
    } else {
        return fromObjectConverter.apply(valueObj);
    }
}


}