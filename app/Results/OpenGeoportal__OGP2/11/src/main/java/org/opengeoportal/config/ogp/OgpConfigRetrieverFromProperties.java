package org.opengeoportal.config.ogp;
 import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.opengeoportal.config.PropertiesFile;
import org.opengeoportal.config.ogp.OgpConfig.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opengeoportal.Interface.PropertiesFile;
public class OgpConfigRetrieverFromProperties implements OgpConfigRetriever{

 private  String ANALYTICS_KEY;

 private  String GOOGLE_MAPS_API_KEY;

 private  String TITLE_PRIMARY;

 private  String TITLE_OFFSET;

 private  String EXTRA_JS;

 private  String EXTRA_CSS;

 private  String EXTERNAL_SEARCH_URL;

 private  String LOGIN_REPOSITORY;

 private  String LOGIN_TYPE;

 private  String LOGIN_URL;

 private  String SECURE_DOMAIN;

 private  String TITLE_PRIMARY_DEFAULT;

 private  String TITLE_OFFSET_DEFAULT;

 private  String LOGIN_TYPE_DEFAULT;

 private  String LOGIN_URL_DEFAULT;

 private PropertiesFile propertiesFile;

 private Properties props;

 private OgpConfig ogpConfig;

 protected  Logger logger;


public PropertiesFile getPropertiesFile(){
    return propertiesFile;
}


@Override
public OgpConfig load(){
    props = propertiesFile.getProperties();
    ogpConfig = new OgpConfig();
    URL extUrl = null;
    if (props.containsKey(EXTERNAL_SEARCH_URL)) {
        String extSearch = props.getProperty(EXTERNAL_SEARCH_URL);
        // add /select, remove trailing slash
        if (extSearch.endsWith("/")) {
            extSearch = extSearch.substring(0, extSearch.lastIndexOf("/"));
        }
        if (!extSearch.endsWith("select")) {
            extSearch += "/select";
        }
        try {
            extUrl = new URL(extSearch);
            ogpConfig.setSearchUrl(extUrl);
        } catch (MalformedURLException e) {
            throw new Exception("External Search URL ['property " + EXTERNAL_SEARCH_URL + "'] is malformed!");
        }
    } else {
        throw new Exception("Must set a search URL!");
    }
    String analyticsKey = getPropertyWithDefault(ANALYTICS_KEY, "");
    ogpConfig.setAnalyticsId(analyticsKey);
    String gmapsAPIKey = getPropertyWithDefault(GOOGLE_MAPS_API_KEY, "");
    ogpConfig.setGmapsAPIKey(gmapsAPIKey);
    String pageTitlePrimary = getPropertyWithDefault(TITLE_PRIMARY, TITLE_PRIMARY_DEFAULT);
    ogpConfig.setPageTitlePrimary(pageTitlePrimary);
    String pageTitleOffset = getPropertyWithDefault(TITLE_OFFSET, TITLE_OFFSET_DEFAULT);
    ogpConfig.setPageTitleOffset(pageTitleOffset);
    String extraJs = getPropertyWithDefault(EXTRA_JS, "");
    ogpConfig.setJsLocalized(extraJs);
    String extraCss = getPropertyWithDefault(EXTRA_CSS, "");
    ogpConfig.setCssLocalized(extraCss);
    LoginConfig logConf = ogpConfig.getLoginConfig();
    // This should throw an error if LOGIN_REPOSITORY is not set properly
    String val = getPropertyWithDefault(LOGIN_REPOSITORY, "");
    if (StringUtils.isNotEmpty(val)) {
        logConf.setRepositoryId(val);
    } else {
        throw new Exception("Must set a value for Login Repository! ['" + LOGIN_REPOSITORY + "']");
    }
    String typeVal = getPropertyWithDefault(LOGIN_TYPE, LOGIN_TYPE_DEFAULT);
    logConf.setType(typeVal);
    String urlVal = getPropertyWithDefault(LOGIN_URL, LOGIN_URL_DEFAULT);
    logConf.setUrl(urlVal);
    // should default to current domain with https:; for now let the client do this
    String sdVal = getPropertyWithDefault(SECURE_DOMAIN, "");
    // should default to current domain with https:; for now let the client do this
    ;
    logConf.setSecureDomain(sdVal);
    return ogpConfig;
}


@Override
public String getPropertyWithDefault(String propertyName,String defaultPropertyValue){
    String val = null;
    if (props.containsKey(propertyName) && StringUtils.isNotEmpty(props.getProperty(propertyName))) {
        // default to type form
        val = props.getProperty(propertyName);
    } else {
        logger.warn(propertyName + " not set.  Using default value '" + defaultPropertyValue + "'.");
        val = defaultPropertyValue;
    }
    return val;
}


public void setPropertiesFile(PropertiesFile propertiesFile){
    this.propertiesFile = propertiesFile;
}


public void setOgpConfig(OgpConfig ogpConfig){
    this.ogpConfig = ogpConfig;
}


@Override
public OgpConfig getConfig(){
    return ogpConfig;
}


}