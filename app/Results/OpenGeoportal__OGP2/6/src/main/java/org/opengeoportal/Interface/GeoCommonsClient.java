package org.opengeoportal.Interface;
public interface GeoCommonsClient {

   public void initializeClient(String username,String password);
   public void checkUser(String username);
   public String uploadWmsDataSet(String layerId);
   public DataSetStatus checkDataSetStatus(String location);
   public String createMap(String basemap,String extent,String title,String description);
   public void addLayerToMap(String mapId,DataSetStatus dataSetStatus);
}