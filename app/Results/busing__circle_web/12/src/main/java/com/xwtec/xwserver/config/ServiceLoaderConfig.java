package com.xwtec.xwserver.config;
 import org.apache.log4j.Logger;
import com.circle.service.dct.IDictService;
import com.xwtec.xwserver.util.SpringUtils;
public class ServiceLoaderConfig {

 private  Logger logger;


public void loadCitiesNameAndAreacodeMapData(){
    logger.info("get into ServiceLoaderConfig.loadCitiesNameAndAreacodeMapData");
    try {
        IDictService dictService = SpringUtils.getBean("dictService");
        dictService.initDictMap();
        dictService.initGoodType();
        dictService.initGoodTypeArg();
        dictService.initGoodTypeAttr();
        dictService.initFarm();
        dictService.initMessageTypeBeans();
        logger.info("ServiceLoaderConfig.loadCitiesNameAndAreacodeMapData had completed successfully.");
    } catch (Exception e) {
        logger.error("ServiceLoaderConfig.loadCitiesNameAndAreacodeMapData had completed failed.");
        logger.error("[ServiceLoaderConfig:doAction]:failed.ex message:" + e.getMessage());
    }
}


public void loaderServices(){
    // 将城市和areacode注入静态map强制刷新
    this.loadCitiesNameAndAreacodeMapData();
}


}