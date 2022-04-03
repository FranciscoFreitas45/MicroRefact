package com.lingxiang2014;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.lingxiang2014.Order.Direction;
public class Page implements Serializable{

 private  long serialVersionUID;

 private  List<T> content;

 private  long total;

 private  Pageable pageable;

public Page() {
    this.total = 0L;
    this.pageable = new Pageable();
}public Page(List<T> content, long total, Pageable pageable) {
    this.content.addAll(content);
    this.total = total;
    this.pageable = pageable;
}
public String getOrderProperty(){
    return pageable.getOrderProperty();
}


public int getPageNumber(){
    return pageable.getPageNumber();
}


public int getPageSize(){
    return pageable.getPageSize();
}


public Pageable getPageable(){
    return pageable;
}


public Direction getOrderDirection(){
    return pageable.getOrderDirection();
}


public List<T> getContent(){
    return content;
}


public List<Filter> getFilters(){
    return pageable.getFilters();
}


public String getSearchValue(){
    return pageable.getSearchValue();
}


public long getTotal(){
    return total;
}


public String getSearchProperty(){
    return pageable.getSearchProperty();
}


public List<Order> getOrders(){
    return pageable.getOrders();
}


public int getTotalPages(){
    return (int) Math.ceil((double) getTotal() / (double) getPageSize());
}


}