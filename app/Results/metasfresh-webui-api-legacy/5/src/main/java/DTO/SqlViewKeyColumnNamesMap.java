package DTO;
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
public class SqlViewKeyColumnNamesMap {

 private  List<SqlEntityFieldBinding> keyFields;

 private  ImmutableMap<String,String> webuiSelectionColumnNamesByKeyColumnName;

 private  ImmutableList<String> keyColumnNames;

 private  String singleKeyColumnName;

 private  ImmutableList<String> webuiSelectionColumnNames;

 private  String singleWebuiSelectionColumnName;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getWebuiSelectionColumnNamesCommaSeparated(Function<String,String> mapper){
    return getWebuiSelectionColumnNames().stream().map(mapper).collect(Collectors.joining(", "));
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


public String getSqlIsNullExpression(String sqlTableAlias){
    final String sqlIsNull = getKeyColumnNames().stream().map(keyColumnName -> sqlTableAlias + "." + keyColumnName + " is null").collect(Collectors.joining(" and "));
    return "(case when " + sqlIsNull + " then 'Y' else 'N' end)";
}


public List<String> getWebuiSelectionColumnNames(){
    if (webuiSelectionColumnNames.isEmpty()) {
        Check.fail("No key column names defined; this={}", this);
    }
    return webuiSelectionColumnNames;
}


public String getSqlJoinCondition(String sourceTableAlias,String selectionTableAlias){
    return prepareSqlJoinCondition().tableAlias1(sourceTableAlias).useKeyColumnNames1(true).tableAlias2(selectionTableAlias).useKeyColumnNames2(false).build();
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


public SqlViewKeyColumnNamesMap ofIntKeyField(String columnName){
    return ofKeyField(PlainSqlEntityFieldBinding.intField(columnName));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ofIntKeyField"))

.queryParam("columnName",columnName);
SqlViewKeyColumnNamesMap aux = restTemplate.getForObject(builder.toUriString(),SqlViewKeyColumnNamesMap.class);
return aux;
}


}