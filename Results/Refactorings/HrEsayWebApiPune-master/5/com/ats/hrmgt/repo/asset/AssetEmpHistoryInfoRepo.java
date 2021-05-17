import com.ats.hrmgt.model.assets.AssetEmpHistoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AssetEmpHistoryInfoRepo extends JpaRepository<AssetEmpHistoryInfo, Integer> {


@Query(value = "SELECT\n" + "     t_asset_trans.asset_trans_id,\n" + "     t_asset_trans.emp_id,\n" + "     t_asset_trans.asset_id,\n" + "     t_asset_trans.use_from_date,\n" + "     t_asset_trans.use_to_date,\n" + "     t_asset_trans.assign_remark,\n" + "     t_asset_trans.return_remark,\n" + "     t_asset_trans.assign_img_file,\n" + "     t_asset_trans.return_img_file,\n" + "     m_employees.emp_code,\n" + "     m_employees.first_name,\n" + "     m_employees.surname,\n" + "     dep.name_sd AS dept_name,\n" + "     dg.name_sd AS emp_desgn,\n" + "     m_assets.asset_code,\n" + "     m_assets.asset_name,\n" + "     loc.loc_name\n" + " FROM\n" + "     t_asset_trans,\n" + "     m_assets,\n" + "     m_employees\n" + " LEFT JOIN m_department dep ON\n" + "     m_employees.depart_id = dep.depart_id\n" + " LEFT JOIN m_designation dg ON\n" + "     m_employees.designation_id = dg.desig_id\n" + " LEFT JOIN m_location loc ON\n" + "     m_employees.location_id = loc.loc_id\n" + " WHERE\n" + "     m_employees.del_status = 1  AND t_asset_trans.del_status = 1 AND t_asset_trans.emp_id=m_employees.emp_id \n" + "               AND t_asset_trans.asset_id = m_assets.asset_id AND t_asset_trans.is_lost = 0 AND t_asset_trans.asset_trans_status = 2 AND t_asset_trans.asset_id=:assetId", nativeQuery = true)
public List<AssetEmpHistoryInfo> getAssetAssignedToEmp(int assetId)


}