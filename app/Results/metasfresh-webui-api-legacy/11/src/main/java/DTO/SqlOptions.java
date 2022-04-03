package DTO;
 import org.adempiere.exceptions.AdempiereException;
import de.metas.util.Check;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
public class SqlOptions {

 private  SqlOptions USE_TABLE_ALIAS_MASTER;

 private  boolean useTableAlias;

 private  String tableAlias;

 private  String tableName;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getTableAlias(){
    if (!useTableAlias) {
        throw new AdempiereException("tableAlias is not available for " + this);
    }
    return tableAlias;
}


public String getTableNameOrAlias(){
    return useTableAlias ? tableAlias : tableName;
}


public SqlOptions usingTableName(String tableName){
    return SqlOptions.builder().useTableAlias(false).tableName(tableName).build();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/usingTableName"))

.queryParam("tableName",tableName);
SqlOptions aux = restTemplate.getForObject(builder.toUriString(),SqlOptions.class);
return aux;
}


}