package DTO;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryBuilder;
import org.adempiere.model.PlainContextAware;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import com.google.common.collect.ImmutableSet;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentFieldWidgetType;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.util.Services;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
public class LabelsLookup implements LookupDataSourceFetcher,LookupDescriptor{

 private  String labelsTableName;

 private  String labelsValueColumnName;

 private  LookupDataSource labelsValuesLookupDataSource;

 private  boolean labelsValuesUseNumericKey;

 private  String labelsLinkColumnName;

 private  String tableName;

 private  String linkColumnName;

 private  Set<CtxName> parameters;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


@Override
public Class<?> getValueClass(){
    return DocumentFieldWidgetType.Labels.getValueClass();
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.lookup;
}


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return this;
}


@Override
public String getCachePrefix(){
    // not important because isCached() returns false
    return null;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public Optional<String> getLookupTableName(){
    return Optional.of(labelsTableName);
}


@Override
public Set<String> getDependsOnFieldNames(){
    return CtxNames.toNames(parameters);
}


public LabelsLookup cast(LookupDescriptor lookupDescriptor){
    return (LabelsLookup) lookupDescriptor;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/cast"))

.queryParam("lookupDescriptor",lookupDescriptor);
LabelsLookup aux = restTemplate.getForObject(builder.toUriString(),LabelsLookup.class);
return aux;
}


public LookupValuesList retrieveExistingValues(int linkId){
    if (linkId <= 0) {
        return LookupValuesList.EMPTY;
    }
    final List<String> existingItems = retrieveExistingValuesRecordQuery(linkId).create().listDistinct(labelsValueColumnName, String.class);
    if (existingItems.isEmpty()) {
        return LookupValuesList.EMPTY;
    }
    return labelsValuesLookupDataSource.findByIdsOrdered(existingItems);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/retrieveExistingValues"))

.queryParam("linkId",linkId);
LookupValuesList aux = restTemplate.getForObject(builder.toUriString(),LookupValuesList.class);
return aux;
}


}