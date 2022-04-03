package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QuickType;
public interface QuickTypeRepository extends JpaRepository<QuickType, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public List<QuickType> findByOrgiAndQuicktype(String orgi,String quicktype)
;

public QuickType findByOrgiAndName(String orgi,String name)
;

public int countByOrgiAndNameAndParentid(String orgi,String name,String parentid)
;

public QuickType findByIdAndOrgi(String id,String orgi)
;

public List<QuickType> findByOrgiAndQuicktypeAndCreater(String orgi,String quicktype,String creater)
;

}