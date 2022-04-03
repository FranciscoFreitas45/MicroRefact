package com.weflors.repository;
 import com.weflors.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;
public interface ClientRepository extends JpaRepository<ClientEntity, Integer>{


@Query("select b.eMail from ClientEntity b")
public List<String> getAllClientEmail()
;

@Query("select c.discount from ClientEntity c where c.eMail = :eMail")
public Integer getDiscountByClientEmail(String eMail)
;

@Query("select c from ClientEntity c where c.clientId = :clientID")
public ClientEntity getClientByClientID(Integer clientID)
;

@Modifying
@Transactional
@Query("delete from ClientEntity where clientId = :clientId")
public void deleteByClientId(Integer clientId)
;

@Query("select true from ClientEntity c where c.eMail = :eMail")
public Boolean existByEMail(String eMail)
;

@Modifying
@Transactional
@Query("update ClientEntity set clientName = :clientName, clientSurname = :clientSurname, dateOfBirth = :dateOfBirth, eMail = :eMail," + "phone = :phone, discount = :discount, address = :address, zipCode = :zipCode where clientId = :clientId")
public void updateClientById(String clientName,String clientSurname,Date dateOfBirth,String eMail,String phone,Integer discount,String address,String zipCode,Integer clientId)
;

@Query("select c from ClientEntity c where c.eMail = :eMail")
public ClientEntity getClientByEmail(String eMail)
;

}