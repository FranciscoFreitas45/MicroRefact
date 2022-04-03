package com.crontab;
 import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
public class ServiceFactory {

 private  Logger LOGGER;

 private  ServiceFactory SINGLETON_INSTANCE;

 private  Map<String,Object> serviceMap;

/**
 * Private constructor to instantiate the factory since the factory is a singleton class.
 */
protected ServiceFactory() {
}
public Object createService(String serviceName){
    Object service = null;
    try {
        service = Class.forName(serviceName).newInstance();
    } catch (Exception e) {
        LOGGER.error("Service not found: " + e.getMessage(), e);
        throw new ObjectNotFoundException("Service not found.", e);
    }
    if (service instanceof OrderValidationService) {
        initService((OrderValidationService) service);
    } else if (service instanceof InvestManagerFundService) {
        initService((InvestManagerFundService) service);
    }
    if (service instanceof OrderNormalSizeService) {
        initService((OrderNormalSizeService) service);
    }
    // legacy OMS service
    if (service instanceof FxOrderService) {
        initService((FxOrderService) service);
    }
    if (service instanceof QuartzService) {
        initService((QuartzService) service);
    }
    serviceMap.put(serviceName, service);
    return service;
}


public Object findService(String serviceName){
    Object object = serviceMap.get(serviceName);
    if (object == null) {
        object = this.createService(serviceName);
    }
    return object;
}


public void initService(FxOrderService service){
    LOGGER.debug("init FxOrderService begins ...");
    FxOrderDao fxOrderDao = new FxOrderDaoImpl();
    fxOrderDao.setDataSource(new CloudDataSource(CloudDataSource.LEGACY_OMS_DATASOURCE));
    service.setFxOrderDao(fxOrderDao);
    LOGGER.debug("init FxOrderService ends ...");
}


public ServiceFactory getInstance(){
    return SINGLETON_INSTANCE;
}


}