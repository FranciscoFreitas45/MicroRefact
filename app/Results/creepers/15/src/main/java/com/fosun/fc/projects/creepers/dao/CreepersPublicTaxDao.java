package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersPublicTax;
public interface CreepersPublicTaxDao extends JpaSpecificationExecutor<TCreepersPublicTax>, JpaRepository<TCreepersPublicTax, Long>{


@Query("select t from TCreepersPublicTax t where t.rptNo = :rptNo")
public List<TCreepersPublicTax> queryByRptNo(String rptNo)
;

public List<TCreepersPublicTax> getTCreepersPublicTaxs(Long id);

public void setTCreepersPublicTaxs(Long id,List<TCreepersPublicTax> TCreepersPublicTaxs);

public TCreepersPublicTax addTCreepersPublicTax(Long id,TCreepersPublicTax TCreepersPublicTax);

public TCreepersPublicTax removeTCreepersPublicTax(Long id,TCreepersPublicTax TCreepersPublicTax);

}