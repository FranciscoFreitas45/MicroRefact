import com.ats.hrmgt.model.AssetAmc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface AssetAmcRepo extends JpaRepository<AssetAmc, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE t_asset_amc SET del_status=0 WHERE amc_id=:assetAmcId", nativeQuery = true)
public int deleteAssetAmc(int assetAmcId)


@Query(value = "SELECT\n" + "    amc.amc_id,\n" + "    amc.asset_id,\n" + "    amc.amc_fr_date,\n" + "    amc.amc_to_date,\n" + "    amc.amc_amt,\n" + "    amc.amc_status,\n" + "    amc.positive_remark,\n" + "    amc.negative_remark,\n" + "    amc.term_and_condi,\n" + "    amc.vendor_id,\n" + "    amc.amc_doc_file,\n" + "    amc.maker_user_id,\n" + "    amc.update_datetime,\n" + "    amc.del_status,amc.ex_int1,\n" + "    amc.ex_var1,\n" + "    amc.ex_int2,\n" + "    astvendor.comp_name as ex_var2\n" + "FROM\n" + "    t_asset_amc amc,\n" + "    m_asset_vendor astvendor\n" + "WHERE\n" + "    amc.del_status=1 AND\n" + "    astvendor.vendor_id=amc.vendor_id", nativeQuery = true)
public List<AssetAmc> getAllAssetAMC()


public List<AssetAmc> findByDelStatusOrderByAmcIdDesc(int del)


@Transactional
@Modifying
@Query(value = "UPDATE t_asset_amc SET amc_status=:status,  maker_user_id=:userUpdateId, update_datetime=:updateTime WHERE amc_id=:assetAMCId", nativeQuery = true)
public int terminateAMC(int assetAMCId,int status,int userUpdateId,String updateTime)


public AssetAmc findByAmcId(int assetAmcId)


@Transactional
@Modifying
@Query(value = "UPDATE t_asset_amc SET amc_status=:status WHERE amc_id=:assetAmcId", nativeQuery = true)
public int updtAmcStatus(int assetAmcId,int status)


}