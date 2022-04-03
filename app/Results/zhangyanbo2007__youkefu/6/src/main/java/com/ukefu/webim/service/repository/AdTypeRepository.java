package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.AdType;
public interface AdTypeRepository extends JpaRepository<AdType, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public List<AdType> findByOrgi(String orgi)
;

public Page<AdType> findByAdposAndOrgi(String adpos,String orgi,Pageable page)
;

public AdType findByIdAndOrgi(String id,String orgi)
;

}