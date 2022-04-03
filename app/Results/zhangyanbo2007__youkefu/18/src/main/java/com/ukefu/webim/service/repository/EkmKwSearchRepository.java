package com.ukefu.webim.service.repository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.EkmKwSearch;
public interface EkmKwSearchRepository extends JpaRepository<EkmKwSearch, String>{


public Page<EkmKwSearch> findByOrgi(String orgi,Pageable paramPageable)
;

public EkmKwSearch findByConditionsAndOrgi(String conditions,String orgi)
;

public EkmKwSearch findByIdAndOrgi(String id,String orgi)
;

}