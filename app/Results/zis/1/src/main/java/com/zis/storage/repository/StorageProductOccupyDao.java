package com.zis.storage.repository;
 import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.zis.storage.entity.StorageProductOccupy;
public interface StorageProductOccupyDao extends CrudRepository<StorageProductOccupy, Integer>{


public List<StorageProductOccupy> findByOrderId(Integer orderId)
;

public StorageProductOccupy findByOrderIdAndProductId(Integer orderId,Integer productId)
;

}