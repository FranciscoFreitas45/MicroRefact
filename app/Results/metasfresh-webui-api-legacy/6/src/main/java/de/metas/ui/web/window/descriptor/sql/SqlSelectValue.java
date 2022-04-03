package de.metas.ui.web.window.descriptor.sql;
 import java.util.Objects;
import de.metas.util.Check;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class SqlSelectValue {

 private  String tableNameOrAlias;

 private  String columnName;

@Getter
 private  String virtualColumnSql;

@Getter
 private  String columnNameAlias;


public SqlSelectValue withVirtualColumnSql(String virtualColumnSql){
    return !Objects.equals(this.virtualColumnSql, virtualColumnSql) ? toBuilder().virtualColumnSql(virtualColumnSql).build() : this;
}


public boolean isVirtualColumn(){
    return virtualColumnSql != null;
}


public SqlSelectValue withJoinOnTableNameOrAlias(String tableNameOrAlias){
    final String tableNameOrAliasEffective = virtualColumnSql != null ? null : tableNameOrAlias;
    return !Objects.equals(this.tableNameOrAlias, tableNameOrAliasEffective) ? toBuilder().tableNameOrAlias(tableNameOrAliasEffective).build() : this;
}


public String toSqlString(){
    if (virtualColumnSql != null) {
        return virtualColumnSql;
    } else if (tableNameOrAlias != null) {
        return tableNameOrAlias + "." + columnName;
    } else {
        return columnName;
    }
}


public SqlSelectValue withColumnNameAlias(String columnNameAlias){
    return !Objects.equals(this.columnNameAlias, columnNameAlias) ? toBuilder().columnNameAlias(columnNameAlias).build() : this;
}


public String toSqlStringWithColumnNameAlias(){
    return toSqlString() + " AS " + columnNameAlias;
}


}