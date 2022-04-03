package de.metas.ui.web.view;
 import java.math.BigDecimal;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.Values;
import de.metas.ui.web.window.datatypes.json.JSONLookupValue;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.ui.web.window.datatypes.json.JSONOptions;
import de.metas.util.NumberUtils;
import de.metas.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@ToString
@EqualsAndHashCode
public class ViewRowFieldNameAndJsonValues {

 private  ViewRowFieldNameAndJsonValues EMPTY;

 private  ImmutableMap<String,Object> map;


public Object getAsJsonObject(String fieldName,JSONOptions jsonOpts){
    final Object valueObj = map.get(fieldName);
    if (JSONNullValue.isNull(valueObj)) {
        return null;
    } else if (valueObj instanceof ITranslatableString) {
        return ((ITranslatableString) valueObj).translate(jsonOpts.getAdLanguage());
    } else {
        return Values.valueToJsonObject(valueObj, jsonOpts);
    }
}


public BigDecimal getAsBigDecimal(String fieldName,BigDecimal defaultValueIfNotFoundOrError){
    final Object valueObj = map.get(fieldName);
    if (JSONNullValue.isNull(valueObj)) {
        return defaultValueIfNotFoundOrError;
    } else {
        return NumberUtils.asBigDecimal(valueObj, defaultValueIfNotFoundOrError);
    }
}


public boolean getAsBoolean(String fieldName,boolean defaultValueIfNotFoundOrError){
    final Object valueObj = map.get(fieldName);
    return StringUtils.toBoolean(valueObj, defaultValueIfNotFoundOrError);
}


public ViewRowFieldNameAndJsonValues ofMap(ImmutableMap<String,Object> map){
    if (map.isEmpty()) {
        return EMPTY;
    }
    return new ViewRowFieldNameAndJsonValues(map);
}


public ImmutableSet<String> getFieldNames(){
    return map.keySet();
}


public int getAsInt(String fieldName,int defaultValueIfNotFoundOrError){
    final Object valueObj = map.get(fieldName);
    if (JSONNullValue.toNullIfInstance(valueObj) == null) {
        return defaultValueIfNotFoundOrError;
    } else if (valueObj instanceof Number) {
        return ((Number) valueObj).intValue();
    } else if (valueObj instanceof LookupValue) {
        return ((LookupValue) valueObj).getIdAsInt();
    } else if (valueObj instanceof JSONLookupValue) {
        return ((JSONLookupValue) valueObj).getKeyAsInt();
    } else {
        return NumberUtils.asInt(valueObj, defaultValueIfNotFoundOrError);
    }
}


}