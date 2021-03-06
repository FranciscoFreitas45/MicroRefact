package com.ukefu.util.callout;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.client.NettyClients;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.util.task.DSDataEvent;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.CallOutConfigRepository;
import com.ukefu.webim.service.repository.CallOutNamesRepository;
import com.ukefu.webim.service.repository.CallOutTaskRepository;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.web.model.CallOutConfig;
import com.ukefu.webim.web.model.CallOutNames;
import com.ukefu.webim.web.model.CallOutTask;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.TableProperties;
import DTO.TableProperties;
import DTO.MetadataTable;
import DTO.DSDataEvent;
public class CallOutUtils {


public CallOutNames processNames(UKDataBean name,String orgi,int leavenames,CallOutNamesRepository callOutNamesRes){
    String batid = (String) name.getValues().get("batid");
    String taskid = (String) name.getValues().get("taskid");
    JobDetail batch = UKDataContext.getContext().getBean(JobDetailRepository.class).findByIdAndOrgi(batid, orgi);
    CallOutTask task = UKDataContext.getContext().getBean(CallOutTaskRepository.class).findByIdAndOrgi(taskid, orgi);
    CallOutNames callOutName = null;
    List<CallOutNames> callNamesList = callOutNamesRes.findByDataidAndCreaterAndOrgi((String) name.getValues().get("id"), (String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT), orgi);
    if (callNamesList.size() > 0) {
        callOutName = callNamesList.get(0);
    }
    if (callOutName == null) {
        callOutName = new CallOutNames();
    }
    if (callOutName != null) {
        callOutName.setOrgi(orgi);
        if (task != null) {
            // ????????????
            callOutName.setName(task.getName());
            callOutName.setActid(task.getActid());
        }
        if (batch != null) {
            callOutName.setBatname(batch.getName());
        }
        callOutName.setBatid(batid);
        callOutName.setTaskid(taskid);
        callOutName.setMetaname(batch.getActid());
        callOutName.setFilterid((String) name.getValues().get("filterid"));
        callOutName.setDataid((String) name.getValues().get("id"));
        callOutName.setStatus(UKDataContext.NamesProcessStatus.DIS.toString());
        callOutName.setCreater((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
        callOutName.setOrgan((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
        callOutName.setCreatetime(new Date());
        callOutName.setUpdatetime(new Date());
        Object apstatus = name.getValues().get("apstatus");
        if (apstatus != null && !StringUtils.isBlank(apstatus.toString()) && apstatus.toString().toLowerCase().equals("true")) {
            callOutName.setReservation(true);
        } else {
            callOutName.setReservation(false);
        }
        callOutName.setMemo((String) name.getValues().get("apmemo"));
        callOutName.setWorkstatus((String) name.getValues().get("workstatus"));
        callOutName.setOwneruser((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
        callOutName.setOwnerdept((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
        callOutName.setOwnerai((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
        callOutName.setOwnerforecast((String) name.getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
        callOutName.setLeavenum(leavenames);
    }
    String dial_number = null;
    boolean disphonenum = false;
    String distype = null;
    if (batch != null && !StringUtils.isBlank(batch.getActid())) {
        MetadataTable table = UKDataContext.getContext().getBean(MetadataRepository.class).findByTablename(batch.getActid());
        for (TableProperties tp : table.getTableproperty()) {
            if (tp.isPhonenumber()) {
                dial_number = (String) name.getValues().get(tp.getFieldname());
                disphonenum = tp.isSecfield();
                distype = tp.getSecdistype();
                break;
            }
        }
    }
    if (!StringUtils.isBlank(dial_number)) {
        callOutName.setPhonenumber(dial_number);
        if (disphonenum) {
            callOutName.setDistype(distype);
        }
    }
    callOutNamesRes.save(callOutName);
    return callOutName;
}


public TableProperties initProperties(String name,String title,String type,String orgi,String tableName,boolean sysfield){
    TableProperties tablePorperties = new TableProperties(name, type, 255, tableName);
    tablePorperties.setOrgi(orgi);
    tablePorperties.setDatatypecode(0);
    tablePorperties.setLength(255);
    tablePorperties.setDatatypename(type);
    tablePorperties.setName(title);
    tablePorperties.setSysfield(sysfield);
    return tablePorperties;
}


public List<CallOutConfig> initCallOutConfig(){
    List<CallOutConfig> configList = new ArrayList<CallOutConfig>();
    if (UKDataContext.getContext() != null) {
        CallOutConfigRepository callOutConfigRepository = UKDataContext.getContext().getBean(CallOutConfigRepository.class);
        configList = callOutConfigRepository.findAll();
    }
    return configList;
}


public void processMetadataTable(boolean findId,MetadataTable metaDataTable,DSDataEvent event){
    metaDataTable.getTableproperty().add(initProperties("id", "??????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("orgi", "??????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("creater", "?????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("createtime", "????????????", "Datetime", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("validresult", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("validmessage", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("assuser", "???????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_AI, "??????AI", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_TIME, "????????????", "Datetime", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("status", "??????", "String", event.getOrgi(), event.getTablename(), true));
    /**
     * ?????????/??????
     */
    metaDataTable.getTableproperty().add(initProperties("process", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("processtime", "????????????", "Datetime", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("processmemo", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("metaid", "?????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("actid", "??????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("batid", "??????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("taskid", "??????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("filterid", "??????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("cusid", "??????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("calloutfilid", "????????????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("execid", "????????????ID", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("callstatus", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("workstatus", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("apstatus", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("aptime", "????????????", "Date", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("apmemo", "????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("callresult", "??????????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("calltime", "????????????", "Date", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("firstcalltimes", "??????????????????", "Date", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("firstcallstatus", "??????????????????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("calltimes", "????????????", "Long", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("succcall", "??????????????????", "Long", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("faildcall", "??????????????????", "Long", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("ringtime", "????????????", "Long", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("incall", "????????????", "Long", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("level", "??????", "String", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("levelscore", "??????", "Long", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("focustimes", "???????????????", "Long", event.getOrgi(), event.getTablename(), true));
    metaDataTable.getTableproperty().add(initProperties("afterprocesstime", "???????????????", "Long", event.getOrgi(), event.getTablename(), true));
}


}