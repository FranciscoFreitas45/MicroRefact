package com.ukefu.webim.service.repository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Log;
public interface LogRepository extends JpaRepository<Log, String>{


public Page<Log> findByOrgi(String orgi,Pageable page)
;

public Page<Log> findByOrgiAndLevels(String orgi,String levels,Pageable page)
;

}