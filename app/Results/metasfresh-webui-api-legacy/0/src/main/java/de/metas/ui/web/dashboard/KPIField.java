package de.metas.ui.web.dashboard;
 import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import org.compiere.util.DisplayType;
import org.compiere.util.TimeUtil;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.slf4j.Logger;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.Language;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.Check;
import lombok.NonNull;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class KPIField {

 private  Logger logger;

 private  String fieldName;

 private  boolean groupBy;

 private  ITranslatableString caption;

 private  ITranslatableString offsetCaption;

 private  ITranslatableString description;

 private  String unit;

 private  KPIFieldValueType valueType;

 private  Integer numberPrecision;

 private  String color;

 private  String esPathStr;

 private  List<String> esPath;

 private  BucketValueExtractor bucketValueExtractor;

 private  String fieldName;

 private  boolean groupBy;

 private  ITranslatableString caption;

 private  ITranslatableString offsetCaption;

 private  ITranslatableString description;

 private  String unit;

 private  KPIFieldValueType valueType;

 private  Integer numberPrecision;

 private  String color;

 private  String esPathStr;

 private  List<String> esPath;

 private  Splitter PATH_SPLITTER;


public boolean isGroupBy(){
    return groupBy;
}


public BucketValueExtractor createBucketValueExtractor(List<String> path){
    if (path.size() == 1) {
        final String fieldName = path.get(0);
        if ("doc_count".equals(fieldName)) {
            return (containingAggName, bucket) -> bucket.getDocCount();
        } else if ("key".equals(fieldName)) {
            return (containingAggName, bucket) -> bucket.getKeyAsString();
        }
    }
    return (containingAggName, bucket) -> bucket.getProperty(containingAggName, path);
}


public Builder setFieldName(String fieldName){
    this.fieldName = fieldName;
    return this;
}


public Builder setESPath(String esPathStr){
    this.esPathStr = esPathStr.trim();
    esPath = PATH_SPLITTER.splitToList(this.esPathStr);
    return this;
}


public BucketValueExtractor getBucketValueExtractor(){
    return bucketValueExtractor;
}


public Builder setCaption(ITranslatableString caption){
    Check.assumeNotNull(caption, "Parameter caption is not null");
    this.caption = caption;
    return this;
}


public Builder setOffsetCaption(ITranslatableString offsetCaption){
    Check.assumeNotNull(offsetCaption, "Parameter offsetCaption is not null");
    this.offsetCaption = offsetCaption;
    return this;
}


public Builder setDescription(ITranslatableString description){
    Check.assumeNotNull(description, "Parameter description is not null");
    this.description = description;
    return this;
}


public String getCaption(String adLanguage){
    return caption.translate(adLanguage);
}


public String getDescription(String adLanguage){
    return description.translate(adLanguage);
}


public Builder setGroupBy(boolean groupBy){
    this.groupBy = groupBy;
    return this;
}


public Object convertValueToJsonUserFriendly(Object value,JSONOptions jsonOpts){
    if (value == null) {
        return null;
    }
    try {
        if (valueType == KPIFieldValueType.Date) {
            if (value instanceof String) {
                final Date date = TimeUtil.asDate(DateTimeConverters.fromObjectToZonedDateTime(value));
                final Language language = Language.getLanguage(jsonOpts.getAdLanguage());
                return DisplayType.getDateFormat(DisplayType.Date, language).format(date);
            } else if (value instanceof Date) {
                final Date date = (Date) value;
                final Language language = Language.getLanguage(jsonOpts.getAdLanguage());
                return DisplayType.getDateFormat(DisplayType.Date, language).format(date);
            } else if (value instanceof Number) {
                final long millis = ((Number) value).longValue();
                final Date date = new Date(millis);
                final Language language = Language.getLanguage(jsonOpts.getAdLanguage());
                return DisplayType.getDateFormat(DisplayType.Date, language).format(date);
            } else {
                return value.toString();
            }
        } else if (valueType == KPIFieldValueType.DateTime) {
            if (value instanceof String) {
                final Date date = TimeUtil.asDate(DateTimeConverters.fromObjectToZonedDateTime(value));
                final Language language = Language.getLanguage(jsonOpts.getAdLanguage());
                return DisplayType.getDateFormat(DisplayType.DateTime, language).format(date);
            } else if (value instanceof Date) {
                final Date date = (Date) value;
                final Language language = Language.getLanguage(jsonOpts.getAdLanguage());
                return DisplayType.getDateFormat(DisplayType.DateTime, language).format(date);
            } else if (value instanceof Number) {
                final long millis = ((Number) value).longValue();
                final Date date = new Date(millis);
                final Language language = Language.getLanguage(jsonOpts.getAdLanguage());
                return DisplayType.getDateFormat(DisplayType.DateTime, language).format(date);
            } else {
                return value.toString();
            }
        } else {
            return convertValueToJson(value, jsonOpts);
        }
    } catch (Exception ex) {
        logger.warn("Failed converting {} for field {}", value, this, ex);
        return value.toString();
    }
}


public Builder setValueType(KPIFieldValueType valueType){
    this.valueType = valueType;
    return this;
}


public Builder setUnit(String unit){
    this.unit = unit;
    return this;
}


public Builder builder(){
    return new Builder();
}


public String getOffsetCaption(String adLanguage){
    return offsetCaption.translate(adLanguage);
}


public KPIFieldValueType getValueType(){
    return valueType;
}


public BigDecimal roundToPrecision(BigDecimal bd){
    if (numberPrecision == null) {
        return bd;
    } else {
        return bd.setScale(numberPrecision, RoundingMode.HALF_UP);
    }
}


public List<String> getESPath(){
    return esPath;
}


public String getOffsetFieldName(){
    return fieldName + "_offset";
}


public String getColor(){
    return color;
}


public String getESPathAsString(){
    return esPathStr;
}


public Object extractValue(String containingAggName,MultiBucketsAggregation.Bucket bucket)


public Builder setNumberPrecision(Integer numberPrecision){
    this.numberPrecision = numberPrecision;
    return this;
}


public Builder setColor(String color){
    this.color = color;
    return this;
}


public KPIField build(){
    return new KPIField(this);
}


public Object convertValueToJson(Object value,JSONOptions jsonOpts){
    if (value == null) {
        return null;
    }
    try {
        switch(valueType) {
            case Date:
                {
                    final LocalDate date = DateTimeConverters.fromObjectToLocalDate(value);
                    return DateTimeConverters.toJson(date);
                }
            case DateTime:
                {
                    final ZonedDateTime date = DateTimeConverters.fromObjectToZonedDateTime(value);
                    return DateTimeConverters.toJson(date, jsonOpts.getZoneId());
                }
            case Number:
                {
                    if (value instanceof String) {
                        final BigDecimal bd = new BigDecimal(value.toString());
                        return roundToPrecision(bd);
                    } else if (value instanceof Double) {
                        final BigDecimal bd = BigDecimal.valueOf(((Double) value).doubleValue());
                        return roundToPrecision(bd);
                    } else if (value instanceof Number) {
                        final BigDecimal bd = BigDecimal.valueOf(((Number) value).intValue());
                        return roundToPrecision(bd);
                    } else if (value instanceof Integer) {
                        return value;
                    } else {
                        return value;
                    }
                }
            case String:
                {
                    return value.toString();
                }
            default:
                {
                    throw new IllegalStateException("valueType not supported: " + valueType);
                }
        }
    } catch (Exception ex) {
        logger.warn("Failed converting {} for field {}", value, this, ex);
        return value.toString();
    }
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("fieldName", fieldName).add("groupBy", groupBy).add("esPath", esPath).add("valueType", valueType).toString();
}


public String getFieldName(){
    return fieldName;
}


public String getUnit(){
    return unit;
}


}