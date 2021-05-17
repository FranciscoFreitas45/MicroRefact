import com.ats.hrmgt.model.assets.AMCInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AMCInfoRepo extends JpaRepository<AMCInfo, Integer> {


@Query(value = "SELECT\n" + "        t_asset_amc.amc_id,\n" + "        t_asset_amc.asset_id,\n" + "        t_asset_amc.amc_fr_date,\n" + "        t_asset_amc.amc_to_date,\n" + "        t_asset_amc.amc_amt,\n" + "        t_asset_amc.positive_remark,\n" + "        t_asset_amc.negative_remark,\n" + "        t_asset_amc.vendor_id,\n" + "        m_asset_vendor.comp_name,\n" + "        t_asset_amc.amc_status,\n" + "        dm_status_mst.status_text,\n" + "        t_asset_amc.ex_var1,\n" + "        t_asset_amc.ex_var2,\n" + "        m_assets.asset_status\n" + "    FROM\n" + "        t_asset_amc,\n" + "        m_asset_vendor,\n" + "        dm_status_mst,\n" + "        m_assets\n" + "    WHERE\n" + "        t_asset_amc.amc_status=dm_status_mst.status_value \n" + "        AND t_asset_amc.del_status=1 \n" + "        AND t_asset_amc.vendor_id=m_asset_vendor.vendor_id \n" + "        AND m_asset_vendor.del_status=1 \n" + "        AND m_assets.asset_id=t_asset_amc.asset_id\n" + "        AND t_asset_amc.asset_id=:assetId", nativeQuery = true)
public List<AMCInfo> getAssetAMCInfoByAssetId(int assetId)


}