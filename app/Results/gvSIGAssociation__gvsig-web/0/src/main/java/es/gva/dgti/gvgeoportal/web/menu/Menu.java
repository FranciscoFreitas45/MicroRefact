package es.gva.dgti.gvgeoportal.web.menu;
 import java.util.ArrayList;
import java.util.List;
public class Menu {

 private  String id;

 private  List<MenuItem> children;

protected Menu(String id) {
    if (id == null) {
        this.id = "_menu";
    } else {
        this.id = id;
    }
}
public List<MenuItem> getChildren(){
    return this.children;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return this.id;
}


public void addChild(MenuItem child){
    this.children.add(child);
}


public void setChildren(List<MenuItem> children){
    this.children = children;
}


}