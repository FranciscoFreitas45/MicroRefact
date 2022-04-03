package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Database;
public interface DatabaseRepository extends JpaRepository<Database, String>{


public List<Database> findByOrgi(String orgi)
;

public Database findByIdAndOrgi(String id,String orgi)
;

}