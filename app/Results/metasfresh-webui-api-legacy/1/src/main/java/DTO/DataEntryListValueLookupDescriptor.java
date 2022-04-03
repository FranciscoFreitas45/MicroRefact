package DTO;
 import java.util.List;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.dataentry.layout.DataEntryListValue;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import lombok.NonNull;
public class DataEntryListValueLookupDescriptor implements LookupDescriptor{

 private  DataEntryListValueDataSourceFetcher dataEntryListValueDataSourceFetcher;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return dataEntryListValueDataSourceFetcher;
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.list;
}


@Override
public Set<String> getDependsOnFieldNames(){
    return ImmutableSet.of();
}


public DataEntryListValueLookupDescriptor of(List<DataEntryListValue> listValues){
    return new DataEntryListValueLookupDescriptor(listValues);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("listValues",listValues);
DataEntryListValueLookupDescriptor aux = restTemplate.getForObject(builder.toUriString(),DataEntryListValueLookupDescriptor.class);
return aux;
}


}