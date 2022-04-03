package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.IMGroup;
public interface IMGroupRepository extends JpaRepository<IMGroup, String>{


public int countByNameAndOrgi(String name,String orgi)
;

public IMGroup findById(String id)
;

public List<IMGroup> findByCreaterAndOrgi(String user,String orgi)
;

}