package com.easyshopping;
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
public void setOrderDirection(Direction orderDirection){
    this.orderDirection = orderDirection;
}


public Direction getOrderDirection(){
    return orderDirection;
}


public void setOrders(List<Order> orders){
    this.orders = orders;
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


public void setOrderProperty(String orderProperty){
    this.orderProperty = orderProperty;
}


public void setFilters(List<Filter> filters){
    this.filters = filters;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 37).append(getPageNumber()).append(getPageSize()).append(getSearchProperty()).append(getSearchValue()).append(getOrderProperty()).append(getOrderDirection()).append(getFilters()).append(getOrders()).toHashCode();
}


public void setSearchValue(String searchValue){
    this.searchValue = searchValue;
}


@Override
public boolean equals(Object obj){
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    if (this == obj) {
        return true;
    }
    Pageable other = (Pageable) obj;
    return new EqualsBuilder().append(getPageNumber(), other.getPageNumber()).append(getPageSize(), other.getPageSize()).append(getSearchProperty(), other.getSearchProperty()).append(getSearchValue(), other.getSearchValue()).append(getOrderProperty(), other.getOrderProperty()).append(getOrderDirection(), other.getOrderDirection()).append(getFilters(), other.getFilters()).append(getOrders(), other.getOrders()).isEquals();
}


public List<Filter> getFilters(){
    return filters;
}


public String getSearchValue(){
    return searchValue;
}


public void setPageNumber(int pageNumber){
    if (pageNumber < 1) {
        pageNumber = DEFAULT_PAGE_NUMBER;
    }
    this.pageNumber = pageNumber;
}


public void setSearchProperty(String searchProperty){
    this.searchProperty = searchProperty;
}


public String getSearchProperty(){
    return searchProperty;
}


public void setPageSize(int pageSize){
    if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
        pageSize = DEFAULT_PAGE_SIZE;
    }
    this.pageSize = pageSize;
}


}