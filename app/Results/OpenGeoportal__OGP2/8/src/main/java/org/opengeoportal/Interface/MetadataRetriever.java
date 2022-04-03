package org.opengeoportal.Interface;
public interface MetadataRetriever {

   public String getContactName(String layerID);
   public String getContactAddress(String layerID);
   public String getContactPhoneNumber(String layerId);
}