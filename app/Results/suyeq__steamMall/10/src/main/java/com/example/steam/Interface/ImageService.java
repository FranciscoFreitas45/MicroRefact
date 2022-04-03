package com.example.steam.Interface;
public interface ImageService {

   public Long addImage(Image image);
   public String findImageUrlById(long id,String dataSource);
   public Image findImageById(long id);
}