package com.zis.DTO;
 public class Page {

 public  int DEFAULT_PAGE_SIZE;

 private  int pageSize;

 private  int totalPageCount;

 private  int currentPage;

 private  int totalRecordCount;

 private  int beginIndex;

 private  boolean hasNext;

 private  boolean hasPre;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

private Page() {
}
public int getPageSize(){
    return pageSize;
}


public int getCurrentPage(){
    return currentPage;
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createPage"))

.queryParam("currentPage",currentPage)
.queryParam("pageSize",pageSize)
.queryParam("totalRecordCount",totalRecordCount)
;
Page aux = restTemplate.getForObject(builder.toUriString(),Page.class);
return aux;
}


}