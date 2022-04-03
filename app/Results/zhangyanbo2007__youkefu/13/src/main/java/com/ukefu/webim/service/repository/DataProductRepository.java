package com.ukefu.webim.service.repository;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ukefu.webim.web.model.DataProduct;
public interface DataProductRepository extends JpaRepository<DataProduct, String>{


public Page<DataProduct> findByOrgiAndNameLike(String orgi,String name,Pageable page)
;

public Page<DataProduct> findByOrgi(String orgi,Pageable page)
;

public DataProduct findByIdAndOrgi(String id,String orgi)
;

public int countByOrgiAndNameLike(String orgi,String name)
;

}