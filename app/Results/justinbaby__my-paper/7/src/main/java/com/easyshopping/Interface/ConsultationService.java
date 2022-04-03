package com.easyshopping.Interface;
public interface ConsultationService {

   public List<Consultation> findList(Member member,Product product,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion);
}