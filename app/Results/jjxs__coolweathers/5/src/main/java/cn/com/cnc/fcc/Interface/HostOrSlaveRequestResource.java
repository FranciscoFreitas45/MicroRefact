package cn.com.cnc.fcc.Interface;
public interface HostOrSlaveRequestResource {

   public String saveInfomation(List<HstServer> hstServers,String localUrl,String localNode,String hostNodeInfo,List<HstServer> postHstServers);
}