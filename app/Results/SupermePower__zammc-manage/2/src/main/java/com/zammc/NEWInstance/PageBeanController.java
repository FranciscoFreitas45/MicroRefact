package com.zammc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PageBeanController {

 private PageBean pagebean;

 private PageBean pagebean;


@PutMapping
("/setPageSize")
public void setPageSize(@RequestParam(name = "pageSize") int pageSize){
pagebean.setPageSize(pageSize);
}


@PutMapping
("/setPageNum")
public void setPageNum(@RequestParam(name = "pageNum") int pageNum){
pagebean.setPageNum(pageNum);
}


@PutMapping
("/setTotalPage")
public void setTotalPage(@RequestParam(name = "totalPage") int totalPage){
pagebean.setTotalPage(totalPage);
}


@PutMapping
("/setTotalRecorder")
public void setTotalRecorder(@RequestParam(name = "totalRecorder") long totalRecorder){
pagebean.setTotalRecorder(totalRecorder);
}


@PutMapping
("/setContent")
public void setContent(@RequestParam(name = "content") List<?> content){
pagebean.setContent(content);
}


}