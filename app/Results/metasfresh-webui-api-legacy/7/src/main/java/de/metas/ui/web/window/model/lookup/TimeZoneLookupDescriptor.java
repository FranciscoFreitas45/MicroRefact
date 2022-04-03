package de.metas.ui.web.window.model.lookup;
 import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Optional;
import java.util.Set;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.descriptor.LookupDescriptorProvider;
import de.metas.ui.web.window.descriptor.LookupDescriptorProviders;
import de.metas.ui.web.window.descriptor.SimpleLookupDescriptorTemplate;
import de.metas.ui.web.window.model.lookup.LookupValueFilterPredicates.LookupValueFilterPredicate;
import de.metas.util.Check;
public class TimeZoneLookupDescriptor extends SimpleLookupDescriptorTemplate{

 public  TimeZoneLookupDescriptor instance;

 public  LookupDescriptorProvider provider;

 private  LookupValuesList all;


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    final LookupValueFilterPredicate filter = evalCtx.getFilterPredicate();
    final int offset = evalCtx.getOffset(0);
    final int limit = evalCtx.getLimit(50);
    return getAll().filter(filter, offset, limit);
}


public LookupValuesList getAll(){
    LookupValuesList all = this.all;
    if (all == null) {
        all = this.all = ZoneId.getAvailableZoneIds().stream().map(zoneId -> fromZoneIdToLookupValue(zoneId)).collect(LookupValuesList.collect());
    }
    return all;
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    final String zoneIdStr = evalCtx.getIdToFilterAsString();
    if (Check.isEmpty(zoneIdStr, true)) {
        return null;
    }
    return getAll().getById(zoneIdStr.trim());
}


@Override
public Optional<String> getLookupTableName(){
    return Optional.empty();
}


public StringLookupValue fromZoneIdToLookupValue(String zoneIdStr){
    final ZoneId zoneId = ZoneId.of(zoneIdStr);
    final ITranslatableString displayName = TranslatableStrings.builder().appendTimeZone(zoneId, TextStyle.FULL_STANDALONE).append(" - ").append(zoneId.getId()).build();
    final ITranslatableString helpText = TranslatableStrings.empty();
    return StringLookupValue.of(zoneIdStr, displayName, helpText);
}


@Override
public boolean isNumericKey(){
    return false;
}


@Override
public Set<String> getDependsOnFieldNames(){
    return ImmutableSet.of();
}


}