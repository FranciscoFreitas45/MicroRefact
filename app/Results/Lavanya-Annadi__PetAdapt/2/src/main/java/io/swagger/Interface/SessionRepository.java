package io.swagger.Interface;
public interface SessionRepository {

   public Object save(Object Object);
   public Session findBySessionId(String session);
}