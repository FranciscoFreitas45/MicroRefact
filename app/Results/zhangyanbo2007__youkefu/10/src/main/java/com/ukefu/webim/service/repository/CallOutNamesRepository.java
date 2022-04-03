package com.ukefu.webim.service.repository;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.ukefu.webim.web.model.CallOutNames;
public interface CallOutNamesRepository extends JpaRepository<CallOutNames, String>{


@Query(value = "delete from CallOutNames where orgi = ?1 and batid = ?2")
public List<CallOutNames> findByOrgiAndOnweraiAndDuration(String metaname,String dataid,String orgi)
;

public Page<CallOutNames> findByOrgi(String orgi,Pageable page)
;

public List<CallOutNames> findByDataidAndOrgi(String dataid,String orgi)
;

public List<CallOutNames> findByActidAndOrgi(String actid,String orgi)
;

public Page<CallOutNames> findAll(Specification<CallOutNames> spec,Pageable pageable)
;

public Page<CallOutNames> findByCreaterAndOrgi(String creater,String orgi,Pageable page)
;

public List<CallOutNames> findByDataidAndCreaterAndOrgi(String dataid,String creater,String orgi)
;

public Page<CallOutNames> findByOwneruserAndOrgi(String owneruser,String orgi,Pageable page)
;

public List<CallOutNames> findByMetanameAndDataidAndOrgi(String metaname,String dataid,String orgi)
;

public List<CallOutNames> findByNameAndOrgi(String name,String orgi)
;

public Page<CallOutNames> findByOrganAndOrgi(String organ,String orgi,Pageable page)
;

@Modifying
@Transactional
@Query(value = "delete from CallOutNames where orgi = ?1 and owneruser = ?2")
public void deleteByOrgiAndOwneruser(String orgi,String owneruser)
;

@Modifying
@Transactional
@Query(value = "delete from CallOutNames where orgi = ?1 and batid = ?2")
public void deleteByOrgiAndBatid(String orgi,String batid)
;

public CallOutNames findByIdAndOrgi(String id,String orgi)
;

@Modifying
@Transactional
@Query(value = "delete from CallOutNames where orgi = ?1 and dataid = ?2")
public void deleteByOrgiAndDataid(String orgi,String dataid)
;

public Page<CallOutNames> findByOwneraiAndOrgi(String ownerai,String orgi,Pageable page)
;

}