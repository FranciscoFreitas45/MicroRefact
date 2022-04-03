package es.gva.dgti.gvgeoportal.web.menu;
 import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
@Component
@Configurable
public class MenuLoader extends WebApplicationObjectSupport{

 public  String MENU_CONFIG_FILE;

 public  String MENU_SERVLET_CONTEXT_KEY;

 public  String MENU_ITEM_ELEMENT;


public void initApplicationContext(){
    if (!(getApplicationContext() instanceof WebApplicationContext)) {
        return;
    }
    Menu menu;
    try {
        menu = loadMenu();
    } catch (Exception e) {
        throw new ApplicationContextException("Error loading gvNIX web menu", e);
    }
    getServletContext().setAttribute(MENU_SERVLET_CONTEXT_KEY, menu);
}


public Menu loadMenu(){
    // load and parse menu.xml
    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    Document xml = null;
    InputStream input = getServletContext().getResourceAsStream(MENU_CONFIG_FILE);
    Assert.notNull(input, "gvNIX menu configuration not found '".concat(MENU_CONFIG_FILE).concat("'"));
    try {
        xml = db.parse(input);
    } catch (Exception e) {
        throw e;
    } finally {
        input.close();
    }
    // create menu root
    Element root = xml.getDocumentElement();
    String menuId = root.getAttribute("id");
    Menu menu = new Menu(menuId);
    // parse children
    NodeList childNodes = root.getChildNodes();
    // return empty menu if there are no children
    if (childNodes.getLength() == 0) {
        return menu;
    }
    // load root menu items and their children
    List<MenuItem> childItems = loadMenuItems(childNodes);
    menu.setChildren(childItems);
    return menu;
}


public List<MenuItem> loadMenuItems(NodeList nodes){
    List<MenuItem> items = new ArrayList<MenuItem>();
    for (int i = 0; i < nodes.getLength(); i++) {
        Node node = nodes.item(i);
        if (node.getNodeType() != Node.ELEMENT_NODE || !MENU_ITEM_ELEMENT.equals(node.getNodeName())) {
            continue;
        }
        // create the MenuItem object
        MenuItem item = new MenuItem((Element) node);
        // recursively load children
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            List<MenuItem> childItems = loadMenuItems(childNodes);
            item.setChildren(childItems);
        }
        // add MenuItem to result list
        items.add(item);
    }
    return items;
}


}