package com.easyshopping.Interface;
public interface ReceiverService {

   public Page<Receiver> findPage(Member member,Pageable pageable);
   public Object save(Object Object);
   public Object find(Object Object);
   public Object update(Object Object);
   public Object delete(Object Object);
}