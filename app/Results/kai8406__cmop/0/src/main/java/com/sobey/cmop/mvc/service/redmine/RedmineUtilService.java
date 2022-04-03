package com.sobey.cmop.mvc.service.redmine;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sobey.cmop.mvc.comm.BaseSevcie;
import com.sobey.cmop.mvc.constant.ApplyConstant;
import com.sobey.cmop.mvc.constant.FieldNameConstant;
import com.sobey.cmop.mvc.constant.FieldNameConstant.Compate;
import com.sobey.cmop.mvc.constant.FieldNameConstant.Dns;
import com.sobey.cmop.mvc.constant.FieldNameConstant.Eip;
import com.sobey.cmop.mvc.constant.FieldNameConstant.Elb;
import com.sobey.cmop.mvc.constant.FieldNameConstant.MdnLiveItem;
import com.sobey.cmop.mvc.constant.FieldNameConstant.MdnVodItem;
import com.sobey.cmop.mvc.constant.FieldNameConstant.Storage;
import com.sobey.cmop.mvc.constant.FieldNameConstant.monitorCompute;
import com.sobey.cmop.mvc.constant.FieldNameConstant.monitorElb;
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.constant.RedmineConstant;
import com.sobey.cmop.mvc.constant.ResourcesConstant;
import com.sobey.cmop.mvc.entity.Apply;
import com.sobey.cmop.mvc.entity.Change;
import com.sobey.cmop.mvc.entity.ChangeItem;
import com.sobey.cmop.mvc.entity.ComputeItem;
import com.sobey.cmop.mvc.entity.CpItem;
import com.sobey.cmop.mvc.entity.Failure;
import com.sobey.cmop.mvc.entity.MdnItem;
import com.sobey.cmop.mvc.entity.MonitorCompute;
import com.sobey.cmop.mvc.entity.MonitorElb;
import com.sobey.cmop.mvc.entity.MonitorMail;
import com.sobey.cmop.mvc.entity.MonitorPhone;
import com.sobey.cmop.mvc.entity.NetworkDnsItem;
import com.sobey.cmop.mvc.entity.NetworkEipItem;
import com.sobey.cmop.mvc.entity.NetworkElbItem;
import com.sobey.cmop.mvc.entity.NetworkEsgItem;
import com.sobey.cmop.mvc.entity.Resources;
import com.sobey.cmop.mvc.entity.ServiceTag;
import com.sobey.cmop.mvc.entity.StorageItem;
import com.sobey.cmop.mvc.entity.User;
import com.sobey.framework.utils.DateUtil;
import com.sobey.cmop.mvc.DTO.Failure;
import com.sobey.cmop.mvc.DTO.ServiceTag;
@Service
@Transactional(readOnly = true)
public class RedmineUtilService extends BaseSevcie{

 private  Logger logger;

 private  String NEWLINE;

 private  String BLANK;

 private  String RARR;

 private  String CHANGE_ONECMDB_NOTIFICATION;


public String failureResourcesRedmineDesc(Failure failure,List<ComputeItem> computeItems,List<StorageItem> storageItems,List<NetworkElbItem> elbItems,List<NetworkEipItem> eipItems,List<NetworkDnsItem> dnsItems,List<MonitorMail> monitorMails,List<MonitorPhone> monitorPhones,List<MonitorCompute> monitorComputes,List<MonitorElb> monitorElbs,List<MdnItem> mdnItems,List<CpItem> cpItems){
    try {
        StringBuilder content = new StringBuilder();
        content.append("# +*故障申报信息*+").append(NEWLINE);
        content.append("<pre>").append(NEWLINE);
        content.append("申报人：").append(failure.getUser().getName()).append(NEWLINE);
        content.append("申报标题：").append(failure.getTitle()).append(NEWLINE);
        content.append("申报时间：").append(failure.getCreateTime()).append(NEWLINE);
        content.append("故障类型：").append(ApplyConstant.ServiceType.get(failure.getFaultType())).append(NEWLINE);
        content.append("优先级：").append(RedmineConstant.Priority.get(failure.getLevel())).append(NEWLINE);
        content.append("受理人：").append(RedmineConstant.Assignee.get(failure.getAssignee())).append(NEWLINE);
        content.append("故障现象及描述：").append(failure.getDescription()).append(NEWLINE);
        content.append("</pre>");
        this.generateContentByLists(failure.getUser(), content, computeItems, storageItems, elbItems, eipItems, dnsItems, monitorMails, monitorPhones, monitorComputes, monitorElbs, mdnItems, cpItems);
        return content.toString();
    } catch (Exception e) {
        logger.error("--->故障申报Failure拼装Redmine内容出错：" + e.getMessage());
        return null;
    }
}


public void saveChangeTextAndNEWLINE(String fieldName,StringBuilder content,ChangeItem changeItem){
    content.append(fieldName + ":" + BLANK).append(NEWLINE).append(changeItem.getOldString()).append(RARR).append(NEWLINE).append(changeItem.getNewString()).append(NEWLINE);
}


public void generateContentByLists(User user,StringBuilder content,List<ComputeItem> computeItems,List<StorageItem> storageItems,List<NetworkElbItem> elbItems,List<NetworkEipItem> eipItems,List<NetworkDnsItem> dnsItems,List<MonitorMail> monitorMails,List<MonitorPhone> monitorPhones,List<MonitorCompute> monitorComputes,List<MonitorElb> monitorElbs,List<MdnItem> mdnItems,List<CpItem> cpItems){
    RedmineTextUtil.generateCompute(content, computeItems);
    RedmineTextUtil.generateStorage(content, storageItems);
    RedmineTextUtil.generateElb(content, elbItems);
    RedmineTextUtil.generateEip(content, eipItems);
    RedmineTextUtil.generateDNS(content, dnsItems);
    RedmineTextUtil.generateMonitorMail(content, monitorMails);
    RedmineTextUtil.generateMonitorPhone(content, monitorPhones);
    RedmineTextUtil.generateMonitorCompute(content, monitorComputes);
    RedmineTextUtil.generateMonitorElb(content, monitorElbs);
    RedmineTextUtil.generateMdn(content, mdnItems);
    RedmineTextUtil.generateCP(content, cpItems);
}


public String applyRedmineDesc(Apply apply){
    try {
        StringBuilder content = new StringBuilder();
        content.append("*服务申请的详细信息*").append(NEWLINE + NEWLINE);
        content.append("# +*基本信息*+").append(NEWLINE);
        content.append("<pre>").append(NEWLINE);
        content.append("申请标题: ").append(apply.getTitle()).append(NEWLINE);
        content.append("服务标签: ").append(apply.getServiceTag()).append(NEWLINE);
        content.append("优先级: ").append(RedmineConstant.Priority.get(apply.getPriority())).append(NEWLINE);
        content.append("服务起止日期: ").append(apply.getServiceStart()).append(" 至 ").append(apply.getServiceEnd()).append(NEWLINE);
        content.append("用途描述: ").append(apply.getDescription()).append(NEWLINE);
        content.append("申请人: ").append(apply.getUser().getName()).append(NEWLINE);
        content.append("申请时间: ").append(DateUtil.formatDate(apply.getCreateTime())).append(NEWLINE);
        content.append("</pre>");
        content.append(NEWLINE);
        // 拼装计算资源Compute信息
        this.generateContentByLists(apply.getUser(), content, new ArrayList<ComputeItem>(apply.getComputeItems()), new ArrayList<StorageItem>(apply.getStorageItems()), new ArrayList<NetworkElbItem>(apply.getNetworkElbItems()), new ArrayList<NetworkEipItem>(apply.getNetworkEipItems()), new ArrayList<NetworkDnsItem>(apply.getNetworkDnsItems()), new ArrayList<MonitorMail>(apply.getMonitorMails()), new ArrayList<MonitorPhone>(apply.getMonitorPhones()), new ArrayList<MonitorCompute>(apply.getMonitorComputes()), new ArrayList<MonitorElb>(apply.getMonitorElbs()), new ArrayList<MdnItem>(apply.getMdnItems()), new ArrayList<CpItem>(apply.getCpItems()));
        return content.toString();
    } catch (Exception e) {
        logger.error("--->服务申请Apply拼装Redmine内容出错：" + e.getMessage());
        return null;
    }
}


public void saveESGChangeText(String fieldName,StringBuilder content,ChangeItem changeItem){
    String[] ids = StringUtils.split(changeItem.getNewValue(), ",");
    List<NetworkEsgItem> networkEsgItems = new ArrayList<NetworkEsgItem>();
    for (String id : ids) {
        networkEsgItems.add(comm.esgService.getNetworkEsgItem(Integer.valueOf(id)));
    }
    content.append(fieldName + ":" + BLANK).append(changeItem.getOldString()).append(RARR).append(ComputeItem.extractDetailToString(networkEsgItems)).append(NEWLINE);
}


public String resourcesRedmineDesc(ServiceTag serviceTag){
    try {
        StringBuilder content = new StringBuilder();
        content.append("*服务变更的详细信息*").append(NEWLINE + NEWLINE);
        content.append("* +*服务标签基本信息*+").append(NEWLINE);
        content.append("<pre>").append(NEWLINE);
        content.append("标签名: ").append(serviceTag.getName()).append(NEWLINE);
        content.append("优先级: ").append(RedmineConstant.Priority.get(serviceTag.getPriority())).append(NEWLINE);
        content.append("服务起止日期: ").append(serviceTag.getServiceStart()).append(" 至 ").append(serviceTag.getServiceEnd()).append(NEWLINE);
        content.append("用途描述: ").append(serviceTag.getDescription()).append(NEWLINE);
        content.append("申请人: ").append(serviceTag.getUser().getName()).append(NEWLINE);
        content.append("</pre>");
        content.append(NEWLINE + NEWLINE);
        content.append("* +*变更信息*+").append(NEWLINE);
        content.append("<pre>").append(NEWLINE);
        List<Resources> resourcesList = comm.resourcesService.getCommitedResourcesListByServiceTagId(serviceTag.getId());
        for (Resources resources : resourcesList) {
            Integer serviceType = resources.getServiceType();
            for (Change change : resources.getChanges()) {
                // 资源标识符(标识符) + 变更说明
                content.append("变更资源标识符:" + BLANK).append(resources.getServiceIdentifier());
                // 只有当ip不为0.0.0.0时才插入IP(即资源本身有IP时,像DNS,ES3这些没有IP的资源将不显示ip)
                if (!IpPoolConstant.DEFAULT_IPADDRESS.equals(resources.getIpAddress())) {
                    String remark = "";
                    // 增加ECS的备注,以便于工单IP的替换.
                    if (ResourcesConstant.ServiceType.ECS.toInteger().equals(serviceType)) {
                        ComputeItem computeItem = comm.computeService.getComputeItem(resources.getServiceId());
                        remark = computeItem.getRemark();
                        content.append("(" + remark + " - " + resources.getIpAddress() + ")");
                    } else {
                        content.append("(" + resources.getIpAddress() + ")");
                    }
                }
                content.append(BLANK + BLANK).append("变更描述:" + BLANK).append(change.getDescription()).append(NEWLINE);
                content.append(NEWLINE).append("变更项:");
                if (change.getSubResourcesId() != null) {
                    content.append("(服务子项ID:" + change.getSubResourcesId() + ")");
                }
                content.append(BLANK + "旧值").append(RARR).append("新值").append(NEWLINE);
                for (ChangeItem changeItem : change.getChangeItems()) {
                    String fieldName = changeItem.getFieldName();
                    if (serviceType.equals(ResourcesConstant.ServiceType.PCS.toInteger()) || serviceType.equals(ResourcesConstant.ServiceType.ECS.toInteger())) {
                        // 计算资源Compute信息
                        for (Compate enumField : FieldNameConstant.Compate.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                if (FieldNameConstant.Compate.应用信息.toString().equals(fieldName)) {
                                    this.saveChangeTextAndNEWLINE(fieldName, content, changeItem);
                                } else if (FieldNameConstant.Compate.ESG.toString().equals(fieldName)) {
                                    this.saveESGChangeText(fieldName + CHANGE_ONECMDB_NOTIFICATION, content, changeItem);
                                } else {
                                    this.saveChangeText(fieldName, content, changeItem);
                                }
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.ES3.toInteger())) {
                        // 存储空间Storage信息
                        for (Storage enumField : FieldNameConstant.Storage.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                if (FieldNameConstant.Storage.容量空间.toString().equals(fieldName)) {
                                    this.saveChangeText(fieldName + "(GB)", content, changeItem);
                                } else {
                                    this.saveChangeText(fieldName, content, changeItem);
                                }
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.ELB.toInteger())) {
                        // 变更负载均衡器ELB
                        for (Elb enumField : FieldNameConstant.Elb.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                if (FieldNameConstant.Elb.端口信息.toString().equals(fieldName)) {
                                    this.saveChangeTextAndNEWLINE(fieldName, content, changeItem);
                                } else if (FieldNameConstant.Elb.关联实例.toString().equals(fieldName)) {
                                    this.saveChangeTextAndNEWLINE(fieldName + CHANGE_ONECMDB_NOTIFICATION, content, changeItem);
                                } else {
                                    this.saveChangeText(fieldName, content, changeItem);
                                }
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.EIP.toInteger())) {
                        // EIP
                        for (Eip enumField : FieldNameConstant.Eip.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                if (FieldNameConstant.Eip.端口信息.toString().equals(fieldName)) {
                                    this.saveChangeTextAndNEWLINE(fieldName, content, changeItem);
                                } else {
                                    this.saveChangeText(fieldName, content, changeItem);
                                }
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.DNS.toInteger())) {
                        // 拼装DNS信息
                        for (Dns enumField : FieldNameConstant.Dns.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                this.saveChangeText(fieldName, content, changeItem);
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.MONITOR_COMPUTE.toInteger())) {
                        // monitorCompute
                        for (monitorCompute enumField : FieldNameConstant.monitorCompute.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                this.saveChangeText(fieldName, content, changeItem);
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.MONITOR_ELB.toInteger())) {
                        // monitorElb
                        for (monitorElb enumField : FieldNameConstant.monitorElb.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                this.saveChangeText(fieldName, content, changeItem);
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.MDN.toInteger())) {
                        // MDN
                        for (com.sobey.cmop.mvc.constant.FieldNameConstant.MdnItem enumField : FieldNameConstant.MdnItem.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                this.saveChangeText(fieldName, content, changeItem);
                            }
                        }
                        // MDNVod
                        for (MdnVodItem enumField : FieldNameConstant.MdnVodItem.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                this.saveChangeText(fieldName, content, changeItem);
                            }
                        }
                        // MDNLive
                        for (MdnLiveItem enumField : FieldNameConstant.MdnLiveItem.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                this.saveChangeText(fieldName, content, changeItem);
                            }
                        }
                    } else if (serviceType.equals(ResourcesConstant.ServiceType.CP.toInteger())) {
                        // 云生产CP
                        for (com.sobey.cmop.mvc.constant.FieldNameConstant.CpItem enumField : FieldNameConstant.CpItem.values()) {
                            if (enumField.toString().equals(fieldName)) {
                                this.saveChangeText(fieldName, content, changeItem);
                            }
                        }
                    }
                }
            }
            content.append(NEWLINE);
        }
        content.append("</pre>").append(NEWLINE);
        return content.toString();
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("--->拼装Redmine内容出错：" + e.getMessage());
        return null;
    }
}


public void saveChangeText(String fieldName,StringBuilder content,ChangeItem changeItem){
    content.append(fieldName + ":" + BLANK).append(changeItem.getOldString()).append(RARR).append(changeItem.getNewString()).append(NEWLINE);
}


public String recycleResourcesRedmineDesc(User user,List<ComputeItem> computeItems,List<StorageItem> storageItems,List<NetworkElbItem> elbItems,List<NetworkEipItem> eipItems,List<NetworkDnsItem> dnsItems,List<MonitorMail> monitorMails,List<MonitorPhone> monitorPhones,List<MonitorCompute> monitorComputes,List<MonitorElb> monitorElbs,List<MdnItem> mdnItems,List<CpItem> cpItems){
    try {
        StringBuilder content = new StringBuilder();
        // 拼装资源信息
        this.generateContentByLists(user, content, computeItems, storageItems, elbItems, eipItems, dnsItems, monitorMails, monitorPhones, monitorComputes, monitorElbs, mdnItems, cpItems);
        return content.toString();
    } catch (Exception e) {
        logger.error("--->资源变更Resources拼装Redmine内容出错：" + e.getMessage());
        return null;
    }
}


}