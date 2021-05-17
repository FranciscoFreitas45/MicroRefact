import com.ats.hrmgt.model.AssetServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AssetServiceDetailsRepo extends JpaRepository<AssetServiceDetails, Integer> {


@Query(value = "SELECT\n" + "    service.t_servicing_id,\n" + "    service.asset_id,\n" + "    service.service_type,\n" + "    service.service_date,\n" + "    service.next_service_date,\n" + "    service.service_desc,\n" + "    service.bill_amt,\n" + "    service.service_remark,\n" + "    vendor.comp_name AS service_vendor,\n" + "    service.ex_int1,\n" + "    service.ex_int2,\n" + "    service.ex_var1,\n" + "    service.ex_var2\n" + "FROM\n" + "    t_asset_servicing service,\n" + "    m_asset_vendor vendor\n" + "WHERE\n" + "    service.vendor_id=vendor.vendor_id AND\n" + "    service.del_status=1 AND\n" + "    service.asset_id=:assetId ORDER BY t_servicing_id DESC", nativeQuery = true)
public List<AssetServiceDetails> getAssetServiceList(int assetId)


}