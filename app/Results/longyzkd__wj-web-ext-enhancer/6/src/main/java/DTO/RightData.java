package DTO;
 import java.util.List;
public class RightData {

 private  String menuId;

 private  String menuName;

 private  String pmenuId;

 private  String Note;

 private  Boolean checked;

 private  Boolean leaf;

 private  Boolean expanded;

 private  List<RightData> children;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getMenuId(){
    return menuId;
}


public Boolean getExpanded(){
    return expanded;
}


public String getNote(){
    return Note;
}


public List<RightData> getChildren(){
    return children;
}


public Boolean getLeaf(){
    return leaf;
}


public String getMenuName(){
    return menuName;
}


public Boolean getChecked(){
    return checked;
}


public String getPmenuId(){
    return pmenuId;
}


public void setChecked(Boolean checked){
    this.checked = checked;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChecked"))

.queryParam("checked",checked)
;
restTemplate.put(builder.toUriString(),null);
}


public void setExpanded(Boolean expanded){
    this.expanded = expanded;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExpanded"))

.queryParam("expanded",expanded)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMenuId(String menuId){
    this.menuId = menuId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMenuId"))

.queryParam("menuId",menuId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMenuName(String menuName){
    this.menuName = menuName;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMenuName"))

.queryParam("menuName",menuName)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPmenuId(String pmenuId){
    this.pmenuId = pmenuId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPmenuId"))

.queryParam("pmenuId",pmenuId)
;
restTemplate.put(builder.toUriString(),null);
}


}