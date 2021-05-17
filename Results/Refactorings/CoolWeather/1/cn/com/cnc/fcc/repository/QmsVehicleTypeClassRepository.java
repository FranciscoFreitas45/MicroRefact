import cn.com.cnc.fcc.domain.QmsVehicleTypeClass;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
@Repository
public interface QmsVehicleTypeClassRepository extends JpaRepository<QmsVehicleTypeClass, Long> {


public List<QmsVehicleTypeClass> findByVehicleClass(String s)


public List<QmsVehicleTypeClass> findByFlagStatusAndVehicleClass(String string,String id)


public Optional<QmsVehicleTypeClass> findByVehicleClassAndFlagStatus(String id,String string)


}