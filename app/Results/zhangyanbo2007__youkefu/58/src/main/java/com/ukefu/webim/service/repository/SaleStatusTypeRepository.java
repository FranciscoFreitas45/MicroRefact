package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SaleStatusType;
public interface SaleStatusTypeRepository extends JpaRepository<SaleStatusType, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public List<SaleStatusType> findByOrgi(String orgi)
;

public List<SaleStatusType> findByOrgiAndActivityid(String orgi,String activityid)
;

public SaleStatusType findByNameAndOrgi(String name,String orgi)
;

public SaleStatusType findByIdAndOrgiAndActivityid(String id,String orgi,String Activityid)
;

public List<SaleStatusType> findByOrgiAndActivityidAndParentid(String orgi,String activityid,String parentid)
;

public SaleStatusType findByIdAndOrgi(String id,String orgi)
;

}