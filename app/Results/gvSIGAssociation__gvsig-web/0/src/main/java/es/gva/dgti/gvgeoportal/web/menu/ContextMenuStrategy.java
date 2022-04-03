package es.gva.dgti.gvgeoportal.web.menu;
 import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
public interface ContextMenuStrategy {


public String getName()
;

public List<MenuItem> query(HttpServletRequest request,ServletContext jspContext,Menu menu)
;

}