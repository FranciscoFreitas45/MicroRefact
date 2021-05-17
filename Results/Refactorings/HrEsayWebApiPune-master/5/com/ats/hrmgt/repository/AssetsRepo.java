import com.ats.hrmgt.model.Assets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface AssetsRepo extends JpaRepository<Assets, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE m_assets SET asset_status=:assetStatus, maker_user_id=:userUpdateId, update_datetime=:updateTime WHERE asset_id=:assetId", nativeQuery = true)
public int changeAssetStatusToLost(int assetId,int assetStatus,int userUpdateId,String updateTime)


public Assets findByAssetId(int assetId)


public List<Assets> findByDelStatusOrderByAssetIdDesc(int del)


@Transactional
@Modifying
@Query(value = "UPDATE m_assets SET asset_status=:stat WHERE asset_id=:assetId", nativeQuery = true)
public int changeAssetStatus(int assetId,int stat)


public List<Assets> findByLocIdAndDelStatus(int locId,int del)


@Transactional
@Modifying
@Query(value = "UPDATE m_assets SET del_status=0 WHERE asset_id=:assetId", nativeQuery = true)
public int deleteAsset(int assetId)


@Transactional
@Modifying
@Query(value = "UPDATE\n" + "    m_assets\n" + "SET\n" + "    asset_status = :status,\n" + "    scrap_date =:scrapDate,\n" + "    scrap_remark =:remark,\n" + "    scrap_authoriy_details =:scrapAuthority,\n" + "    scrap_login_userid =:scrapUserLogInId,\n" + "    scrap_datetime =:scrapDateTime\n" + "WHERE\n" + "    asset_id=:assetId", nativeQuery = true)
public int makeAssetScrap(int assetId,int status,String scrapDate,String remark,String scrapAuthority,int scrapUserLogInId,String scrapDateTime)


}