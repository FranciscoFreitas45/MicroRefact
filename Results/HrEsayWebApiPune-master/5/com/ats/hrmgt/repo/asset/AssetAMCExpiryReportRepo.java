import com.ats.hrmgt.model.assets.AssetAMCExpiryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AssetAMCExpiryReportRepo extends JpaRepository<AssetAMCExpiryReport, Integer> {


@Query(value = "SELECT\n" + "	UUID() as id,\n" + "    m_assets.asset_id,\n" + "    m_assets.asset_code,\n" + "    m_assets.asset_name,\n" + "    m_asset_category.asset_cat_id,\n" + "    m_asset_category.cat_name,\n" + "    m_asset_vendor.comp_name,\n" + "    m_asset_vendor.contact_no1,\n" + "    m_asset_vendor.conatct_person_name,\n" + "    m_asset_vendor.contact_person_no,\n" + "    m_location.loc_name,\n" + "    t_asset_amc.amc_fr_date,\n" + "    t_asset_amc.amc_to_date,\n" + "    m_assets.ex_int1,\n" + "    m_assets.ex_int2,\n" + "    m_assets.ex_var1,\n" + "    m_assets.ex_var2\n" + "FROM\n" + "    m_asset_category,\n" + "    m_assets,\n" + "    t_asset_amc,\n" + "    m_asset_vendor,\n" + "    m_location\n" + "WHERE\n" + "    m_assets.asset_id = t_asset_amc.asset_id AND m_assets.asset_status IN(0, 1) AND " + "m_assets.asset_cat_id = m_asset_category.asset_cat_id AND m_assets.del_status = 1 AND " + "m_asset_category.del_status = 1 AND t_asset_amc.del_status = 1 AND m_asset_vendor.del_status = 1 " + "AND m_asset_vendor.vendor_id = t_asset_amc.vendor_id AND t_asset_amc.amc_status = 11 AND " + "t_asset_amc.amc_to_date <= CURRENT_DATE AND m_assets.loc_id = m_location.loc_id AND m_location.loc_id =:locId", nativeQuery = true)
public List<AssetAMCExpiryReport> getAllAssetAMCExpiryDetails(int locId)


}