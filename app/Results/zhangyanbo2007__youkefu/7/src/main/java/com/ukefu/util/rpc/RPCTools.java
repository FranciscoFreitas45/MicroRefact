package com.ukefu.util.rpc;
 import com.hazelcast.core.HazelcastInstance;
import com.ukefu.core.UKDataContext;
public class RPCTools {


public void sendEntIMEventMessage(String id,String event,Object data){
    UKDataContext.getContext().getBean(HazelcastInstance.class).getTopic(UKDataContext.UCKeFuTopic.TOPIC_ENTIM.toString()).publish(new RPCDataBean(id, event, data));
}


public void sendAgentEventMessage(String id,String event,Object data){
    UKDataContext.getContext().getBean(HazelcastInstance.class).getTopic(UKDataContext.UCKeFuTopic.TOPIC_AGENT.toString()).publish(new RPCDataBean(id, event, data));
}


public void sendJobDetailMessage(String id,String event,Object data){
    UKDataContext.getContext().getBean(HazelcastInstance.class).getTopic(UKDataContext.UCKeFuTopic.TOPIC_JOBDETAIL.toString()).publish(new RPCDataBean(id, event, data));
}


public void sendCallCenterMessage(String id,String event,Object data){
    UKDataContext.getContext().getBean(HazelcastInstance.class).getTopic(UKDataContext.UCKeFuTopic.TOPIC_CALLCENTER.toString()).publish(new RPCDataBean(id, event, data));
}


public void sendIMEventMessage(String id,String event,Object data){
    UKDataContext.getContext().getBean(HazelcastInstance.class).getTopic(UKDataContext.UCKeFuTopic.TOPIC_IM.toString()).publish(new RPCDataBean(id, event, data));
}


public void published(String name,String event,Object data){
    UKDataContext.getContext().getBean(HazelcastInstance.class).getTopic(UKDataContext.UCKeFuTopic.NAMESPACE.toString()).publish(new RPCDataBean(name, event, data));
}


}