package br.com.fatecmogidascruzes.controller;
 import javax.servlet.http.HttpServletRequest;
public class Controller {


public String getURLFor(HttpServletRequest request,String resource){
    String currentRequestURL = request.getRequestURL().toString();
    String currentResource = request.getServletPath().substring(1);
    String resourceWithoutSlash = resource.startsWith("/") ? resource.substring(1) : resource;
    return currentRequestURL.substring(0, currentRequestURL.indexOf(currentResource)) + resourceWithoutSlash;
}


}