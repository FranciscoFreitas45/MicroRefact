package de.metas.ui.web.window.datatypes.DataTypes;
 import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import de.metas.logging.LogManager;
import de.metas.ui.web.upload.WebuiImageId;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONLookupValuesList;
import de.metas.ui.web.window.datatypes.json.JSONRange;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.model.lookup.LookupValueByIdSupplier;
import de.metas.util.Check;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
@SuppressWarnings("serial")
public class ValueConversionException extends AdempiereException{


public ValueConversionException setFieldName(String fieldName){
    setParameter("fieldName", fieldName);
    return this;
}


public ValueConversionException setWidgetType(DocumentFieldWidgetType widgetType){
    setParameter("widgetType", widgetType);
    return this;
}


public ValueConversionException wrapIfNeeded(Throwable ex){
    if (ex instanceof ValueConversionException) {
        return (ValueConversionException) ex;
    }
    final Throwable cause = extractCause(ex);
    if (cause instanceof ValueConversionException) {
        return (ValueConversionException) cause;
    } else {
        return new ValueConversionException(cause);
    }
}


public ValueConversionException setLookupDataSource(LookupValueByIdSupplier lookupDataSource){
    setParameter("lookupDataSource", lookupDataSource);
    return this;
}


public ValueConversionException setTargetType(Class<?> targetType){
    setParameter("targetType", targetType);
    return this;
}


public ValueConversionException setFromValue(Object fromValue){
    setParameter("value", fromValue);
    setParameter("valueClass", fromValue != null ? fromValue.getClass() : null);
    return this;
}


}