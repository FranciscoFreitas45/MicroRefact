package org.opengeoportal.download;
 import java.io.File;
public interface MetadataRetriever {


public String getXMLStringFromId(String layerID,String xmlFormat)
;

public String getMetadataAsHtml(String layerID)
;

public File getXMLFile(String metadataFileName,File xmlFile)
;

public String getContactPhoneNumber(String layerId)
;

public String getContactName(String layerID)
;

public String getContactAddress(String layerID)
;

}