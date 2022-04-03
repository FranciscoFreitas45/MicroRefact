package DTO;
 import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.adempiere.ad.expression.exceptions.ExpressionEvaluationException;
import org.adempiere.ad.validationRule.INamePairPredicate;
import org.adempiere.ad.validationRule.IValidationContext;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluatee2;
import org.compiere.util.NamePair;
import org.compiere.util.TimeUtil;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableMap;
import de.metas.logging.LogManager;
import de.metas.security.UserRolePermissionsKey;
import de.metas.security.impl.AccessSqlStringExpression;
import de.metas.ui.web.view.ViewId;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.descriptor.sql.SqlForFetchingLookupById;
import de.metas.ui.web.window.descriptor.sql.SqlForFetchingLookups;
import de.metas.ui.web.window.model.lookup.LookupValueFilterPredicates.LookupValueFilterPredicate;
import de.metas.util.NumberUtils;
import de.metas.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class LookupDataSourceContext implements Evaluatee2,IValidationContext{

 private  Logger logger;

 public  String FILTER_Any;

 private  String FILTER_Any_SQL;

 public  CtxName PARAM_AD_Language;

 public  CtxName PARAM_UserRolePermissionsKey;

 public  CtxName PARAM_Filter;

 public  CtxName PARAM_FilterSql;

 public  CtxName PARAM_ViewId;

 public  CtxName PARAM_ViewSize;

 private  String lookupTableName;

 private  ImmutableMap<String,Object> parameterValues;

 private  Object idToFilter;

 private  INamePairPredicate postQueryPredicate;

 private  Evaluatee parentEvaluatee;

 private  String lookupTableName;

 private  INamePairPredicate postQueryPredicate;

 private  Map<String,Object> name2value;

 private  Object idToFilter;

 private  Collection<CtxName> _requiredParameters;

 private  boolean _requiredParameters_copyOnAdd;

 private  Map<String,Object> valuesCollected;


public int getLimit(int defaultValue){
    return get_ValueAsInt(SqlForFetchingLookups.PARAM_Limit.getName(), defaultValue);
}


public int getOffset(int defaultValue){
    return get_ValueAsInt(SqlForFetchingLookups.PARAM_Offset.getName(), defaultValue);
}


public Integer getIdToFilterAsInt(Integer defaultValue){
    if (idToFilter == null) {
        return defaultValue;
    } else if (idToFilter instanceof Number) {
        return ((Number) idToFilter).intValue();
    } else {
        final String idToFilterStr = idToFilter.toString();
        if (idToFilterStr.isEmpty()) {
            return defaultValue;
        }
        return Integer.parseInt(idToFilterStr);
    }
}


public String getIdToFilterAsString(){
    return idToFilter != null ? idToFilter.toString() : null;
}


public String getFilterOrIfAny(String whenAnyFilter){
    final String filterStr = getFilter();
    if (filterStr == FILTER_Any) {
        return whenAnyFilter;
    }
    return filterStr;
}


@Override
public T get_ValueAsObject(String variableName){
    @SuppressWarnings("unchecked")
    final T valueCasted = (T) parameterValues.get(variableName);
    return valueCasted;
}


@Override
public String get_ValueOldAsString(String variableName){
    // TODO implement get_ValueOldAsString
    return null;
}


@Override
public String getTableName(){
    return lookupTableName;
}


@Override
public Integer get_ValueAsInt(String variableName,Integer defaultValue){
    final Object value = parameterValues.get(variableName);
    return convertValueToInteger(value, defaultValue);
}


public String getAD_Language(){
    return get_ValueAsString(PARAM_AD_Language.getName());
}


public String getFilter(){
    return get_ValueAsString(PARAM_Filter.getName());
}


@Override
public Date get_ValueAsDate(String variableName,Date defaultValue){
    final Object value = parameterValues.get(variableName);
    return convertValueToDate(value, defaultValue);
}


public Collection<CtxName> getRequiredParameters(){
    return _requiredParameters;
}


public LookupValueFilterPredicate getFilterPredicate(){
    final String filterStr = getFilter();
    if (filterStr == FILTER_Any) {
        return LookupValueFilterPredicates.MATCH_ALL;
    }
    return LookupValueFilterPredicates.ofFilterAndLanguage(filterStr, getAD_Language());
}


@Override
public String get_ValueAsString(String variableName){
    final Object value = parameterValues.get(variableName);
    if (value == null) {
        return null;
    } else if (value instanceof LookupValue) {
        return ((LookupValue) value).getIdAsString();
    } else if (value instanceof Boolean) {
        return StringUtils.ofBoolean((Boolean) value);
    } else if (TimeUtil.isDateOrTimeObject(value)) {
        // NOTE: because this evaluatee is used to build SQL expressions too,
        // we have to make sure the dates are converted to JDBC format
        final Timestamp jdbcTimestamp = TimeUtil.asTimestamp(value);
        return Env.toString(jdbcTimestamp);
    } else {
        return value.toString();
    }
}


public Object getIdToFilter(){
    return idToFilter;
}


public ViewId getViewId(){
    final ViewId viewId = get_ValueAsObject(PARAM_ViewId.getName());
    if (viewId == null) {
        throw new AdempiereException("@NotFound@ ViewId: ").setParameter("context", this);
    }
    return viewId;
}


}