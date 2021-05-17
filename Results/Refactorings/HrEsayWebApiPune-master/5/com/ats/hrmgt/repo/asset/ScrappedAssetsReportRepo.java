import com.ats.hrmgt.model.assets.ScrappedAssetsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ScrappedAssetsReportRepo extends JpaRepository<ScrappedAssetsReport, Integer> {


@Query(value = "SELECT\n" + "    m_assets.asset_id,\n" + "    m_assets.asset_code,\n" + "    m_assets.asset_name,\n" + "    m_assets.asset_pur_date,\n" + "    m_asset_category.cat_name,\n" + "    m_assets.scrap_date,\n" + "    m_assets.scrap_remark,\n" + "    m_assets.scrap_authoriy_details,\n" + "    m_assets.scrap_datetime,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.surname,\n" + "    dep.name_sd AS dept_name,\n" + "    dg.name_sd AS emp_desgn,\n" + "    m_location.loc_name\n" + "FROM\n" + "    m_assets,\n" + "    m_location,\n" + "    m_user,\n" + "    m_asset_category,\n" + "    m_employees\n" + "LEFT JOIN m_department dep ON\n" + "    m_employees.depart_id = dep.depart_id\n" + "LEFT JOIN m_designation dg ON\n" + "    m_employees.designation_id = dg.desig_id\n" + "WHERE\n" + "    m_asset_category.del_status = 1 AND m_asset_category.asset_cat_id = m_assets.asset_cat_id AND " + "m_assets.scrap_login_userid = m_user.user_id AND m_employees.emp_id=m_user.emp_id AND m_assets.del_status = 1 AND " + "m_assets.asset_status = 4 AND m_location.loc_id=m_assets.loc_id", nativeQuery = true)
public List<ScrappedAssetsReport> getScrappedAllAssetsReport()


@Query(value = "SELECT\n" + "    m_assets.asset_id,\n" + "    m_assets.asset_code,\n" + "    m_assets.asset_name,\n" + "    m_assets.asset_pur_date,\n" + "    m_asset_category.cat_name,\n" + "    m_assets.scrap_date,\n" + "    m_assets.scrap_remark,\n" + "    m_assets.scrap_authoriy_details,\n" + "    m_assets.scrap_datetime,\n" + "    m_employees.emp_code,\n" + "    m_employees.first_name,\n" + "    m_employees.surname,\n" + "    dep.name_sd AS dept_name,\n" + "    dg.name_sd AS emp_desgn,\n" + "    m_location.loc_name\n" + "FROM\n" + "    m_assets,\n" + "    m_location,\n" + "    m_asset_category,\n" + "    m_user,\n" + "    m_employees\n" + "LEFT JOIN m_department dep ON\n" + "    m_employees.depart_id = dep.depart_id\n" + "LEFT JOIN m_designation dg ON\n" + "    m_employees.designation_id = dg.desig_id\n" + "WHERE\n" + "    m_asset_category.del_status = 1 AND m_asset_category.asset_cat_id = m_assets.asset_cat_id AND" + " m_assets.scrap_login_userid = m_user.user_id AND m_employees.emp_id=m_user.emp_id AND m_assets.del_status = 1 AND " + "m_assets.asset_status = 4 AND m_location.loc_id=m_assets.loc_id AND m_assets.loc_id=:locId", nativeQuery = true)
public List<ScrappedAssetsReport> getScrappedAllAssetsReportByLocId(int locId)


}