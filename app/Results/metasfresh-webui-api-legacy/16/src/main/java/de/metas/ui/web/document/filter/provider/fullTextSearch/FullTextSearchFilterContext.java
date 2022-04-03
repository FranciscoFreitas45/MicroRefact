package de.metas.ui.web.document.filter.provider.fullTextSearch;
 import org.adempiere.model.InterfaceWrapperHelper;
import org.elasticsearch.client.Client;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
@Value
@Builder
public class FullTextSearchFilterContext {

@NonNull
 final  Client elasticsearchClient;

@NonNull
 final  String modelTableName;

@NonNull
 final  String esIndexName;

@NonNull
@Singular
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