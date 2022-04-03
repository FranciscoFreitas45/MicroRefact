package org.gliderwiki.util;
 import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.gliderwiki.web.system.SystemConst;
public class PropertyUtil {


public Properties getVersionPropertyInfo(String svcPath,boolean isServer){
    Properties props = null;
    File file = null;
    InputStream ins = null;
    try {
        file = new File(svcPath);
        props = new Properties();
        if (isServer) {
            ins = new FileInputStream(svcPath + "/server-version.properties");
        } else {
            ins = new FileInputStream(svcPath + "/client-version.properties");
        }
        props.load(ins);
        ins.close();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        ins.close();
    }
    return props;
}


public Properties getMailPropertyInfo(String svcPath){
    Properties props = null;
    File file = null;
    InputStream ins = null;
    try {
        file = new File(svcPath);
        props = new Properties();
        ins = new FileInputStream(svcPath + "/mail.properties");
        props.load(ins);
        ins.close();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        ins.close();
    }
    return props;
}


public String getVersionProps(HttpServletRequest request,boolean isServer){
    String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "version");
    Properties props = getVersionPropertyInfo(svcPath, isServer);
    String version = props.getProperty("version.info");
    return version;
}


public Properties getPropertyInfo(String svcPath,String propsName){
    Properties props = null;
    File file = null;
    InputStream ins = null;
    try {
        file = new File(svcPath);
        props = new Properties();
        ins = new FileInputStream(svcPath + "/" + propsName);
        props.load(ins);
        ins.close();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        ins.close();
    }
    return props;
}


}