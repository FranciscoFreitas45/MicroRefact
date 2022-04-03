package org.opengeoportal.ogc;
 import org.opengeoportal.solr.SolrRecord;
public interface AugmentedSolrRecordRetriever {


public AugmentedSolrRecord getWmsPlusSolrInfo(String layerId)
;

public OwsInfo getOgcDataInfo(String layerId)
;

public AugmentedSolrRecord getOgcAugmentedSolrRecord(SolrRecord solrRecord)
;

public OwsInfo getWmsInfo(String layerId)
;

}