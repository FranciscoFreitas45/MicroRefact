package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersInsurancePayment;
public interface CreepersInsurancePaymentDao extends JpaRepository<TCreepersInsurancePayment, Long>, JpaSpecificationExecutor<TCreepersInsurancePayment>{


@Modifying
@Query("delete from TCreepersInsurancePayment t where t.certNo = :certNo")
public void deleteByCertNo(String certNo)
;

@Query("select t from TCreepersInsurancePayment t where t.certNo = :certNo")
public List<TCreepersInsurancePayment> queryListByCertNo(String certNo)
;

}