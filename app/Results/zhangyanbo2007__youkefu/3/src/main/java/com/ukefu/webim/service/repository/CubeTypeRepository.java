package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.CubeType;
public interface CubeTypeRepository extends JpaRepository<CubeType, String>{


public List<CubeType> findByOrgi(String orgi)
;

public CubeType findByOrgiAndName(String orgi,String name)
;

public CubeType findByIdAndOrgi(String id,String orgi)
;

}