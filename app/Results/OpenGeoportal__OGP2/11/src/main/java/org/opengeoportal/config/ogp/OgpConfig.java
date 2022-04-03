package org.opengeoportal.config.ogp;
 import java.net.URL;
public class OgpConfig {

 private String pageTitlePrimary;

 private String pageTitleOffset;

 private String jsLocalized;

 private String cssLocalized;

 private URL searchUrl;

 private String analyticsId;

 private String gmapsAPIKey;

 private LoginConfig loginConfig;

 private String repositoryId;

 private String type;

 private String url;

 private String secureDomain;


public void setJsLocalized(String jsLocalized){
    this.jsLocalized = jsLocalized;
}


public void setAnalyticsId(String analyticsId){
    this.analyticsId = analyticsId;
}


public LoginConfig getLoginConfig(){
    return loginConfig;
}


public String getSecureDomain(){
    return secureDomain;
}


public String getPageTitlePrimary(){
    return pageTitlePrimary;
}


public String getCssLocalized(){
    return cssLocalized;
}


public void setLoginConfig(LoginConfig loginConfig){
    this.loginConfig = loginConfig;
}


public void setType(String type){
    this.type = type;
}


public void setUrl(String url){
    this.url = url;
}


public void setPageTitleOffset(String pageTitleOffset){
    this.pageTitleOffset = pageTitleOffset;
}


public String getUrl(){
    return url;
}


public String getPageTitleOffset(){
    return pageTitleOffset;
}


public void setCssLocalized(String cssLocalized){
    this.cssLocalized = cssLocalized;
}


public String getRepositoryId(){
    return repositoryId;
}


public String getType(){
    return type;
}


public void setGmapsAPIKey(String gmapsAPIKey){
    this.gmapsAPIKey = gmapsAPIKey;
}


public String getJsLocalized(){
    return jsLocalized;
}


public void setSecureDomain(String secureDomain){
    this.secureDomain = secureDomain;
}


public void setPageTitlePrimary(String pageTitle){
    this.pageTitlePrimary = pageTitle;
}


public URL getSearchUrl(){
    return searchUrl;
}


public void setSearchUrl(URL searchUrl){
    this.searchUrl = searchUrl;
}


public String getAnalyticsId(){
    return analyticsId;
}


public String getGmapsAPIKey(){
    return gmapsAPIKey;
}


public void setRepositoryId(String repositoryId){
    this.repositoryId = repositoryId;
}


}