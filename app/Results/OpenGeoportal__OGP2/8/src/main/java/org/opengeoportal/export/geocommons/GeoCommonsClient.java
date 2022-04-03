package org.opengeoportal.export.geocommons;
 import org.opengeoportal.layer.BoundingBox;
public interface GeoCommonsClient {


public String uploadWmsDataSet(String layerId)
;

public void checkUser(String username)
;

public DataSetStatus checkDataSetStatus(String location)
;

public String uploadShapeFile(String layerId,BoundingBox bounds)
;

public String createMap(String basemap,String extent,String title,String description)
;

public String createUser(String full_name,String login,String password,String password_confirmation,String email)
;

public void initializeClient(String username,String password)
;

public void addLayerToMap(String mapId,DataSetStatus dataSetStatus)
;

}