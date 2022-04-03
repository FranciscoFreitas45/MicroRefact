package com.fzshuai.service;
 import com.fzshuai.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface TagService {


public Tag updateTag(Long id,Tag type)
;

public void deleteTag(Long id)
;

public List<Tag> listTag(String ids)
;

public Tag getTagByName(String name)
;

public Tag getTag(Long id)
;

public List<Tag> listTagTop(Integer size)
;

public Tag saveTag(Tag type)
;

}