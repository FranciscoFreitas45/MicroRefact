package DTO;
 import org.adempiere.model.InterfaceWrapperHelper;
import org.elasticsearch.client.Client;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
public class FullTextSearchFilterContext {

 final  Client elasticsearchClient;

 final  String modelTableName;

 final  String esIndexName;

 final  ImmutableSet<String> esSearchFieldNames;


public String getKeyColumnName(){
    return InterfaceWrapperHelper.getKeyColumnName(getModelTableName());
}


public String getEsKeyColumnName(){
    return getKeyColumnName();
}


public String[] getEsSearchFieldNamesAsArray(){
    return esSearchFieldNames.toArray(new String[esSearchFieldNames.size()]);
}


}