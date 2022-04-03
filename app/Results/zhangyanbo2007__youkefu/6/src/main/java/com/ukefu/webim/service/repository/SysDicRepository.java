package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SysDic;
public interface SysDicRepository extends JpaRepository<SysDic, String>{


public int countByName(String name)
;

public List<SysDic> findByParentid(String type)
;

public List<SysDic> findByParentidAndName(String parentid,String name)
;

public SysDic findByCode(String code)
;

public SysDic findById(String id)
;

public Page<SysDic> findByDicid(String id,Pageable paramPageable)
;

public List<SysDic> findByCodeOrName(String code,String name)
;

public Page<SysDic> findAll(Pageable paramPageable)
;

public List<SysDic> findByDicidAndName(String dicid,String name)
;

}