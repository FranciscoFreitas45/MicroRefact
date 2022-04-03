package org.opengeoportal.config.search;
 import java.net.URL;
import java.util.List;
public class SearchConfig {

 private URL searchUrl;

 private URL internalSearchUrl;

 private List<SearchRepository> searchRepositories;


public void setSearchRepositories(List<SearchRepository> searchRepositories){
    this.searchRepositories = searchRepositories;
}


public void setInternalSearchUrl(URL internalSearchUrl){
    this.internalSearchUrl = internalSearchUrl;
}


public URL getSearchUrl(){
    return searchUrl;
}


public void setSearchUrl(URL searchUrl){
    this.searchUrl = searchUrl;
}


public List<SearchRepository> getSearchRepositories(){
    return searchRepositories;
}


public URL getInternalSearchUrl(){
    return internalSearchUrl;
}


}