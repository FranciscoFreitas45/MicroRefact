package com.ukefu.webim.service.resource;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.util.es.SearchTools;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.webim.service.impl.BatchDataProcess;
import com.ukefu.webim.service.impl.ESDataExchangeImpl;
import com.ukefu.webim.service.repository.CallAgentRepository;
import com.ukefu.webim.service.repository.CallOutFilterRepository;
import com.ukefu.webim.service.repository.CallOutTaskRepository;
import com.ukefu.webim.service.repository.FormFilterItemRepository;
import com.ukefu.webim.service.repository.FormFilterRepository;
import com.ukefu.webim.service.repository.JobDetailRepository;
import com.ukefu.webim.service.repository.MetadataRepository;
import com.ukefu.webim.util.CallCenterUtils;
import com.ukefu.webim.web.model.CallAgent;
import com.ukefu.webim.web.model.CallOutFilter;
import com.ukefu.webim.web.model.CallOutTask;
import com.ukefu.webim.web.model.FormFilter;
import com.ukefu.webim.web.model.FormFilterItem;
import com.ukefu.webim.web.model.JobDetail;
import com.ukefu.webim.web.model.MetadataTable;
import Interface.FormFilterRepository;
import Interface.FormFilter;
import Interface.CallAgent;
import Interface.CallOutTask;
import Interface.CallOutFilter;
import Interface.CallOutFilterRepository;
import DTO.CallAgent;
import DTO.FormFilter;
public class ActivityResource extends Resource{

 private  JobDetail jobDetail;

 private  FormFilterRepository formFilterRes;

 private  FormFilterItemRepository formFilterItemRes;

 private  PageImpl<UKDataBean> dataList;

 private  MetadataTable metadataTable;

 private  FormFilter formFilter;

 private  List<CallAgent> callAgentList;

 private  CallAgent current;

 private  CallOutTask task;

 private  CallOutFilter filter;

 private  CallOutTaskRepository callOutTaskRes;

 private  CallOutFilterRepository callOutFilterRes;

 private  JobDetailRepository batchRes;

 private  MetadataRepository metadataRes;

 private  JobDetail batch;

 private  AtomicInteger assignorganInt;

 private  AtomicInteger assignForecastInt;

 private  BatchDataProcess batchDataProcess;

 private  boolean isEnd;

 private  boolean isInit;

 private  Integer actiNum;

 private  int currentSum;

 private Map<String,Map<String,Integer>> taskids;

 private Map<String,Map<String,Integer>> filterids;

 private Map<String,Map<String,Integer>> batids;

public ActivityResource(JobDetail jobDetail) {
    this.jobDetail = jobDetail;
    this.formFilterRes = UKDataContext.getContext().getBean(FormFilterRepository.class);
    this.formFilterItemRes = UKDataContext.getContext().getBean(FormFilterItemRepository.class);
    this.callOutTaskRes = UKDataContext.getContext().getBean(CallOutTaskRepository.class);
    this.callOutFilterRes = UKDataContext.getContext().getBean(CallOutFilterRepository.class);
    this.batchRes = UKDataContext.getContext().getBean(JobDetailRepository.class);
    this.metadataRes = UKDataContext.getContext().getBean(MetadataRepository.class);
    this.batchDataProcess = new BatchDataProcess(null, UKDataContext.getContext().getBean(ESDataExchangeImpl.class));
}
@Override
public OutputTextFormat next(){
    OutputTextFormat outputTextFormat = null;
    if (this.dataList != null && this.current != null && this.actiNum > atomInt.intValue()) {
        synchronized (this.dataList) {
            if (atomInt.intValue() < this.currentSum) {
                if (this.isRecovery()) {
                    UKDataBean dataBean = this.dataList.getContent().get(this.currentSum - atomInt.intValue() - 1);
                    outputTextFormat = new OutputTextFormat(this.jobDetail);
                    if (this.formFilter != null) {
                        outputTextFormat.setTitle(this.formFilter.getName());
                    }
                    outputTextFormat.setDataBean(dataBean);
                    atomInt.incrementAndGet();
                } else if (this.dataList != null) {
                    if (this.current.getDisnames().intValue() >= this.current.getDisnum()) {
                        if (this.callAgentList.size() > 0) {
                            // ?????????
                            this.current = this.callAgentList.remove(0);
                        } else {
                            this.current = null;
                        }
                    }
                    if (this.current != null) {
                        UKDataBean dataBean = this.dataList.getContent().get(this.currentSum - atomInt.intValue() - 1);
                        outputTextFormat = new OutputTextFormat(this.jobDetail);
                        if (this.formFilter != null) {
                            outputTextFormat.setTitle(this.formFilter.getName());
                        }
                        outputTextFormat.setDataBean(dataBean);
                        atomInt.incrementAndGet();
                        /**
                         * ?????????????????????????????? ??? ????????????????????????????????????
                         */
                        this.callAgentList.add(this.current);
                        if (this.callAgentList.size() > 0) {
                            this.current = this.callAgentList.remove(0);
                        }
                    }
                }
            } else {
                if (!this.isEnd && !isRecovery()) {
                    this.callAgentList.add(this.current);
                }
            }
        }
    }
    if (outputTextFormat == null && !this.isEnd && this.current != null) {
        this.isInit = false;
        batchDataProcess.end();
        this.begin();
        outputTextFormat = new OutputTextFormat(this.jobDetail);
    }
    return outputTextFormat;
}


@Override
public boolean isAvailable(){
    return true;
}


@Override
public void process(OutputTextFormat meta,JobDetail job){
    /**
     * ????????????
     */
    if (this.isRecovery()) {
        if (meta != null && meta.getDataBean() != null) {
            // ???????????????????????????
            if (taskids.get(meta.getDataBean().getValues().get("taskid")) == null) {
                taskids.put((String) meta.getDataBean().getValues().get("taskid"), new HashMap<String, Integer>());
            }
            if (taskids.get(meta.getDataBean().getValues().get("taskid")) != null) {
                Map<String, Integer> m = taskids.get(meta.getDataBean().getValues().get("taskid"));
                if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null) {
                    // ??????
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null) {
                    // ai
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_AI, m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null) {
                    // ???????????????
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null && StringUtils.isBlank(this.jobDetail.getExecto())) {
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) + 1 : 1);
                }
                m.put("sum", m.get("sum") != null ? m.get("sum") + 1 : 1);
                taskids.put((String) meta.getDataBean().getValues().get("taskid"), m);
            }
            // ?????????????????????
            if (filterids.get(meta.getDataBean().getValues().get("calloutfilid")) == null) {
                filterids.put((String) meta.getDataBean().getValues().get("calloutfilid"), new HashMap<String, Integer>());
            }
            if (filterids.get(meta.getDataBean().getValues().get("calloutfilid")) != null) {
                Map<String, Integer> m = filterids.get(meta.getDataBean().getValues().get("calloutfilid"));
                if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null) {
                    // ??????
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null) {
                    // ai
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_AI, m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null) {
                    // ???????????????
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null && StringUtils.isBlank(this.jobDetail.getExecto())) {
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) + 1 : 1);
                }
                m.put("sum", m.get("sum") != null ? m.get("sum") + 1 : 1);
                filterids.put((String) meta.getDataBean().getValues().get("calloutfilid"), m);
            }
            // ?????????????????????
            if (batids.get(meta.getDataBean().getValues().get("batid")) == null) {
                batids.put((String) meta.getDataBean().getValues().get("batid"), new HashMap<String, Integer>());
            }
            if (batids.get(meta.getDataBean().getValues().get("batid")) != null) {
                Map<String, Integer> m = batids.get(meta.getDataBean().getValues().get("batid"));
                if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null) {
                    // ??????
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null) {
                    // ai
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_AI, m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null) {
                    // ???????????????
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) + 1 : 1);
                } else if (meta.getDataBean().getValues().get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null && StringUtils.isBlank(this.jobDetail.getExecto())) {
                    m.put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null ? m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) + 1 : 1);
                }
                m.put("sum", m.get("sum") != null ? m.get("sum") + 1 : 1);
                batids.put((String) meta.getDataBean().getValues().get("batid"), m);
            }
            if (!StringUtils.isBlank(this.jobDetail.getExecto())) {
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, null);
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_AI, null);
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, null);
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_TIME, System.currentTimeMillis());
                meta.getDataBean().getValues().put("status", UKDataContext.NamesDisStatusType.DISORGAN.toString());
                meta.getDataBean().getValues().put("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString());
            } else {
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_AI, null);
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, null);
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, null);
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, null);
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_TIME, null);
                meta.getDataBean().getValues().put("status", UKDataContext.NamesDisStatusType.NOT.toString());
                meta.getDataBean().getValues().put("callstatus", UKDataContext.NameStatusTypeEnum.NOTCALL.toString());
            }
            meta.getDataBean().getValues().put("afterprocesstime", 0);
        }
    } else {
        if (this.current != null && meta != null && meta.getDataBean() != null) {
            this.current.getDisnames().incrementAndGet();
            /**
             */
            meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_TIME, System.currentTimeMillis());
            meta.getDataBean().getValues().put("actid", this.jobDetail.getId());
            meta.getDataBean().getValues().put("metaid", this.metadataTable.getTablename());
            meta.getDataBean().getValues().put("batid", this.formFilter.getBatid());
            meta.getDataBean().getValues().put("taskid", this.task.getId());
            meta.getDataBean().getValues().put("filterid", this.formFilter.getId());
            meta.getDataBean().getValues().put("calloutfilid", this.filter.getId());
            meta.getDataBean().getValues().put("taskid", this.task.getId());
            if (!StringUtils.isBlank(this.jobDetail.getUserid())) {
                meta.getDataBean().getValues().put("assuser", this.jobDetail.getUserid());
            } else {
                meta.getDataBean().getValues().put("assuser", this.jobDetail.getCreater());
            }
            /**
             * ??????ID
             */
            if ("agent".equals(this.current.getDistype())) {
                meta.getDataBean().getValues().put("status", UKDataContext.NamesDisStatusType.DISAGENT.toString());
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_AGENT, this.current.getDistarget());
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, this.current.getOrgan());
                this.assignInt.incrementAndGet();
            } else if ("skill".equals(this.current.getDistype())) {
                meta.getDataBean().getValues().put("status", UKDataContext.NamesDisStatusType.DISORGAN.toString());
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, this.current.getDistarget());
                this.assignorganInt.incrementAndGet();
            } else if ("ai".equals(this.current.getDistype())) {
                meta.getDataBean().getValues().put("status", UKDataContext.NamesDisStatusType.DISAI.toString());
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_AI, this.current.getDistarget());
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, this.current.getOrgan());
                this.assignAiInt.incrementAndGet();
            } else if ("forecast".equals(this.current.getDistype())) {
                meta.getDataBean().getValues().put("status", UKDataContext.NamesDisStatusType.DISFORECAST.toString());
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST, this.current.getDistarget());
                meta.getDataBean().getValues().put(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN, this.current.getOrgan());
                this.assignForecastInt.incrementAndGet();
            }
        }
    }
    if (meta.getDataBean() != null) {
        meta.getDataBean().getValues().put("updatetime", System.currentTimeMillis());
        /**
         * ?????????????????????????????????????????????????????????????????????????????????
         */
        batchDataProcess.process(meta.getDataBean());
    }
}


@Override
public void updateTask(){
    /**
     * ????????????????????????????????????????????????
     */
    this.jobDetail.setExecmd(null);
    this.jobDetail.setExectype(null);
    this.jobDetail.setExectarget(null);
    this.jobDetail.setExecto(null);
}


@Override
public void rmResource(){
/**
 * ????????????
 */
}


@Override
public OutputTextFormat getText(OutputTextFormat object){
    return object;
}


public boolean isRecovery(){
    return !StringUtils.isBlank(this.jobDetail.getExecmd()) && this.jobDetail.getExecmd().equals("recovery");
}


@Override
public void end(boolean clear){
    try {
        if (this.atomInt.intValue() > 0) {
            this.batchDataProcess.end();
        }
        // doNothing
        if (this.isRecovery()) {
            List<String> idList = new ArrayList<String>();
            Iterator<String> iterator = taskids.keySet().iterator();
            while (iterator.hasNext()) {
                String itemp = iterator.next();
                if (!StringUtils.isBlank(itemp)) {
                    idList.add(itemp);
                }
            }
            List<CallOutTask> taskList = callOutTaskRes.findAll(idList);
            if (taskList.size() > 0) {
                for (CallOutTask task : taskList) {
                    Map<String, Integer> m = taskids.get(task.getId());
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null) {
                        // ??????????????????
                        // ??????????????????
                        task.setAssigned(task.getAssigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                        // ????????????
                        task.setNotassigned(task.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            task.setAssignedorgan(task.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                            task.setNotassigned(task.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                        }
                    }
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null) {
                        // ???????????????
                        // ??????????????????
                        task.setAssignedorgan(task.getAssignedorgan() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                        // ????????????
                        task.setNotassigned(task.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            task.setAssignedorgan(task.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                            task.setNotassigned(task.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                        }
                    }
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null) {
                        // ?????????Ai
                        // ??????????????????
                        task.setAssignedai(task.getAssignedai() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                        // ????????????
                        task.setNotassigned(task.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            task.setAssignedorgan(task.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                            task.setNotassigned(task.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                        }
                    }
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null) {
                        // ????????????????????????
                        // ??????????????????
                        task.setAssignedforecast(task.getAssignedforecast() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                        // ????????????
                        task.setNotassigned(task.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            task.setAssignedorgan(task.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                            task.setNotassigned(task.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                        }
                    }
                    if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                        // ??????????????????
                        task.setReorgannum(task.getReorgannum() + (m.get("sum") != null ? m.get("sum") : 0));
                    } else {
                        // ??????????????????
                        task.setRenum(task.getRenum() + (m.get("sum") != null ? m.get("sum") : 0));
                    }
                }
                callOutTaskRes.save(taskList);
            }
            List<String> filteridList = new ArrayList<String>();
            Iterator<String> filint = filterids.keySet().iterator();
            while (filint.hasNext()) {
                String filtemp = filint.next();
                if (!StringUtils.isBlank(filtemp)) {
                    filteridList.add(filtemp);
                }
            }
            List<CallOutFilter> filterList = callOutFilterRes.findAll(filteridList);
            if (filterList.size() > 0) {
                for (CallOutFilter filter : filterList) {
                    Map<String, Integer> m = filterids.get(filter.getId());
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT) != null) {
                        // ??????????????????
                        // ??????????????????
                        filter.setAssigned(filter.getAssigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                        // ????????????
                        filter.setNotassigned(filter.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            filter.setAssignedorgan(filter.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                            filter.setNotassigned(filter.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AGENT));
                        }
                    }
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN) != null) {
                        // ???????????????
                        // ??????????????????
                        filter.setAssignedorgan(filter.getAssignedorgan() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                        // ????????????
                        filter.setNotassigned(filter.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                        filter.setReorgannum(filter.getReorgannum() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            filter.setAssignedorgan(filter.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                            filter.setNotassigned(filter.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_ORGAN));
                        }
                    }
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI) != null) {
                        // ?????????Ai
                        // ??????????????????
                        filter.setAssignedai(filter.getAssignedai() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                        // ????????????
                        filter.setNotassigned(filter.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            filter.setAssignedorgan(filter.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                            filter.setNotassigned(filter.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_AI));
                        }
                    }
                    if (m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST) != null) {
                        // ????????????????????????
                        // ??????????????????
                        filter.setAssignedforecast(filter.getAssignedforecast() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                        // ????????????
                        filter.setNotassigned(filter.getNotassigned() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                        // ???????????????
                        if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                            filter.setAssignedorgan(filter.getAssignedorgan() + m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                            filter.setNotassigned(filter.getNotassigned() - m.get(UKDataContext.UKEFU_SYSTEM_DIS_FORECAST));
                        }
                    }
                    if (StringUtils.isNotBlank(this.jobDetail.getExecto())) {
                        // ??????????????????
                        filter.setReorgannum(filter.getReorgannum() + (m.get("sum") != null ? m.get("sum") : 0));
                    } else {
                        // ??????????????????
                        filter.setRenum(filter.getRenum() + (m.get("sum") != null ? m.get("sum") : 0));
                    }
                }
            }
            callOutFilterRes.save(filterList);
            List<String> batidList = new ArrayList<String>();
            Iterator<String> batint = batids.keySet().iterator();
            while (batint.hasNext()) {
                String batemp = batint.next();
                if (!StringUtils.isBlank(batemp)) {
                    batidList.add(batemp);
                }
            }
            List<JobDetail> batList = batchRes.findAll(batidList);
            if (batList.size() > 0) {
                for (JobDetail batch : batList) {
                    Map<String, Integer> m = batids.get(batch.getId());
                    if (StringUtils.isBlank(this.jobDetail.getExecto())) {
                        // ??????????????????
                        batch.setAssigned(batch.getAssigned() - (m.get("sum") != null ? m.get("sum") : 0));
                        // ??????????????????
                        batch.setNotassigned(batch.getNotassigned() + (m.get("sum") != null ? m.get("sum") : 0));
                    }
                }
            }
            batchRes.save(batList);
        } else {
            /**
             * FormFilter????????????????????????????????????
             */
            if (formFilterRes != null && this.formFilter != null) {
                this.formFilter.setFilternum(this.formFilter.getFilternum() + 1);
                formFilterRes.save(this.formFilter);
            }
            /**
             * ???????????????????????????????????????????????????????????? ??? ????????????????????????
             */
            if (this.batchRes != null && this.batch != null) {
                if (this.isRecovery()) {
                    batch.setAssigned(batch.getAssigned() - this.atomInt.intValue());
                } else {
                    batch.setAssigned(batch.getAssigned() + this.atomInt.intValue());
                }
                batch.setNotassigned(batch.getNamenum() - batch.getAssigned());
                this.batchRes.save(batch);
            }
            if (this.task != null) {
                if (this.isRecovery()) {
                    if (!StringUtils.isBlank(this.jobDetail.getExecto())) {
                        this.task.setReorgannum(this.atomInt.intValue());
                    } else {
                        this.task.setRenum(this.atomInt.intValue());
                    }
                } else {
                    this.task.setAssigned(this.assignInt.intValue());
                    this.task.setAssignedorgan(this.assignorganInt.intValue());
                    this.task.setAssignedai(this.assignAiInt.intValue());
                    this.task.setAssignedai(this.assignAiInt.intValue());
                    this.task.setAssignedforecast(this.assignForecastInt.intValue());
                    this.task.setNotassigned(this.task.getNamenum() - this.assignInt.intValue() - this.assignorganInt.intValue() - this.assignAiInt.intValue() - this.assignForecastInt.intValue());
                }
                this.callOutTaskRes.save(this.task);
            }
            if (this.filter != null) {
                if (this.isRecovery()) {
                    if (!StringUtils.isBlank(this.jobDetail.getExecto())) {
                        this.filter.setReorgannum(this.atomInt.intValue());
                    } else {
                        this.filter.setRenum(this.atomInt.intValue());
                    }
                } else {
                    this.filter.setAssigned(this.assignInt.intValue());
                    this.filter.setAssignedorgan(this.assignorganInt.intValue());
                    this.filter.setAssignedai(this.assignAiInt.intValue());
                    this.filter.setAssignedforecast(this.assignForecastInt.intValue());
                    this.filter.setNotassigned(this.task.getNamenum() - this.assignInt.intValue() - this.assignorganInt.intValue() - this.assignAiInt.intValue() - this.assignForecastInt.intValue());
                }
                this.callOutFilterRes.save(this.filter);
            }
        }
        CallCenterUtils.getCalloutCount(this.jobDetail.getOrgi(), null, null);
    } catch (Exception e) {
    // TODO: handle exception
    } finally {
        this.updateTask();
    }
}


@Override
public void begin(){
    if (this.isRecovery()) {
        if (this.isInit) {
            this.taskids = new HashMap<String, Map<String, Integer>>();
            this.filterids = new HashMap<String, Map<String, Integer>>();
            this.batids = new HashMap<String, Map<String, Integer>>();
        }
        // ???????????? , ???????????????????????????  ??? ?????? ??????ID?????????ID?????????ID?????????ID
        this.dataList = SearchTools.recoversearch(this.jobDetail.getOrgi(), this.jobDetail.getExectype(), this.jobDetail.getExectarget(), metadataTable, 0, 1000);
        // ????????????????????????
        if (this.dataList.getTotalElements() > this.dataList.getContent().size()) {
            this.isEnd = false;
        } else {
            this.isEnd = true;
        }
        if (this.isInit) {
            this.actiNum = (int) this.dataList.getTotalElements();
            this.current = new CallAgent();
        }
        if (this.dataList != null) {
            this.currentSum = this.currentSum + this.dataList.getContent().size();
        }
        this.jobDetail.setExecnum(this.jobDetail.getExecnum() + 1);
    } else {
        if (!StringUtils.isBlank(jobDetail.getFilterid())) {
            formFilter = formFilterRes.findByIdAndOrgi(jobDetail.getFilterid(), this.jobDetail.getOrgi());
            List<FormFilterItem> formFilterList = formFilterItemRes.findByOrgiAndFormfilterid(this.jobDetail.getOrgi(), jobDetail.getFilterid());
            if (formFilter != null && !StringUtils.isBlank(formFilter.getFiltertype())) {
                if (formFilter.getFiltertype().equals(UKDataContext.FormFilterTypeEnum.BATCH.toString())) {
                    batch = batchRes.findByIdAndOrgi(formFilter.getBatid(), this.jobDetail.getOrgi());
                    if (batch != null && !StringUtils.isBlank(batch.getActid())) {
                        metadataTable = metadataRes.findByTablename(batch.getActid());
                    }
                } else {
                    // ?????????
                    if (!StringUtils.isBlank(formFilter.getTableid())) {
                        metadataTable = metadataRes.findById(formFilter.getTableid());
                    }
                }
            }
            if (isInit) {
                if (this.callAgentList == null) {
                    this.callAgentList = UKDataContext.getContext().getBean(CallAgentRepository.class).findByActidAndOrgi(this.jobDetail.getId(), this.jobDetail.getOrgi());
                }
            }
            /**
             * ?????? ??????????????? ?????????????????? , ????????????????????? ??????????????????????????? ??? ????????????????????????????????????
             */
            if (this.callAgentList != null && this.callAgentList.size() > 0) {
                if (isInit) {
                    // ??????????????????
                    if (this.actiNum == null) {
                        this.actiNum = 0;
                        for (CallAgent c : this.callAgentList) {
                            this.actiNum = this.actiNum + c.getDisnum();
                        }
                    }
                    // ???????????????????????????
                    if (this.actiNum > this.jobDetail.getNamenum()) {
                        this.actiNum = this.jobDetail.getNamenum();
                    }
                    // ???????????? ????????????
                    if ("part".equals(this.jobDetail.getDistype())) {
                        if ("part".equals(this.jobDetail.getDistpolicy())) {
                            // ??????
                            int n = (this.jobDetail.getNamenum() * this.jobDetail.getPolicynum()) / 100;
                            // ???????????????????????????
                            if (this.actiNum > n) {
                                this.actiNum = n;
                            }
                        } else if ("num".equals(this.jobDetail.getDistpolicy())) {
                            // ???????????????????????????
                            if (this.actiNum > this.jobDetail.getPolicynum()) {
                                this.actiNum = this.jobDetail.getPolicynum();
                            }
                        }
                    }
                }
                this.current = this.callAgentList.remove(0);
            }
            if (metadataTable != null) {
                this.dataList = SearchTools.dissearch(this.jobDetail.getOrgi(), formFilter, formFilterList, metadataTable, 0, 1000);
                // ???????????????????????? ???????????????>??????????????????
                if (this.dataList.getTotalElements() > this.dataList.getContent().size() && actiNum != null && (this.actiNum - this.atomInt.intValue()) > this.dataList.getContent().size()) {
                    this.isEnd = false;
                } else {
                    this.isEnd = true;
                }
            }
            if (this.dataList != null) {
                this.currentSum = this.currentSum + this.dataList.getContent().size();
            }
            this.jobDetail.setExecnum(this.jobDetail.getExecnum() + 1);
            if (isInit) {
                /*if(this.isRecovery() && !StringUtils.isBlank(this.jobDetail.getExectype()) && (this.jobDetail.getExectype().equals("filterid") || this.jobDetail.getExectype().equals("filterskill") || this.jobDetail.getExectype().equals("taskskill") || this.jobDetail.getExectype().equals("taskid"))) {
						if(this.jobDetail.getExectype().equals("filterid") || this.jobDetail.getExectype().equals("filterskill")) {
							this.filter = this.callOutFilterRes.findByIdAndOrgi(this.jobDetail.getExectarget(), this.jobDetail.getOrgi()) ;
						}else if(this.jobDetail.getExectype().equals("taskid") || this.jobDetail.getExectype().equals("taskskill") ) {
							this.task = this.callOutTaskRes.findByIdAndOrgi(this.jobDetail.getExectarget(), this.jobDetail.getOrgi()) ;
						}
					}else {*/
                task = new CallOutTask();
                task.setName(this.jobDetail.getName() + "_" + UKTools.dateFormate.format(new Date()));
                task.setBatid(formFilter.getBatid());
                task.setOrgi(this.jobDetail.getOrgi());
                if (this.isRecovery()) {
                    task.setExectype(UKDataContext.ActivityExecType.RECOVERY.toString());
                } else {
                    task.setExectype(UKDataContext.ActivityExecType.DEFAULT.toString());
                }
                task.setFilterid(formFilter.getId());
                task.setActid(this.jobDetail.getId());
                task.setExecnum(this.jobDetail.getExecnum());
                task.setOrgan(this.jobDetail.getOrgan());
                task.setCreatetime(new Date());
                if (this.dataList != null) {
                    task.setNamenum((int) this.dataList.getTotalElements());
                    task.setNotassigned((int) this.dataList.getTotalElements());
                }
                this.callOutTaskRes.save(task);
                filter = new CallOutFilter();
                formFilter.setExecnum(formFilter.getExecnum() + 1);
                UKTools.copyProperties(task, filter);
                filter.setName(this.formFilter.getName() + "_" + UKTools.dateFormate.format(new Date()));
                filter.setExecnum(formFilter.getExecnum());
                this.callOutFilterRes.save(filter);
            /*}*/
            }
        }
    }
}


@Override
public JobDetail getJob(){
    return this.jobDetail;
}


}