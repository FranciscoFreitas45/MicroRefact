import com.ats.hrmgt.model.assets.AssetNotificatn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AssetNotificatnRepo extends JpaRepository<AssetNotificatn, Integer> {


@Query(value = "SELECT\n" + "    m_assets.asset_id,\n" + "    m_assets.asset_code,\n" + "    t_asset_trans.asset_trans_id,\n" + "    m_asset_category.asset_cat_id,\n" + "    m_asset_category.cat_name,\n" + "    m_assets.asset_name,\n" + "    t_asset_trans.use_to_date,\n" + "    t_asset_trans.use_from_date,\n" + "    m_asset_category.return_notifctn_days,\n" + "    DATEDIFF(\n" + "        t_asset_trans.use_to_date,\n" + "        (CURRENT_DATE)\n" + "    ) AS returtn_in_days,\n" + "    DATEDIFF(\n" + "        t_asset_trans.use_to_date,\n" + "        (\n" + "            CURRENT_DATE + m_asset_category.return_notifctn_days\n" + "        )\n" + "    ) AS alarm_days,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.surname,\n" + "    m_employees.mobile_no_1,\n" + "    m_employees.email_id,\n" + "    dep.name_sd AS dept_name,\n" + "    dg.name_sd AS emp_desgn\n" + "FROM\n" + "    m_assets,\n" + "    t_asset_trans,\n" + "    m_asset_category,\n" + "    m_employees\n" + "LEFT JOIN m_department dep ON\n" + "    m_employees.depart_id = dep.depart_id\n" + "LEFT JOIN m_designation dg ON\n" + "    m_employees.designation_id = dg.desig_id\n" + "WHERE\n" + "    m_assets.asset_id = t_asset_trans.asset_id AND t_asset_trans.use_to_date <=(\n" + "        CURRENT_DATE + m_asset_category.return_notifctn_days\n" + "    ) AND m_asset_category.asset_cat_id = m_assets.asset_cat_id AND t_asset_trans.asset_trans_status = 1 AND m_assets.asset_status = 1 AND m_assets.del_status = 1 AND t_asset_trans.del_status = 1 AND m_asset_category.del_status = 1 AND m_employees.del_status = 1 AND t_asset_trans.emp_id = m_employees.emp_id", nativeQuery = true)
public List<AssetNotificatn> getAssetDetailForNotification()


}