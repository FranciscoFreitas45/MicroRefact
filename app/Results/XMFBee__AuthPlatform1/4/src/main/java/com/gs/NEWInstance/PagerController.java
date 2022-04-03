package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PagerController {

 private Pager pager;

 private Pager pager;


@PutMapping
("/setPageSize")
public void setPageSize(@RequestParam(name = "pageSize") int pageSize){
pager.setPageSize(pageSize);
}


@PutMapping
("/setTotalRecords")
public void setTotalRecords(@RequestParam(name = "totalRecords") int totalRecords){
pager.setTotalRecords(totalRecords);
}


@PutMapping
("/setUser")
public void setUser(@RequestParam(name = "user") User user){
pager.setUser(user);
}


}