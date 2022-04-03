package DTO;
 import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import de.metas.dataentry.DataEntryListValueId;
import de.metas.dataentry.layout.DataEntryListValue;
import de.metas.dataentry.model.I_DataEntry_ListValue;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext.Builder;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import lombok.NonNull;
import lombok.ToString;
public class DataEntryListValueDataSourceFetcher implements LookupDataSourceFetcher{

 private  CtxName CTX_NAME_LIST_VALUE_ID;

 private  String LOOKUP_TABLE_NAME;

 private  ImmutableSet<CtxName> dependsOnContextVariables;

 private  ImmutableBiMap<DataEntryListValueId,IntegerLookupValue> id2LookupValue;


public DataEntryListValueId getListValueIdForLookup(IntegerLookupValue value){
    return id2LookupValue.inverse().get(value);
}


public Object getLookupForForListValueId(DataEntryListValueId dataEntryListValueId){
    return id2LookupValue.get(dataEntryListValueId);
}


@Override
public String getCachePrefix(){
    return DataEntryListValueDataSourceFetcher.class.getSimpleName();
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public Optional<String> getLookupTableName(){
    return Optional.of(LOOKUP_TABLE_NAME);
}


}