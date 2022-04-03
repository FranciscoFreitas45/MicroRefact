package com.sobey.cmop.mvc.service.esg;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.AccountConstant;
import com.sobey.cmop.mvc.constant.NetworkConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.dao.EsgRuleItemDao;
import com.sobey.cmop.mvc.dao.NetworkEsgItemDao;
import com.sobey.cmop.mvc.entity.EsgRuleItem;
import com.sobey.cmop.mvc.entity.Group;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.SearchFilter;
import com.sobey.framework.utils.SearchFilter.Operator;
import com.sobey.cmop.mvc.DTO.Group;
import com.sobey.cmop.mvc.DTO.User;
import com.sobey.cmop.mvc.DTO.SearchFilter;
@Service
@Transactional(readOnly = true)
public class EsgService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  NetworkEsgItemDao networkEsgItemDao;

@Resource
 private  EsgRuleItemDao esgRuleItemDao;


@Transactional(readOnly = false)
public NetworkEsgItem updateESG(Integer id,String description,String[] protocols,String[] portRanges,String[] visitSources,String[] visitTargets){
    NetworkEsgItem networkEsgItem = this.getNetworkEsgItem(id);
    networkEsgItem.setDescription(description);
    this.saveOrUpdate(networkEsgItem);
    // 删除老的rule
    this.deleteEsgRuleItem(this.getEsgRuleItemListByEsgId(id));
    // ESG的规则保存
    List<EsgRuleItem> esgRuleItems = this.wrapEsgRuleItemToList(networkEsgItem, protocols, portRanges, visitSources, visitTargets);
    this.saveOrUpate(esgRuleItems);
    /* 保存至oneCMDB */
    comm.oneCmdbUtilService.saveESGToOneCMDB(networkEsgItem);
    return networkEsgItem;
}


@Transactional(readOnly = false)
public void delete(Integer id){
    // 删除oneCMDB的数据.
    comm.oneCmdbUtilService.deleteESGToOneCMDB(this.getNetworkEsgItem(id));
    networkEsgItemDao.delete(id);
}


public EsgRuleItem getEsgRule(Integer id){
    return esgRuleItemDao.findOne(id);
}


public List<EsgRuleItem> getEsgRuleItemListByEsgId(Integer esgId){
    return esgRuleItemDao.findByNetworkEsgItemId(esgId);
}


public List<NetworkEsgItem> getESGList(){
    return networkEsgItemDao.findByUserIdOrShare(getCurrentUserId(), NetworkConstant.Share.公用.toBoolean());
}


public List<EsgRuleItem> wrapEsgRuleItemToList(NetworkEsgItem networkEsgItem,String[] protocols,String[] portRanges,String[] visitSources,String[] visitTargets){
    List<EsgRuleItem> esgRuleItems = new ArrayList<EsgRuleItem>();
    int protocolSize = protocols.length;
    if (protocolSize == 1) {
        StringBuilder portRangeSB = new StringBuilder();
        StringBuilder sourceSB = new StringBuilder();
        StringBuilder targetRangeSB = new StringBuilder();
        for (String portRange : portRanges) {
            portRangeSB.append(portRange).append(",");
        }
        for (String visitSource : visitSources) {
            sourceSB.append(visitSource).append(",");
        }
        for (String visitTarget : visitTargets) {
            targetRangeSB.append(visitTarget).append(",");
        }
        EsgRuleItem esgRuleItem = new EsgRuleItem(networkEsgItem, protocols[0], portRangeSB.toString().substring(0, portRangeSB.toString().length() - 1), sourceSB.toString().substring(0, sourceSB.toString().length() - 1), targetRangeSB.toString().substring(0, targetRangeSB.toString().length() - 1));
        esgRuleItems.add(esgRuleItem);
    } else {
        for (int i = 0; i < protocolSize; i++) {
            EsgRuleItem esgRuleItem = new EsgRuleItem(networkEsgItem, protocols[i], portRanges[i], visitSources[i], visitTargets[i]);
            esgRuleItems.add(esgRuleItem);
        }
    }
    return esgRuleItems;
}


@Transactional(readOnly = false)
public void deleteEsgRuleItem(Collection<EsgRuleItem> esgRuleItems){
    esgRuleItemDao.delete(esgRuleItems);
}


public NetworkEsgItem getNetworkEsgItem(Integer id){
    return networkEsgItemDao.findOne(id);
}


public boolean isShare(User user){
    boolean result = false;
    List<Integer> groups = new ArrayList<Integer>();
    // 设置指定的权限角色.该权限角色创建的ESG将成为公共可以用的ESG.
    groups.add(AccountConstant.DefaultGroups.admin.toInteger());
    // 如果包含有指定权限角色,则设置User为null并break Loop.
    for (Group group : user.getGroupList()) {
        if (groups.contains(group.getId())) {
            result = true;
            break;
        }
    }
    return result;
}


public Page<NetworkEsgItem> getNetworkEsgItemPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    filters.put("networkEsgItem.user.id", new SearchFilter("user.id", Operator.EQ, getCurrentUserId()));
    Specification<NetworkEsgItem> spec = DynamicSpecifications.bySearchFilter(filters.values(), NetworkEsgItem.class);
    return networkEsgItemDao.findAll(spec, pageRequest);
}


@Transactional(readOnly = false)
public NetworkEsgItem saveESG(String description,String[] protocols,String[] portRanges,String[] visitSources,String[] visitTargets){
    String identifier = comm.applyService.generateIdentifier(ResourcesConstant.ServiceType.ESG.toInteger());
    NetworkEsgItem networkEsgItem = new NetworkEsgItem();
    networkEsgItem.setUser(comm.accountService.getCurrentUser());
    networkEsgItem.setDescription(description);
    networkEsgItem.setIdentifier(identifier);
    networkEsgItem.setShare(this.isShare(comm.accountService.getCurrentUser()));
    this.saveOrUpdate(networkEsgItem);
    // ESG的规则保存
    List<EsgRuleItem> esgRuleItems = this.wrapEsgRuleItemToList(networkEsgItem, protocols, portRanges, visitSources, visitTargets);
    this.saveOrUpate(esgRuleItems);
    /* 保存至oneCMDB */
    comm.oneCmdbUtilService.saveESGToOneCMDB(networkEsgItem);
    return networkEsgItem;
}


@Transactional(readOnly = false)
public NetworkEsgItem saveOrUpdate(NetworkEsgItem networkEsgItem){
    return networkEsgItemDao.save(networkEsgItem);
}


@Transactional(readOnly = false)
public void saveOrUpate(Collection<EsgRuleItem> esgRuleItems){
    esgRuleItemDao.save(esgRuleItems);
}


}