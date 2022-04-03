package de.metas.ui.web.dataentry.window.descriptor.factory;
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


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return dataEntryListValueDataSourceFetcher;
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.list;
}


@Override
public boolean hasParameters(){
    return false;
}


@Override
public boolean isHighVolume(){
    return false;
}


public DataEntryListValueLookupDescriptor of(List<DataEntryListValue> listValues){
    return new DataEntryListValueLookupDescriptor(listValues);
}


@Override
public boolean isNumericKey(){
    return true;
}


@Override
public Set<String> getDependsOnFieldNames(){
    return ImmutableSet.of();
}


}