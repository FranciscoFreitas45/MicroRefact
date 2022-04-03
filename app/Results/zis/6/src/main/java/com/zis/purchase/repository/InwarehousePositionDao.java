package com.zis.purchase.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.zis.purchase.bean.InwarehousePosition;
public interface InwarehousePositionDao extends CrudRepository<InwarehousePosition, Integer>{


@Query(value = "from InwarehousePosition where inwarehouseId = :inId and isFull = false order by id asc")
public List<InwarehousePosition> findAvailablePosition(Integer inwarehouseId)
;

public void setInwarehouseId(Integer id,Integer inwarehouseId);

public void setPositionLabel(Integer id,String positionLabel);

public void setCurrentAmount(Integer id,Integer currentAmount);

public void setGmtCreate(Integer id,Date gmtCreate);

public void setGmtModify(Integer id,Date gmtModify);

}