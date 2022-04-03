package org.jeecgframework.web.system.service.impl;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.MutiLangEntity;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Interface.ICommonDao;
@Service("mutiLangService")
public class MutiLangServiceImpl implements MutiLangServiceI{

 private  Logger logger;

@Autowired
 public  ICommonDao commonDao;

@Autowired
 private  CacheServiceI cacheService;


public String getLang(String lanKey,String langArg){
    String langContext = "";
    if (StringUtil.isEmpty(langArg)) {
        langContext = getLang(lanKey);
    } else {
        String[] argArray = langArg.split(",");
        langContext = getLang(lanKey);
        for (int i = 0; i < argArray.length; i++) {
            String langKeyArg = argArray[i].trim();
            String langKeyContext = getLang(langKeyArg);
            langContext = StringUtil.replace(langContext, "{" + i + "}", langKeyContext);
        }
    }
    return langContext;
}


public void putMutiLang(MutiLangEntity mutiLangEntity){
    Map<String, String> ls;
    Object obj = cacheService.get(CacheServiceI.FOREVER_CACHE, ResourceUtil.MUTI_LANG_FOREVER_CACHE_KEY);
    if (obj != null) {
        ls = (Map<String, String>) obj;
    } else {
        ls = new HashMap<String, String>();
    }
    ls.put(mutiLangEntity.getLangKey() + "_" + mutiLangEntity.getLangCode(), mutiLangEntity.getLangContext());
    cacheService.put(CacheServiceI.FOREVER_CACHE, ResourceUtil.MUTI_LANG_FOREVER_CACHE_KEY, ls);
}


@Transactional(readOnly = true)
public void initAllMutiLang(){
    Map<String, String> ls = new HashMap<String, String>();
    List<MutiLangEntity> mutiLang = this.commonDao.loadAll(MutiLangEntity.class);
    for (MutiLangEntity mutiLangEntity : mutiLang) {
        ls.put(mutiLangEntity.getLangKey() + "_" + mutiLangEntity.getLangCode(), mutiLangEntity.getLangContext());
    }
    cacheService.put(CacheServiceI.FOREVER_CACHE, ResourceUtil.MUTI_LANG_FOREVER_CACHE_KEY, ls);
    logger.info("  ------ 初始化国际化语言【系统缓存】  ------ size: [{}] ", ls.size());
}


public void refleshMutiLangCach(){
    logger.info("  ------ 重置国际化语言【系统缓存】  ------  ");
    initAllMutiLang();
}


}