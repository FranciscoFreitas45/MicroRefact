import com.ats.hrmgt.model.assets.AssetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AssetInfoRepo extends JpaRepository<AssetInfo, Integer> {


@Query(value = " SELECT m_assets.asset_id,m_assets.asset_code,m_assets.asset_name," + " m_assets.asset_desc,m_assets.asset_cat_id,m_assets.asset_make," + " m_assets.asset_model, m_assets.asset_srno,m_assets.asset_pur_date," + " m_assets.vendor_id,m_assets.asset_remark, " + " m_assets.loc_id,m_assets.ex_var1,m_assets.ex_var2,  " + " m_asset_category.cat_name,m_location.loc_name,m_asset_vendor.comp_name, " + " m_asset_vendor.contact_no1,m_asset_vendor.conatct_person_name, " + " m_asset_vendor.contact_person_no,m_asset_vendor.contact_person_email  " + " FROM m_assets,m_asset_category,m_asset_vendor, m_location " + " WHERE m_assets.vendor_id=m_asset_vendor.vendor_id AND " + " m_asset_vendor.del_status=1 and m_assets.del_status=1 and " + " m_assets.loc_id=m_location.loc_id and m_location.del_status=1 and " + " m_assets.asset_cat_id=m_asset_category.asset_cat_id and " + " m_asset_category.del_status=1 AND  " + "m_assets.asset_status IN (:statusList) and m_location.loc_id=:locId " + "", nativeQuery = true)
public List<AssetInfo> getUnAssignedAssetByLocIdAndStatus(int locId,List<String> statusList)


}