package com.ukefu.webim.service.impl;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import org.springframework.stereotype.Service;
import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.core.IMap;
import com.hazelcast.query.PagingPredicate;
import com.hazelcast.query.Predicates;
import com.hazelcast.query.SqlPredicate;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.freeswitch.model.CallCenterAgent;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.quene.AgentCallOutFilterPredicate;
import com.ukefu.webim.service.quene.AiCallOutFilterPredicate;
import com.ukefu.webim.service.quene.CallCenterAgentOrgiFilterPredicate;
import com.ukefu.webim.service.quene.CallCenterAgentReadyOrgiFilterPredicate;
import com.ukefu.webim.service.quene.CallCenterInCallOrgiFilterPredicate;
import com.ukefu.webim.service.quene.ForecastAgentFilterPredicate;
import com.ukefu.webim.service.quene.ForecastCallOutFilterPredicate;
import com.ukefu.webim.web.model.AgentReport;
import com.ukefu.webim.web.model.CallOutNames;
@Service("calloutquene")
public class CallOutQuene {


@SuppressWarnings({ "unchecked", "rawtypes" })
public int countAiCallOut(String orgi,String ownerai){
    /**
     * 统计当前在线的坐席数量
     */
    IMap callOutMap = (IMap<String, Object>) CacheHelper.getCallOutCacheBean().getCache();
    Long names = (Long) callOutMap.aggregate(Aggregators.<Map.Entry<String, CallOutNames>>count(), new AiCallOutFilterPredicate(orgi, ownerai));
    return names != null ? names.intValue() : 0;
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public int countForecastAgent(String orgi,String ownerforecast){
    /**
     * 统计当前在线的坐席数量
     */
    IMap agentMap = (IMap<String, Object>) CacheHelper.getCallCenterAgentCacheBean().getCache();
    Long agents = (Long) agentMap.aggregate(Aggregators.<Map.Entry<String, CallCenterAgent>>count(), new ForecastAgentFilterPredicate(orgi, ownerforecast));
    return agents != null ? agents.intValue() : 0;
}


@SuppressWarnings("unchecked")
public List<CallCenterAgent> forecastAgent(String forecastid){
    List<CallCenterAgent> agentList = new ArrayList<CallCenterAgent>();
    if (CacheHelper.getCallCenterAgentCacheBean() != null && CacheHelper.getCallCenterAgentCacheBean().getCache() != null) {
        PagingPredicate<String, CallCenterAgent> pagingPredicate = new PagingPredicate<String, CallCenterAgent>(new SqlPredicate("forecastvalue like '%" + forecastid + "%' AND workstatus ='" + UKDataContext.WorkStatusEnum.CALLOUT.toString() + "'"), 1);
        agentList.addAll(((IMap<String, CallCenterAgent>) CacheHelper.getCallCenterAgentCacheBean().getCache()).values(pagingPredicate));
    }
    return agentList;
}


public CallCenterAgent updateAgentStatus(String forecastid,String orgi){
    CallCenterAgent agent = null;
    Lock lock = CacheHelper.getCallCenterAgentCacheBean().getLock("AgentLOCK", orgi);
    lock.lock();
    try {
        List<CallCenterAgent> agents = forecastAgent(forecastid);
        if (agents != null && agents.size() > 0) {
            agent = agents.get(0);
            agent.setWorkstatus(UKDataContext.WorkStatusEnum.PREVIEW.toString());
            CacheHelper.getCallCenterAgentCacheBean().put(agent.getUserid(), agent, orgi);
        }
    } finally {
        lock.unlock();
    }
    return agent;
}


@SuppressWarnings("unchecked")
public List<CallCenterAgent> extention(String extno){
    List<CallCenterAgent> agentList = new ArrayList<CallCenterAgent>();
    if (CacheHelper.getCallCenterAgentCacheBean() != null && CacheHelper.getCallCenterAgentCacheBean().getCache() != null) {
        PagingPredicate<String, CallCenterAgent> pagingPredicate = new PagingPredicate<String, CallCenterAgent>(new SqlPredicate("extno = '" + extno + "'"), 10);
        agentList.addAll(((IMap<String, CallCenterAgent>) CacheHelper.getCallCenterAgentCacheBean().getCache()).values(pagingPredicate));
    }
    return agentList;
}


@SuppressWarnings("unchecked")
public List<CallCenterAgent> service(String sip){
    List<CallCenterAgent> agentList = new ArrayList<CallCenterAgent>();
    if (CacheHelper.getCallCenterAgentCacheBean() != null && CacheHelper.getCallCenterAgentCacheBean().getCache() != null) {
        PagingPredicate<String, CallCenterAgent> pagingPredicate = new PagingPredicate<String, CallCenterAgent>(new SqlPredicate("siptrunk = '" + sip + "'"), 10);
        agentList.addAll(((IMap<String, CallCenterAgent>) CacheHelper.getCallCenterAgentCacheBean().getCache()).values(pagingPredicate));
    }
    return agentList;
}


@SuppressWarnings("unchecked")
public List<CallCenterAgent> forecast(String forecastid){
    List<CallCenterAgent> agentList = new ArrayList<CallCenterAgent>();
    if (CacheHelper.getCallCenterAgentCacheBean() != null && CacheHelper.getCallCenterAgentCacheBean().getCache() != null) {
        PagingPredicate<String, CallCenterAgent> pagingPredicate = new PagingPredicate<String, CallCenterAgent>(Predicates.like("forecastvalue", forecastid), 1);
        agentList.addAll(((IMap<String, CallCenterAgent>) CacheHelper.getCallCenterAgentCacheBean().getCache()).values(pagingPredicate));
    }
    return agentList;
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public AgentReport getCallCenterAgentReport(String orgi){
    /**
     * 统计当前在线的坐席数量
     */
    AgentReport report = new AgentReport();
    IMap callCenterAgentMap = (IMap<String, Object>) CacheHelper.getCallCenterAgentCacheBean().getCache();
    Long agents = (Long) callCenterAgentMap.aggregate(Aggregators.<Map.Entry<String, CallCenterAgent>>count(), new CallCenterAgentOrgiFilterPredicate(orgi));
    report.setAgents(agents.intValue());
    Long readyAgent = (Long) callCenterAgentMap.aggregate(Aggregators.<Map.Entry<String, CallCenterAgent>>count(), new CallCenterAgentReadyOrgiFilterPredicate(orgi));
    report.setReadyagents(readyAgent.intValue());
    Long inCallAgent = (Long) callCenterAgentMap.aggregate(Aggregators.<Map.Entry<String, CallCenterAgent>>count(), new CallCenterInCallOrgiFilterPredicate(orgi));
    report.setIncall(inCallAgent.intValue());
    report.setOrgi(orgi);
    return report;
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public int countAgentCallOut(String orgi){
    /**
     * 统计当前在线的坐席数量
     */
    IMap callOutMap = (IMap<String, Object>) CacheHelper.getCallOutCacheBean().getCache();
    Long names = (Long) callOutMap.aggregate(Aggregators.<Map.Entry<String, CallOutNames>>count(), new AgentCallOutFilterPredicate(orgi));
    return names != null ? names.intValue() : 0;
}


@SuppressWarnings("unchecked")
public List<CallOutNames> callOutNames(String calltype,int p,int ps){
    List<CallOutNames> callOutNamesList = new ArrayList<CallOutNames>();
    if (CacheHelper.getCallOutCacheBean() != null && CacheHelper.getCallOutCacheBean().getCache() != null) {
        PagingPredicate<String, CallOutNames> pagingPredicate = new PagingPredicate<String, CallOutNames>(new SqlPredicate("calltype = '" + calltype + "'"), 10);
        pagingPredicate.setPage(p);
        callOutNamesList.addAll(((IMap<String, CallOutNames>) CacheHelper.getCallOutCacheBean().getCache()).values(pagingPredicate));
    }
    return callOutNamesList;
}


@SuppressWarnings({ "unchecked", "rawtypes" })
public int countForecastCallOut(String orgi,String ownerforecast){
    /**
     * 统计当前在线的坐席数量
     */
    IMap callOutMap = (IMap<String, Object>) CacheHelper.getCallOutCacheBean().getCache();
    Long names = (Long) callOutMap.aggregate(Aggregators.<Map.Entry<String, CallOutNames>>count(), new ForecastCallOutFilterPredicate(orgi, ownerforecast));
    return names != null ? names.intValue() : 0;
}


}