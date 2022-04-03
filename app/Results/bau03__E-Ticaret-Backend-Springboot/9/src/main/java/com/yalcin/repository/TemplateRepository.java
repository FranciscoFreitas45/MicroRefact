package com.yalcin.repository;
 import com.yalcin.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer>{


public Template findByTemplateName(String templateName)
;

}