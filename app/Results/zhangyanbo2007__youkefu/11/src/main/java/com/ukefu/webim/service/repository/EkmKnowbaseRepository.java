package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKnowbase;
public interface EkmKnowbaseRepository extends JpaRepository<EkmKnowbase, String>{


public EkmKnowbase findByNameAndOrgiAndOwn(String name,String orgi,String own)
;

public List<EkmKnowbase> findByOrgiAndOwn(String orgi,String own)
;

public EkmKnowbase findByKbviewidAndOrgi(String kbviewid,String orgi)
;

public EkmKnowbase findByIdAndOrgi(String id,String orgi)
;

public Page<EkmKnowbase> findAll(Specification<EkmKnowbase> spec,Pageable page)
;

public List<EkmKnowbase> findByOrgiAndOwnAndCreater(String orgi,String own,String creater)
;

}