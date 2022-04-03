package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersListFund;
public interface CreepersListFundDao extends JpaSpecificationExecutor<TCreepersListFund>, JpaRepository<TCreepersListFund, Long>{


@Modifying(clearAutomatically = true)
@Query("update TCreepersListFund t set t.flag = :flag, t.updatedDt = sysdate where t.userCode = :userCode")
public void updateListByUserCode(String userCode,String flag)
;

@Query("select t from TCreepersListFund t where t.userCode = :userCode")
public List<TCreepersListFund> queryListByUserCode(String userCode)
;

public TCreepersListFund findTop1ByUserCode(String userCode)
;

}