package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersInsuranceBasic;
public interface CreepersInsuranceBasicDao extends JpaSpecificationExecutor<TCreepersInsuranceBasic>, JpaRepository<TCreepersInsuranceBasic, Long>{


@Modifying
@Query("delete from TCreepersInsuranceBasic t where t.certNo = :certNo")
public void deleteByCertNo(String certNo)
;

@Query("select t from TCreepersInsuranceBasic t where t.certNo = :certNo")
public List<TCreepersInsuranceBasic> queryListByCertNo(String certNo)
;

}