package net.shangtech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PageController {

 private Page page;

 private Page page;


@PutMapping
("/setQuery")
public void setQuery(@RequestParam(name = "hql") String hql,@RequestParam(name = "values") Object values){
page.setQuery(hql,values);
}


@PutMapping
("/setTotalCount")
public void setTotalCount(@RequestParam(name = "totalCount") int totalCount){
page.setTotalCount(totalCount);
}


@PutMapping
("/setResult")
public void setResult(@RequestParam(name = "result") List<T> result){
page.setResult(result);
}


}