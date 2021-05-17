import com.ats.hrmgt.model.AssetServicing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
public interface AssetServicingRepo extends JpaRepository<AssetServicing, Integer> {


@Modifying
@Transactional
@Query(value = "UPDATE `t_asset_servicing` SET `service_type` = 1 WHERE `t_asset_servicing`.`t_servicing_id` !=:serviceId AND t_asset_servicing.asset_id=:assetId", nativeQuery = true)
public int chngRegAssetService(int serviceId,int assetId)


public AssetServicing findByTServicingId(int serviceId)


@Modifying
@Transactional
@Query(value = "UPDATE t_asset_servicing SET del_status=0 WHERE t_servicing_id=:serviceId", nativeQuery = true)
public int deleteAssetServiceInfo(int serviceId)


}