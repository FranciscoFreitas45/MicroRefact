package com.sobey.cmop.mvc.service.basicdata;
 import java.util.ArrayList;
import java.util.Date;
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
import com.sobey.cmop.mvc.constant.IpPoolConstant;
import com.sobey.cmop.mvc.dao.IpPoolDao;
import com.sobey.cmop.mvc.dao.custom.IpPoolDaoCustom;
import com.sobey.cmop.mvc.entity.HostServer;
import com.sobey.cmop.mvc.entity.IpPool;
import com.sobey.cmop.mvc.entity.Vlan;
import com.sobey.framework.utils.DynamicSpecifications;
import com.sobey.framework.utils.SearchFilter;
@Service
@Transactional(readOnly = true)
public class IpPoolService extends BaseSevcie{

 private  Logger logger;

@Resource
 private  IpPoolDao ipPoolDao;

@Resource
 private  IpPoolDaoCustom customDao;


public List<IpPool> getIpPoolByPoolTypeAndStatus(Integer poolType,Integer status){
    return ipPoolDao.findByPoolTypeAndStatus(poolType, status);
}


public Page<IpPool> getIpPoolPageable(Map<String,Object> searchParams,int pageNumber,int pageSize){
    PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
    Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
    Specification<IpPool> spec = DynamicSpecifications.bySearchFilter(filters.values(), IpPool.class);
    return ipPoolDao.findAll(spec, pageRequest);
}


public List<String> getAllIpAddress(){
    List<IpPool> ipPools = (List<IpPool>) ipPoolDao.findAll();
    List<String> list = new ArrayList<String>();
    for (IpPool ipPool : ipPools) {
        list.add(ipPool.getIpAddress());
    }
    return list;
}


@Transactional(readOnly = false)
public void deleteIpPool(Integer id){
    // 删除oneCMDB下的ip
    comm.oneCmdbUtilService.deleteIpPoolToOneCMDB(this.getIpPool(id));
    ipPoolDao.delete(id);
}


public IpPool initIpPool(String ipAddress){
    IpPool ipPool = ipPoolDao.findByIpAddress(ipAddress);
    if (ipPool != null) {
        ipPool.setStatus(IpPoolConstant.IpStatus.未使用.toInteger());
        ipPool.setHostServer(null);
        return this.saveOrUpdate(ipPool);
    }
    return null;
}


public List<IpPool> findIpPoolByVlan(String vlanAlias){
    return ipPoolDao.findByVlanAliasAndStatus(vlanAlias, 1);
}


public List<String> getInsertIpAddressList(String ipAddress){
    List<String> list = new ArrayList<String>();
    // 不包含"/"和"," 表示是一个单独的IP
    if (ipAddress.indexOf("/") == -1 && ipAddress.indexOf(",") == -1) {
        list.add(ipAddress);
    } else if (ipAddress.indexOf("/") == -1 && ipAddress.indexOf(",") != -1) {
        // 不包含"/" 但包含"," 表示是多个IP.
        String[] ipArray = ipAddress.split(",");
        for (String ip : ipArray) {
            list.add(ip);
        }
    } else {
        // 表示IP段.eg: 192.168.0.2/200
        // 以最后一个"."进行切割.
        String[] parts = ipAddress.split("(?<=\\.)(?!.*\\.)");
        // parts[0] = 192.168.0.
        String startParts = parts[0];
        // parts[1] = 2/200
        String endParts = parts[1];
        // 对IP段进行切割
        String[] ends = endParts.split("/");
        // 起始IP :2
        int first = Integer.parseInt(ends[0]);
        // 结束IP :200
        int last = Integer.parseInt(ends[1]);
        for (int i = first; i <= last; i++) {
            list.add(startParts + i);
        }
    }
    return list;
}


@Transactional(readOnly = false)
public void updateIpPoolByIpAddress(String ipAddress,Integer status,HostServer hostServer){
    // TODO 待优化,最终目标是删除此方法.
    IpPool ipPool = ipPoolDao.findByIpAddress(ipAddress);
    if (ipPool != null) {
        ipPool.setStatus(status);
        ipPool.setHostServer(hostServer);
        ipPoolDao.save(ipPool);
    }
}


public IpPool getIpPool(Integer id){
    return ipPoolDao.findOne(id);
}


public IpPool findIpPoolByIpAddress(String ipAddress){
    return ipPoolDao.findByIpAddress(ipAddress);
}


@Transactional(readOnly = false)
public IpPool saveOrUpdate(IpPool ipPool){
    return ipPoolDao.save(ipPool);
}


@Transactional(readOnly = false)
public void saveIpPool(IpPool ipPool){
    // 同步至oneCMDB
    List<IpPool> ipPools = new ArrayList<IpPool>();
    ipPools.add(ipPool);
    comm.oneCmdbUtilService.saveIpPoolToOneCMDB(ipPools, ipPool.getPoolType());
    ipPoolDao.save(ipPool);
}


}