package org.opengeoportal.Interface;
public interface LayerRequest {

   public void setMetadata(Boolean metadata);
   public SolrRecord getLayerInfo();
   public BoundingBox getRequestedBounds();
}