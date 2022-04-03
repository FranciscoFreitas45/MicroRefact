package com.easyshopping.dao;
 import java.util.List;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Review;
import com.easyshopping.entity.Review.Type;
public interface ReviewDao extends BaseDao<Review, Long>{


public long calculateScoreCount(Product product)
;

public List<Review> findList(Member member,Product product,Type type,Boolean isShow,Integer count,List<Filter> filters,List<Order> orders)
;

public boolean isReviewed(Member member,Product product)
;

public Long count(Member member,Product product,Type type,Boolean isShow)
;

public Page<Review> findPage(Member member,Product product,Type type,Boolean isShow,Pageable pageable)
;

public long calculateTotalScore(Product product)
;

}