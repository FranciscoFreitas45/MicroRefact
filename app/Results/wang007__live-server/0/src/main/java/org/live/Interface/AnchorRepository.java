package org.live.Interface;
public interface AnchorRepository {

   public Anchor findAnchorByUser(MobileUser user);
   public Object save(Object Object);
}