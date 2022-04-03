package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PageController {

 private Page page;

 private Page page;


@PutMapping
("/setRowsPerPage")
public void setRowsPerPage(@RequestParam(name = "rowsPerPage") int rowsPerPage){
page.setRowsPerPage(rowsPerPage);
}


@PutMapping
("/setCurrentPage")
public void setCurrentPage(@RequestParam(name = "currentPage") int currentPage){
page.setCurrentPage(currentPage);
}


@PutMapping
("/setSearchParam")
public void setSearchParam(@RequestParam(name = "searchParam") Map<String,String> searchParam){
page.setSearchParam(searchParam);
}


}