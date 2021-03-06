package org.opengeoportal.config.search;
 import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.opengeoportal.config.PropertiesFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SearchConfigRetrieverFromProperties implements SearchConfigRetriever{

 private  String INTERNAL_SEARCH_URL;

 private  String EXTERNAL_SEARCH_URL;

 private  String SEARCH_REPOSITORIES;

 private  String DEFAULT_REPOSITORIES;

 private PropertiesFile propertiesFile;

 private SearchConfig searchConfig;

 protected  Logger logger;


public PropertiesFile getPropertiesFile(){
    return propertiesFile;
}


@Override
public SearchConfig load(){
    Properties props = propertiesFile.getProperties();
    /*URL searchUrl;
		URL internalSearchUrl;
		List<SearchRepository> searchRepositories;*/
    searchConfig = new SearchConfig();
    URL extUrl = null;
    if (props.containsKey(EXTERNAL_SEARCH_URL)) {
        String extSearch = props.getProperty(EXTERNAL_SEARCH_URL);
        try {
            extUrl = new URL(extSearch);
            searchConfig.setSearchUrl(extUrl);
        } catch (MalformedURLException e) {
            throw new Exception("External Search URL ['property " + EXTERNAL_SEARCH_URL + "'] is malformed!");
        }
    } else {
        throw new Exception("Must set a search URL!");
    }
    URL intUrl = null;
    if (props.containsKey(INTERNAL_SEARCH_URL) && StringUtils.isNotEmpty(props.getProperty(INTERNAL_SEARCH_URL))) {
        String intSearch = props.getProperty(INTERNAL_SEARCH_URL);
        try {
            intUrl = new URL(intSearch);
        } catch (MalformedURLException e) {
            logger.warn("Internal Search URL ['property " + INTERNAL_SEARCH_URL + "'] is malformed!");
            intUrl = extUrl;
        }
    } else {
        // use the external facing solr url internally if no internal url is set
        intUrl = extUrl;
        logger.debug("the internal url is set to the same value as the external url");
    }
    searchConfig.setInternalSearchUrl(intUrl);
    List<SearchRepository> searchRep = new ArrayList<SearchRepository>();
    searchConfig.setSearchRepositories(searchRep);
    if (props.containsKey(SEARCH_REPOSITORIES)) {
        String reps = props.getProperty(SEARCH_REPOSITORIES);
        for (String rep : StringUtils.split(reps, ",")) {
            SearchRepository sr = new SearchRepository();
            sr.id = rep;
            sr.selected = isSelectedByDefault(props, rep);
            searchRep.add(sr);
        }
    }
    return searchConfig;
}


public Boolean isSelectedByDefault(Properties props,String repositoryId){
    if (props.containsKey(DEFAULT_REPOSITORIES)) {
        String reps = props.getProperty(DEFAULT_REPOSITORIES);
        for (String rep : StringUtils.split(reps, ",")) {
            if (rep.equalsIgnoreCase("all") || rep.equalsIgnoreCase(repositoryId)) {
                return true;
            }
        }
    }
    return false;
}


@Override
public URL getSearchUrl(){
    return getConfig().getSearchUrl();
}


public void setPropertiesFile(PropertiesFile propertiesFile){
    this.propertiesFile = propertiesFile;
}


public void setSearchConfig(SearchConfig searchConfig){
    this.searchConfig = searchConfig;
}


@Override
public List<SearchRepository> getSearchRepositories(){
    return getConfig().getSearchRepositories();
}


@Override
public SearchConfig getConfig(){
    return searchConfig;
}


@Override
public URL getInternalSearchUrl(){
    return getConfig().getInternalSearchUrl();
}


}