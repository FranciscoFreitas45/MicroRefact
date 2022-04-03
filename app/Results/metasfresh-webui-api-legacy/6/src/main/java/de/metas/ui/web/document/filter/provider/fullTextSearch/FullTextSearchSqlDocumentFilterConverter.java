package de.metas.ui.web.document.filter.provider.fullTextSearch;
 import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.compiere.util.DB;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import com.google.common.collect.ImmutableList;
import com.jgoodies.common.base.Objects;
import de.metas.logging.LogManager;
import de.metas.ui.web.document.filter.DocumentFilter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverter;
import de.metas.ui.web.document.filter.sql.SqlDocumentFilterConverterContext;
import de.metas.ui.web.document.filter.sql.SqlParamsCollector;
import de.metas.ui.web.window.model.sql.SqlOptions;
import de.metas.util.Check;
import de.metas.util.NumberUtils;
public class FullTextSearchSqlDocumentFilterConverter implements SqlDocumentFilterConverter{

 public  FullTextSearchSqlDocumentFilterConverter instance;

 static  String FILTER_ID;

 static  String PARAM_SearchText;

 static  String PARAM_Context;

 private  Logger logger;


@Override
public String getSql(SqlParamsCollector sqlParamsOut,DocumentFilter filter,SqlOptions sqlOpts,SqlDocumentFilterConverterContext context){
    final String text = filter.getParameterValueAsString(PARAM_SearchText);
    if (Check.isEmpty(text, true)) {
        return "1=1";
    }
    final FullTextSearchFilterContext ftsContext = filter.getParameterValueAs(PARAM_Context);
    // shall not happen
    Check.assumeNotNull(ftsContext, "Parameter ftsContext is not null");
    logger.trace("context: {}", ftsContext);
    final Client elasticsearchClient = ftsContext.getElasticsearchClient();
    final String esIndexName = ftsContext.getEsIndexName();
    final String keyColumnName = ftsContext.getKeyColumnName();
    final String esKeyColumnName = ftsContext.getEsKeyColumnName();
    final QueryBuilder query = QueryBuilders.multiMatchQuery(text, ftsContext.getEsSearchFieldNamesAsArray());
    logger.trace("ES query: {}", query);
    final SearchResponse searchResponse = elasticsearchClient.prepareSearch(esIndexName).setQuery(query).setExplain(logger.isTraceEnabled()).get();
    logger.trace("ES response: {}", searchResponse);
    final List<Integer> recordIds = Stream.of(searchResponse.getHits().getHits()).map(hit -> extractId(hit, esKeyColumnName)).filter(id -> id >= 0).distinct().collect(ImmutableList.toImmutableList());
    logger.trace("Record IDs: {}", recordIds);
    if (recordIds.isEmpty()) {
        return "1=0";
    }
    final String keyColumnNameFQ = sqlOpts.getTableNameOrAlias() + "." + keyColumnName;
    return DB.buildSqlList(keyColumnNameFQ, recordIds, null);
}


public int extractId(SearchHit hit,String esKeyColumnName){
    final Map<String, Object> source = hit.getSource();
    return NumberUtils.asInt(source.get(esKeyColumnName), -1);
}


@Override
public boolean canConvert(String filterId){
    return Objects.equals(filterId, FILTER_ID);
}


}