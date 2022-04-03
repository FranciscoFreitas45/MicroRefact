package br.com.fatecmogidascruzes.data;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SearchCriteria {

 protected  String filter;

 protected  List<String> whatToFilter;

 protected  List<String> orderBy;

 protected  Integer initialRegister;

 protected  Integer numberOfRegisters;

 protected  Order order;


public boolean hasPagination(){
    return initialRegister >= 0 && numberOfRegisters > 0;
}


public void setOrderBy(List<String> orderBy){
    this.orderBy = orderBy;
}


public boolean hasFilter(){
    return filter != null && filter.trim().length() > 0 && !whatToFilter.isEmpty();
}


public void setWhatToFilter(List<String> filterBy){
    this.whatToFilter = filterBy;
}


public void setNumberOfRegisters(Integer numberOfRegisters){
    this.numberOfRegisters = numberOfRegisters;
}


public void setFilter(String filter){
    this.filter = filter;
}


public void addSortBy(String ordersBy){
    this.orderBy.addAll(Arrays.asList(ordersBy));
}


public String getFilter(){
    return filter;
}


public void setOrder(Order order){
    this.order = order;
}


public Integer getInitialRegister(){
    return initialRegister;
}


public void setInitialRegister(Integer initialRegister){
    this.initialRegister = initialRegister;
}


public Integer getNumberOfRegisters(){
    return numberOfRegisters;
}


public List<String> getOrderBy(){
    return orderBy;
}


public Order getOrder(){
    return order;
}


public boolean isOrdered(){
    return !orderBy.isEmpty();
}


public List<String> getCamposFiltragem(){
    return whatToFilter;
}


}