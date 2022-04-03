package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceSum;
public interface CreepersInsuranceSumDao extends JpaSpecificationExecutor<TCreepersInsuranceSum>, JpaRepository<TCreepersInsuranceSum, Long>{


@Modifying
@Query("delete from TCreepersInsuranceSum t where t.certNo = :certNo")
public void deleteByCertNo(String certNo)
;

@Query("select t from TCreepersInsuranceSum t where t.certNo = :certNo")
public List<TCreepersInsuranceSum> queryListByCertNo(String certNo)
;

}