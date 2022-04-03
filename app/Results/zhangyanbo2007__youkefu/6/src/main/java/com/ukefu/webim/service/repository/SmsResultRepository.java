package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.SmsResult;
public interface SmsResultRepository extends JpaRepository<SmsResult, String>{


public Page<SmsResult> findByOrgi(String orgi,Pageable paramPageable)
;

public Page<SmsResult> findByTemplettypeAndOrgi(String templettype,String orgi,Pageable paramPageable)
;

public SmsResult findByIdAndOrgi(String id,String orgi)
;

public Page<SmsResult> findAll(Specification<SmsResult> spec,Pageable pageable)
;

}