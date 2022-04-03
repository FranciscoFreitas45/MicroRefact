package es.gva.dgti.gvgeoportal.web.menu;
 import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
public class BaseURLContextMenuStrategy implements ContextMenuStrategy{

 private  Pattern EL_PATTERN;

 private Iterator<MenuItem> iterator;


public boolean isSameDestination(HttpServletRequest request,ServletContext jspContext,String path1,Set<String> paramNames,Map<String,String> paramValues,String destination2){
    if (destination2 == null || destination2.isEmpty()) {
        return false;
    }
    int questionCharIndes2 = destination2.indexOf('?');
    // Url's files have the same size.
    String path2;
    String query2;
    if (questionCharIndes2 < 0) {
        path2 = destination2;
        query2 = null;
    } else {
        path2 = destination2.substring(0, questionCharIndes2);
        query2 = destination2.substring(questionCharIndes2 + 1);
    }
    if (path1.length() != path2.length()) {
        return false;
    }
    if (!path1.equalsIgnoreCase(path2)) {
        // Url's files are different
        return false;
    }
    // Compare parameters without values
    if (paramNames.isEmpty() && query2 != null) {
        return false;
    } else if (query2 == null && !paramNames.isEmpty()) {
        return false;
    }
    String[] paramTokens2 = new String[] {};
    if (query2 != null) {
        paramTokens2 = query2.split("[&]");
    }
    Set<String> paramNames2 = new TreeSet<String>();
    Map<String, String> paramValues2 = new HashMap<String, String>();
    String paramName;
    String paramValue;
    int equalCharIndex;
    for (String paramToken : paramTokens2) {
        equalCharIndex = paramToken.indexOf('=');
        if (equalCharIndex < 0) {
            paramName = paramToken;
            paramValue = null;
        } else {
            paramName = paramToken.substring(0, equalCharIndex);
            paramValue = paramToken.substring(equalCharIndex + 1, paramToken.length());
        }
        // TODO case insensitive compare: check servlet specification
        paramNames2.add(paramName.toLowerCase());
        paramValues2.put(paramName, paramValue);
    }
    // check for duplicated parameters
    if (paramNames.size() != paramNames2.size()) {
        return false;
    }
    Iterator<String> iterParamNames1 = paramNames.iterator();
    Iterator<String> iterParamNames2 = paramNames2.iterator();
    String paramName2;
    String paramValue2;
    while (iterParamNames1.hasNext()) {
        paramName = iterParamNames1.next();
        paramName2 = iterParamNames2.next();
        if (!paramName.equals(paramName2)) {
            return false;
        }
        paramValue = paramValues.get(paramName);
        paramValue2 = paramValues2.get(paramName2);
        if (paramValue2 != null && EL_PATTERN.matcher(paramValue2).matches()) {
            continue;
        }
        if (!hasText(paramValue)) {
            if (hasText(paramValue2)) {
                return false;
            }
            continue;
        }
        if (!paramValue.equals(paramValue2)) {
            return false;
        }
    }
    return true;
}


@SuppressWarnings("unchecked")
public MenuItem getItemFromCurrentURL(HttpServletRequest request,ServletContext jspContext,Menu menu){
    // If request comes from a internal server forward we'll need original
    // request URI
    // See Servlert 2.4 specification, section SRV.8.4.2 for more
    // information
    String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
    String contextPath;
    if (path == null) {
        // If isn't a forwarded request so we'll use the original URI
        path = request.getRequestURI();
        contextPath = request.getContextPath();
    } else {
        contextPath = (String) request.getAttribute("javax.servlet.forward.context_path");
    }
    if (path.startsWith(contextPath)) {
        path = path.substring(contextPath.length());
    }
    Enumeration<String> paramNamesEnum = request.getParameterNames();
    TreeSet<String> paramNames = new TreeSet<String>();
    // We create a new Map because request.getParameterMap() is a map of
    // String[].
    // In java 1.5 application will throw a ClassCastException if you try
    // set
    // ParameterMap's value into a String variable.
    // In java 1.6 works fine.
    Map<String, String> paramValues = new HashMap<String, String>(request.getParameterMap().size());
    String paramName;
    while (paramNamesEnum.hasMoreElements()) {
        paramName = paramNamesEnum.nextElement();
        paramNames.add(paramName);
        paramValues.put(paramName, request.getParameter(paramName));
    }
    class StackItem {

        Iterator<MenuItem> iterator;

        public StackItem(Iterator<MenuItem> childIterator) {
            this.iterator = childIterator;
        }
    }
    Stack<StackItem> stack = new Stack<StackItem>();
    stack.push(new StackItem(menu.getChildren().iterator()));
    StackItem curStackItem;
    MenuItem curItem;
    while (!stack.isEmpty()) {
        curStackItem = stack.pop();
        while (curStackItem.iterator.hasNext()) {
            curItem = curStackItem.iterator.next();
            if (isSameDestination(request, jspContext, path, paramNames, paramValues, curItem.getUrl())) {
                return curItem;
            }
            if (curItem.hasChildren()) {
                stack.push(curStackItem);
                stack.push(new StackItem(curItem.getChildren().iterator()));
                break;
            }
        }
    }
    return null;
}


public boolean hasText(String var){
    if (var == null) {
        return false;
    }
    return var.trim().isEmpty();
}


}