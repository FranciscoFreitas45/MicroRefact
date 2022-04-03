package de.metas.ui.web.pattribute;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.adempiere.mm.attributes.AttributeValueId;
import org.adempiere.mm.attributes.api.IAttributesBL;
import org.adempiere.mm.attributes.spi.IAttributeValuesProvider;
import org.compiere.model.I_M_Attribute;
import org.compiere.model.I_M_AttributeValue;
import org.compiere.util.CtxName;
import org.compiere.util.CtxNames;
import org.compiere.util.NamePair;
import com.google.common.collect.ImmutableSet;
import de.metas.cache.CCache.CCacheStats;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import de.metas.ui.web.window.model.lookup.LookupValueFilterPredicates.LookupValueFilterPredicate;
import de.metas.util.Services;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class ASILookupDescriptor implements LookupDataSourceFetcher,LookupDescriptor{

 private  Optional<String> LookupTableName;

 private  String CONTEXT_LookupTableName;

 private  IAttributeValuesProvider attributeValuesProvider;

 private  ImmutableSet<CtxName> dependsOnContextVariables;


@Override
public List<CCacheStats> getCacheStats(){
    return attributeValuesProvider.getCacheStats();
}


public LookupDataSourceContext.Builder prepareNewContext(){
    return LookupDataSourceContext.builder(CONTEXT_LookupTableName);
}


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    final LookupValueFilterPredicate filter = evalCtx.getFilterPredicate();
    final int limit = evalCtx.getLimit(Integer.MAX_VALUE);
    final int offset = evalCtx.getOffset(0);
    return attributeValuesProvider.getAvailableValues(evalCtx).stream().map(namePair -> StringLookupValue.of(namePair.getID(), TranslatableStrings.constant(namePair.getName()), TranslatableStrings.constant(namePair.getDescription()))).filter(filter).skip(offset).limit(limit).collect(LookupValuesList.collect());
}


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return this;
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.list;
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    final Object id = evalCtx.getIdToFilter();
    final NamePair valueNP = attributeValuesProvider.getAttributeValueOrNull(evalCtx, id);
    return LookupValue.fromNamePair(valueNP, evalCtx.getAD_Language(), LOOKUPVALUE_NULL);
}


@Override
public boolean isHighVolume(){
    return attributeValuesProvider.isHighVolume();
}


@Override
public String getCachePrefix(){
    return attributeValuesProvider.getCachePrefix();
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
}


@Override
public boolean isCached(){
    return true;
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingById(Object id){
    return prepareNewContext().putFilterById(id);
}


@Override
public boolean hasParameters(){
    return false;
}


@Override
public Optional<String> getLookupTableName(){
    return LookupTableName;
}


public ASILookupDescriptor of(I_M_Attribute attribute){
    final IAttributeValuesProvider attributeValuesProvider = Services.get(IAttributesBL.class).createAttributeValuesProvider(attribute);
    return new ASILookupDescriptor(attributeValuesProvider);
}


@Override
public Set<String> getDependsOnFieldNames(){
    return ImmutableSet.of();
}


public int getM_AttributeValue_ID(LookupValue lookupValue){
    if (lookupValue == null) {
        return -1;
    }
    final String valueKey = lookupValue.getIdAsString();
    final AttributeValueId attributeValueId = attributeValuesProvider.getAttributeValueIdOrNull(valueKey);
    return AttributeValueId.toRepoId(attributeValueId);
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingList(){
    return prepareNewContext().requiresParameters(dependsOnContextVariables).requiresFilterAndLimit();
}


}