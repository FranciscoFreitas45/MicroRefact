import com.ats.hrmgt.model.AssignedAssetsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AssignedAssetsListRepo extends JpaRepository<AssignedAssetsList, Integer> {


@Query(value = "SELECT\n" + "   tassets.asset_trans_id,\n" + "   assets.asset_id,\n" + "   assets.asset_code,\n" + "   assets.asset_name,\n" + "   cat.cat_name,\n" + "   tassets.use_from_date,\n" + "   tassets.use_to_date,\n" + "   tassets.assign_remark,\n" + "   tassets.assign_img_file,\n" + "   emp.emp_id\n" + "    \n" + "FROM\n" + "    t_asset_trans tassets, \n" + "    m_assets assets,\n" + "    m_asset_category cat,\n" + "    m_employees emp\n" + "WHERE\n" + "    assets.asset_cat_id=cat.asset_cat_id AND\n" + "    assets.asset_id=tassets.asset_id AND\n" + "    tassets.del_status=1 AND tassets.asset_trans_status=1 AND\n" + "    emp.emp_id=tassets.emp_id AND\n" + "    emp.emp_id=:empId", nativeQuery = true)
public List<AssignedAssetsList> getAllAssignedAssetsToEmp(int empId)


}