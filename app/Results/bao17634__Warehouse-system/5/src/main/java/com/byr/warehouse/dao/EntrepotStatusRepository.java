package com.byr.warehouse.dao;
 import com.byr.warehouse.pojo.EntrepotStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
@Repository
public interface EntrepotStatusRepository extends JpaRepository<EntrepotStatus, Long>{


public List<EntrepotStatus> findEntrepotStatusByid(Long id)
;

@Query("SELECT entrepot FROM EntrepotStatus entrepot where entrepot.enterCode = :enterCode and materialCode like :materialCode")
public List<EntrepotStatus> findByEnterCodeAndMaterialCode(String enterCode,String materialCode)
;

@Query("select entrepot from EntrepotStatus entrepot")
public List<EntrepotStatus> getTotalSize()
;

public List<EntrepotStatus> findEntrepotStatusBymaterialCode(String materialCode)
;

public List<EntrepotStatus> findEntrepotStatusByEnterCode(String enterCode)
;

@Query("SELECT entrepot FROM EntrepotStatus entrepot where entrepot.entranceDate <= :entranceDate and goodsStatus='良品'")
public List<EntrepotStatus> findBeforeDate(Date date)
;

public void setTotalSize(Long id,int totalSize);

public void setEnterCode(Long id,String enterCode);

public void setMaterialCode(Long id,String materialCode);

public void setEntranceDate(Long id,Date entranceDate);

public void setUpdateDate(Long id,Date updateDate);

public void setProduceDate(Long id,String produceDate);

public void setMaterialSpec(Long id,String materialSpec);

public void setProductName(Long id,String productName);

public void setPosition(Long id,String position);

}