package com.easyshopping.Interface;
public interface FileService {

   public boolean isValid(FileType fileType,MultipartFile multipartFile);
   public String uploadLocal(FileType fileType,MultipartFile multipartFile);
}