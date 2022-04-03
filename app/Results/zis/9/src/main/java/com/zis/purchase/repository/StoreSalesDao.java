package com.zis.purchase.repository;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.zis.purchase.bean.Storesales;
public interface StoreSalesDao extends CrudRepository<Storesales, Integer>{


public List<Storesales> findByBookId(Integer bookId)
;

}