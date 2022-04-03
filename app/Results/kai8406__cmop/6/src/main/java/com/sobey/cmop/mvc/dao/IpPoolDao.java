package com.sobey.cmop.mvc.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.sobey.cmop.mvc.entity.IpPool;
public interface IpPoolDao extends PagingAndSortingRepository<IpPool, Integer>, JpaSpecificationExecutor<IpPool>{


public IpPool findByIpAddress(String ipAddress)
;

public List<IpPool> findByPoolTypeAndStatus(Integer poolType,Integer status)
;

public List<IpPool> findByVlanAliasAndStatus(String vlanAlias,Integer status)
;

}