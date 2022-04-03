package de.metas.ui.web.window.model.lookup;
 import java.util.List;
import java.util.Optional;
import org.adempiere.ad.service.impl.LookupDAO.SQLNamePairIterator;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.ad.validationRule.INamePairPredicate;
import org.compiere.util.DB;
import org.slf4j.Logger;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import de.metas.cache.CCache.CCacheStats;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.WindowConstants;
import de.metas.ui.web.window.datatypes.DebugProperties;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.IntegerLookupValue;
import de.metas.ui.web.window.datatypes.LookupValue.StringLookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.LookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlForFetchingLookupById;
import de.metas.ui.web.window.descriptor.sql.SqlForFetchingLookups;
import de.metas.ui.web.window.descriptor.sql.SqlLookupDescriptor;
import de.metas.util.StringUtils;
import lombok.NonNull;
public class GenericSqlLookupDataSourceFetcher implements LookupDataSourceFetcher{

 private  Logger logger;

@NonNull
 private  String lookupTableName;

@NonNull
 private  Optional<String> lookupTableNameAsOptional;

 private  boolean numericKey;

 private  int entityTypeIndex;

 private  SqlForFetchingLookups sqlForFetchingExpression;

 private  SqlForFetchingLookupById sqlForFetchingLookupByIdExpression;

 private  INamePairPredicate postQueryPredicate;

 private  boolean isTranslatable;

 private  Optional<WindowId> zoomIntoWindowId;


@Override
public List<CCacheStats> getCacheStats(){
    return ImmutableList.of();
}


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    final String sqlForFetching = sqlForFetchingExpression.evaluate(evalCtx);
    final String adLanguage = isTranslatable ? evalCtx.getAD_Language() : null;
    try (final SQLNamePairIterator data = new SQLNamePairIterator(sqlForFetching, numericKey, entityTypeIndex)) {
        DebugProperties debugProperties = null;
        if (WindowConstants.isProtocolDebugging()) {
            debugProperties = DebugProperties.EMPTY.withProperty("debug-sql", sqlForFetching).withProperty("debug-params", evalCtx.toString());
        }
        final LookupValuesList values = data.fetchAll().stream().filter(evalCtx::acceptItem).map(namePair -> LookupValue.fromNamePair(namePair, adLanguage)).collect(LookupValuesList.collect(debugProperties));
        logger.trace("Returning values={} (executed sql: {})", values, sqlForFetching);
        return values;
    }
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    final Object id = evalCtx.getIdToFilter();
    if (id == null) {
        throw new IllegalStateException("No ID provided in " + evalCtx);
    }
    final String sqlForFetchingLookupById = sqlForFetchingLookupByIdExpression.evaluate(evalCtx);
    final String[] nameAndDescriptionAndActive = DB.getSQLValueArrayEx(ITrx.TRXNAME_None, sqlForFetchingLookupById, id);
    if (nameAndDescriptionAndActive == null || nameAndDescriptionAndActive.length == 0) {
        return LOOKUPVALUE_NULL;
    }
    final String displayName = nameAndDescriptionAndActive[0];
    final String description = nameAndDescriptionAndActive.length >= 2 ? nameAndDescriptionAndActive[1] : null;
    final boolean active = nameAndDescriptionAndActive.length >= 3 ? StringUtils.toBoolean(nameAndDescriptionAndActive[2]) : true;
    final ITranslatableString displayNameTrl;
    final ITranslatableString descriptionTrl;
    if (isTranslatable) {
        final String adLanguage = evalCtx.getAD_Language();
        displayNameTrl = TranslatableStrings.singleLanguage(adLanguage, displayName);
        descriptionTrl = TranslatableStrings.singleLanguage(adLanguage, description);
    } else {
        displayNameTrl = TranslatableStrings.anyLanguage(displayName);
        descriptionTrl = TranslatableStrings.anyLanguage(description);
    }
    if (id instanceof Integer) {
        final Integer idInt = (Integer) id;
        return IntegerLookupValue.builder().id(idInt).displayName(displayNameTrl).description(descriptionTrl).active(active).build();
    } else {
        final String idString = id.toString();
        return StringLookupValue.builder().id(idString).displayName(displayNameTrl).description(descriptionTrl).active(active).build();
    }
}


@Override
public String getCachePrefix(){
    // NOTE: it's very important to have the lookupTableName as cache name prefix because we want the cache invalidation to happen for this table
    return lookupTableName;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return zoomIntoWindowId;
}


@Override
public boolean isNumericKey(){
    return numericKey;
}


@Override
public void cacheInvalidate(){
}


@Override
public boolean isCached(){
    return false;
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingById(Object id){
    return LookupDataSourceContext.builder(lookupTableName).putFilterByIdParameterName("?").putFilterById(id).setRequiredParameters(sqlForFetchingLookupByIdExpression.getParameters());
}


@Override
public Optional<String> getLookupTableName(){
    return lookupTableNameAsOptional;
}


public GenericSqlLookupDataSourceFetcher of(LookupDescriptor lookupDescriptor){
    return new GenericSqlLookupDataSourceFetcher(lookupDescriptor);
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("lookupTableName", lookupTableName).add("sqlForFetchingExpression", sqlForFetchingExpression).add("postQueryPredicate", postQueryPredicate).toString();
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingList(){
    return LookupDataSourceContext.builder(lookupTableName).putPostQueryPredicate(postQueryPredicate).setRequiredParameters(sqlForFetchingExpression.getParameters());
}


}