package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Quality;
public interface QualityRepository extends JpaRepository<Quality, String>{


public List<Quality> findByOrgi(String orgi)
;

public Quality findByIdAndOrgi(String id,String orgi)
;

public List<Quality> findByQualitytypeAndOrgi(String qualitytype,String orgi)
;

}