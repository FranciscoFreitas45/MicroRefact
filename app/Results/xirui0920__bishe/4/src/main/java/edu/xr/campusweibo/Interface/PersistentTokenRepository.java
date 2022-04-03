package edu.xr.campusweibo.Interface;
public interface PersistentTokenRepository {

   public List<PersistentToken> findByUser(User user);
   public Object delete(Object Object);
}