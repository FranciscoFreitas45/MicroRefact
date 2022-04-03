package org.opengeoportal.metadata;
 import java.util.List;
import java.util.Set;
import org.apache.solr.client.solrj.SolrServer;
import org.opengeoportal.solr.SolrRecord;
public interface LayerInfoRetriever {


public List<SolrRecord> fetchAllowedRecords(Set<String> layerIdSet)
;

public SolrRecord getAllLayerInfo(String layerId)
;

public List<SolrRecord> fetchAllLayerInfo(Set<String> layerIds)
;

public SolrServer getSolrServer()
;

}