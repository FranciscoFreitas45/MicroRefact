package com.lingxiang2014.service.impl;
 import javax.annotation.Resource;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.lingxiang2014.service.CacheService;
import com.lingxiang2014.util.SettingUtils;
import freemarker.template.TemplateModelException;
@Service("cacheServiceImpl")
public class CacheServiceImpl implements CacheService{

@Resource(name = "ehCacheManager")
 private  CacheManager cacheManager;

@Resource(name = "messageSource")
 private  ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

@Resource(name = "freeMarkerConfigurer")
 private  FreeMarkerConfigurer freeMarkerConfigurer;


public String getDiskStorePath(){
    return cacheManager.getConfiguration().getDiskStoreConfiguration().getPath();
}


public int getCacheSize(){
    int cacheSize = 0;
    String[] cacheNames = cacheManager.getCacheNames();
    if (cacheNames != null) {
        for (String cacheName : cacheNames) {
            Ehcache cache = cacheManager.getEhcache(cacheName);
            if (cache != null) {
                cacheSize += cache.getSize();
            }
        }
    }
    return cacheSize;
}


@CacheEvict(value = { "setting", "authorization", "logConfig", "template", "area", "seo", "adPosition", "memberAttribute", "tag", "article", "articleCategory" }, allEntries = true)
public void clear(){
    reloadableResourceBundleMessageSource.clearCache();
    try {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("setting", SettingUtils.get());
    } catch (TemplateModelException e) {
        e.printStackTrace();
    }
    freeMarkerConfigurer.getConfiguration().clearTemplateCache();
}


}