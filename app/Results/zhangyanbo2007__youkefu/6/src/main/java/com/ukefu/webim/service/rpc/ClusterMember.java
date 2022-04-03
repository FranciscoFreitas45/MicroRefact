package com.ukefu.webim.service.rpc;
 import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;
import com.ukefu.util.UKTools;
public class ClusterMember implements MembershipListener{

 private  HazelcastInstance hazelcastInstance;

public ClusterMember(HazelcastInstance hazelcastInstance) {
    this.hazelcastInstance = hazelcastInstance;
}
@Override
public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent){
}


@Override
public void memberAdded(MembershipEvent membershipEvent){
    UKTools.voteMaster(hazelcastInstance);
}


@Override
public void memberRemoved(MembershipEvent membershipEvent){
    UKTools.voteMaster(hazelcastInstance);
}


}