package de.metas.ui.web.view.descriptor;
 import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.descriptor.sql.SqlSelectValue;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import lombok.ToString;
@Builder
@ToString
public class SqlViewGroupingBinding {

@Singular("groupBy")
 private  ImmutableSet<String> groupByFieldNames;

@Singular("columnSql")
 private  ImmutableMap<String,SqlSelectValue> columnSqlByFieldName;

@NonNull
@Default
@Getter
 private  SqlViewRowIdsConverter rowIdsConverter;


public boolean isGroupBy(String fieldName){
    return groupByFieldNames.contains(fieldName);
}


public SqlSelectValue getColumnSqlByFieldName(String fieldName){
    return columnSqlByFieldName.get(fieldName);
}


public boolean isAggregated(String fieldName){
    return columnSqlByFieldName.containsKey(fieldName);
}


public ImmutableSet<String> getGroupByFieldNames(){
    return groupByFieldNames;
}


}