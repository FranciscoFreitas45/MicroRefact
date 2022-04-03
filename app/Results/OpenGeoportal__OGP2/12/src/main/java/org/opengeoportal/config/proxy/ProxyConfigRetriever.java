package org.opengeoportal.config.proxy;
 import java.io.IOException;
import java.util.List;
public interface ProxyConfigRetriever {


public String getInternalUrl(String type,String repository,String accessLevel,String locationField)
;

public boolean hasProxy(String type,String repository,String accessLevel)
;

public List<ProxyConfig> load()
;

public List<ProxyConfig> getPublicConfig()
;

public String getExternalUrl(String type,String repository,String accessLevel,String locationField)
;

public String getExternalProxyUrl(String type,String repository,String accessLevel)
;

public String getInternalProxyUrl(String type,String repository,String accessLevel)
;

public boolean hasCredentials(String type,String repository,String accessLevel)
;

public List<ProxyConfig> getConfig()
;

}