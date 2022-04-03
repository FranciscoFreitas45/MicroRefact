package com.gbcom.common.template.xml.msg;
 import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.gbcom.common.template.xml.msg.process.IMessageProcess;
import com.gbcom.op.util.xml.XStreamUtil;
public class RouteInfoManager {

 private  RouteInfoContext routeContext;

 private  Map<Integer,IMessageProcess> msgProcess;

 private  RouteInfoManager instance;

private RouteInfoManager() {
    load();
}
@SuppressWarnings("unchecked")
public void load(){
    final Class<?>[] classContext = { RouteInfoContext.class, RouteInfo.class };
    final URL url = Thread.currentThread().getContextClassLoader().getResource("config/msg/routeinfo.xml");
    routeContext = XStreamUtil.fromXML(RouteInfoContext.class, url.getFile(), classContext);
    for (RouteInfo routeInfoRoute : routeContext.getRouteList()) {
        int msgID = routeInfoRoute.getMsgid();
        String process = routeInfoRoute.getProcessor();
        try {
            Class<IMessageProcess> tmp = (Class<IMessageProcess>) Class.forName(process);
            msgProcess.put(msgID, tmp.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }
}


public IMessageProcess getMsgProcess(int id){
    return msgProcess.get(id);
}


public RouteInfoManager getInstance(){
    return instance;
}


}