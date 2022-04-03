package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Consultation;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
public interface ConsultationService extends BaseService<Consultation, Long>{


public List<Consultation> findList(Member member,Product product,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

public Long count(Member member,Product product,Boolean isShow)
;

public Page<Consultation> findPage(Member member,Product product,Boolean isShow,Pageable pageable)
;

public void reply(Consultation consultation,Consultation replyConsultation)
;

}