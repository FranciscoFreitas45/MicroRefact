package DTO;
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
public class ASILookupDescriptor implements LookupDataSourceFetcher,LookupDescriptor{

 private  Optional<String> LookupTableName;

 private  String CONTEXT_LookupTableName;

 private  IAttributeValuesProvider attributeValuesProvider;

 private  ImmutableSet<CtxName> dependsOnContextVariables;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


@Override
public List<CCacheStats> getCacheStats(){
    return attributeValuesProvider.getCacheStats();
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
public String getCachePrefix(){
    return attributeValuesProvider.getCachePrefix();
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public Optional<String> getLookupTableName(){
    return LookupTableName;
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


public ASILookupDescriptor of(I_M_Attribute attribute){
    final IAttributeValuesProvider attributeValuesProvider = Services.get(IAttributesBL.class).createAttributeValuesProvider(attribute);
    return new ASILookupDescriptor(attributeValuesProvider);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("attribute",attribute);
ASILookupDescriptor aux = restTemplate.getForObject(builder.toUriString(),ASILookupDescriptor.class);
return aux;
}


}