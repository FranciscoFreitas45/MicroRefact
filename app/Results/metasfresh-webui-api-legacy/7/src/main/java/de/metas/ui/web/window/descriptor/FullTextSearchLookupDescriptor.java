package de.metas.ui.web.window.descriptor;
 import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import org.adempiere.model.InterfaceWrapperHelper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import de.metas.logging.LogManager;
import de.metas.ui.web.window.datatypes.LookupValue;
import de.metas.ui.web.window.datatypes.LookupValuesList;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.descriptor.DocumentLayoutElementFieldDescriptor.LookupSource;
import de.metas.ui.web.window.descriptor.sql.ISqlLookupDescriptor;
import de.metas.ui.web.window.descriptor.sql.SqlForFetchingLookupById;
import de.metas.ui.web.window.model.lookup.LookupDataSource;
import de.metas.ui.web.window.model.lookup.LookupDataSourceContext;
import de.metas.ui.web.window.model.lookup.LookupDataSourceFetcher;
import de.metas.util.NumberUtils;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
@Value
public class FullTextSearchLookupDescriptor implements ISqlLookupDescriptor,LookupDataSourceFetcher{

 private  Logger logger;

 private  Client elasticsearchClient;

 private  String modelTableName;

 private  String esIndexName;

 private  String esKeyColumnName;

 private  String[] esSearchFieldNames;

 private  ISqlLookupDescriptor sqlLookupDescriptor;

 private  LookupDataSource databaseLookup;


@Override
public LookupValuesList retrieveEntities(LookupDataSourceContext evalCtx){
    logger.trace("Retrieving entries for: {}", evalCtx);
    if (evalCtx.isAnyFilter()) {
        // usually that's the case of dropdowns. In that case we don't want to use elasticsearch.
        logger.trace("Fallback to database lookup because ANY filter was used");
        return databaseLookup.findEntities(evalCtx);
    }
    final QueryBuilder query = createElasticsearchQuery(evalCtx);
    logger.trace("ES query: {}", query);
    int maxSize = Math.min(evalCtx.getLimit(100), 100);
    final SearchResponse searchResponse = elasticsearchClient.prepareSearch(esIndexName).setQuery(query).setExplain(logger.isTraceEnabled()).setSize(maxSize).addField(esKeyColumnName).get();
    logger.trace("ES response: {}", searchResponse);
    final List<Integer> recordIds = Stream.of(searchResponse.getHits().getHits()).map(hit -> extractId(hit)).distinct().collect(ImmutableList.toImmutableList());
    logger.trace("Record IDs: {}", recordIds);
    final LookupValuesList lookupValues = databaseLookup.findByIdsOrdered(recordIds);
    logger.trace("Lookup values: {}", lookupValues);
    return lookupValues;
}


@Override
public LookupDataSourceFetcher getLookupDataSourceFetcher(){
    return this;
}


@Override
public LookupSource getLookupSourceType(){
    return LookupSource.lookup;
}


@Override
public LookupValue retrieveLookupValueById(LookupDataSourceContext evalCtx){
    return databaseLookup.findById(evalCtx.getIdToFilter());
}


@Override
public String getCachePrefix(){
    return null;
}


@Override
public boolean isHighVolume(){
    return true;
}


@Override
public Optional<WindowId> getZoomIntoWindowId(){
    return Optional.empty();
}


@Override
public Optional<String> getTableName(){
    return Optional.of(modelTableName);
}


@Override
public boolean isNumericKey(){
    return true;
}


public QueryBuilder createElasticsearchQuery(LookupDataSourceContext evalCtx){
    final String text = evalCtx.getFilter();
    return QueryBuilders.multiMatchQuery(text, esSearchFieldNames);
}


@Override
public void cacheInvalidate(){
// nothing
}


@Override
public boolean isCached(){
    return true;
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingById(Object id){
    return LookupDataSourceContext.builder(modelTableName).putFilterById(id);
}


public int extractId(SearchHit hit){
    final Object value = hit.getFields().get(esKeyColumnName).getValue();
    return NumberUtils.asInt(value, -1);
}


@Override
public boolean hasParameters(){
    return true;
}


@Override
public Optional<String> getLookupTableName(){
    return Optional.of(modelTableName);
}


@Override
public SqlForFetchingLookupById getSqlForFetchingLookupByIdExpression(){
    return sqlLookupDescriptor != null ? sqlLookupDescriptor.getSqlForFetchingLookupByIdExpression() : null;
}


@Override
public Set<String> getDependsOnFieldNames(){
    return null;
}


@Override
public LookupDataSourceContext.Builder newContextForFetchingList(){
    return LookupDataSourceContext.builder(modelTableName);
}


}