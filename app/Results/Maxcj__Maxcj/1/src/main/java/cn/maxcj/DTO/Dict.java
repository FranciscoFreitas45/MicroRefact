package cn.maxcj.DTO;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
public class Dict extends Model<Dict>{

 private  long serialVersionUID;

 private  Integer id;

 private  Integer num;

 private  Integer pid;

 private  String name;

 private  String code;

 private  String tips;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getName(){
    return name;
}


public Integer getId(){
    return id;
}


public String getTips(){
    return tips;
}


public Integer getNum(){
    return num;
}


public Integer getPid(){
    return pid;
}


public String getCode(){
    return code;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


}