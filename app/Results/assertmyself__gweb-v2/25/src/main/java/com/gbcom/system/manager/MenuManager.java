package com.gbcom.system.manager;
 import com.gbcom.system.menu.AttributeValueConveter;
import com.gbcom.system.menu.LiTag;
import com.gbcom.system.menu.Menu;
import com.gbcom.system.menu.UlTag;
import com.thoughtworks.xstream.XStream;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
public class MenuManager {

 public  String MENUTAG;

 public  String ULLIST;

 public  String LITAG;

 public  String ULTAG;

 public  String LILIST;

 private  XStream xStream;

 private  List<UlTag> ulTagList;

 private  UlTag selectUlTag;

 private  String ulStr;

 private  String filePath;

/**
 * 默认构造方法
 */
public MenuManager() {
    xStream = new XStream();
    xStream.addImplicitCollection(UlTag.class, MenuManager.LILIST);
    xStream.addImplicitCollection(Menu.class, MenuManager.ULLIST);
    xStream.alias(MenuManager.ULTAG, UlTag.class);
    xStream.alias(MenuManager.LITAG, LiTag.class);
    xStream.alias(MenuManager.MENUTAG, Menu.class);
    xStream.aliasAttribute(LiTag.class, "id", "id");
    xStream.aliasAttribute(UlTag.class, "id", "id");
    xStream.aliasAttribute(UlTag.class, "text", "text");
    xStream.registerConverter(new AttributeValueConveter(xStream.getMapper()));
}
public void generateChildMenu(String id){
    for (UlTag ulTag : ulTagList) {
        if (id.equals(ulTag.getId())) {
            selectUlTag = ulTag;
            ulStr = xStream.toXML(selectUlTag);
            break;
        }
    }
}


public String getUlStr(String id){
    generateChildMenu(id);
    return ulStr;
}


public void setUlStr(String ulStr){
    this.ulStr = ulStr;
}


public void setSelectUlTag(UlTag selectUlTag){
    this.selectUlTag = selectUlTag;
}


public String getFilePath(){
    return filePath;
}


public void setUlTagList(List<UlTag> ulTagList){
    this.ulTagList = ulTagList;
}


public String tag2String(UlTag ulTag){
    return xStream.toXML(ulTag);
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public void parse(){
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    Resource rs = resolver.getResource(getFilePath());
    Menu menu = (Menu) xStream.fromXML(new FileInputStream(rs.getFile()));
    ulTagList = menu.getUlList();
}


public UlTag getSelectUlTag(String id){
    generateChildMenu(id);
    return selectUlTag;
}


public List<UlTag> getUlTagList(){
    return ulTagList;
}


}