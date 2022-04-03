package com.zis.Interface;
public interface DefaultBookMetadataCaptureHandler {

   public BookMetadata captureDetailPage(String itemId,String bookMetadataSource);
   public BookMetadata captureListPage(String keyword);
}