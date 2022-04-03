package es.gva.dgti.gvgeoportal.web.menu;
 import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
@Component(URLBrothersContextMenuStrategy.NAME)
@Configurable
public class URLBrothersContextMenuStrategy extends BaseURLContextMenuStrategy{

 public  String NAME;


public String getName(){
    return NAME;
}


public List<MenuItem> query(HttpServletRequest request,ServletContext jspContext,Menu menu){
    MenuItem currentItem = getItemFromCurrentURL(request, jspContext, menu);
    if (currentItem == null) {
        return null;
    }
    return currentItem.getParent().getChildren();
}


}