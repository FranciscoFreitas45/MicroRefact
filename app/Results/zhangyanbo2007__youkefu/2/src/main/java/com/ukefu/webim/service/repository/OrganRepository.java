package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Organ;
public interface OrganRepository extends JpaRepository<Organ, String>{


public List<Organ> findByOrgiAndOrgid(String orgi,String orgid)
;

public List<Organ> findByOrgi(String orgi)
;

public List<Organ> findByOrgiAndParent(String orgi,String parent)
;

public Organ findByNameAndOrgi(String paramString,String orgi)
;

public Organ findByNameAndOrgiAndOrgid(String paramString,String orgi,String orgid)
;

public List<Organ> findByOrgiAndSkill(String orgi,boolean skill)
;

public Organ findByIdAndOrgi(String paramString,String orgi)
;

public List<Organ> findByOrgiAndSkillAndOrgid(String orgi,boolean skill,String orgid)
;

public List<Organ> findAll(Specification<Organ> spec)
;

public List<Organ> findByIdInAndSkill(List<String> organIdList,boolean b)
;

}