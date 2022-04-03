package com.ec.survey.model;
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
public boolean isHideNumberOfItems(){
    return hideNumberOfItems;
}


public void setEnableGoToLastPage(boolean enableGoToLastPage){
    this.enableGoToLastPage = enableGoToLastPage;
}


public void setItemsPerPageOptionsActivity(List<Integer> itemsPerPageOptionsActivity){
    this.itemsPerPageOptionsActivity = itemsPerPageOptionsActivity;
}


public void setCurrentPage(int currentPage){
    this.currentPage = currentPage;
    determineFirstAndLastOnPage();
}


public List<Integer> getItemsPerPageOptionsActivity(){
    return itemsPerPageOptionsActivity;
}


public Paging<T> clean(){
    Paging<T> clean = new Paging<>();
    clean.setCurrentPage(currentPage);
    clean.firstItemOnPage = firstItemOnPage;
    clean.itemsPerPage = itemsPerPage;
    clean.itemsPerPageOptions = itemsPerPageOptions;
    clean.lastItemOnPage = lastItemOnPage;
    clean.numberOfItems = numberOfItems;
    return clean;
}


public int getItemsPerPage(){
    return itemsPerPage;
}


public void setHideNumberOfItems(boolean hideNumberOfItems){
    this.hideNumberOfItems = hideNumberOfItems;
}


public List<T> getItems(){
    return items;
}


public void setNumberOfItems(int numberOfItems){
    this.numberOfItems = numberOfItems;
}


public void determineFirstAndLastOnPage(){
    firstItemOnPage = (currentPage - 1) * itemsPerPage + 1;
    lastItemOnPage = firstItemOnPage + itemsPerPage - 1;
    if (firstItemOnPage < 0)
        firstItemOnPage = 0;
    if (numberOfItems == 0)
        firstItemOnPage = 0;
    if ((lastItemOnPage) > numberOfItems) {
        lastItemOnPage = numberOfItems;
    }
}


public void setItems(List<T> items){
    this.items = items;
}


public void setFirstItemOnPage(int firstItemOnPage){
    this.firstItemOnPage = firstItemOnPage;
}


public int getCurrentPage(){
    return currentPage;
}


public void setItemsPerPageOptions(List<Integer> itemsPerPageOptions){
    this.itemsPerPageOptions = itemsPerPageOptions;
}


public int getFirstItemOnPage(){
    return firstItemOnPage;
}


public boolean isEnableGoToLastPage(){
    return enableGoToLastPage;
}


public void setItemsPerPage(int itemsPerPage){
    this.itemsPerPage = itemsPerPage;
}


public int getLastItemOnPage(){
    return lastItemOnPage;
}


public void setLastItemOnPage(int lastItemOnPage){
    this.lastItemOnPage = lastItemOnPage;
}


public int getNumberOfItems(){
    return numberOfItems;
}


public List<Integer> getItemsPerPageOptions(){
    return itemsPerPageOptions;
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
}


}