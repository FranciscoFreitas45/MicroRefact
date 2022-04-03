package com.zis.bookinfo.repository;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.zis.bookinfo.bean.YouluSales;
public interface YouluSalesDao extends CrudRepository<YouluSales, Integer>{


public List<YouluSales> findByOutId(Integer outId)
;

}