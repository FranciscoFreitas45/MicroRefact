package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmDimensionType;
public interface EkmDimensionTypeRepository extends JpaRepository<EkmDimensionType, String>{


public List<EkmDimensionType> findByDatastatusAndOrgi(boolean datastatus,String orgi)
;

public EkmDimensionType findByNameAndDimensionidAndDatastatusAndOrgi(String Name,String dimensionid,boolean datastatus,String orgi)
;

public List<EkmDimensionType> findByDimensionidAndDatastatusAndOrgi(String dimensionid,boolean datastatus,String orgi)
;

public List<EkmDimensionType> findByParentAndDatastatusAndOrgi(String parent,boolean datastatus,String orgi)
;

public EkmDimensionType findByIdAndOrgi(String id,String orgi)
;

}