package com.kingen.service.client;
 import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Maps;
import com.kingen.bean.Client;
import com.kingen.bean.ClientContact;
import com.kingen.bean.ClientContactRel;
import com.kingen.bean.ClientContactRelId;
import com.kingen.repository.client.ClientContactDao;
import com.kingen.repository.client.ClientDao;
import com.kingen.service.CommonService;
import com.kingen.util.BeanUtils;
import com.kingen.util.Digests;
import com.kingen.util.Page;
@Component
@Transactional
public class ClientService extends CommonService<Client, String>{

@Autowired
 private  ClientDao dao;

@Autowired
 private  ClientContactDao ccdao;

 private  Logger logger;


public Page<ClientContact> findClientContacts(Page<ClientContact> page,String clientId){
    return ccdao.findClientContacts(page, clientId);
}


public void save(String clientId,ClientContact data){
    Assert.hasText(clientId, "clientId不应为空");
    if (data != null) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("email", data.getEmail());
        // List<ClientContact> u = ccdao.find(params);//查询出的用户会回滚？
        // 查询出的用户会回滚？
        List<ClientContact> u = ccdao.find(data, "email");
        if (!CollectionUtils.isEmpty(u)) {
            throw new Exception("新建联系人失败，邮件已存在！");
        } else {
            Digests.entryptPassword(data);
            ccdao.save(data);
            if (StringUtils.isNotBlank(clientId)) {
                ClientContactRelId relId = new ClientContactRelId(clientId, data.getId());
                ClientContactRel rel = new ClientContactRel(relId);
                ccdao.saveme(rel);
            }
        }
    }
}


public void update(ClientContact c){
    ClientContact t = ccdao.unique(c.getId());
    // 密码不更新，密码单独更新
    BeanUtils.copyNotNullProperties(c, t, new String[] { "password" });
    ccdao.update(t);
}


public void delThemCascade(String string,List<String> ids){
    Assert.notEmpty(ids, "ID不应为空");
    int i = 0;
    if ("client".equals(string)) {
        for (String id : ids) {
            // 
            // 删除客户
            dao.delete(id);
            // 删除联系人
            dao.deleteContactByClient(id);
            // 删除关系
            dao.delRel("client", id);
            if (++i % 50 == 0) {
                // 如果是50个客户就直接刷新缓存
                dao.flushAndClear();
            }
        }
    } else {
        for (String id : ids) {
            // 删除客户联系人
            ccdao.delete(id);
            // 删除关系
            dao.delRel("contact", id);
            if (++i % 50 == 0) {
                dao.flushAndClear();
            }
        }
    }
}


}