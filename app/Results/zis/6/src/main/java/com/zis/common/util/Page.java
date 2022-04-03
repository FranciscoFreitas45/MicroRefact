package com.zis.common.util;
 public class Page {

 public  int DEFAULT_PAGE_SIZE;

 private  int pageSize;

 private  int totalPageCount;

 private  int currentPage;

 private  int totalRecordCount;

 private  int beginIndex;

 private  boolean hasNext;

 private  boolean hasPre;

private Page() {
}
public Page createPage(int currentPage,int pageSize,int totalRecordCount){
    Page page = new Page();
    page.currentPage = initCurrentPage(currentPage);
    page.pageSize = initPageSize(pageSize);
    page.totalRecordCount = totalRecordCount;
    page.beginIndex = (currentPage - 1) * pageSize;
    page.totalPageCount = initTotalPageCount(page.totalRecordCount, page.pageSize);
    page.hasPre = (page.currentPage == 1) ? false : true;
    page.hasNext = (page.currentPage == page.totalPageCount || page.totalPageCount == 0) ? false : true;
    return page;
}


public int getPageSize(){
    return pageSize;
}


public boolean isHasPre(){
    return hasPre;
}


public int initPageSize(int pageSizeInput){
    return pageSizeInput < 1 ? DEFAULT_PAGE_SIZE : pageSizeInput;
}


public int getCurrentPage(){
    return currentPage;
}


public boolean isHasNext(){
    return hasNext;
}


public int initTotalPageCount(int totalRecordCount,int pageSize){
    int mod = totalRecordCount % pageSize;
    if (mod == 0) {
        return totalRecordCount / pageSize;
    } else {
        return totalRecordCount / pageSize + 1;
    }
}


public int initCurrentPage(int currentPageInput){
    return currentPageInput < 1 ? 1 : currentPageInput;
}


public int getBeginIndex(){
    return beginIndex;
}


public int getTotalRecordCount(){
    return totalRecordCount;
}


public int getTotalPageCount(){
    return totalPageCount;
}


}