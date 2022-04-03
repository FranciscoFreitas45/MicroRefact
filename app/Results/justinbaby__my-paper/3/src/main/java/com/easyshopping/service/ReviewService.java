package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Review;
import com.easyshopping.entity.Review.Type;
public interface ReviewService extends BaseService<Review, Long>{


public List<Review> findList(Member member,Product product,Type type,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders,String cacheRegion)
;

public boolean isReviewed(Member member,Product product)
;

public Long count(Member member,Product product,Type type,Boolean isShow)
;

public Page<Review> findPage(Member member,Product product,Type type,Boolean isShow,Pageable pageable)
;

}