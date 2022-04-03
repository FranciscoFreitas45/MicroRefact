package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
public class Menu extends Model<Menu>{

 private  long serialVersionUID;

 private  Long id;

 private  String code;

 private  String pcode;

 private  String pcodes;

 private  String name;

 private  String icon;

 private  String url;

 private  Integer num;

 private  Integer levels;

 private  Integer ismenu;

 private  String tips;

 private  Integer status;

 private  Integer isopen;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


public String getName(){
    return name;
}


public Long getId(){
    return id;
}


public Integer getStatus(){
    return status;
}


public Integer getLevels(){
    return levels;
}


public String getPcode(){
    return pcode;
}


public Integer getNum(){
    return num;
}


public String getCode(){
    return code;
}


public Integer getIsopen(){
    return isopen;
}


public String getTips(){
    return tips;
}


public String getIcon(){
    return icon;
}


public String getPcodes(){
    return pcodes;
}


public String getUrl(){
    return url;
}


public Integer getIsmenu(){
    return ismenu;
}


public void setCode(String code){
    this.code = code;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCode"))

.queryParam("code",code)
;
restTemplate.put(builder.toUriString(),null);
}


}