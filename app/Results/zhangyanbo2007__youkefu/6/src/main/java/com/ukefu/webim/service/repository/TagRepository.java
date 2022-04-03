package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.Tag;
public interface TagRepository extends JpaRepository<Tag, String>{


public Tag findByOrgiAndId(String orgi,String id)
;

public int countByOrgiAndTagAndTagtype(String orgi,String tag,String tagtype)
;

public List<Tag> findByOrgi(String orgi)
;

public Tag findByOrgiAndTag(String orgi,String tag)
;

public List<Tag> findByOrgiAndTagAndTagtype(String orgi,String tag,String tagtype)
;

public List<Tag> findByOrgiAndTagtype(String orgi,String tagtype)
;

}