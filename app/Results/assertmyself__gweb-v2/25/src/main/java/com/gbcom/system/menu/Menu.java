package com.gbcom.system.menu;
 import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.List;
public class Menu {

 private  List<UlTag> ulList;


public void main(String[] args){
    XStream x = new XStream();
    String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<menu><ul>\n" + "    <li ><a href=\"/sample/ui.do\" func =\"pageInit()\">单位管理</a></li>\n" + "    <li><a href=\"/sample/tree.do\" func =\"\">人员管理</a></li>\n" + "    <li><a href=\"#\">权限管理</a></li>\n" + "    <li><a href=\"#\">代码管理</a></li>\n" + "    <li><a href=\"#\">系统信息</a></li>\n" + "    <li><a href=\"#\">文档管理</a></li>\n" + "</ul></menu>";
    x.addImplicitCollection(UlTag.class, "liList");
    x.addImplicitCollection(Menu.class, "ulList");
    x.alias("ul", UlTag.class);
    x.alias("li", LiTag.class);
    x.alias("menu", Menu.class);
    // x.aliasAttribute(ATag.class,ATag.HREF,ATag.HREF);
    // x.aliasAttribute(ATag.class,ATag.FUNC,ATag.FUNC);
    x.registerConverter(new AttributeValueConveter(x.getMapper()));
    Menu o = (Menu) x.fromXML(xml);
    String s = x.toXML(o.getUlList().get(0));
    System.out.println("s = " + s);
    System.out.println("o.getLiList().size() = " + o.getUlList().get(0).getLiList().get(0).getA().getText());
    ATag tag = new ATag("sdf", "sd", "dsf");
    System.out.println("x.toXML(tag) = " + x.toXML(tag));
// System.out.println("o.getUlList().size() = " + o.getLiList().size());
}


public void setUlList(List<UlTag> ulList){
    this.ulList = ulList;
}


public List<UlTag> getUlList(){
    if (ulList == null) {
        ulList = new ArrayList<UlTag>();
    }
    return ulList;
}


}