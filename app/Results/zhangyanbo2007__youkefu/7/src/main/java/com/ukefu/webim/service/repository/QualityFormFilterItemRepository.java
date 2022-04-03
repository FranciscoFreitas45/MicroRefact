package com.ukefu.webim.service.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.QualityFormFilterItem;
public interface QualityFormFilterItemRepository extends JpaRepository<QualityFormFilterItem, String>{


public List<QualityFormFilterItem> findByOrgiAndQcformfilterid(String orgi,String qcformfilterid)
;

public QualityFormFilterItem findByIdAndOrgi(String id,String orgi)
;

}