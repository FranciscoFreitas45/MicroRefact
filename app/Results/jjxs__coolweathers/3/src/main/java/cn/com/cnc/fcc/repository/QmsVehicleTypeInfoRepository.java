package cn.com.cnc.fcc.repository;
 import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsVehicleTypeInfoRepository extends JpaRepository<QmsVehicleTypeInfo, Long>, JpaSpecificationExecutor<QmsVehicleTypeInfo>{


@Query(value = "select q from QmsVehicleTypeInfo q  where q.flagStatus = 0 and q.vehicleType like %:vehicleType% and q.vehicleTypeName like %:vehicleTypeName%")
public List<QmsVehicleTypeInfo> getVehicleTypeInfo(String vehicleType,String vehicleTypeName)
;

public Optional<QmsVehicleTypeInfo> findByFlagStatusAndVehicleTypeOrderById(String id,String string)
;

public QmsVehicleTypeInfo findByVehicleTypeAndFlagStatus(String vehicleType,String flagStatus)
;

public List<QmsVehicleTypeInfo> findByVehicleTypeAndFlagStatusOrderById(String string,String id)
;

public List<QmsVehicleTypeInfo> findByFlagStatusAndId(String flagStatus,Long vehicleId)
;

public List<QmsVehicleTypeInfo> findByFlagStatusOrderById(String flagStatus)
;

public List<QmsVehicleTypeInfo> findByIdAndFlagStatus(Long id,String FlagStatus)
;

@Modifying()
@Query(value = "DELETE FROM  QmsVehicleTypeInfo where vehicleType= ?1 ")
public Integer updateIdModifyTime(String VehicleType,String Username,ZonedDateTime modifyTime)
;

public List<QmsVehicleTypeInfo> findByVehicleClassId(Integer s)
;

public List<QmsVehicleTypeInfo> findAllByFlagStatusOrderByIdDesc(String FlagStatus)
;

public List<QmsVehicleTypeInfo> findByFlagStatusAndVehicleType(String string,String string2)
;

}