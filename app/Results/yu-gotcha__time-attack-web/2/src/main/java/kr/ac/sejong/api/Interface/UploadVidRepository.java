package kr.ac.sejong.api.Interface;
public interface UploadVidRepository {

   public List<UploadVid> findByVidUpUser(User user);
   public Object size(Object Object);
}