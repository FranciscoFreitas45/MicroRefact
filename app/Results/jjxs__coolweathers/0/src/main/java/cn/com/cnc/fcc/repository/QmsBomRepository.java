package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsBom;
import java.util.List;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsBomRepository extends JpaRepository<QmsBom, Long>{


@Query(value = "select r from QmsBom r where flagStatus = :flagStatus and (vehicleType = :vehicleType or vehicleType like %:vehicleTypeName%) and (parentMaterielCd is null or trim(parentMaterielCd)='') ")
public List<QmsBom> getVehicleTypeAndVehicleTypeName(String vehicleType,String vehicleTypeName,String FlagStatus)
;

public List<QmsBom> findByVehicleIdAndMaterielIdAndParentMaterielIDAndFlagStatus(Integer vehicleId,Integer materielId,Integer parentMaterielID,String FlagStatus)
;

public List<QmsBom> findByVehicleIdAndFlagStatus(Integer vehicleType,String flagStatus)
;

public List<QmsBom> findByVehicleIdAndParentMaterielIDAndMaterielIdAndFlagStatusAndIdNot(Integer vehicleId,Integer ParentMaterielID,Integer materielId,String FlagStatus,Long id)
;

public List<QmsBom> findByVehicleIdAndParentMaterielIDAndMaterielIdAndFlagStatus(Integer vehicleType,Integer parentMaterielCd,Integer materielCd,String flagStatus)
;

public List<QmsBom> findByVehicleIdAndMaterielIdAndFlagStatus(Integer vehicleType,Integer parentMaterielCd,String flagStatus)
;

}