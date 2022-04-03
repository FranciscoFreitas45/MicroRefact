package com.gbcom.system.filter;
 import javax.servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
public class WordFilter implements Filter{

 private  String[] words;


@Override
public String[] getParameterValues(String name){
    String[] temp = super.getParameterValues(name);
    if (temp != null) {
        String[] contents = new String[temp.length];
        for (int i = 0; i < temp.length; i++) {
            String content = temp[i];
            contents[i] = doFilter(content);
        }
        return contents;
    } else {
        return null;
    }
}


@Override
public void init(FilterConfig config){
}


public String doFilter(String content){
    if (content != null) {
        for (int i = 0; i < words.length; i++) {
            if (content.indexOf(words[i]) != -1) {
                content = content.replace(words[i], "*");
            }
        }
    }
    return content;
}


@Override
public void destroy(){
}


@Override
public String getParameter(String name){
    String content = super.getParameter(name);
    return doFilter(content);
}


}