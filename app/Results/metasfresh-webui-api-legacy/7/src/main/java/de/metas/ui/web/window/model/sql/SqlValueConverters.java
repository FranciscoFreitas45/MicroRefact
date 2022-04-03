package de.metas.ui.web.window.model.sql;
 import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import org.compiere.util.DisplayType;
import org.compiere.util.TimeUtil;
import de.metas.ui.web.window.datatypes.ColorValue;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.Password;
import de.metas.ui.web.window.datatypes.json.DateTimeConverters;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.util.ColorId;
import de.metas.util.IColorRepository;
import de.metas.util.Services;
import de.metas.util.lang.RepoIdAware;
import lombok.experimental.UtilityClass;
@UtilityClass
public class SqlValueConverters {


public Object convertToPOValue(Object value,String columnName,DocumentFieldWidgetType widgetType,Class<?> targetClass){
    final Class<?> valueClass = JSONNullValue.isNull(value) ? null : value.getClass();
    if (valueClass != null && targetClass.isAssignableFrom(valueClass)) {
        return value;
    } else if (int.class.equals(targetClass) || Integer.class.equals(targetClass)) {
        if (JSONNullValue.isNull(value)) {
            return null;
        } else if (LookupValue.class.isAssignableFrom(valueClass)) {
            return ((LookupValue) value).getIdAsInt();
        } else if (Number.class.isAssignableFrom(valueClass)) {
            return ((Number) value).intValue();
        } else if (String.class.equals(valueClass)) {
            return new BigDecimal((String) value).intValue();
        } else if (Map.class.isAssignableFrom(valueClass)) {
            @SuppressWarnings("unchecked")
            final Map<String, Object> map = (Map<String, Object>) value;
            final IntegerLookupValue lookupValue = JSONLookupValue.integerLookupValueFromJsonMap(map);
            return lookupValue == null ? null : lookupValue.getIdAsInt();
        } else if (ColorValue.class.equals(valueClass)) {
            final ColorValue color = (ColorValue) value;
            final ColorId adColorId = Services.get(IColorRepository.class).saveFlatColorAndReturnId(color.getHexString());
            return adColorId.getRepoId();
        } else if (RepoIdAware.class.isAssignableFrom(valueClass)) {
            final RepoIdAware repoIdAware = (RepoIdAware) value;
            return repoIdAware.getRepoId();
        }
    } else if (String.class.equals(targetClass)) {
        if (JSONNullValue.isNull(value)) {
            return null;
        } else if (LookupValue.class.isAssignableFrom(valueClass)) {
            return ((LookupValue) value).getIdAsString();
        } else if (Map.class.isAssignableFrom(valueClass)) {
            @SuppressWarnings("unchecked")
            final Map<String, Object> map = (Map<String, Object>) value;
            final StringLookupValue lookupValue = JSONLookupValue.stringLookupValueFromJsonMap(map);
            return lookupValue == null ? null : lookupValue.getIdAsString();
        } else if (Password.class.isAssignableFrom(valueClass)) {
            final Password password = (Password) value;
            return password.getAsString();
        }
    } else if (Timestamp.class.equals(targetClass)) {
        if (JSONNullValue.isNull(value)) {
            return null;
        } else {
            final Object valueDate = DateTimeConverters.fromObject(value, widgetType);
            return TimeUtil.asTimestamp(valueDate);
        }
    } else if (Boolean.class.equals(targetClass) || boolean.class.equals(targetClass)) {
        if (JSONNullValue.isNull(value)) {
            return false;
        } else if (String.class.equals(valueClass)) {
            return DisplayType.toBoolean(value);
        } else if (StringLookupValue.class.isAssignableFrom(valueClass)) {
            // Corner case: e.g. Posted column which is a List but the PO is handling it as boolean
            final StringLookupValue stringLookupValue = (StringLookupValue) value;
            final Boolean valueBoolean = DisplayType.toBoolean(stringLookupValue.getIdAsString(), null);
            if (valueBoolean != null) {
                return valueBoolean;
            }
        }
    }
    // Better return the original value and let the PO fail.
    return value;
// throw new AdempiereException("Cannot convert value '" + value + "' from " + valueClass + " to " + targetClass
// + "\n ColumnName: " + columnName
// + "\n PO: " + po);
}


}