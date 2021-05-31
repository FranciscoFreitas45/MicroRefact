import com.ats.hrmgt.model.assets.AssetsDashDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface AssetsDashRepo extends JpaRepository<AssetsDashDetails, Integer> {


@Query(value = "SELECT\n" + "	 UUID() as id,\n" + "    assets.asset_id,\n" + "    assets.asset_code,\n" + "    assets.asset_name,\n" + "    assets.asset_make,\n" + "    assets.asset_model,\n" + "    assets.asset_srno,\n" + "    assets.asset_pur_date,\n" + "    assets.asset_pur_image,\n" + "    loc.loc_name,\n" + "    vendor.comp_name,\n" + "    cat.cat_name,\n" + "    assets.ex_int1,\n" + "    assets.ex_int2,\n" + "    assets.ex_var1,\n" + "    assets.ex_var2\n" + "FROM\n" + "     m_assets assets\n" + "     LEFT JOIN m_location loc ON loc.loc_id=assets.loc_id\n" + "     LEFT JOIN m_asset_vendor vendor ON assets.vendor_id=vendor.vendor_id\n" + "     LEFT JOIN m_asset_category cat ON assets.asset_cat_id=cat.asset_cat_id\n" + "    \n" + "WHERE\n" + "    assets.asset_pur_date BETWEEN :fromDate AND :toDate AND\n" + "    assets.del_status=1 AND\n" + "    assets.asset_status !=3 AND assets.asset_status !=4 AND\n" + "    vendor.vendor_id=:vendorIds\n" + "ORDER BY assets.asset_pur_date DESC, assets.asset_cat_id DESC", nativeQuery = true)
public List<AssetsDashDetails> getAssetsDashDetailsByVendorId(int vendorIds,String fromDate,String toDate)


@Query(value = "SELECT\n" + "	UUID() as id,\n" + "    assets.asset_id,\n" + "    assets.asset_code,\n" + "    assets.asset_name,\n" + "    assets.asset_make,\n" + "    assets.asset_model,\n" + "    assets.asset_srno,\n" + "    assets.asset_pur_date,\n" + "    assets.asset_pur_image,\n" + "    loc.loc_name,\n" + "    vendor.comp_name,\n" + "    cat.cat_name,\n" + "    assets.ex_int1,\n" + "    assets.ex_int2,\n" + "    assets.ex_var1,\n" + "    assets.ex_var2\n" + "    \n" + "FROM\n" + "     m_assets assets\n" + "     LEFT JOIN m_location loc ON loc.loc_id=assets.loc_id\n" + "     LEFT JOIN m_asset_vendor vendor ON assets.vendor_id=vendor.vendor_id\n" + "     LEFT JOIN m_asset_category cat ON assets.asset_cat_id=cat.asset_cat_id\n" + "    \n" + "WHERE\n" + "    assets.asset_pur_date BETWEEN :fromDate AND :toDate AND\n" + "    assets.del_status=1 AND\n" + "    assets.asset_status !=3 AND assets.asset_status !=4\n" + "ORDER BY assets.asset_pur_date DESC, assets.asset_cat_id DESC", nativeQuery = true)
public List<AssetsDashDetails> getAllAssetsDashDetailsBetweenDates(String fromDate,String toDate)


@Query(value = "SELECT\n" + "	UUID() as id,\n" + "    assets.asset_id,\n" + "    assets.asset_code,\n" + "    assets.asset_name,\n" + "    assets.asset_make,\n" + "    assets.asset_model,\n" + "    assets.asset_srno,\n" + "    assets.asset_pur_date,\n" + "    assets.asset_pur_image,\n" + "    loc.loc_name,\n" + "    vendor.comp_name,\n" + "    cat.cat_name,\n" + "    assets.ex_int1,\n" + "    assets.ex_int2,\n" + "    assets.ex_var1,\n" + "    assets.ex_var2\n" + "FROM\n" + "     m_assets assets\n" + "     LEFT JOIN m_location loc ON loc.loc_id=assets.loc_id\n" + "     LEFT JOIN m_asset_vendor vendor ON assets.vendor_id=vendor.vendor_id\n" + "     LEFT JOIN m_asset_category cat ON assets.asset_cat_id=cat.asset_cat_id\n" + "    \n" + "WHERE\n" + "    assets.asset_pur_date BETWEEN :fromDate AND :toDate AND\n" + "    assets.del_status=1 AND\n" + "    assets.asset_status !=3 AND assets.asset_status !=4 AND\n" + "    loc.loc_id=:locId\n" + "ORDER BY assets.asset_pur_date DESC, assets.asset_cat_id DESC", nativeQuery = true)
public List<AssetsDashDetails> getAllAssetsDashDetails(int locId,String fromDate,String toDate)


}