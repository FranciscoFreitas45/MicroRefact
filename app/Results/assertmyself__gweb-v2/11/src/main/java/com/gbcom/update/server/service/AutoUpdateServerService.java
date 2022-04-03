package com.gbcom.update.server.service;
 import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.gbcom.system.utils.ZipFileUtil;
import com.gbcom.update.common.VersionInfo;
import com.gbcom.update.common.exception.AutoUpdateException;
import com.gbcom.update.common.util.AutoUpdateUtil;
import com.gbcom.update.server.xml.FilterRule;
import com.gbcom.update.server.xml.UpdateServerContextManager;
@Service("AutoUpgradeService")
public class AutoUpdateServerService {

 private  Logger LOG;


public void downNewestVersion(HttpServletResponse response,HttpServletRequest request,String product){
    try {
        String filePath = request.getSession().getServletContext().getRealPath(AutoUpdateUtil.getProperty("server.war.path") + product + ".zip");
        LOG.info("download file start ; war file path : " + filePath);
        AutoUpdateUtil.download(response, filePath);
    } catch (Exception e) {
        throw new AutoUpdateException("download newest version file exception :", e);
    }
}


public VersionInfo getNewestVersionInfo(String product){
    VersionInfo versionInfo = UpdateServerContextManager.getInstance().getVersionInfo(product);
    return versionInfo;
}


public boolean isNewestVersion(String product,String curVer,String verNo,String time){
    try {
        VersionInfo versionInfo = UpdateServerContextManager.getInstance().getVersionInfo(product);
        if (versionInfo == null) {
            return false;
        }
        String versionTime = versionInfo.getDate();
        String versionNo = versionInfo.getNo();
        List<FilterRule> filterRules = UpdateServerContextManager.getInstance().getFilterRules(product);
        for (FilterRule rule : filterRules) {
            if (rule.getFilterConst().equals("ver") && rule.getFilterContent().equals(versionNo)) {
                return false;
            }
        }
        return Long.parseLong(time) >= Long.parseLong(versionTime);
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        throw new AutoUpdateException("check version exception :", e);
    }
}


public boolean zipNewestVersion(String src,String dst,List<String> excludes,List<String> includes){
    try {
        LOG.info("zip file start !");
        List<String> products = UpdateServerContextManager.getInstance().getProducts();
        for (String product : products) {
            String proName = UpdateServerContextManager.getInstance().getVersionInfo(product).getName();
            String srcPath = src + proName;
            LOG.info("file path :" + srcPath);
            File file = new File(srcPath);
            if (file.exists()) {
                File zipFile = new File(srcPath + ".zip");
                if (!zipFile.exists()) {
                    ZipFileUtil.zip(srcPath, srcPath + ".zip", excludes, includes);
                }
            } else {
                return false;
            }
        }
        return true;
    } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        return false;
    }
}


}