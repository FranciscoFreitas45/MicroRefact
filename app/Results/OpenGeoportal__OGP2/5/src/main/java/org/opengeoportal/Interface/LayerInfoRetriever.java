package org.opengeoportal.Interface;
public interface LayerInfoRetriever {

   public List<SolrRecord> fetchAllowedRecords(Set<String> layerIdSet);
}