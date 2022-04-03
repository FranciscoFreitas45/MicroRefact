package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class SysDic {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String title;

 private  String code;

 private  String orgi;

 private  String ctype;

 private  String parentid;

 private  String iconstr;

 private  String iconskin;

 private  String description;

 private  String catetype;

 private  String memo;

 private  String creater;

 private  boolean haschild;

 private  boolean discode;

 private  Date createtime;

 private  Date updatetime;

 private  int sortindex;

 private  String dicid;

 private  String menutype;

 private  String rules;

 private  String module;

 private  String url;

 private  String mlevel;

 private  boolean defaultvalue;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getIconskin(){
    return iconskin;
}


public String getModule(){
    return module;
}


public String getName(){
    return name;
}


public String getTitle(){
    return title;
}


public String getMlevel(){
    return mlevel;
}


public String getIconstr(){
    return iconstr;
}


public String getCatetype(){
    return catetype;
}


public String getCode(){
    return code;
}


public String getCtype(){
    return ctype;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getUrl(){
    return url;
}


public String getMemo(){
    return memo;
}


public String getDicid(){
    return dicid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getDescription(){
    return description;
}


public Date getCreatetime(){
    return createtime;
}


public String getCreater(){
    return creater;
}


public String getRules(){
    return rules;
}


public String getParentid(){
    return parentid;
}


public String getMenutype(){
    return menutype;
}


public String getOrgi(){
    return orgi;
}


public int getSortindex(){
    return sortindex;
}


public boolean isDiscode(){
    return discode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isDiscode"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}