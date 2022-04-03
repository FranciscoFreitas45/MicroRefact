package br.com.fatecmogidascruzes.Interface;
public interface FileService {

   public Object removeByKey(Object Object);
   public File saveImage(MultipartFile multipartFile,String alternativeDescription);
   public File saveFile(MultipartFile file);
}