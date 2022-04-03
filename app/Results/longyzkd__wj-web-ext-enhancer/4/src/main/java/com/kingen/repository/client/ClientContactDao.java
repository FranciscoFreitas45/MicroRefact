package com.kingen.repository.client;
 import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.google.common.collect.Maps;
import com.kingen.bean.ClientContact;
import com.kingen.repository.CommonDao;
import com.kingen.util.Page;
@Component
public class ClientContactDao extends CommonDao<ClientContact, String>{


public List<ClientContact> findClientContacts(String clientId){
    Assert.hasText(clientId, "客户ID不应为空");
    Map<String, Object> parameter = Maps.newHashMap();
    parameter.put("p1", clientId);
    StringBuilder hql = new StringBuilder("select u from ClientContact u,ClientContactRel uo where uo.id.clientId=:p1 and uo.id.clientContactId=u.id");
    return find(hql.toString(), parameter);
}


}