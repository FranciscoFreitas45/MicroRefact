import com.ats.hrmgt.model.assets.VendorWiseTtlAssetsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface VendorWiseTtlAssetsReportRepo extends JpaRepository<VendorWiseTtlAssetsReport, Integer> {


@Query(value = "SELECT\n" + "    m_asset_vendor.vendor_id,\n" + "    m_asset_vendor.comp_name,\n" + "    m_asset_vendor.contact_no1,\n" + "    m_asset_vendor.conatct_person_name,\n" + "    m_asset_vendor.contact_person_no,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_vendor.vendor_id = m_assets.vendor_id AND m_assets.asset_status = 0\n" + "    ),\n" + "    0\n" + "    ) AS un_assigned,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_vendor.vendor_id = m_assets.vendor_id AND m_assets.asset_status = 1\n" + "    ),\n" + "    0\n" + "    ) AS assigned,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_vendor.vendor_id = m_assets.vendor_id AND m_assets.asset_status = 3\n" + "    ),\n" + "    0\n" + "    ) AS lost,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_vendor.vendor_id = m_assets.vendor_id AND m_assets.asset_status = 4\n" + "    ),\n" + "    0\n" + "    ) AS scrapped\n" + "FROM\n" + "    m_asset_vendor\n" + "WHERE\n" + "    m_asset_vendor.del_status = 1\n" + "GROUP BY\n" + "    m_asset_vendor.vendor_id\n" + "ORDER BY\n" + "    m_asset_vendor.comp_name", nativeQuery = true)
public List<VendorWiseTtlAssetsReport> getVendorWiseReport()


}