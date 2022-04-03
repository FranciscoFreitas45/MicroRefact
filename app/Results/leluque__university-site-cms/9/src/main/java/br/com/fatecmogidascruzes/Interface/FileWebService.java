package br.com.fatecmogidascruzes.Interface;
public interface FileWebService {

   public FileDTO getImage(UUID fileHash,Integer width,Integer height);
   public FileDTO getFile(UUID fileHash);
}