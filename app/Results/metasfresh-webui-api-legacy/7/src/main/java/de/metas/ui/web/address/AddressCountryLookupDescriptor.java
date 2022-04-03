package de.metas.ui.web.address;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.I_C_Country;
import org.compiere.util.Env;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache;
import de.metas.cache.CCache.CCacheStats;
import de.metas.i18n.IModelTranslationMap;
import de.metas.i18n.ITranslatableString;
import de.metas.location.ICountryDAO;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext.Builder;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import de.metas.ui.web.window.model.lookup.LookupValueFilterPredicates.LookupValueFilterPredicate;
import de.metas.util.Services;
import de.metas.util.lang.CoalesceUtil;
public class AddressCountryLookupDescriptor implements LookupDataSourceFetcher,LookupDescriptor{

 private  Optional<String> LookupTableName;

 private  String CACHE_PREFIX;

 private  String CONTEXT_LookupTableName;

 private  CCache<Object,LookupValuesList> allCountriesCache;


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.of(allCountriesCache.stats());
}


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    // 
    // Determine what we will filter
    final LookupValueFilterPredicate filter = evalCtx.getFilterPredicate();
    final int offset = evalCtx.getOffset(0);
    final int limit = evalCtx.getLimit(filter.isMatchAll() ? Integer.MAX_VALUE : 100);
    // 
    // Get, filter, return
    return getAllCountriesById().getValues().stream().filter(filter).skip(offset).limit(limit).collect(LookupValuesList.collect());
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
    final Object id = evalCtx.getIdToFilter();
    if (id == null) {
        throw new IllegalStateException("No ID provided in " + evalCtx);
    }
    return getLookupValueById(id);
}


@Override
public String getCachePrefix(){
    return CACHE_PREFIX;
}


@Override
public boolean isHighVolume(){
    return true;
}


public IntegerLookupValue createLookupValue(I_C_Country countryRecord){
    final int countryId = countryRecord.getC_Country_ID();
    final IModelTranslationMap modelTranslationMap = InterfaceWrapperHelper.getModelTranslationMap(countryRecord);
    final ITranslatableString countryName = modelTranslationMap.getColumnTrl(I_C_Country.COLUMNNAME_Name, countryRecord.getName());
    final ITranslatableString countryDescription = modelTranslationMap.getColumnTrl(I_C_Country.COLUMNNAME_Description, countryRecord.getName());
    return IntegerLookupValue.of(countryId, countryName, countryDescription);
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public boolean isNumericKey(){
    return true;
}


public LookupValue getLookupValueById(Object idObj){
    final LookupValue country = getAllCountriesById().getById(idObj);
    return CoalesceUtil.coalesce(country, LOOKUPVALUE_NULL);
}


public AddressCountryLookupDescriptor newInstance(){
    return new AddressCountryLookupDescriptor();
}


@Override
public void cacheInvalidate(){
    allCountriesCache.reset();
}


@Override
public boolean isCached(){
    return true;
}


@Override
public Builder newContextForFetchingById(Object id){
    return LookupDataSourceContext.builder(CONTEXT_LookupTableName).requiresAD_Language().putFilterById(id);
}


public LookupValuesList getAllCountriesById(){
    final Object cacheKey = "ALL";
    return allCountriesCache.getOrLoad(cacheKey, () -> retriveAllCountriesById());
}


@Override
public boolean hasParameters(){
    // yes, we will need some params like AD_Language, Filter, Limit etc
    return true;
}


@Override
public Optional<String> getLookupTableName(){
    return LookupTableName;
}


@Override
public Set<String> getDependsOnFieldNames(){
    // there are no other fields on which we depend
    return ImmutableSet.of();
}


@Override
public Builder newContextForFetchingList(){
    return LookupDataSourceContext.builder(CONTEXT_LookupTableName).requiresFilterAndLimit().requiresAD_Language();
}


public LookupValuesList retriveAllCountriesById(){
    return Services.get(ICountryDAO.class).getCountries(Env.getCtx()).stream().map(countryRecord -> createLookupValue(countryRecord)).collect(LookupValuesList.collect());
}


}