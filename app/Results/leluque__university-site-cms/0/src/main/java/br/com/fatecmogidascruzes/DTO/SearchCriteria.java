package br.com.fatecmogidascruzes.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";


public String getFilter(){
    return filter;
}


public Integer getInitialRegister(){
    return initialRegister;
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


public List<String> getCamposFiltragem(){
    return whatToFilter;
}


public void setFilter(String filter){
    this.filter = filter;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilter"))

.queryParam("filter",filter)
;
restTemplate.put(builder.toUriString(),null);
}


public void addSortBy(String ordersBy){
    this.orderBy.addAll(Arrays.asList(ordersBy));
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addSortBy"))

.queryParam("ordersBy",ordersBy)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrder(Order order){
    this.order = order;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrder"))

.queryParam("order",order)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWhatToFilter(List<String> filterBy){
    this.whatToFilter = filterBy;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWhatToFilter"))

.queryParam("filterBy",filterBy)
;
restTemplate.put(builder.toUriString(),null);
}


public void setInitialRegister(Integer initialRegister){
    this.initialRegister = initialRegister;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setInitialRegister"))

.queryParam("initialRegister",initialRegister)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumberOfRegisters(Integer numberOfRegisters){
    this.numberOfRegisters = numberOfRegisters;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumberOfRegisters"))

.queryParam("numberOfRegisters",numberOfRegisters)
;
restTemplate.put(builder.toUriString(),null);
}


}