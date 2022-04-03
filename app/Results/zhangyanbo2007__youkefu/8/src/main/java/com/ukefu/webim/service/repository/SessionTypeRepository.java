package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SessionType;
public interface SessionTypeRepository extends JpaRepository<SessionType, String>{


public List<SessionType> findByOrgiAndParentid(String orgi,String parentid)
;

public List<SessionType> findByOrgiAndDicid(String orgi,String dicid)
;

public SessionType findById(String id)
;

public List<SessionType> findByCodeOrName(String code,String name)
;

public List<SessionType> findByOrgiAndCtype(String orgi,String ctype)
;

public List<SessionType> findByIdAndOrgi(String id,String orgi)
;

public Page<SessionType> findByOrgiAndParentidAndCtype(String orgi,String parentid,String ctype,Pageable page)
;

public List<SessionType> findByDicidAndName(String dicid,String name)
;

}