package de.metas.ui.web.view.descriptor;
 import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import de.metas.ui.web.base.model.I_T_WEBUI_ViewSelection;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentIdsSelection;
import de.metas.ui.web.window.descriptor.sql.PlainSqlEntityFieldBinding;
import de.metas.ui.web.window.descriptor.sql.SqlEntityFieldBinding;
import de.metas.ui.web.window.model.sql.SqlDocumentQueryBuilder;
import de.metas.util.Check;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.util.DB;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
@ToString(of = "webuiSelectionColumnNamesByKeyColumnName")
public class SqlViewKeyColumnNamesMap {

 private  List<SqlEntityFieldBinding> keyFields;

 private  ImmutableMap<String,String> webuiSelectionColumnNamesByKeyColumnName;

 private  ImmutableList<String> keyColumnNames;

 private  String singleKeyColumnName;

 private  ImmutableList<String> webuiSelectionColumnNames;

 private  String singleWebuiSelectionColumnName;


public String getWebuiSelectionColumnNamesCommaSeparated(Function<String,String> mapper){
    return getWebuiSelectionColumnNames().stream().map(mapper).collect(Collectors.joining(", "));
}


public boolean isSingleKey(){
    return singleKeyColumnName != null;
}


@Builder(builderMethodName = "prepareSqlFilterByRowIds", builderClassName = "SqlFilterByRowIdsBuilder")
public SqlAndParams getSqlFilterByRowIds(DocumentIdsSelection rowIds,SqlViewRowIdsConverter rowIdsConverter,String sqlColumnPrefix,boolean useKeyColumnName,boolean embedSqlParams){
    if (rowIds.isEmpty()) {
        throw new AdempiereException("rowIds shall not be empty");
    }
    if (isSingleKey()) {
        final String selectionColumnName = useKeyColumnName ? getSingleKeyColumnName() : getSingleWebuiSelectionColumnName();
        final String keyColumnName = (sqlColumnPrefix != null ? sqlColumnPrefix : "") + selectionColumnName;
        final Set<Integer> recordIds = rowIdsConverter != null ? rowIdsConverter.convertToRecordIds(rowIds) : rowIds.toIntSet();
        if (recordIds.isEmpty()) {
            throw new AdempiereException("No recordIds were extracted from " + rowIds);
        }
        final List<Object> sqlParams = embedSqlParams ? null : new ArrayList<>();
        final String sql = DB.buildSqlList(keyColumnName, recordIds, sqlParams);
        return SqlAndParams.of(sql, sqlParams != null ? sqlParams : ImmutableList.of());
    } else {
        final List<SqlAndParams> sqls = rowIds.toSet().stream().map(rowId -> getSqlFilterByRowId(rowId, sqlColumnPrefix, useKeyColumnName, embedSqlParams)).collect(ImmutableList.toImmutableList());
        return SqlAndParams.and(sqls);
    }
}


public String getSingleWebuiSelectionColumnName(){
    if (singleWebuiSelectionColumnName == null) {
        throw new AdempiereException("Not single primary key: " + this);
    }
    return singleWebuiSelectionColumnName;
}


public String getWebuiSelectionColumnNameForKeyColumnName(String keyColumnName){
    final String webuiSelectionColumnName = webuiSelectionColumnNamesByKeyColumnName.get(keyColumnName);
    if (webuiSelectionColumnName == null) {
        throw new AdempiereException("No " + I_T_WEBUI_ViewSelection.Table_Name + " mapping found for " + keyColumnName);
    }
    return webuiSelectionColumnName;
}


public List<String> getKeyColumnNames(){
    if (keyColumnNames.isEmpty()) {
        throw new AdempiereException("No key column names defined");
    }
    return keyColumnNames;
}


public String buildKeyColumnNameEffective(String keyColumnName,String sqlColumnPrefix,boolean useKeyColumnName){
    final String selectionColumnName = useKeyColumnName ? keyColumnName : getWebuiSelectionColumnNameForKeyColumnName(keyColumnName);
    if (sqlColumnPrefix != null && !sqlColumnPrefix.isEmpty()) {
        return sqlColumnPrefix + selectionColumnName;
    } else {
        return selectionColumnName;
    }
}


@Builder(builderMethodName = "prepareSqlJoinCondition", builderClassName = "SqlJoinConditionBuilder")
public String buildSqlJoinCondition(String tableAlias1,boolean useKeyColumnNames1,String tableAlias2,boolean useKeyColumnNames2){
    Check.assumeNotEmpty(tableAlias1, "tableAlias1 is not empty");
    Check.assumeNotEmpty(tableAlias2, "tableAlias2 is not empty");
    final StringBuilder sqlJoinCondition = new StringBuilder();
    for (final String keyColumnName : getKeyColumnNames()) {
        final String columnName1 = useKeyColumnNames1 ? keyColumnName : getWebuiSelectionColumnNameForKeyColumnName(keyColumnName);
        final String columnName2 = useKeyColumnNames2 ? keyColumnName : getWebuiSelectionColumnNameForKeyColumnName(keyColumnName);
        if (sqlJoinCondition.length() > 0) {
            sqlJoinCondition.append(" AND ");
        }
        sqlJoinCondition.append(tableAlias1).append(".").append(columnName1).append("=").append(tableAlias2).append(".").append(columnName2);
    }
    return sqlJoinCondition.toString();
}


public ImmutableMap<String,String> buildWebuiSelectionColumnNamesByKeyColumnName(List<SqlEntityFieldBinding> keyFields){
    final List<String> availableIntKeys = new ArrayList<>(I_T_WEBUI_ViewSelection.COLUMNNAME_IntKeys);
    final List<String> availableStringKeys = new ArrayList<>(I_T_WEBUI_ViewSelection.COLUMNNAME_StringKeys);
    final ImmutableMap.Builder<String, String> keyColumnName2selectionColumnName = ImmutableMap.builder();
    for (final SqlEntityFieldBinding keyField : keyFields) {
        final List<String> availableKeys;
        final Class<?> sqlValueClass = keyField.getSqlValueClass();
        if (Integer.class.equals(sqlValueClass) || int.class.equals(sqlValueClass)) {
            availableKeys = availableIntKeys;
        } else if (String.class.equals(sqlValueClass)) {
            availableKeys = availableStringKeys;
        } else {
            throw new AdempiereException("Only integer or string types are supported for key columns: " + keyField);
        }
        if (availableKeys.isEmpty()) {
            throw new AdempiereException("No more available key columns found in " + I_T_WEBUI_ViewSelection.Table_Name + " for " + keyField);
        }
        final String webuiSelectionColumnName = availableKeys.remove(0);
        keyColumnName2selectionColumnName.put(keyField.getColumnName(), webuiSelectionColumnName);
    }
    return keyColumnName2selectionColumnName.build();
}


public String getKeyColumnNamePairsCommaSeparated(BiFunction<String,String,String> mapper){
    return webuiSelectionColumnNamesByKeyColumnName.entrySet().stream().map(e -> {
        final String keyColumnName = e.getKey();
        final String webuiSelectionColumnName = e.getValue();
        return mapper.apply(keyColumnName, webuiSelectionColumnName);
    }).collect(Collectors.joining(", "));
}


public String getSingleKeyColumnName(){
    if (singleKeyColumnName == null) {
        throw new AdempiereException("Not single primary key: " + this);
    }
    return singleKeyColumnName;
}


public String getKeyColumnNamesCommaSeparated(Function<String,String> mapper){
    return getKeyColumnNames().stream().map(mapper).collect(Collectors.joining(", "));
}


public List<Object> getSqlValuesList(DocumentId rowId){
    return ImmutableList.copyOf(extractComposedKey(rowId).values());
}


public SqlViewKeyColumnNamesMap ofIntKeyField(String columnName){
    return ofKeyField(PlainSqlEntityFieldBinding.intField(columnName));
}


public String getSqlIsNullExpression(String sqlTableAlias){
    final String sqlIsNull = getKeyColumnNames().stream().map(keyColumnName -> sqlTableAlias + "." + keyColumnName + " is null").collect(Collectors.joining(" and "));
    return "(case when " + sqlIsNull + " then 'Y' else 'N' end)";
}


public boolean isKeyPartFieldName(String fieldName){
    return webuiSelectionColumnNamesByKeyColumnName.containsKey(fieldName);
}


public DocumentId retrieveRowId(ResultSet rs,String sqlColumnPrefix,boolean useKeyColumnNames){
    final List<Object> rowIdParts = keyFields.stream().map(keyField -> retrieveRowIdPart(rs, buildKeyColumnNameEffective(keyField.getColumnName(), sqlColumnPrefix, useKeyColumnNames), keyField.getSqlValueClass())).collect(Collectors.toList());
    final boolean isNotNull = rowIdParts.stream().anyMatch(Objects::nonNull);
    if (!isNotNull) {
        return null;
    }
    return DocumentId.ofComposedKeyParts(rowIdParts);
}


public List<String> getWebuiSelectionColumnNames(){
    if (webuiSelectionColumnNames.isEmpty()) {
        Check.fail("No key column names defined; this={}", this);
    }
    return webuiSelectionColumnNames;
}


public Object retrieveRowIdPart(ResultSet rs,String columnName,Class<?> sqlValueClass){
    try {
        if (Integer.class.equals(sqlValueClass) || int.class.equals(sqlValueClass)) {
            final int rowIdPart = rs.getInt(columnName);
            if (rs.wasNull()) {
                return null;
            }
            return rowIdPart;
        } else if (String.class.equals(sqlValueClass)) {
            return rs.getString(columnName);
        } else {
            throw new AdempiereException("Unsupported type " + sqlValueClass + " for " + columnName);
        }
    } catch (final SQLException ex) {
        throw new DBException("Failed fetching " + columnName + " (" + sqlValueClass + ")", ex);
    }
}


public SqlViewKeyColumnNamesMap ofKeyFields(Collection<? extends SqlEntityFieldBinding> keyFields){
    return new SqlViewKeyColumnNamesMap(ImmutableList.copyOf(keyFields));
}


public String getSqlJoinCondition(String sourceTableAlias,String selectionTableAlias){
    return prepareSqlJoinCondition().tableAlias1(sourceTableAlias).useKeyColumnNames1(true).tableAlias2(selectionTableAlias).useKeyColumnNames2(false).build();
}


public Map<String,Object> extractComposedKey(DocumentId rowId){
    return SqlDocumentQueryBuilder.extractComposedKey(rowId, keyFields);
}


public int getKeyPartsCount(){
    return webuiSelectionColumnNamesByKeyColumnName.size();
}


public SqlAndParams getSqlValuesCommaSeparated(DocumentId rowId){
    final StringBuilder sql = new StringBuilder();
    final List<Object> sqlParams = new ArrayList<>();
    extractComposedKey(rowId).forEach((keyColumnName, value) -> {
        if (sql.length() > 0) {
            sql.append(", ");
        }
        sql.append("?");
        sqlParams.add(value);
    });
    return SqlAndParams.of(sql.toString(), sqlParams);
}


public SqlViewKeyColumnNamesMap ofKeyField(SqlEntityFieldBinding keyField){
    return new SqlViewKeyColumnNamesMap(ImmutableList.of(keyField));
}


public SqlAndParams getSqlFilterByRowId(DocumentId rowId,String sqlColumnPrefix,boolean useKeyColumnName,boolean embedSqlParams){
    final List<Object> sqlParams = new ArrayList<>();
    final StringBuilder sql = new StringBuilder();
    extractComposedKey(rowId).forEach((keyColumnName, value) -> {
        final String selectionColumnName = useKeyColumnName ? keyColumnName : getWebuiSelectionColumnNameForKeyColumnName(keyColumnName);
        sql.append(sqlColumnPrefix != null ? sqlColumnPrefix : "").append(selectionColumnName).append("=?");
        sqlParams.add(value);
    });
    return SqlAndParams.of(sql.toString(), sqlParams);
}


}