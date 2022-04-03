package org.opengeoportal.security;
 import java.io.Serializable;
import org.opengeoportal.config.ogp.OgpConfigRetriever;
import org.opengeoportal.solr.SolrRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
public class LayerPermissionEvaluator implements PermissionEvaluator{

 final  Logger logger;

@Autowired
 private OgpConfigRetriever ogpConfigRetriever;


@Override
public boolean hasPermission(Authentication auth,Serializable targetId,String targetType,Object permission){
    // TODO Auto-generated method stub
    return false;
}


public boolean hasAuthority(Authentication authentication,String role){
    logger.info(authentication.getName());
    for (GrantedAuthority ga : authentication.getAuthorities()) {
        logger.info(ga.getAuthority());
        if (ga.getAuthority().equalsIgnoreCase(role)) {
            return true;
        }
    }
    return false;
}


}