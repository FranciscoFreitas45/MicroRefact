package org.opengeoportal.config.ogp;
 public interface OgpConfigRetriever {


public OgpConfig load()
;

public String getPropertyWithDefault(String propertyName,String defaultPropertyValue)
;

public OgpConfig getConfig()
;

}