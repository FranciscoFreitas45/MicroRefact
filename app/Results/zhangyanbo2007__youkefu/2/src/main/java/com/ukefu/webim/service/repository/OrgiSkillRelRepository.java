package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.OrgiSkillRel;
public interface OrgiSkillRelRepository extends JpaRepository<OrgiSkillRel, String>{


public List<OrgiSkillRel> findBySkillid(String skillid)
;

public List<OrgiSkillRel> findByOrgi(String orgi)
;

}