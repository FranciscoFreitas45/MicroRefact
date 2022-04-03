package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Notice;
public interface NoticeRepository extends JpaRepository<Notice, String>{


public List<Notice> findByTitleAndOrgi(String title,String orgi)
;

public List<Notice> findByOrgi(String orgi)
;

public Notice findByIdAndOrgi(String id,String orgi)
;

public Page<Notice> findAll(Specification<Notice> spec,Pageable page)
;

}