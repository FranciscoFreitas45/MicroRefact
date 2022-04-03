package org.opengeoportal.Interface;
public interface LayerInfoRetriever {

   public List<SolrRecord> fetchAllLayerInfo(Set<String> layerIds);
}