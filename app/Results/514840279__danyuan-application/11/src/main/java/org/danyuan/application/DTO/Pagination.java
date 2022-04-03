package org.danyuan.application.DTO;
 import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Pagination {

 public  Integer pageNumber;

 public  Integer pageSize;

 public  String uuid;

 public  List<String> uuidList;

 public  String searchText;

 public  String username;

 public  String sortName;

 public  String filter;

 public  String sortOrder;

 private List<T> list;

 public  T info;

 public  Map<String,String> map;


public String getSearchText(){
    return searchText;
}


public String getSortOrder(){
    return sortOrder;
}


public String getUsername(){
    return username;
}


public Integer getPageNumber(){
    return pageNumber;
}


public List<T> getList(){
    return list;
}


public String getSortName(){
    return sortName;
}


public List<String> getUuidList(){
    return uuidList;
}


public T getInfo(){
    return info;
}


public Map<String,String> getMap(){
    return map;
}


public String getFilter(){
    return filter;
}


public Integer getPageSize(){
    return pageSize;
}


public String getUuid(){
    return uuid;
}


}