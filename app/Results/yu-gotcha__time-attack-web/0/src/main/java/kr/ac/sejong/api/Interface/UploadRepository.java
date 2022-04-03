package kr.ac.sejong.api.Interface;
public interface UploadRepository {

   public List<Upload> findByUser(User user);
}