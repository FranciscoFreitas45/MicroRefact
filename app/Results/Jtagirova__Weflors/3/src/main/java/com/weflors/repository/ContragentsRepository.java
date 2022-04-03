package com.weflors.repository;
 import com.weflors.entity.ContragentsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
public interface ContragentsRepository extends JpaRepository<ContragentsEntity, Integer>{


@Modifying
@Transactional
@Query("delete from ContragentsEntity where contragentId = :contragentId")
public void deleteContragentById(Integer contragentId)
;

@Query("select ca from ContragentsEntity ca where ca.contragentName = :contragentName")
public Optional<ContragentsEntity> findByContragentName(String contragentName)
;

@Query("select DISTINCT ca from ContragentsEntity ca where ca.contragentId = :contragentID")
public ContragentsEntity findByContragentID(Integer contragentID)
;

@Modifying
@Transactional
@Query("update ContragentsEntity set address = :address, contragentName = :contragentName, phone1 = :phone1, phone2 = :phone2," + "unk = :unk, inn = :inn, zipCode = :zipCode where contragentId = :contragentId")
public void updateContragentById(String address,String contragentName,String phone1,String phone2,String unk,String inn,String zipCode,Integer contragentId)
;

}