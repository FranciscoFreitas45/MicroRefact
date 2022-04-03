package org.opengeoportal.metadata;
 import java.net.URL;
import java.util.List;
import java.util.Set;
import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.opengeoportal.config.search.SearchConfigRetriever;
import org.opengeoportal.solr.SolrRecord;
import org.opengeoportal.utilities.http.OgpHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PostFilter;
import org.opengeoportal.Interface.SearchConfigRetriever;
import org.opengeoportal.Interface.OgpHttpClient;
public class SolrLayerInfoRetriever implements LayerInfoRetriever{

 private  SolrServer solrServer;

 final  Logger logger;

@Autowired
 private  SearchConfigRetriever searchConfigRetriever;

@Autowired
@Qualifier("httpClient.pooling")
 private OgpHttpClient ogpHttpClient;


public void init(){
    HttpClient httpClient = ogpHttpClient.getCloseableHttpClient();
    URL url = searchConfigRetriever.getInternalSearchUrl();
    String url$ = url.toString();
    if (url$.contains("select")) {
        url$ = url$.substring(0, url$.indexOf("/select"));
    }
    logger.debug("creating Solr Server at " + url$);
    HttpSolrServer httpServer = new HttpSolrServer(url$, httpClient);
    httpServer.setParser(new XMLResponseParser());
    this.solrServer = (SolrServer) httpServer;
}


@Override
@PostFilter("hasPermission(filterObject, 'download')")
public List<SolrRecord> fetchAllowedRecords(Set<String> layerIdSet){
    List<SolrRecord> allRecords = fetchAllLayerInfo(layerIdSet);
    return allRecords;
}


@Override
public SolrRecord getAllLayerInfo(String layerId){
    String query = "LayerId:" + ClientUtils.escapeQueryChars(layerId.trim());
    SolrQuery queryObj = new SolrQuery();
    queryObj.setQuery(query);
    List<SolrRecord> results = getSolrServer().query(queryObj).getBeans(SolrRecord.class);
    if (results.isEmpty()) {
        throw new SolrServerException("Layer with id ['" + layerId.trim() + "'] not found in the Solr index.");
    } else {
        return results.get(0);
    }
}


public List<SolrRecord> fetchAllLayerInfo(Set<String> layerIds){
    SolrServer server = getSolrServer();
    String query = "";
    for (String layerId : layerIds) {
        logger.debug(layerId);
        query += "LayerId:" + ClientUtils.escapeQueryChars(layerId.trim());
        query += " OR ";
    }
    if (query.length() > 0) {
        query = query.substring(0, query.lastIndexOf(" OR "));
    }
    /*ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("q", query);

            QueryResponse response = server.query(params);*/
    logger.debug("Solr query terms: " + query);
    SolrQuery queryObj = new SolrQuery();
    queryObj.setQuery(query);
    QueryResponse response = null;
    try {
        response = server.query(queryObj);
    } catch (Exception e) {
        logger.error(e.getMessage());
    }
    // logger.info(response.getResults().get(0).getFieldValue("Name").toString());
    List<SolrRecord> results = response.getBeans(SolrRecord.class);
    return results;
}


@Override
public SolrServer getSolrServer(){
    if (solrServer == null) {
        try {
            init();
        } catch (Exception e) {
            logger.error("problem creating solr server");
        }
    }
    return solrServer;
}


}