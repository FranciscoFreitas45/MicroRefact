package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Dimension;
public interface DimensionRepository extends JpaRepository<Dimension, String>{


public List<Dimension> findByCubeid(String cubeid)
;

public int countByFktable(String fktable)
;

public Dimension findByIdAndOrgi(String id,String orgi)
;

}