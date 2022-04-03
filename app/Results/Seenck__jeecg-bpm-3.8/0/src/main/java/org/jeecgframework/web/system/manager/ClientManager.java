package org.jeecgframework.web.system.manager;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service("clientManager")
public class ClientManager {

 private  Logger log;

 private  String ONLINE_CLIENTS_CACHE_KEY;

@Resource
 private  CacheServiceI cacheService;


@SuppressWarnings("unchecked")
public boolean addClientToCachedMap(String sessionId,Client client){
    HashMap<String, Client> onLineClients;
    if (cacheService.get(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY) == null) {
        onLineClients = new HashMap<String, Client>();
    } else {
        onLineClients = (HashMap<String, Client>) cacheService.get(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY);
    }
    onLineClients.put(sessionId, client);
    cacheService.put(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY, onLineClients);
    return true;
}


public void addClinet(String sessionId,Client client){
    // 当前session会话，保存登录用户信息
    ContextHolderUtils.getSession().setAttribute(sessionId, client);
    // 保存在线用户信息列表
    if (client != null && client.getUser() != null) {
        Client ret = new Client();
        ret.setIp(client.getIp());
        ret.setLogindatetime(client.getLogindatetime());
        // 在线用户列表缓存，只保留几个字段显示即可，其他菜单权限内容不需要，降低内存占用
        TSUser t = new TSUser();
        t.setUserName(client.getUser().getUserName());
        t.setRealName(client.getUser().getRealName());
        ret.setUser(t);
        addClientToCachedMap(sessionId, ret);
    }
}


@SuppressWarnings("unchecked")
public Collection<Client> getAllClient(){
    if (cacheService.get(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY) != null) {
        HashMap<String, Client> onLineClients = (HashMap<String, Client>) cacheService.get(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY);
        return onLineClients.values();
    } else
        return new ArrayList<Client>();
}


public Client getClient(){
    String sessionId = ContextHolderUtils.getSession().getId();
    if (!StringUtils.isEmpty(sessionId) && ContextHolderUtils.getSession().getAttribute(sessionId) != null) {
        return (Client) ContextHolderUtils.getSession().getAttribute(sessionId);
    } else {
        return null;
    }
}


public ClientManager getInstance(){
    ClientManager clientManager = ApplicationContextUtil.getContext().getBean(ClientManager.class);
    log.debug("  ------------获取工具类------------clientManager------------------");
    return clientManager;
}


@SuppressWarnings("unchecked")
public boolean removeClientFromCachedMap(String sessionId){
    HashMap<String, Client> onLineClients;
    if (cacheService.get(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY) != null) {
        onLineClients = (HashMap<String, Client>) cacheService.get(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY);
        onLineClients.remove(sessionId);
        cacheService.put(CacheServiceI.FOREVER_CACHE, ONLINE_CLIENTS_CACHE_KEY, onLineClients);
        return true;
    } else {
        return false;
    }
}


public void removeClinet(String sessionId){
    try {
        ContextHolderUtils.removeSession(sessionId);
    } catch (Exception e) {
    }
    try {
        HttpSession session = ContextHolderUtils.getSession();
        session.removeAttribute(sessionId);
    } catch (Exception e) {
    }
    // 从在线用户列表移除用户
    removeClientFromCachedMap(sessionId);
}


}