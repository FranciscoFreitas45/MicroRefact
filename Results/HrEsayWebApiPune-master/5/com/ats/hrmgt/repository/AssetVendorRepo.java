import com.ats.hrmgt.model.AssetVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface AssetVendorRepo extends JpaRepository<AssetVendor, Integer> {


public AssetVendor findByVendorId(int assetVendorId)


@Query(value = "SELECT\n" + "    COUNT(vendor_id)\n" + "FROM\n" + "    `m_asset_vendor`\n" + "WHERE\n" + "    del_status = 1 AND vendor_id !=:vendorId AND vendor_email =:vendorEmail", nativeQuery = true)
public int getVendorDataByEmail(int vendorId,String vendorEmail)


@Query(value = "SELECT\n" + "    COUNT(vendor_id)\n" + "FROM\n" + "    `m_asset_vendor`\n" + "WHERE\n" + "    del_status = 1 AND vendor_id !=:vendorId AND comp_name =:compName", nativeQuery = true)
public int getVendorDataByCompName(int vendorId,String compName)


@Transactional
@Modifying
@Query(value = "UPDATE m_asset_vendor SET del_status=0 WHERE vendor_id=:assetVendorId", nativeQuery = true)
public int deleteAssetVendor(int assetVendorId)


public AssetVendor findByVendorEmailAndDelStatus(String vendorEmail,int i)


public List<AssetVendor> findByDelStatusOrderByVendorIdDesc(int del)


public AssetVendor findByCompNameAndVendorEmail(String compName,String vendorEmail)


public AssetVendor findByCompNameAndDelStatus(String compName,int i)


}