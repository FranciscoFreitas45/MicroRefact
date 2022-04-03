package org.opengeoportal.config.search;
 import java.net.URL;
import java.util.List;
public interface SearchConfigRetriever {


public SearchConfig load()
;

public URL getSearchUrl()
;

public List<SearchRepository> getSearchRepositories()
;

public SearchConfig getConfig()
;

public URL getInternalSearchUrl()
;

}