package com.sobey.cmop.mvc.service.onecmdb;
 import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.onecmdb.core.IRfcResult;
import org.onecmdb.core.internal.model.QueryCriteria;
import org.onecmdb.core.utils.bean.CiBean;
import org.onecmdb.core.utils.wsdl.IOneCMDBWebService;
import org.onecmdb.core.utils.wsdl.OneCMDBServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Maps;
import com.sobey.cmop.mvc.comm.BaseSevcie;
public class OneCmdbService extends BaseSevcie{

 private  Logger logger;

 private  String token;

 public  IOneCMDBWebService service;


public List<CiBean> search(QueryCriteria qc){
    try {
        CiBean[] ciBeanArray = service.search(token, qc);
        List<CiBean> ciBeans = Arrays.asList(ciBeanArray);
        return ciBeans;
    } catch (Exception e) {
        logger.error("--->OneCMDB search error：" + e.getMessage());
        if (e.getMessage().indexOf("No Session found") > 0) {
            initService();
            search(qc);
        }
        return null;
    }
}


public CiBean findCiBeanByAlias(String alias){
    QueryCriteria<Object> qc = new QueryCriteria<Object>();
    qc.setCiAlias(alias);
    List<CiBean> ciBeans = search(qc);
    if (ciBeans.isEmpty()) {
        return null;
    }
    return ciBeans.get(0);
}


public IOneCMDBWebService initService(){
    try {
        String url = CONFIG_LOADER.getProperty("onecmdbHost");
        String username = CONFIG_LOADER.getProperty("onecmdbUsername");
        String pwd = CONFIG_LOADER.getProperty("onecmdbPwd");
        IOneCMDBWebService service = OneCMDBServiceFactory.getWebService(url);
        // 通过OneCMDB身份认证
        token = service.auth(username, pwd);
        logger.info("Authenticated token=" + token);
        return service;
    } catch (Exception e) {
        logger.error("--->初始化OneCMDB接口服务失败！" + e.getMessage());
        return null;
    }
}


public String findCiAliasByText(String ci,String text){
    QueryCriteria qc = new QueryCriteria();
    qc.setOffspringOfAlias(ci);
    qc.setTextMatchValue(true);
    qc.setText(text);
    List<CiBean> ciBeans = search(qc);
    if (ciBeans != null) {
        logger.info("find results(" + ci + "," + text + ")：" + ciBeans.size());
        for (CiBean ciBean : ciBeans) {
            return ciBean.getAlias();
        }
    }
    return "";
}


public boolean update(List<CiBean> list){
    try {
        IRfcResult result = service.update(token, list.toArray(new CiBean[0]), null);
        if (result.isRejected()) {
            logger.info("Can't add/update instances cause by：" + result.getRejectCause());
            return false;
        } else {
            logger.info("Instances added or updated");
            return true;
        }
    } catch (Exception e) {
        logger.error("--->OneCMDB add/update error：" + e.getMessage());
        if (e.getMessage().indexOf("No Session found") > 0) {
            initService();
            update(list);
        }
        return false;
    }
}


public boolean delete(List<CiBean> list){
    try {
        IRfcResult result = service.update(token, null, list.toArray(new CiBean[0]));
        if (result.isRejected()) {
            logger.info("Can't delete instances cause by：" + result.getRejectCause());
            return false;
        } else {
            logger.info("Instances deleted");
            return true;
        }
    } catch (Exception e) {
        logger.error("--->OneCMDB delete error：" + e.getMessage());
        if (e.getMessage().indexOf("No Session found") > 0) {
            initService();
            delete(list);
        }
        return false;
    }
}


public Map<String,String> findCiByText(String ci,String text){
    QueryCriteria qc = new QueryCriteria();
    qc.setOffspringOfAlias(ci);
    qc.setTextMatchValue(true);
    qc.setText(text);
    List<CiBean> ciBeans = search(qc);
    Map<String, String> temp = Maps.newHashMap();
    if (ciBeans != null) {
        for (CiBean ciBean : ciBeans) {
            temp.put(ciBean.getAlias(), ciBean.getDisplayName());
        }
    }
    logger.info("find results(" + ci + "," + text + ")：" + temp.size());
    return temp;
}


}