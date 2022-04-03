package com.ec.survey.DTO;
 import java.util.ArrayList;
import java.util.List;
public class Paging {

 private  int firstItemOnPage;

 private  int lastItemOnPage;

 private  int itemsPerPage;

 private  int numberOfItems;

 private  int currentPage;

 private  List<Integer> itemsPerPageOptions;

 private  List<Integer> itemsPerPageOptionsActivity;

 private  boolean hideNumberOfItems;

 private  boolean enableGoToLastPage;

 private  List<T> items;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public Paging() {
    itemsPerPageOptions = new ArrayList<>();
    itemsPerPageOptions.add(10);
    itemsPerPageOptions.add(20);
    itemsPerPageOptions.add(50);
    itemsPerPageOptionsActivity = new ArrayList<>();
    itemsPerPageOptionsActivity.add(20);
    itemsPerPageOptionsActivity.add(50);
    itemsPerPageOptionsActivity.add(100);
    itemsPerPageOptionsActivity.add(200);
    itemsPerPageOptionsActivity.add(300);
}
public List<Integer> getItemsPerPageOptionsActivity(){
    return itemsPerPageOptionsActivity;
}


public int getItemsPerPage(){
    return itemsPerPage;
}


public List<T> getItems(){
    return items;
}


public int getCurrentPage(){
    return currentPage;
}


public int getFirstItemOnPage(){
    return firstItemOnPage;
}


public int getLastItemOnPage(){
    return lastItemOnPage;
}


public int getNumberOfItems(){
    return numberOfItems;
}


public List<Integer> getItemsPerPageOptions(){
    return itemsPerPageOptions;
}


public void setCurrentPage(int currentPage){
    this.currentPage = currentPage;
    determineFirstAndLastOnPage();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCurrentPage"))

.queryParam("currentPage",currentPage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setItemsPerPage(int itemsPerPage){
    this.itemsPerPage = itemsPerPage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setItemsPerPage"))

.queryParam("itemsPerPage",itemsPerPage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setItems(List<T> items){
    this.items = items;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setItems"))

.queryParam("items",items)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumberOfItems(int numberOfItems){
    this.numberOfItems = numberOfItems;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumberOfItems"))

.queryParam("numberOfItems",numberOfItems)
;
restTemplate.put(builder.toUriString(),null);
}


public void moveTo(String page){
    int lastPage = numberOfItems / itemsPerPage + (numberOfItems % itemsPerPage != 0 ? 1 : 0);
    switch(page) {
        case "first":
            setCurrentPage(1);
            break;
        case "last":
            setCurrentPage(lastPage);
            break;
        default:
            try {
                int newPage = Integer.parseInt(page);
                if (newPage < 1) {
                    newPage = 1;
                } else if (newPage > lastPage) {
                    newPage = lastPage;
                }
                setCurrentPage(newPage);
            } catch (Exception e) {
                setCurrentPage(1);
            }
            break;
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/moveTo"))

.queryParam("page",page)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHideNumberOfItems(boolean hideNumberOfItems){
    this.hideNumberOfItems = hideNumberOfItems;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setHideNumberOfItems"))

.queryParam("hideNumberOfItems",hideNumberOfItems)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEnableGoToLastPage(boolean enableGoToLastPage){
    this.enableGoToLastPage = enableGoToLastPage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEnableGoToLastPage"))

.queryParam("enableGoToLastPage",enableGoToLastPage)
;
restTemplate.put(builder.toUriString(),null);
}


}