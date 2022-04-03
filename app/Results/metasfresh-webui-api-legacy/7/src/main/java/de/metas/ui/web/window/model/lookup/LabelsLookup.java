package de.metas.ui.web.window.model.lookup;
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

@Getter
 private  String labelsTableName;

@Getter
 private  String labelsValueColumnName;

 private  LookupDataSource labelsValuesLookupDataSource;

@Getter
 private  boolean labelsValuesUseNumericKey;

@Getter
 private  String labelsLinkColumnName;

 private  String tableName;

@Getter
 private  String linkColumnName;

 private  Set<CtxName> parameters;


@Override
public Class<?> getValueClass(){
    return DocumentFieldWidgetType.Labels.getValueClass();
}


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    final String filter = evalCtx.getFilter();
    return labelsValuesLookupDataSource.findEntities(evalCtx, filter);
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
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    final String id = evalCtx.getIdToFilterAsString();
    if (id == null) {
        throw new IllegalStateException("No ID provided in " + evalCtx);
    }
    return labelsValuesLookupDataSource.findById(id);
}


@Override
public String getCachePrefix(){
    // not important because isCached() returns false
    return null;
}


@Override
public boolean isHighVolume(){
    return false;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public boolean isNumericKey(){
    return false;
}


@Override
public void cacheInvalidate(){
    labelsValuesLookupDataSource.cacheInvalidate();
}


public IQueryBuilder<Object> retrieveExistingValuesRecordQuery(int linkId){
    return Services.get(IQueryBL.class).createQueryBuilder(labelsTableName, PlainContextAware.newWithThreadInheritedTrx()).addOnlyActiveRecordsFilter().addEqualsFilter(getLabelsLinkColumnName(), // parent link
    linkId);
}


@Override
public boolean isCached(){
    return true;
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingById(Object id){
    return LookupDataSourceContext.builder(tableName).putFilterById(id);
}


public LabelsLookup cast(LookupDescriptor lookupDescriptor){
    return (LabelsLookup) lookupDescriptor;
}


@Override
public boolean hasParameters(){
    return true;
}


@Override
public Optional<String> getLookupTableName(){
    return Optional.of(labelsTableName);
}


@Override
public Set<String> getDependsOnFieldNames(){
    return CtxNames.toNames(parameters);
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
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingList(){
    return LookupDataSourceContext.builder(tableName).setRequiredParameters(parameters).requiresAD_Language();
}


}