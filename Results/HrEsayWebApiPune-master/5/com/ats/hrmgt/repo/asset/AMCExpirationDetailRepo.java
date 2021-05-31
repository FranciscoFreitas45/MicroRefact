import com.ats.hrmgt.model.assets.AMCExpirationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AMCExpirationDetailRepo extends JpaRepository<AMCExpirationDetail, Integer> {


@Query(value = "SELECT\n" + "    t_asset_amc.amc_id,\n" + "    t_asset_amc.asset_id,\n" + "    t_asset_amc.amc_fr_date,\n" + "    t_asset_amc.amc_to_date,\n" + "    t_asset_amc.amc_amt,\n" + "    m_assets.asset_code,\n" + "    m_assets.asset_name,\n" + "    m_asset_category.cat_name,\n" + "    m_asset_vendor.comp_name,\n" + "    m_asset_vendor.contact_no1,\n" + "    m_asset_vendor.conatct_person_name,\n" + "    m_asset_vendor.contact_person_no,\n" + "    m_asset_vendor.contact_person_email,\n" + "    DATEDIFF(\n" + "        t_asset_amc.amc_to_date,\n" + "        CURRENT_DATE\n" + "    ) AS amc_due_days,\n" + "    DATEDIFF(\n" + "        t_asset_amc.amc_to_date,\n" + "        (\n" + "            CURRENT_DATE + m_asset_category.amc_notifctn_days\n" + "        )\n" + "    ) AS alarm_days\n" + "FROM\n" + "    t_asset_amc,\n" + "    m_assets,\n" + "    m_asset_vendor,\n" + "    m_asset_category\n" + "WHERE\n" + "    t_asset_amc.asset_id = m_assets.asset_id AND t_asset_amc.vendor_id = m_asset_vendor.vendor_id AND m_assets.asset_cat_id = m_asset_category.asset_cat_id AND m_assets.asset_status IN(0, 1) AND m_assets.del_status = 1 AND m_asset_vendor.del_status = 1 AND m_asset_category.del_status = 1 AND t_asset_amc.del_status = 1 AND t_asset_amc.amc_to_date <=(\n" + "        CURRENT_DATE + m_asset_category.amc_notifctn_days\n" + "    ) AND t_asset_amc.amc_status = 11", nativeQuery = true)
public List<AMCExpirationDetail> getAMCExpirationDetailForNotificatn()


}