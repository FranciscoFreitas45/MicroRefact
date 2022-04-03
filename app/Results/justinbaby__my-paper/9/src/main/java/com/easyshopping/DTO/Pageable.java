package com.easyshopping.DTO;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.easyshopping.Order.Direction;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Pageable implements Serializable{

 private  long serialVersionUID;

 private  int DEFAULT_PAGE_NUMBER;

 private  int DEFAULT_PAGE_SIZE;

 private  int MAX_PAGE_SIZE;

 private  int pageNumber;

 private  int pageSize;

 private  String searchProperty;

 private  String searchValue;

 private  String orderProperty;

 private  Direction orderDirection;

 private  List<Filter> filters;

 private  List<Order> orders;

/**
 * 初始化一个新创建的Pageable对象
 */
public Pageable() {
}/**
 * 初始化一个新创建的Pageable对象
 *
 * @param pageNumber
 *            页码
 * @param pageSize
 *            每页记录数
 */
public Pageable(Integer pageNumber, Integer pageSize) {
    if (pageNumber != null && pageNumber >= 1) {
        this.pageNumber = pageNumber;
    }
    if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
        this.pageSize = pageSize;
    }
}
public Direction getOrderDirection(){
    return orderDirection;
}


public List<Order> getOrders(){
    return orders;
}


public String getOrderProperty(){
    return orderProperty;
}


public int getPageNumber(){
    return pageNumber;
}


public int getPageSize(){
    return pageSize;
}


public List<Filter> getFilters(){
    return filters;
}


public String getSearchValue(){
    return searchValue;
}


public String getSearchProperty(){
    return searchProperty;
}


}