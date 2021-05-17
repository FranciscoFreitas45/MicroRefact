import com.ats.hrmgt.model.assets.AssetLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface AssetLogRepo extends JpaRepository<AssetLog, Integer> {


@Query(value = " SELECT COUNT(*) FROM t_asset_amc WHERE t_asset_amc.del_status=1 and t_asset_amc.asset_id=:assetId and t_asset_amc.amc_id!=:amcId", nativeQuery = true)
public Integer getAMCRecordCount(int assetId,int amcId)


@Query(value = " SELECT COUNT(*) FROM t_asset_amc WHERE t_asset_amc.del_status=1 and t_asset_amc.amc_status=11 and amc_to_date<CURRENT_DATE and  t_asset_amc.asset_id=:assetId and t_asset_amc.amc_id!=:amcId", nativeQuery = true)
public Integer getAMCRecordCountForLivePending(int assetId,int amcId)


public AssetLog save(AssetLog log)


@Query(value = "SELECT COUNT( t_asset_log.asset_log_id) FROM  t_asset_log WHERE  t_asset_log.del_status=1", nativeQuery = true)
public int getCountAssetLogId()


@Query(value = " SELECT COUNT(*) FROM t_asset_amc WHERE t_asset_amc.del_status=1 and t_asset_amc.amc_status=11 and amc_to_date>CURRENT_DATE and  t_asset_amc.asset_id=:assetId and t_asset_amc.amc_id!=:amcId", nativeQuery = true)
public Integer getAMCRecordCountForLive(int assetId,int amcId)


}