package de.metas.ui.web.window.model.sql;
 import org.adempiere.exceptions.AdempiereException;
import de.metas.util.Check;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
@EqualsAndHashCode
@ToString
public class SqlOptions {

 private  SqlOptions USE_TABLE_ALIAS_MASTER;

 private  boolean useTableAlias;

 private  String tableAlias;

 private  String tableName;


public String getTableAlias(){
    if (!useTableAlias) {
        throw new AdempiereException("tableAlias is not available for " + this);
    }
    return tableAlias;
}


public SqlOptions usingTableName(String tableName){
    return SqlOptions.builder().useTableAlias(false).tableName(tableName).build();
}


public String getTableNameOrAlias(){
    return useTableAlias ? tableAlias : tableName;
}


public boolean isUseTableAlias(){
    return useTableAlias;
}


public SqlOptions usingTableAlias(String sqlTableAlias){
    if (USE_TABLE_ALIAS_MASTER.tableAlias.equals(sqlTableAlias)) {
        return USE_TABLE_ALIAS_MASTER;
    }
    return builder().useTableAlias(true).tableAlias(sqlTableAlias).build();
}


}