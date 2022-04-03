package org.opengeoportal.Interface;
public interface FeatureSourceToShape {

   public FeatureSourceRetriever getFeatureSourceRetriever();
   public void setFeatureCollectionBBox(Envelope bbox);
   public Set<File> exportToShapefiles();
}