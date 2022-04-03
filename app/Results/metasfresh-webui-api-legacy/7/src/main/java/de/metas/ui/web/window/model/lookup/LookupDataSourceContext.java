package de.metas.ui.web.window.model.lookup;
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
@Immutable
@ToString
@EqualsAndHashCode
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


public Builder putShowInactive(boolean showInactive){
    final String sqlShowInactive = showInactive ? SqlForFetchingLookupById.SQL_PARAM_VALUE_ShowInactive_Yes : SqlForFetchingLookupById.SQL_PARAM_VALUE_ShowInactive_No;
    putValue(SqlForFetchingLookupById.SQL_PARAM_ShowInactive, sqlShowInactive);
    return this;
}


public int getLimit(int defaultValue){
    return get_ValueAsInt(SqlForFetchingLookups.PARAM_Limit.getName(), defaultValue);
}


public Builder putFilterById(Object id){
    idToFilter = id;
    return this;
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


public Builder requiresParameters(Collection<CtxName> requiredParameters){
    requiredParameters.forEach(this::requiresParameter);
    return this;
}


public String getIdToFilterAsString(){
    return idToFilter != null ? idToFilter.toString() : null;
}


public boolean isAnyFilter(){
    final String filterStr = getFilter();
    return filterStr == FILTER_Any;
}


public Builder builder(String lookupTableName){
    return new Builder(lookupTableName);
}


public Builder requiresFilterAndLimit(){
    requiresParameter(PARAM_Filter);
    requiresParameter(PARAM_FilterSql);
    requiresParameter(SqlForFetchingLookups.PARAM_Limit);
    requiresParameter(SqlForFetchingLookups.PARAM_Offset);
    return this;
}


public String getFilterOrIfAny(String whenAnyFilter){
    final String filterStr = getFilter();
    if (filterStr == FILTER_Any) {
        return whenAnyFilter;
    }
    return filterStr;
}


public Builder requiresAD_Language(){
    requiresParameter(PARAM_AD_Language);
    return this;
}


public boolean acceptItem(NamePair item){
    if (postQueryPredicate == null) {
        return true;
    } else {
        return postQueryPredicate.accept(this, item);
    }
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


public Builder builderWithoutTableName(){
    return new Builder(null);
}


@Override
public Integer get_ValueAsInt(String variableName,Integer defaultValue){
    final Object value = parameterValues.get(variableName);
    return convertValueToInteger(value, defaultValue);
}


public String getAD_Language(){
    return get_ValueAsString(PARAM_AD_Language.getName());
}


public Integer convertValueToInteger(Object value,Integer defaultValue){
    if (value == null) {
        return defaultValue;
    }
    try {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof LookupValue) {
            return ((LookupValue) value).getIdAsInt();
        } else {
            return NumberUtils.asInteger(value, defaultValue);
        }
    } catch (final Exception ex) {
        logger.warn("Failed lookup's ID to integer: {}. Returning default value={}.", value, defaultValue, ex);
        return defaultValue;
    }
}


public Builder putPostQueryPredicate(INamePairPredicate postQueryPredicate){
    this.postQueryPredicate = postQueryPredicate;
    return this;
}


public void collectContextValue(CtxName variableName,boolean failIfNotFound){
    if (valuesCollected.containsKey(variableName.getName())) {
        return;
    }
    final Object value = findContextValueOrNull(variableName);
    if (value == null) {
        if (failIfNotFound) {
            throw new ExpressionEvaluationException("@NotFound@: " + variableName);
        }
    } else {
        valuesCollected.put(variableName.getName(), value);
    }
}


public String getFilter(){
    return get_ValueAsString(PARAM_Filter.getName());
}


@Override
public Date get_ValueAsDate(String variableName,Date defaultValue){
    final Object value = parameterValues.get(variableName);
    return convertValueToDate(value, defaultValue);
}


public Builder requiresParameter(CtxName requiredParameter){
    if (_requiredParameters != null && _requiredParameters.contains(requiredParameter)) {
        // we already have the parameter => do nothing
        return this;
    }
    if (_requiredParameters == null) {
        _requiredParameters = new HashSet<>();
        _requiredParameters_copyOnAdd = false;
    } else if (_requiredParameters_copyOnAdd) {
        _requiredParameters = new HashSet<>(_requiredParameters);
        _requiredParameters_copyOnAdd = false;
    }
    _requiredParameters.add(requiredParameter);
    return this;
}


public String convertFilterToSql(String filter){
    if (filter == FILTER_Any) {
        return FILTER_Any_SQL;
    }
    String searchSql = filter;
    if (!searchSql.startsWith("%")) {
        searchSql = "%" + searchSql;
    }
    if (!searchSql.endsWith("%")) {
        searchSql += "%";
    }
    return DB.TO_STRING(searchSql);
}


public Object findContextValueOrNull(CtxName variableName){
    // 
    // Check given parameters
    if (name2value.containsKey(variableName.getName())) {
        final Object valueObj = name2value.get(variableName.getName());
        if (valueObj != null) {
            return valueObj;
        }
    }
    // Fallback to document evaluatee
    if (parentEvaluatee != null) {
        final Object value = parentEvaluatee.get_ValueIfExists(variableName.getName(), Object.class).orElse(null);
        if (value != null) {
            return value;
        }
    }
    // Fallback to the variableName's default value
    if (variableName.getDefaultValue() != CtxNames.VALUE_NULL) {
        return variableName.getDefaultValue();
    }
    // Value not found
    return null;
}


@Override
public boolean has_Variable(String variableName){
    return parameterValues.containsKey(variableName);
}


public Date convertValueToDate(Object value,Date defaultValue){
    if (value == null) {
        return null;
    }
    try {
        // note: always use TimeUtil.asDate to make sure that we really get a "Date"-date and not a "Timestamp"-date
        // goal: avoid trouble with equals()
        if (value instanceof String) {
            return TimeUtil.asDate(Env.parseTimestamp(value.toString()));
        } else {
            return TimeUtil.asDate(value);
        }
    } catch (Exception ex) {
        logger.warn("Cannot convert '{}' ({}) to to Date. Returning default value: {}.", value, value.getClass(), defaultValue, ex);
        return defaultValue;
    }
}


public LookupDataSourceContext build(){
    // 
    // Pre-build preparations
    {
        // 
        // Standard values, needed by each query
        final Properties ctx = Env.getCtx();
        final String adLanguage = Env.getAD_Language(ctx);
        final String permissionsKey = UserRolePermissionsKey.toPermissionsKeyString(ctx);
        putValue(PARAM_AD_Language, adLanguage);
        putValue(PARAM_UserRolePermissionsKey, permissionsKey);
    }
    // 
    // Collect all values required for given query
    // failIfNotFound=true
    collectContextValues(getRequiredParameters(), true);
    // 
    // Collect all values required by the post-query predicate
    // failIfNotFound=false because it might be that NOT all postQueryPredicate's parameters are mandatory!
    collectContextValues(CtxNames.parseAll(postQueryPredicate.getParameters()), false);
    // 
    // Build the effective context
    return new LookupDataSourceContext(lookupTableName, valuesCollected, idToFilter, postQueryPredicate);
}


public Collection<CtxName> getRequiredParameters(){
    return _requiredParameters;
}


public Builder setRequiredParameters(Collection<CtxName> requiredParameters){
    _requiredParameters = requiredParameters;
    _requiredParameters_copyOnAdd = true;
    return this;
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


public Builder putValue(CtxName name,Object value){
    name2value.put(name.getName(), value);
    return this;
}


public Builder putFilterByIdParameterName(String sqlId){
    putValue(SqlForFetchingLookupById.SQL_PARAM_KeyId, sqlId);
    return this;
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


public Builder putFilter(String filter,int offset,int limit){
    putValue(PARAM_Filter, filter);
    putValue(PARAM_FilterSql, convertFilterToSql(filter));
    putValue(SqlForFetchingLookups.PARAM_Offset, offset);
    putValue(SqlForFetchingLookups.PARAM_Limit, limit);
    return this;
}


public Builder requiresUserRolePermissionsKey(){
    requiresParameter(PARAM_UserRolePermissionsKey);
    return this;
}


public Builder setParentEvaluatee(Evaluatee parentEvaluatee){
    this.parentEvaluatee = parentEvaluatee;
    return this;
}


public Builder collectContextValues(Collection<CtxName> parameters,boolean failIfNotFound){
    if (parameters == null || parameters.isEmpty()) {
        return this;
    }
    for (final CtxName parameterName : parameters) {
        collectContextValue(parameterName, failIfNotFound);
    }
    return this;
}


}