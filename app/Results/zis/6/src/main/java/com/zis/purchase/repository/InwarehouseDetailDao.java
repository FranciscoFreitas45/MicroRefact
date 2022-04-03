package com.zis.purchase.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.purchase.bean.InwarehouseDetail;
public interface InwarehouseDetailDao extends PagingAndSortingRepository<InwarehouseDetail, Integer>, JpaSpecificationExecutor<InwarehouseDetail>{


@Query(value = "from InwarehouseDetail where inwarehouseId in (:ids)")
public List<InwarehouseDetail> findByInwarehouseIds(Integer[] inwarehouseIds)
;

public void setAmount(Integer id,Integer amount);

}