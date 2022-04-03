package de.metas.ui.web.window.datatypes;
 import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.compiere.util.NamePair;
import org.compiere.util.TimeUtil;
import de.metas.currency.Amount;
import de.metas.money.Money;
import de.metas.quantity.Quantity;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.ui.web.window.datatypes.json.JSONRange;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@UtilityClass
public class Values {


public Object valueToJsonObject(Object value,JSONOptions jsonOpts,UnaryOperator<Object> fallbackMapper){
    if (JSONNullValue.isNull(value)) {
        return JSONNullValue.instance;
    } else if (value instanceof java.util.Date) {
        final ZonedDateTime valueDate = TimeUtil.asZonedDateTime(value);
        return DateTimeConverters.toJson(valueDate, jsonOpts.getZoneId());
    } else if (value instanceof LocalDate) {
        return localDateToJson((LocalDate) value);
    } else if (value instanceof LocalTime) {
        return DateTimeConverters.toJson((LocalTime) value);
    } else if (value instanceof ZonedDateTime) {
        return DateTimeConverters.toJson((ZonedDateTime) value, jsonOpts.getZoneId());
    } else if (value instanceof Instant) {
        return DateTimeConverters.toJson((Instant) value, jsonOpts.getZoneId());
    } else if (value instanceof DateRangeValue) {
        final DateRangeValue dateRange = (DateRangeValue) value;
        return JSONRange.of(dateRange);
    } else if (value instanceof LookupValue) {
        final LookupValue lookupValue = (LookupValue) value;
        return JSONLookupValue.ofLookupValue(lookupValue, jsonOpts.getAdLanguage());
    } else if (value instanceof LookupValuesList) {
        final LookupValuesList lookupValues = (LookupValuesList) value;
        return JSONLookupValuesList.ofLookupValuesList(lookupValues, jsonOpts.getAdLanguage());
    } else if (value instanceof NamePair) {
        final NamePair lookupValue = (NamePair) value;
        return JSONLookupValue.ofNamePair(lookupValue);
    } else if (value instanceof BigDecimal) {
        return bigDecimalToJson((BigDecimal) value);
    } else if (value instanceof Quantity) {
        return bigDecimalToJson(((Quantity) value).toBigDecimal());
    } else if (value instanceof Money) {
        return bigDecimalToJson(((Money) value).toBigDecimal());
    } else if (value instanceof Amount) {
        return bigDecimalToJson(((Amount) value).getAsBigDecimal());
    } else if (value instanceof DocumentId) {
        return ((DocumentId) value).toJson();
    } else if (value instanceof Collection) {
        final Collection<?> valuesList = (Collection<?>) value;
        return valuesList.stream().map(v -> valueToJsonObject(v, jsonOpts, fallbackMapper)).collect(// don't use ImmutableList because we might get null values
        Collectors.toCollection(ArrayList::new));
    } else {
        return fallbackMapper.apply(value);
    }
}


public String bigDecimalToJson(BigDecimal value){
    // NOTE: because javascript cannot distinguish between "1.00" and "1.0" as number,
    // we need to provide the BigDecimals as Strings.
    return value.toPlainString();
}


public String localDateToJson(LocalDate value){
    return DateTimeConverters.toJson(value);
}


}