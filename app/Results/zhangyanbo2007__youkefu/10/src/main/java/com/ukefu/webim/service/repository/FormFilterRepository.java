package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.FormFilter;
public interface FormFilterRepository extends JpaRepository<FormFilter, String>{


public List<FormFilter> findByOrgi(String orgi)
;

public List<FormFilter> findByNameAndOrgi(String name,String orgi)
;

public FormFilter findByIdAndOrgi(String id,String orgi)
;

public List<FormFilter> findAll(Specification<FormFilter> spec)
;

}