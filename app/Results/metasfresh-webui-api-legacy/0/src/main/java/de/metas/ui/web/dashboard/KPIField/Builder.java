package de.metas.ui.web.dashboard.KPIField;
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
public class Builder {

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


public Builder setNumberPrecision(Integer numberPrecision){
    this.numberPrecision = numberPrecision;
    return this;
}


public Builder setColor(String color){
    this.color = color;
    return this;
}


public Builder setFieldName(String fieldName){
    this.fieldName = fieldName;
    return this;
}


public Builder setValueType(KPIFieldValueType valueType){
    this.valueType = valueType;
    return this;
}


public KPIField build(){
    return new KPIField(this);
}


public Builder setUnit(String unit){
    this.unit = unit;
    return this;
}


public Builder setESPath(String esPathStr){
    this.esPathStr = esPathStr.trim();
    esPath = PATH_SPLITTER.splitToList(this.esPathStr);
    return this;
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


public Builder setGroupBy(boolean groupBy){
    this.groupBy = groupBy;
    return this;
}


}