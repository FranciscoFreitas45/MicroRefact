package DTO;
 import java.util.List;
import java.util.Map;
public class ComboTree {

 private  String id;

 private  String text;

 private  String iconCls;

 private  Boolean checked;

 private  Map<String,Object> attributes;

 private  List<ComboTree> children;

 private  String state;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getIconCls(){
    return iconCls;
}


public Map<String,Object> getAttributes(){
    return attributes;
}


public String getText(){
    return text;
}


public String getId(){
    return id;
}


public List<ComboTree> getChildren(){
    return children;
}


public String getState(){
    return state;
}


public Boolean getChecked(){
    return checked;
}


public void setId(String id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setText(String text){
    this.text = text;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setText"))

.queryParam("text",text)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChecked(Boolean checked){
    this.checked = checked;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChecked"))

.queryParam("checked",checked)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChildren(List<ComboTree> children){
    this.children = children;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChildren"))

.queryParam("children",children)
;
restTemplate.put(builder.toUriString(),null);
}


public void setState(String state){
    this.state = state;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setState"))

.queryParam("state",state)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAttributes(Map<String,Object> attributes){
    this.attributes = attributes;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAttributes"))

.queryParam("attributes",attributes)
;
restTemplate.put(builder.toUriString(),null);
}


}