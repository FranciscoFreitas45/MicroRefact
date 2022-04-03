package com.project.stockexchangeappbackend.service;
 import com.project.stockexchangeappbackend.dto.CreateTagDTO;
import com.project.stockexchangeappbackend.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.util.Optional;
public interface TagService {


public Tag findTag(String name)
;

public void createTag(CreateTagDTO createTagDTO)
;

public void removeTag(String name)
;

public Page<Tag> getTags(Pageable pageable,Specification<Tag> specification)
;

public Optional<Tag> getTag(String name)
;

}