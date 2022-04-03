package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PaginationController {

 private Pagination pagination;

 private Pagination pagination;


@PutMapping
("/setInfo")
public void setInfo(@RequestParam(name = "info") T info){
pagination.setInfo(info);
}


}