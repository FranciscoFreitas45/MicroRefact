package cn.com.cnc.fcc.Interface;
public interface HstServerInfoRepository {

   public List<HstServer> findByNodeId(String nodeId,int hostSlaveFlag);
   public List<HstServer> findHostServer(int hostSlaveFlag);
}