package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CallOutNamesHis;
public interface CallOutNamesHisRepository extends JpaRepository<CallOutNamesHis, String>{


public List<CallOutNamesHis> findByDataidAndCreaterAndOrgi(String dataid,String creater,String orgi)
;

public Page<CallOutNamesHis> findByOrgi(String orgi,Pageable page)
;

public List<CallOutNamesHis> findByDataidAndOrgi(String dataid,String orgi)
;

public Page<CallOutNamesHis> findByOwneruserAndOrgi(String owneruser,String orgi,Pageable page)
;

public List<CallOutNamesHis> findByNameAndOrgi(String name,String orgi)
;

public List<CallOutNamesHis> findByActidAndOrgi(String actid,String orgi)
;

public Page<CallOutNamesHis> findByOrganAndOrgi(String organ,String orgi,Pageable page)
;

public Page<CallOutNamesHis> findByOrgiAndDataidAndActidAndStatusNot(String orgi,String dataid,String actid,String status,Pageable page)
;

public CallOutNamesHis findByIdAndOrgi(String id,String orgi)
;

public Page<CallOutNamesHis> findAll(Specification<CallOutNamesHis> spec,Pageable pageable)
;

public Page<CallOutNamesHis> findByCreaterAndOrgi(String creater,String orgi,Pageable page)
;

}