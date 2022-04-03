package com.project.stockexchangeappbackend.repository.specification;
 import com.project.stockexchangeappbackend.entity.Tag;
import net.kaczmarzyk.spring.data.jpa.domain;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
@And({ @Spec(path = "name", spec = LikeIgnoreCase.class) })
public interface TagSpecification extends Specification<Tag>{


}