package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.TagRelation;
public interface TagRelationRepository extends JpaRepository<TagRelation, String>{


public TagRelation findByUseridAndTagid(String userid,String tagid)
;

public List<TagRelation> findByUserid(String userid)
;

}