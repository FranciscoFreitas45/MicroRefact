package org.opengeoportal.config;
 import java.util.List;
import org.opengeoportal.config.ogp.OgpConfig;
import org.opengeoportal.config.ogp.OgpConfigRetriever;
import org.opengeoportal.config.proxy.ProxyConfig;
import org.opengeoportal.config.proxy.ProxyConfigRetriever;
import org.opengeoportal.config.repositories.RepositoryConfig;
import org.opengeoportal.config.repositories.RepositoryConfigRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.opengeoportal.Interface.OgpConfigRetriever;
@Controller
@RequestMapping("/config")
public class ConfigController {

 final  Logger logger;

@Autowired
 private RepositoryConfigRetriever repositoryConfigRetriever;

@Autowired
 private OgpConfigRetriever ogpConfigRetriever;

@Autowired
 private ProxyConfigRetriever proxyConfigRetriever;


@RequestMapping(value = "repositories", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public List<RepositoryConfig> getRepositoryConfig(){
    return repositoryConfigRetriever.getConfig();
}


@RequestMapping(value = "proxy", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public List<ProxyConfig> getProxyConfig(){
    // must only serve a reduced version...we don't want to hand out internal addresses/credentials to the client
    return proxyConfigRetriever.getPublicConfig();
}


@RequestMapping(value = "general", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public OgpConfig getSearchConfig(){
    return ogpConfigRetriever.getConfig();
}


}