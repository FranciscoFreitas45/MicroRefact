package es.gva.dgti.gvgeoportal.web.menu;
 import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
public class MenuItem {

 private  String DELIMITER;

 private  MenuItem parent;

 private  String url;

 private  String id;

 private  String messageCode;

 private  String roles;

 private  boolean hidden;

 private  List<MenuItem> children;

 private  String labelCode;

/**
 * Load Item values from XML Element. This doesn't load children nodes.
 *
 * @param parent
 * @param element
 */
MenuItem(Element element) {
    this.id = element.getAttribute("id");
    this.labelCode = element.getAttribute("labelCode");
    this.messageCode = element.getAttribute("messageCode");
    this.url = element.getAttribute("url");
    this.roles = element.getAttribute("roles");
    if (element.hasAttribute("hidden")) {
        this.hidden = Boolean.parseBoolean(element.getAttribute("hidden"));
    } else {
        this.hidden = false;
    }
}
public MenuItem getParent(){
    return this.parent;
}


public String getIdAsPath(){
    StringBuilder path = new StringBuilder();
    path.append(DELIMITER);
    // current prefixes have
    String idWithoutPrefix = this.id.substring(2);
    // length = 2
    path.append(idWithoutPrefix.replace("_", DELIMITER));
    return path.toString();
}


public boolean hasChildren(){
    return children != null && children.size() > 0;
}


public String getLabelCode(){
    return labelCode;
}


public String getId(){
    return id;
}


public boolean isHidden(){
    return hidden;
}


public List<MenuItem> getChildren(){
    return this.children;
}


public String getUrl(){
    return this.url;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((url == null) ? 0 : url.hashCode());
    result = prime * result + (hidden ? 1231 : 1237);
    result = prime * result + ((parent == null) ? 0 : parent.hashCode());
    result = prime * result + ((roles == null) ? 0 : roles.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((labelCode == null) ? 0 : labelCode.hashCode());
    result = prime * result + ((messageCode == null) ? 0 : messageCode.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    MenuItem other = (MenuItem) obj;
    if (url == null) {
        if (other.url != null) {
            return false;
        }
    } else if (!url.equals(other.url)) {
        return false;
    }
    if (hidden != other.hidden) {
        return false;
    }
    if (parent == null) {
        if (other.parent != null) {
            return false;
        }
    } else if (!parent.equals(other.parent)) {
        return false;
    }
    if (roles == null) {
        if (other.roles != null) {
            return false;
        }
    } else if (!roles.equals(other.roles)) {
        return false;
    }
    if (id == null) {
        if (other.id != null) {
            return false;
        }
    } else if (!id.equals(other.id)) {
        return false;
    }
    if (labelCode == null) {
        if (other.labelCode != null) {
            return false;
        }
    } else if (!labelCode.equals(other.labelCode)) {
        return false;
    }
    if (messageCode == null) {
        if (other.messageCode != null) {
            return false;
        }
    } else if (!messageCode.equals(other.messageCode)) {
        return false;
    }
    return true;
}


public String getMessageCode(){
    return messageCode;
}


@Override
public String toString(){
    return "MenuItem [".concat("ID=").concat(id).concat(", link=").concat(url).concat("]");
}


public void addChild(MenuItem child){
    if (children == null) {
        children = new ArrayList<MenuItem>();
    }
    children.add(child);
    child.parent = this;
}


public void setChildren(List<MenuItem> children){
    this.children = children;
}


public String getRoles(){
    return this.roles;
}


}