package DTO;
 public class Autocomplete {

 private  String entityName;

 private  String labelField;

 private  String valueField;

 private  String searchField;

 private  String trem;

 private  Integer maxRows;

 private  Integer curPage;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public String getSearchField(){
    return searchField;
}


public Integer getCurPage(){
    if (curPage == null || curPage < 1) {
        curPage = 1;
    }
    return curPage;
}


public String getLabelField(){
    return labelField;
}


public String getTrem(){
    return trem;
}


public String getValueField(){
    return valueField;
}


public Integer getMaxRows(){
    return maxRows;
}


public String getEntityName(){
    return entityName;
}


public void setTrem(String trem){
    this.trem = trem;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTrem"))

.queryParam("trem",trem)
;
restTemplate.put(builder.toUriString(),null);
}


}