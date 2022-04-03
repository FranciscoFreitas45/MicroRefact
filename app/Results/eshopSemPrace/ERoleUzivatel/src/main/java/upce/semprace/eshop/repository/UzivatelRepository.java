package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.*;
import java.util.*;
  import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
public interface UzivatelRepository extends JpaRepository<Uzivatel, Long>{


public Optional<Uzivatel> findByUsername(String username)
;

public Uzivatel findByJmeno(String jmeno)
;

public Optional<Uzivatel> findById(Long id)
;

public void removeUzivatelById(Long id)
;

public Boolean existsByUsername(String username)
;

public Boolean existsByEmail(String email)
;

public Uzivatel findByEmail(String email)
;

@Transactional
 @Modifying
@Query(value = "update uzivatel u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setUzivatel(Long id,Uzivatel uzivatel);

@Query(value = "Select * from uzivatel u  where u.id = ?1", nativeQuery = true)
public Uzivatel getUzivatel(Long id);


@Transactional
 @Modifying
@Query(value = "update uzivatel u set u.id = ?1 where u.prijmeni = ?2", nativeQuery = true)
public void setPrijmeni(Long id,String prijmeni);

@Transactional
 @Modifying
@Query(value = "update uzivatel u set u.id = ?1 where u.id = ?2", nativeQuery = true)
public void setAdresa(Long id,String adresa);

@Transactional
 @Modifying
@Query(value = "update uzivatel u set u.id = ?1 where u.id = ?1", nativeQuery = true)
public void setRoles(Long id,Set<Role> roles);

}