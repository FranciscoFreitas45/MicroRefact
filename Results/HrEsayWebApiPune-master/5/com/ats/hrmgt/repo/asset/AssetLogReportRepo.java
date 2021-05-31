import com.ats.hrmgt.model.assets.AssetLogReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AssetLogReportRepo extends JpaRepository<AssetLogReport, Integer> {


@Query(value = "SELECT\n" + "    t_asset_log.asset_log_id,\n" + "    t_asset_log.asset_id,\n" + "    t_asset_log.asset_trans_id,\n" + "    t_asset_log.asset_log_desc,\n" + "    t_asset_log.asset_log_date,\n" + "    t_asset_log.update_date_time,\n" + "    t_asset_log.maker_user_id,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.surname,\n" + "    dep.name_sd AS dept_name,\n" + "    dg.name_sd AS emp_desgn,\n" + "    m_assets.asset_code,\n" + "    m_assets.asset_name\n" + "FROM\n" + "    t_asset_log,\n" + "    m_assets,\n" + "    m_user,\n" + "    m_employees\n" + "LEFT JOIN m_department dep ON\n" + "    m_employees.depart_id = dep.depart_id\n" + "LEFT JOIN m_designation dg ON\n" + "    m_employees.designation_id = dg.desig_id\n" + "WHERE\n" + "    m_user.user_id = t_asset_log.maker_user_id AND m_user.emp_id = m_employees.emp_id AND\n" + "    m_assets.asset_id=t_asset_log.asset_id AND t_asset_log.asset_id=:assetId", nativeQuery = true)
public List<AssetLogReport> getAssetLogReportByAssetId(int assetId)


@Query(value = "SELECT\n" + "    t_asset_log.asset_log_id,\n" + "    t_asset_log.asset_id,\n" + "    t_asset_log.asset_trans_id,\n" + "    t_asset_log.asset_log_desc,\n" + "    t_asset_log.asset_log_date,\n" + "    t_asset_log.update_date_time,\n" + "    t_asset_log.maker_user_id,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.surname,\n" + "    dep.name_sd AS dept_name,\n" + "    dg.name_sd AS emp_desgn,\n" + "    m_assets.asset_code,\n" + "    m_assets.asset_name\n" + "FROM\n" + "    t_asset_log,\n" + "    m_assets,\n" + "    m_user,\n" + "    m_employees\n" + "LEFT JOIN m_department dep ON\n" + "    m_employees.depart_id = dep.depart_id\n" + "LEFT JOIN m_designation dg ON\n" + "    m_employees.designation_id = dg.desig_id\n" + "WHERE\n" + "    m_user.user_id = t_asset_log.maker_user_id AND m_user.emp_id = m_employees.emp_id AND\n" + "    m_assets.asset_id=t_asset_log.asset_id", nativeQuery = true)
public List<AssetLogReport> getAssetLogReport()


}