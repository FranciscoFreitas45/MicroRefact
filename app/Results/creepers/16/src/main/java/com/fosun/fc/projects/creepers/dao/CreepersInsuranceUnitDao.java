package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceUnit;
public interface CreepersInsuranceUnitDao extends JpaRepository<TCreepersInsuranceUnit, Long>, JpaSpecificationExecutor<TCreepersInsuranceUnit>{


@Modifying
@Query("delete from TCreepersInsuranceUnit t where t.certNo = :certNo")
public void deleteByCertNo(String certNo)
;

@Query("select t from TCreepersInsuranceUnit t where t.certNo = :certNo")
public List<TCreepersInsuranceUnit> queryListByCertNo(String certNo)
;

}