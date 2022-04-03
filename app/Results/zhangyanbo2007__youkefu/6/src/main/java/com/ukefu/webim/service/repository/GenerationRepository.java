package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Generation;
public interface GenerationRepository extends JpaRepository<Generation, String>{


public Generation findByOrgiAndModel(String orgi,String model)
;

public List<Generation> findByOrgi(String orgi)
;

}