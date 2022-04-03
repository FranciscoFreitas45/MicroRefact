package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PaginationMapperController {

 private PaginationMapper paginationmapper;


@GetMapping
("/toSqlPagination")
public SqlPagination toSqlPagination(@RequestParam(name = "paging") Paging paging){
  return paginationmapper.toSqlPagination(paging);
}


}