package com.easyshopping.Interface;
public interface ConsultationDao {

   public List<Consultation> findList(Member member,Product product,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders);
   public Page<Consultation> findPage(Member member,Product product,Boolean isShow,Pageable pageable);
   public Long count(Member member,Product product,Boolean isShow);
   public Object merge(Object Object);
   public Object persist(Object Object);
   public Object flush(Object Object);
}