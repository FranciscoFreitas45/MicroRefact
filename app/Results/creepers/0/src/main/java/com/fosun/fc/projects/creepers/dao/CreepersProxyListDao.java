package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersProxyList;
public interface CreepersProxyListDao extends JpaRepository<TCreepersProxyList, Long>, JpaSpecificationExecutor<TCreepersProxyList>{


@Query("select t from TCreepersProxyList t where t.ip = :ip and t.port = :port")
public List<TCreepersProxyList> queryByIpAndPort(String ip,String port)
;

}