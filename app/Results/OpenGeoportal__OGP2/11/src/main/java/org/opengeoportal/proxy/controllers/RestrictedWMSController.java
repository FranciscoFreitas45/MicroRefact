package org.opengeoportal.proxy.controllers;
 import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opengeoportal.config.ogp.OgpConfigRetriever;
import org.opengeoportal.config.proxy.ProxyConfigRetriever;
import org.opengeoportal.proxy.GenericProxy;
import org.opengeoportal.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.opengeoportal.Interface.ProxyConfigRetriever;
@Controller
@RequestMapping("/restricted/*")
public class RestrictedWMSController {

 final  Logger logger;

@Autowired
 private  ProxyConfigRetriever proxyConfigRetriever;

@Autowired
 private OgpConfigRetriever ogpConfigRetriever;

@Autowired
 private  ProxyFactory proxyFactory;


@RequestMapping(value = "/{repositoryId}/wms", method = RequestMethod.GET)
public void forwardWMSRequest(String repositoryId,HttpServletRequest request,HttpServletResponse response){
    // If the request went through to the controller, we know that the user has authenticated
    // We're also checking that the repositoryId is equal to the one set in config for login capability and that there is a proxy defined
    // for the repositoryId
    // probably a better place to centralize/abstract this
    if (ogpConfigRetriever.getConfig().getLoginConfig().getRepositoryId().equalsIgnoreCase(repositoryId) && proxyConfigRetriever.hasProxy("wms", repositoryId, "restricted")) {
        // forward the request to the protected GeoServer instance
        String remoteUrl = this.getProxyTo(repositoryId) + "?" + request.getQueryString();
        logger.info("executing WMS request to protected GeoServer: " + remoteUrl);
        GenericProxy proxy = proxyFactory.getObject();
        proxy.proxyRequest(request, response, remoteUrl);
    } else {
        response.sendError(403);
    }
}


public String getProxyTo(String repositoryId){
    return proxyConfigRetriever.getInternalProxyUrl("wms", repositoryId, "restricted");
}


}