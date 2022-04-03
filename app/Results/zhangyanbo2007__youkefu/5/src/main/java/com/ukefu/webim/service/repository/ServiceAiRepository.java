package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.ServiceAi;
public interface ServiceAiRepository extends JpaRepository<ServiceAi, String>{


public Page<ServiceAi> findByOrgi(String orgi,Pageable page)
;

public ServiceAi findByOrgiAndName(String orgi,String name)
;

public ServiceAi findByIdAndOrgi(String id,String orgi)
;

public ServiceAi findByCodeAndOrgi(String code,String orgi)
;

}