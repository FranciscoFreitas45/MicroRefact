package com.gbcom.update.server.AutoZipVersionTask;
 import java.util.List;
import org.apache.log4j.Logger;
import com.gbcom.update.common.util.AutoUpdateUtil;
import com.gbcom.update.server.service.AutoUpdateServerService;
import com.gbcom.update.server.xml.UpdateServerContextManager;
import com.hc.core.utils.SpringUtils;
public class AutoZipVersionThread implements Runnable{

 private  String path;

/**
 * 无参构造器
 */
public AutoZipVersionThread() {
}/**
 * @param path
 *            路径
 */
public AutoZipVersionThread(String path) {
    this.path = path;
}
@Override
public void run(){
    String product = "";
    try {
        AutoUpdateServerService autoUpgradeService = (AutoUpdateServerService) SpringUtils.getBean("AutoUpgradeService");
        if (path == null || path.equals("")) {
            stop();
            LOG.error("can't get file absolute path!");
        }
        if (path.indexOf("WEB-INF") == -1) {
            stop();
            LOG.error("file path is wrong path !" + path);
        }
        path = path.substring(1, path.indexOf("WEB-INF"));
        List<String> products = UpdateServerContextManager.getInstance().getProducts();
        for (int i = 0; i < products.size(); i++) {
            product = products.get(i);
            boolean result = autoUpgradeService.zipNewestVersion(path + AutoUpdateUtil.getProperty("server.war.path"), path + AutoUpdateUtil.getProperty("server.war.path"), UpdateServerContextManager.getInstance().getExcludes(product), UpdateServerContextManager.getInstance().getIncludes(product));
            if (result) {
                LOG.info("zip " + product + " success!");
            } else {
                LOG.info(product + ".zip has existed !");
            }
        }
        stop();
    } catch (Exception e) {
        LOG.info("zip " + product + " failed !");
        LOG.error(e.getMessage(), e);
        stop();
    }
}


}