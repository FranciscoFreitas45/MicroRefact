package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AreaType;
public interface AreaTypeRepository extends JpaRepository<AreaType, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public List<AreaType> findByOrgi(String orgi)
;

public AreaType findByIdAndOrgi(String id,String orgi)
;

}