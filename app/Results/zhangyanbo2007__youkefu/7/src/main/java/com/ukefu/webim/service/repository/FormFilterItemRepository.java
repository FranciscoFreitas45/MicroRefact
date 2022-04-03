package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.FormFilterItem;
public interface FormFilterItemRepository extends JpaRepository<FormFilterItem, String>{


public List<FormFilterItem> findByOrgiAndFormfilterid(String orgi,String formfilterid)
;

public FormFilterItem findByIdAndOrgi(String id,String orgi)
;

}