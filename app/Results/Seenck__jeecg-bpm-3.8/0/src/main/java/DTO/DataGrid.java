package DTO;
 import java.util.List;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
public class DataGrid {

 private  int page;

 private  int rows;

 private  String sort;

 private  String order;

 private  String field;

 private  String treefield;

 private  List results;

 private  int total;

 private  String footer;

 private  String sqlbuilder;

 private  String dataStyle;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getFooter(){
    return footer;
}


public int getPage(){
    return page;
}


public String getField(){
    return field;
}


public String getDataStyle(){
    return dataStyle;
}


public int getRows(){
    if (ContextHolderUtils.getRequest() != null && ResourceUtil.getParameter("rows") != null) {
        return rows;
    }
    return 10000;
}


public String getSort(){
    return sort;
}


public String getSqlbuilder(){
    return sqlbuilder;
}


public String getTreefield(){
    return treefield;
}


public String getOrder(){
    return order;
}


public List getResults(){
    return results;
}


public int getTotal(){
    return total;
}


public void setTotal(int total){
    this.total = total;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTotal"))

.queryParam("total",total)
;
restTemplate.put(builder.toUriString(),null);
}


public void setResults(List results){
    this.results = results;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setResults"))

.queryParam("results",results)
;
restTemplate.put(builder.toUriString(),null);
}


}