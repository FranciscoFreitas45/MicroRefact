package com.cg.oms.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.oms.model.Address;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface AddressRepository extends JpaRepository<Address, Long>{

@Query(value = "Select * from address t  where t.address_id = ?1", nativeQuery = true)
public Address getUserAddress(Integer addressId);

@Transactional
@Modifying
@Query(value = "update address t set t.address_id = ?1 where t.address_id = ?1", nativeQuery = true)
public void setUserAddress(Integer addressId,Address addressAddress);

}