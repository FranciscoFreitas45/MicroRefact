package com.ushahidi.swiftriver.core.solr.repository;
 import java.util.List;
import javax.annotation.Resource;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import com.ushahidi.swiftriver.core.api.filter.DropFilter;
import com.ushahidi.swiftriver.core.api.filter.DropFilter.BoundingBox;
import com.ushahidi.swiftriver.core.solr.DropDocument;
import com.ushahidi.swiftriver.core.solr.util.QueryUtil;
public class DropDocumentRepositoryImpl implements DropSearchRepository{

@Resource
 private  SolrTemplate solrTemplate;

 final  Logger logger;


public void setSolrTemplate(SolrTemplate solrTemplate){
    this.solrTemplate = solrTemplate;
}


public List<DropDocument> findInRiver(Long riverId,DropFilter dropFilter,Pageable pageable){
    String searchTerm = QueryUtil.getQueryString(dropFilter.getKeywords());
    SolrQuery solrQuery = getPreparedSolrQuery(searchTerm, pageable);
    // Add the filter query
    String riverFilter = String.format("riverId:(%d)", riverId);
    // Do we have a bounding box filter?
    if (dropFilter.getBoundingBox() == null) {
        solrQuery.addFilterQuery(riverFilter);
    } else {
        String boundingBoxFilter = getBoundingBoxFilterQuery(dropFilter.getBoundingBox());
        solrQuery.addFilterQuery(riverFilter, boundingBoxFilter);
    }
    return getSearchResults(solrQuery);
}


public SolrQuery getPreparedSolrQuery(String searchTerm,Pageable pageable){
    // Calculate the start row
    Integer start = pageable.getPageNumber() * pageable.getPageSize();
    SolrQuery solrQuery = new SolrQuery();
    solrQuery.set("q", searchTerm);
    solrQuery.set("defType", "edismax");
    solrQuery.set("stopwords", true);
    solrQuery.set("lowercaseOperators", true);
    solrQuery.setStart(start);
    solrQuery.setRows(pageable.getPageSize());
    return solrQuery;
}


public List<DropDocument> find(String searchTerm,Pageable pageable){
    SolrQuery solrQuery = getPreparedSolrQuery(QueryUtil.getQueryString(searchTerm), pageable);
    return getSearchResults(solrQuery);
}


public String getBoundingBoxFilterQuery(BoundingBox boundingBox){
    return String.format("geo:[%s,%s TO %s,%s]", boundingBox.getLatFrom(), boundingBox.getLngFrom(), boundingBox.getLatTo(), boundingBox.getLngTo());
}


public List<DropDocument> findInBucket(Long bucketId,DropFilter dropFilter,Pageable pageable){
    String searchTerm = QueryUtil.getQueryString(dropFilter.getKeywords());
    SolrQuery solrQuery = getPreparedSolrQuery(searchTerm, pageable);
    // Add the filter query
    String bucketFilter = String.format("bucketId:(%d)", bucketId);
    // Do we have a bounding box filter?
    if (dropFilter.getBoundingBox() == null) {
        solrQuery.addFilterQuery(bucketFilter);
    } else {
        String boundingBoxFilter = getBoundingBoxFilterQuery(dropFilter.getBoundingBox());
        solrQuery.addFilterQuery(bucketFilter, boundingBoxFilter);
    }
    return getSearchResults(solrQuery);
}


public List<DropDocument> getSearchResults(SolrQuery solrQuery){
    List<DropDocument> results = null;
    try {
        QueryResponse queryResponse = solrTemplate.getSolrServer().query(solrQuery);
        results = queryResponse.getBeans(DropDocument.class);
    } catch (SolrServerException e) {
        logger.error("An error occurred during search {}", e.getMessage());
    }
    return results;
}


}