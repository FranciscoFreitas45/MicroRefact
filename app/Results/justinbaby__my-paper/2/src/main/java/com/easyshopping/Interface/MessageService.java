package com.easyshopping.Interface;
public interface MessageService {

   public Object find(Object Object);
   public void delete(Long id,Member member);
   public Object save(Object Object);
   public Object update(Object Object);
   public Page<Message> findPage(Member member,Pageable pageable);
   public Page<Message> findDraftPage(Member sender,Pageable pageable);
}