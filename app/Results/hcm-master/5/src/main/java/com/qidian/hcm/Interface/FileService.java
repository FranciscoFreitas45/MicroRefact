package com.qidian.hcm.Interface;
public interface FileService {

   public String getAvatarImgUrl(Long fileId);
   public Attachment uploadFileToOSS(String base64Str,String fileName);
}