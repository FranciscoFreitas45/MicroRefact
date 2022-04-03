package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersListInsurance;
public interface CreepersListInsuranceDao extends JpaSpecificationExecutor<TCreepersListInsurance>, JpaRepository<TCreepersListInsurance, Long>{


@Modifying(clearAutomatically = true)
@Query("update TCreepersListInsurance t set t.flag = :flag, t.updatedDt = sysdate where t.userCode = :userCode")
public void updateListByUserCode(String userCode,String flag)
;

@Query("select t from TCreepersListInsurance t where t.userCode = :userCode")
public List<TCreepersListInsurance> queryListByUserCode(String userCode)
;

public TCreepersListInsurance findTop1ByUserCode(String userCode)
;

}