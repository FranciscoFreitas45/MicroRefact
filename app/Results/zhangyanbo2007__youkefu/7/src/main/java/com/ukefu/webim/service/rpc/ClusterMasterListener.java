package com.ukefu.webim.service.rpc;
 import org.apache.commons.lang3.StringUtils;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.ukefu.core.ClusterContext;
import com.ukefu.util.rpc.RPCDataBean;
public class ClusterMasterListener implements MessageListener<Object>{

 private  HazelcastInstance hazelcastInstance;

public ClusterMasterListener(HazelcastInstance hazelcastInstance) {
    this.hazelcastInstance = hazelcastInstance;
}
@Override
public void onMessage(Message<Object> message){
    RPCDataBean rpcDataBean = (RPCDataBean) message.getMessageObject();
    if (hazelcastInstance != null && rpcDataBean != null && !StringUtils.isBlank(rpcDataBean.getHost())) {
        ClusterContext.getInstance().setHost(rpcDataBean.getHost());
        ClusterContext.getInstance().setPort(rpcDataBean.getPort());
        ClusterContext.getInstance().setId(rpcDataBean.getId());
        ClusterContext.getInstance().setStart(rpcDataBean.getStart());
        if (hazelcastInstance.getCluster().getLocalMember().getStringAttribute("id").equals(rpcDataBean.getId())) {
            ClusterContext.getInstance().setMaster(true);
        } else {
            ClusterContext.getInstance().setMaster(false);
        }
    }
}


}