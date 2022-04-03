package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Skill;
public interface SkillRepository extends JpaRepository<Skill, String>{


public List<Skill> findByOrgi(String orgi)
;

public Skill findByNameAndOrgi(String name,String orgi)
;

public Skill findByIdAndOrgi(String id,String orgi)
;

}