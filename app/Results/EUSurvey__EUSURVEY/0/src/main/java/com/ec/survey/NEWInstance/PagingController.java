package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PagingController {

 private Paging paging;

 private Paging paging;


@PutMapping
("/setNumberOfItems")
public void setNumberOfItems(@RequestParam(name = "numberOfItems") int numberOfItems){
paging.setNumberOfItems(numberOfItems);
}


@PutMapping
("/setCurrentPage")
public void setCurrentPage(@RequestParam(name = "currentPage") int currentPage){
paging.setCurrentPage(currentPage);
}


@PutMapping
("/setItems")
public void setItems(@RequestParam(name = "items") List<T> items){
paging.setItems(items);
}


@PutMapping
("/moveTo")
public void moveTo(@RequestParam(name = "page") String page){
paging.moveTo(page);
}


@GetMapping
("/clean")
public Paging<T> clean(){
  return paging.clean();
}


@PutMapping
("/setItemsPerPage")
public void setItemsPerPage(@RequestParam(name = "itemsPerPage") int itemsPerPage){
paging.setItemsPerPage(itemsPerPage);
}


@PutMapping
("/setHideNumberOfItems")
public void setHideNumberOfItems(@RequestParam(name = "hideNumberOfItems") boolean hideNumberOfItems){
paging.setHideNumberOfItems(hideNumberOfItems);
}


@PutMapping
("/setEnableGoToLastPage")
public void setEnableGoToLastPage(@RequestParam(name = "enableGoToLastPage") boolean enableGoToLastPage){
paging.setEnableGoToLastPage(enableGoToLastPage);
}


}