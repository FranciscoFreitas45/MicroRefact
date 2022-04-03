package de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
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
public class Builder {

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


public Builder putFilterById(Object id){
    idToFilter = id;
    return this;
}


public Builder requiresParameters(Collection<CtxName> requiredParameters){
    requiredParameters.forEach(this::requiresParameter);
    return this;
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


public Builder requiresFilterAndLimit(){
    requiresParameter(PARAM_Filter);
    requiresParameter(PARAM_FilterSql);
    requiresParameter(SqlForFetchingLookups.PARAM_Limit);
    requiresParameter(SqlForFetchingLookups.PARAM_Offset);
    return this;
}


public Builder putValue(CtxName name,Object value){
    name2value.put(name.getName(), value);
    return this;
}


public Builder putFilterByIdParameterName(String sqlId){
    putValue(SqlForFetchingLookupById.SQL_PARAM_KeyId, sqlId);
    return this;
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


public Builder requiresAD_Language(){
    requiresParameter(PARAM_AD_Language);
    return this;
}


}