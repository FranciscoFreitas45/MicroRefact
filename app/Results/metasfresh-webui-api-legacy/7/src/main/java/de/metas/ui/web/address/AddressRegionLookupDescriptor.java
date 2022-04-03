package de.metas.ui.web.address;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_Location;
import org.compiere.model.I_C_Region;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import org.compiere.util.Env;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache;
import de.metas.cache.CCache.CCacheStats;
import de.metas.location.ICountryDAO;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlForFetchingLookups;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext.Builder;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import de.metas.util.Check;
import de.metas.util.Services;
public class AddressRegionLookupDescriptor implements LookupDataSourceFetcher,LookupDescriptor{

 private  Optional<String> LookupTableName;

 private  String CACHE_PREFIX;

 private  String CONTEXT_LookupTableName;

 private  Set<CtxName> PARAMETERS;

 private  CCache<Integer,LookupValuesList> regionsByCountryId;


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.of(regionsByCountryId.stats());
}


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    final int countryId = evalCtx.get_ValueAsInt(I_C_Location.COLUMNNAME_C_Country_ID, -1);
    if (countryId <= 0) {
        return LookupValuesList.EMPTY;
    }
    // 
    // Determine what we will filter
    final String filter = evalCtx.getFilter();
    final boolean matchAll;
    final String filterUC;
    final int limit;
    final int offset = evalCtx.getOffset(0);
    if (filter == LookupDataSourceContext.FILTER_Any) {
        matchAll = true;
        // N/A
        filterUC = null;
        limit = evalCtx.getLimit(Integer.MAX_VALUE);
    } else if (Check.isEmpty(filter, true)) {
        return LookupValuesList.EMPTY;
    } else {
        matchAll = false;
        filterUC = filter.trim().toUpperCase();
        limit = evalCtx.getLimit(100);
    }
    // 
    // Get, filter, return
    return getRegionLookupValues(countryId).getValues().stream().filter(region -> matchAll || matchesFilter(region, filterUC)).skip(offset).limit(limit).collect(LookupValuesList.collect());
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.lookup;
}


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return this;
}


public LookupValuesList getRegionLookupValues(int countryId){
    return regionsByCountryId.getOrLoad(countryId, () -> retrieveRegionLookupValues(countryId));
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    final int id = evalCtx.getIdToFilterAsInt(-1);
    if (id <= 0) {
        throw new IllegalStateException("No ID provided in " + evalCtx);
    }
    final LookupValue region = regionsByCountryId.values().stream().map(regions -> regions.getById(id)).filter(r -> r != null).findFirst().orElse(null);
    if (region != null) {
        return region;
    }
    final I_C_Region regionRecord = InterfaceWrapperHelper.create(Env.getCtx(), id, I_C_Region.class, ITrx.TRXNAME_None);
    if (regionRecord == null) {
        return LOOKUPVALUE_NULL;
    }
    return createLookupValue(regionRecord);
}


@Override
public String getCachePrefix(){
    return CACHE_PREFIX;
}


@Override
public boolean isHighVolume(){
    return true;
}


public IntegerLookupValue createLookupValue(I_C_Region regionRecord){
    return IntegerLookupValue.of(regionRecord.getC_Region_ID(), regionRecord.getName());
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public boolean isNumericKey(){
    return true;
}


public AddressRegionLookupDescriptor newInstance(){
    return new AddressRegionLookupDescriptor();
}


@Override
public void cacheInvalidate(){
    regionsByCountryId.reset();
}


@Override
public boolean isCached(){
    return true;
}


@Override
public Builder newContextForFetchingById(Object id){
    return LookupDataSourceContext.builder(CONTEXT_LookupTableName).putFilterById(id);
}


@Override
public boolean hasParameters(){
    return true;
}


public boolean matchesFilter(LookupValue region,String filterUC){
    final String displayName = region.getDisplayName();
    if (Check.isEmpty(displayName)) {
        return false;
    }
    final String displayNameUC = displayName.trim().toUpperCase();
    return displayNameUC.contains(filterUC);
}


@Override
public Optional<String> getLookupTableName(){
    return LookupTableName;
}


@Override
public Set<String> getDependsOnFieldNames(){
    return CtxNames.toNames(PARAMETERS);
}


public LookupValuesList retrieveRegionLookupValues(int countryId){
    return Services.get(ICountryDAO.class).retrieveRegions(Env.getCtx(), countryId).stream().map(regionRecord -> createLookupValue(regionRecord)).collect(LookupValuesList.collect());
}


@Override
public Builder newContextForFetchingList(){
    return LookupDataSourceContext.builder(CONTEXT_LookupTableName).setRequiredParameters(PARAMETERS);
}


}