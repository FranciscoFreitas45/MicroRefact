package cn.maxcj.modular.system.model;
 import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@TableName("sys_menu")
public class Menu extends Model<Menu>{

 private  long serialVersionUID;

@TableId(value = "id", type = IdType.AUTO)
 private  Long id;

 private  String code;

 private  String pcode;

 private  String pcodes;

@NotBlank
 private  String name;

 private  String icon;

@NotBlank
 private  String url;

 private  Integer num;

 private  Integer levels;

 private  Integer ismenu;

 private  String tips;

 private  Integer status;

 private  Integer isopen;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setNum(Integer num){
    this.num = num;
}


public Long getId(){
    return id;
}


public void setIsmenu(Integer ismenu){
    this.ismenu = ismenu;
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


public void setId(Long id){
    this.id = id;
}


public String getCode(){
    return code;
}


public void setPcode(String pcode){
    this.pcode = pcode;
}


public Integer getIsopen(){
    return isopen;
}


public void setCode(String code){
    this.code = code;
}


public void setPcodes(String pcodes){
    this.pcodes = pcodes;
}


public void setTips(String tips){
    this.tips = tips;
}


public String getTips(){
    return tips;
}


public void setStatus(Integer status){
    this.status = status;
}


public String getIcon(){
    return icon;
}


public void setUrl(String url){
    this.url = url;
}


public void setLevels(Integer levels){
    this.levels = levels;
}


public String getPcodes(){
    return pcodes;
}


public void setIcon(String icon){
    this.icon = icon;
}


public String getUrl(){
    return url;
}


public Integer getIsmenu(){
    return ismenu;
}


public void setIsopen(Integer isopen){
    this.isopen = isopen;
}


@Override
public Serializable pkVal(){
    return this.id;
}


@Override
public String toString(){
    return "Menu{" + "id=" + id + ", code=" + code + ", pcode=" + pcode + ", pcodes=" + pcodes + ", name=" + name + ", icon=" + icon + ", url=" + url + ", num=" + num + ", levels=" + levels + ", ismenu=" + ismenu + ", tips=" + tips + ", status=" + status + ", isopen=" + isopen + "}";
}


}