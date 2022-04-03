package com.hmm.common.web;
 import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
public class ExtjsPageRequest {

 private  int page;

 private  int limit;

 private  String sort;

 private  String dir;


public void setSort(String sort){
    this.sort = sort;
}


public Pageable getPageable(){
    Pageable pageable = null;
    if (!sort.trim().equals("") && !dir.trim().equals("")) {
        Sort pageSort = new Sort(Direction.DESC, sort);
        if (!dir.equals("DESC")) {
            pageSort = new Sort(Direction.ASC, sort);
        }
        pageable = PageRequest.of(page - 1, limit, pageSort);
    } else {
        pageable = PageRequest.of(page - 1, limit);
    }
    return pageable;
}


public void setLimit(int limit){
    this.limit = limit;
}


public void setDir(String dir){
    this.dir = dir;
}


public void setPage(int page){
    this.page = page;
}


}