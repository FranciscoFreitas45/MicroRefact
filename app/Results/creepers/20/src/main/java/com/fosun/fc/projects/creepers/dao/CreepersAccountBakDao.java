package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersAccountBak;
public interface CreepersAccountBakDao extends JpaSpecificationExecutor<TCreepersAccountBak>, JpaRepository<TCreepersAccountBak, Long>{


@Query("select t from TCreepersAccountBak t where t.status = :status")
public List<TCreepersAccountBak> queryByStatus(String status)
;

@Query("select t from TCreepersAccountBak t where t.rptNo = :rptNo")
public List<TCreepersAccountBak> queryByRptNo(String rptNo)
;

@Query("select t from TCreepersAccountBak t where t.usr = :usr")
public List<TCreepersAccountBak> queryByUsr(String usr)
;

@Modifying
@Query("update TCreepersAccountBak t set t.status = '1' where t.rptNo = :rptNo")
public void updateStatusByRptNo(String rptNo)
;

public TCreepersAccountBak findTop1ByUsrAndCde(String usr,String cde)
;

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

public void setTCreepersAccountBak(Long id,TCreepersAccountBak TCreepersAccountBak);

public TCreepersAccountBak getTCreepersAccountBak(Long id);

}