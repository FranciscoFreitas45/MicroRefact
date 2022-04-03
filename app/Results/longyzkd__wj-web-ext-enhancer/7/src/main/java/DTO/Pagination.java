package DTO;
 public class Pagination {

 protected  String pageStr;

 protected  Integer totalSum;

 protected  Integer totalPage;

 protected  Integer currentPage;

 protected  Integer prePage;

 protected  Integer nextPage;

 protected  Integer pageNumDefault;

 protected  Integer pageNum;

 protected  Integer firstResult;

 protected  Integer maxResult;

 protected  String orderStr;

 protected  String orderColumn;

 protected  String preName;


public String getPageStr(){
    return pageStr;
}


public Integer getFirstResult(){
    return firstResult;
}


public Integer getPageNumDefault(){
    return pageNumDefault;
}


public String getOrderStr(){
    return orderStr;
}


public Integer getCurrentPage(){
    return currentPage;
}


public Integer getPrePage(){
    return prePage;
}


public String getPreName(){
    return preName;
}


public Integer getPageNum(){
    if (this.pageNum == null) {
        this.pageNum = this.getPageNumDefault();
    }
    pageNum = pageNum < 1 || pageNum > 1000 ? 10 : pageNum;
    return pageNum;
}


public Integer getNextPage(){
    return nextPage;
}


public Integer getMaxResult(){
    return maxResult;
}


public Integer getTotalSum(){
    return totalSum;
}


public String getOrderColumn(){
    return orderColumn;
}


public Integer getTotalPage(){
    return totalPage;
}


}