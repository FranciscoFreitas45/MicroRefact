package com.fosun.fc.projects.creepers.dao;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.fosun.fc.projects.creepers.entity.TCreepersAssetHandle;
public interface CreepersAssetHandleDao extends JpaRepository<TCreepersAssetHandle, Long>, JpaSpecificationExecutor<TCreepersAssetHandle>{


@Query("select t from TCreepersAssetHandle t where t.rptNo = :rptNo")
public List<TCreepersAssetHandle> queryByRptNo(String rptNo)
;

public void setTCreepersAssetHandles(Long id,List<TCreepersAssetHandle> TCreepersAssetHandles);

public List<TCreepersAssetHandle> getTCreepersAssetHandles(Long id);

public TCreepersAssetHandle addTCreepersAssetHandle(Long id,TCreepersAssetHandle TCreepersAssetHandle);

public TCreepersAssetHandle removeTCreepersAssetHandle(Long id,TCreepersAssetHandle TCreepersAssetHandle);

}