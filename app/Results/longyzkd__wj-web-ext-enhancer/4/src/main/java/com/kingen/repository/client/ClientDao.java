package com.kingen.repository.client;
 import java.util.Map;
import org.springframework.stereotype.Component;
import com.google.common.collect.Maps;
import com.kingen.bean.Client;
import com.kingen.repository.CommonDao;
@Component
public class ClientDao extends CommonDao<Client, String>{


public void delRel(String string,String v){
    String field = "client".equals(string) ? "clientId" : "clientContactId";
    String hql = "delete from ClientContactRel where id." + field + "=:p1 ";
    Map params = Maps.newHashMap();
    params.put("p1", v);
    executeHql(hql, params);
}


public void deleteContactByClient(String clientId){
    String hql = "delete from ClientContact cc where cc.id in (select id.clientContactId from ClientContactRel where id.clientId = :p1 )";
    Map params = Maps.newHashMap();
    params.put("p1", clientId);
    executeHql(hql, params);
}


}