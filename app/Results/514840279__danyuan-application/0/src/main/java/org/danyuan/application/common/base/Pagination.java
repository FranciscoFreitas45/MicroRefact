package org.danyuan.application.common.base;
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


public void setSortName(String sortName){
    this.sortName = sortName;
}


public void setMap(Map<String,String> map){
    this.map = map;
}


public void setPageNumber(Integer pageNumber){
    this.pageNumber = pageNumber;
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


public void setUsername(String username){
    this.username = username;
}


public void setSortOrder(String sortOrder){
    this.sortOrder = sortOrder;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public void setList(List<T> list){
    this.list = list;
}


public Map<String,String> getMap(){
    return map;
}


@SuppressWarnings("unchecked")
public void setFilter(String filter) throws JsonParseException{
    this.filter = filter;
    if (filter != null && !"".equals(filter)) {
        ObjectMapper objmap = new ObjectMapper();
        this.map = objmap.readValue(filter, Map.class);
    }
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


@Override
public String toString(){
    return "Pagination [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", uuid=" + uuid + ", searchText=" + searchText + ", username=" + username + ", sortName=" + sortName + ", filter=" + filter + ", sortOrder=" + sortOrder + ", list=" + list + ", info=" + info + ", map=" + map + "]";
}


public void setInfo(T info){
    this.info = info;
}


public void setSearchText(String searchText){
    this.searchText = searchText;
}


public void setUuidList(List<String> uuidList){
    this.uuidList = uuidList;
}


public void setPageSize(Integer pageSize){
    this.pageSize = pageSize;
}


}