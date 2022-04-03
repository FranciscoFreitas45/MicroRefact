package de.metas.ui.web.document.filter.sql;
 import java.util.ArrayList;
import java.util.List;
import org.adempiere.ad.dao.IQueryFilterModifier;
import org.adempiere.ad.dao.impl.DateTruncQueryFilterModifier;
import org.adempiere.ad.dao.impl.NullQueryFilterModifier;
import org.adempiere.db.DBConstants;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.DocumentFilterParam;
import de.metas.ui.web.document.filter.DocumentFilterParam.Operator;
import de.metas.ui.web.document.filter.DocumentFilterParamDescriptor;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.sql.SqlEntityBinding;
import de.metas.ui.web.window.descriptor.sql.SqlEntityFieldBinding;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import de.metas.ui.web.window.model.lookup.LabelsLookup;
import de.metas.ui.web.window.model.sql.SqlDocumentsRepository;
import de.metas.ui.web.window.model.sql.SqlOptions;
import lombok.NonNull;
public class SqlDefaultDocumentFilterConverter implements SqlDocumentFilterConverter{

 private  SqlEntityBinding entityBinding;


public String buildSqlWhereClause_Like(String sqlColumnExpr,boolean negate,boolean ignoreCase,Object sqlValue,SqlParamsCollector sqlParams){
    if (sqlValue == null) {
        return buildSqlWhereClause_IsNull(sqlColumnExpr, negate);
    }
    String sqlValueStr = sqlValue.toString();
    if (sqlValueStr.isEmpty()) {
        // NO value supplied, it's pointless to enforce a LIKE on that...
        // => considering all matches
        return "";
    }
    if (!sqlValueStr.startsWith("%")) {
        sqlValueStr = "%" + sqlValueStr;
    }
    if (!sqlValueStr.endsWith("%")) {
        sqlValueStr = sqlValueStr + "%";
    }
    final String sqlOperator = (negate ? " NOT " : " ") + (ignoreCase ? "ILIKE " : "LIKE ");
    return new StringBuilder().append(DBConstants.FUNCNAME_unaccent_string).append("(").append(sqlColumnExpr).append(", 1)").append(sqlOperator).append(DBConstants.FUNCNAME_unaccent_string).append("(").append(sqlParams.placeholder(sqlValueStr)).append(", 1)").toString();
}


public String buildSqlWhereClause_IsNull(String sqlColumnExpr,boolean negate){
    return new StringBuilder().append(sqlColumnExpr).append(negate ? " IS NOT NULL" : " IS NULL").toString();
}


public String buildSqlWhereClause_InArray(String sqlColumnExpr,List<Object> sqlValues,SqlParamsCollector sqlParams){
    if (sqlValues == null || sqlValues.isEmpty()) {
        // TODO log a warning or throw exception?!
        return null;
    }
    if (sqlParams.isCollecting()) {
        final List<Object> sqlValuesEffective = new ArrayList<>();
        final String sql = DB.buildSqlList(sqlColumnExpr, sqlValues, sqlValuesEffective);
        // safe
        sqlParams.collectAll(sqlValuesEffective);
        return sql;
    } else {
        return DB.buildSqlList(sqlParams.toList());
    }
}


public String replaceTableNameWithTableAlias(String sql,String tableAlias){
    return SqlEntityBinding.replaceTableNameWithTableAlias(sql, entityBinding.getTableName(), tableAlias);
}


@Override
public boolean canConvert(String filterId){
    return true;
}


public String buildSqlWhereClause(SqlParamsCollector sqlParams,String filterId,DocumentFilterParam filterParam,SqlOptions sqlOpts){
    // 
    // SQL filter
    if (filterParam.isSqlFilter()) {
        String sqlWhereClause = filterParam.getSqlWhereClause().getSql();
        if (sqlOpts.isUseTableAlias()) {
            sqlWhereClause = replaceTableNameWithTableAlias(sqlWhereClause, sqlOpts.getTableAlias());
        }
        final List<Object> sqlWhereClauseParams = filterParam.getSqlWhereClause().getSqlParams();
        sqlParams.collectAll(sqlWhereClauseParams);
        return sqlWhereClause;
    }
    // 
    // Labels filter
    final String parameterName = filterParam.getFieldName();
    final DocumentFieldWidgetType widgetType = getParameterWidgetType(parameterName);
    if (widgetType == DocumentFieldWidgetType.Labels) {
        final DocumentFilterParamDescriptor paramDescriptor = getParameterDescriptor(filterId, parameterName);
        return buildSqlWhereClause_LabelsWidget(filterParam, paramDescriptor, sqlParams, sqlOpts);
    } else // 
    // Standard filter
    {
        return buildSqlWhereClause_StandardWidget(sqlParams, filterParam, sqlOpts);
    }
}


public SqlDefaultDocumentFilterConverter newInstance(SqlEntityBinding entityBinding){
    return new SqlDefaultDocumentFilterConverter(entityBinding);
}


public Object convertToSqlValue(Object value,SqlEntityFieldBinding fieldBinding,IQueryFilterModifier modifier){
    final String columnName = fieldBinding.getColumnName();
    final DocumentFieldWidgetType widgetType = fieldBinding.getWidgetType();
    final Class<?> targetClass = fieldBinding.getSqlValueClass();
    final Object sqlValue = SqlDocumentsRepository.convertValueToPO(value, columnName, widgetType, targetClass);
    return modifier.convertValue(columnName, sqlValue, null);
}


public String buildSqlWhereClause_Between(String sqlColumnExpr,Object sqlValue,Object sqlValueTo,SqlParamsCollector sqlParams){
    if (sqlValue == null) {
        if (sqlValueTo == null) {
            // Both values are null => considering all matches
            return "";
        }
        return buildSqlWhereClause_Compare(sqlColumnExpr, "<=", sqlValueTo, sqlParams);
    }
    if (sqlValueTo == null) {
        // NOTE: at this point sqlValue is not null!
        return buildSqlWhereClause_Compare(sqlColumnExpr, ">=", sqlValue, sqlParams);
    }
    return new StringBuilder().append(sqlColumnExpr).append(" BETWEEN ").append(sqlParams.placeholder(sqlValue)).append(" AND ").append(sqlParams.placeholder(sqlValueTo)).toString();
}


public LookupValuesList extractLookupValuesList(DocumentFilterParam filterParam){
    final Object valueObj = filterParam.getValue();
    if (valueObj == null) {
        return LookupValuesList.EMPTY;
    } else if (valueObj instanceof String && ((String) valueObj).isEmpty()) {
        return LookupValuesList.EMPTY;
    } else if (valueObj instanceof LookupValuesList) {
        return (LookupValuesList) valueObj;
    } else {
        throw new AdempiereException("Connot convert " + valueObj + " to " + LookupValuesList.class + " for " + filterParam);
    }
}


@Override
public String getSql(SqlParamsCollector sqlParams,DocumentFilter filter,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    final String filterId = filter.getFilterId();
    final StringBuilder sql = new StringBuilder();
    for (final DocumentFilterParam filterParam : filter.getParameters()) {
        final String sqlFilterParam = buildSqlWhereClause(sqlParams, filterId, filterParam, sqlOpts);
        if (Check.isEmpty(sqlFilterParam, true)) {
            continue;
        }
        if (sql.length() > 0) {
            sql.append(filterParam.isJoinAnd() ? " AND " : " OR ");
        }
        sql.append("(").append(sqlFilterParam).append(")");
    }
    return sql.toString();
}


public String buildSqlWhereClause_LabelsWidget(DocumentFilterParam filterParam,DocumentFilterParamDescriptor paramDescriptor,SqlParamsCollector sqlParams,SqlOptions sqlOpts){
    final LookupValuesList lookupValues = extractLookupValuesList(filterParam);
    if (lookupValues.isEmpty()) {
        return null;
    }
    final String tableAlias = sqlOpts.getTableNameOrAlias();
    final LabelsLookup lookup = paramDescriptor.getLookupDescriptor().map(LabelsLookup::cast).get();
    final String labelsTableName = lookup.getLabelsTableName();
    final String labelsLinkColumnName = lookup.getLabelsLinkColumnName();
    final String linkColumnName = lookup.getLinkColumnName();
    final String labelsValueColumnName = lookup.getLabelsValueColumnName();
    final StringBuilder sql = new StringBuilder();
    for (final LookupValue lookupValue : lookupValues) {
        if (sql.length() > 0) {
            sql.append(" AND ");
        }
        final Object labelValue = lookup.isLabelsValuesUseNumericKey() ? lookupValue.getIdAsInt() : lookupValue.getIdAsString();
        sql.append("EXISTS (SELECT 1 FROM " + labelsTableName + " labels " + " WHERE labels." + labelsLinkColumnName + "=" + tableAlias + "." + linkColumnName + " AND labels." + labelsValueColumnName + "=" + sqlParams.placeholder(labelValue) + ")");
    }
    return sql.toString();
}


public IQueryFilterModifier extractFieldModifier(DocumentFieldWidgetType widgetType){
    // NOTE: for performance reasons we are not truncating the field if the field is already Date.
    // If we would do so, it might happen that the index we have on that column will not be used.
    // More, we assume the column already contains truncated Date(s).
    if (widgetType.isDateWithTime()) {
        return DateTruncQueryFilterModifier.DAY;
    } else {
        return NullQueryFilterModifier.instance;
    }
}


public String buildSqlWhereClause_Equals(String sqlColumnExpr,Object sqlValue,boolean negate,SqlParamsCollector sqlParams){
    if (sqlValue == null) {
        return buildSqlWhereClause_IsNull(sqlColumnExpr, negate);
    }
    return new StringBuilder().append(sqlColumnExpr).append(negate ? " <> " : " = ").append(sqlParams.placeholder(sqlValue)).toString();
}


public SqlEntityFieldBinding getParameterBinding(String parameterName){
    final SqlEntityFieldBinding fieldBinding = entityBinding.getFieldByFieldName(parameterName);
    return fieldBinding;
}


public DocumentFieldWidgetType getParameterWidgetType(String parameterName){
    return getParameterBinding(parameterName).getWidgetType();
}


public IQueryFilterModifier extractValueModifier(DocumentFieldWidgetType widgetType){
    if (widgetType.isDateWithTime()) {
        return DateTruncQueryFilterModifier.DAY;
    } else {
        return NullQueryFilterModifier.instance;
    }
}


@VisibleForTesting
public SqlSelectValue replaceTableNameWithTableAliasIfNeeded(SqlSelectValue columnSql,SqlOptions sqlOpts){
    if (sqlOpts.isUseTableAlias()) {
        SqlSelectValue columnSqlEffective = columnSql;
        columnSqlEffective = columnSqlEffective.withJoinOnTableNameOrAlias(sqlOpts.getTableAlias());
        if (columnSqlEffective.isVirtualColumn()) {
            final String virtualColumnSql = replaceTableNameWithTableAlias(columnSqlEffective.getVirtualColumnSql(), sqlOpts.getTableAlias());
            columnSqlEffective = columnSqlEffective.withVirtualColumnSql(virtualColumnSql);
        }
        return columnSqlEffective;
    } else {
        return columnSql;
    }
}


public DocumentFilterParamDescriptor getParameterDescriptor(String filterId,String parameterName){
    return entityBinding.getFilterDescriptors().getByFilterId(filterId).getParameterByName(parameterName);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("entityBinding", entityBinding).toString();
}


public String buildSqlWhereClause_StandardWidget(SqlParamsCollector sqlParams,DocumentFilterParam filterParam,SqlOptions sqlOpts){
    final SqlEntityFieldBinding paramBinding = getParameterBinding(filterParam.getFieldName());
    final DocumentFieldWidgetType widgetType = paramBinding.getWidgetType();
    final String columnSqlString;
    {
        final IQueryFilterModifier fieldModifier = extractFieldModifier(widgetType);
        final SqlSelectValue columnSqlEffective = replaceTableNameWithTableAliasIfNeeded(paramBinding.getSqlSelectValue(), sqlOpts);
        columnSqlString = fieldModifier.getColumnSql(columnSqlEffective.toSqlString());
    }
    final IQueryFilterModifier valueModifier = extractValueModifier(widgetType);
    final Operator operator = filterParam.getOperator();
    switch(operator) {
        case EQUAL:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                final boolean negate = false;
                return buildSqlWhereClause_Equals(columnSqlString, sqlValue, negate, sqlParams);
            }
        case NOT_EQUAL:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                final boolean negate = true;
                return buildSqlWhereClause_Equals(columnSqlString, sqlValue, negate, sqlParams);
            }
        case IN_ARRAY:
            {
                final List<Object> sqlValuesList = filterParam.getValueAsList(itemObj -> convertToSqlValue(itemObj, paramBinding, valueModifier));
                return buildSqlWhereClause_InArray(columnSqlString, sqlValuesList, sqlParams);
            }
        case GREATER:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                return buildSqlWhereClause_Compare(columnSqlString, ">", sqlValue, sqlParams);
            }
        case GREATER_OR_EQUAL:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                return buildSqlWhereClause_Compare(columnSqlString, ">=", sqlValue, sqlParams);
            }
        case LESS:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                return buildSqlWhereClause_Compare(columnSqlString, "<", sqlValue, sqlParams);
            }
        case LESS_OR_EQUAL:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                return buildSqlWhereClause_Compare(columnSqlString, "<=", sqlValue, sqlParams);
            }
        case LIKE:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                final boolean negate = false;
                final boolean ignoreCase = false;
                return buildSqlWhereClause_Like(columnSqlString, negate, ignoreCase, sqlValue, sqlParams);
            }
        case NOT_LIKE:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                final boolean negate = true;
                final boolean ignoreCase = false;
                return buildSqlWhereClause_Like(columnSqlString, negate, ignoreCase, sqlValue, sqlParams);
            }
        case LIKE_I:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                final boolean negate = false;
                final boolean ignoreCase = true;
                return buildSqlWhereClause_Like(columnSqlString, negate, ignoreCase, sqlValue, sqlParams);
            }
        case NOT_LIKE_I:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                final boolean negate = true;
                final boolean ignoreCase = true;
                return buildSqlWhereClause_Like(columnSqlString, negate, ignoreCase, sqlValue, sqlParams);
            }
        case BETWEEN:
            {
                final Object sqlValue = convertToSqlValue(filterParam.getValue(), paramBinding, valueModifier);
                final Object sqlValueTo = convertToSqlValue(filterParam.getValueTo(), paramBinding, valueModifier);
                return buildSqlWhereClause_Between(columnSqlString, sqlValue, sqlValueTo, sqlParams);
            }
        default:
            {
                throw new IllegalArgumentException("Operator not supported: " + operator);
            }
    }
}


public String buildSqlWhereClause_Compare(String sqlColumnExpr,String sqlOperator,Object sqlValue,SqlParamsCollector sqlParams){
    return new StringBuilder().append(sqlColumnExpr).append(sqlOperator).append(sqlParams.placeholder(sqlValue)).toString();
}


}