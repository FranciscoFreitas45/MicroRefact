package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicSanction;
public interface CreepersPublicSanctionDao extends JpaSpecificationExecutor<TCreepersPublicSanction>, JpaRepository<TCreepersPublicSanction, Long>{


@Query("select t from TCreepersPublicSanction t where t.rptNo = :rptNo")
public List<TCreepersPublicSanction> queryByRptNo(String rptNo)
;

public void setTCreepersPublicSanctions(Long id,List<TCreepersPublicSanction> TCreepersPublicSanctions);

public List<TCreepersPublicSanction> getTCreepersPublicSanctions(Long id);

public TCreepersPublicSanction removeTCreepersPublicSanction(Long id,TCreepersPublicSanction TCreepersPublicSanction);

public TCreepersPublicSanction addTCreepersPublicSanction(Long id,TCreepersPublicSanction TCreepersPublicSanction);

}