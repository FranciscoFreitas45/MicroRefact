package org.opengeoportal.ogc;
 import org.opengeoportal.solr.SolrRecord;
public interface OgcInfoRequester {


public AugmentedSolrRecord getOgcAugment(SolrRecord solrRecord,String owsUrl)
;

}