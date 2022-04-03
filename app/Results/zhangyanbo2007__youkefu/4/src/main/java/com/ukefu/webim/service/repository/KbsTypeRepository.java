package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.KbsType;
public interface KbsTypeRepository extends JpaRepository<KbsType, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public List<KbsType> findByOrgi(String orgi)
;

public int countByOrgiAndNameAndParentid(String orgi,String name,String parentid)
;

public KbsType findByIdAndOrgi(String id,String orgi)
;

}