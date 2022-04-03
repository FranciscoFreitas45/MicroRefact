package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersCcDetail;
public interface CreepersCcDetailDao extends JpaRepository<TCreepersCcDetail, Long>, JpaSpecificationExecutor<TCreepersCcDetail>{


@Query("select t from TCreepersCcDetail t where t.rptNo = :rptNo")
public List<TCreepersCcDetail> queryByRptNo(String rptNo)
;

public void setTCreepersCcDetails(Long id,List<TCreepersCcDetail> TCreepersCcDetails);

public TCreepersCcDetail removeTCreepersCcDetail(Long id,TCreepersCcDetail TCreepersCcDetail);

public List<TCreepersCcDetail> getTCreepersCcDetails(Long id);

public TCreepersCcDetail addTCreepersCcDetail(Long id,TCreepersCcDetail TCreepersCcDetail);

}