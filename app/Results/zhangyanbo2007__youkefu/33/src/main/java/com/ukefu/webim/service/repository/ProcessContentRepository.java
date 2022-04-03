package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.ProcessContent;
public interface ProcessContentRepository extends JpaRepository<ProcessContent, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public Page<ProcessContent> findByOrgi(String orgi,Pageable page)
;

public ProcessContent findByIdAndOrgi(String id,String orgi)
;

public ProcessContent findByProcessidAndOrgi(String processid,String orgi)
;

public List<ProcessContent> findByProcesstypeAndOrgi(String processtype,String orgi)
;

}