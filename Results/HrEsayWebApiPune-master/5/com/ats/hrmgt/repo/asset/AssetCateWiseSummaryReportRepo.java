import com.ats.hrmgt.model.assets.AssetCateWiseSummaryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface AssetCateWiseSummaryReportRepo extends JpaRepository<AssetCateWiseSummaryReport, Integer> {


@Query(value = "SELECT\n" + "    m_asset_category.asset_cat_id,\n" + "    m_asset_category.cat_name,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_category.asset_cat_id = m_assets.asset_cat_id AND m_assets.asset_status = 0\n" + "    ),\n" + "    0\n" + "    ) AS un_assigned,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_category.asset_cat_id = m_assets.asset_cat_id AND m_assets.asset_status = 1\n" + "    ),\n" + "    0\n" + "    ) AS assigned,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_category.asset_cat_id = m_assets.asset_cat_id AND m_assets.asset_status = 3\n" + "    ),\n" + "    0\n" + "    ) AS lost,\n" + "    IFNULL(\n" + "        (\n" + "        SELECT\n" + "            COUNT(m_assets.asset_id)\n" + "        FROM\n" + "            m_assets\n" + "        WHERE\n" + "            m_assets.del_status = 1 AND m_asset_category.asset_cat_id = m_assets.asset_cat_id AND m_assets.asset_status = 4\n" + "    ),\n" + "    0\n" + "    ) AS scrapped\n" + "FROM\n" + "    m_asset_category\n" + "WHERE\n" + "    m_asset_category.del_status = 1\n" + "GROUP BY\n" + "    m_asset_category.asset_cat_id", nativeQuery = true)
public List<AssetCateWiseSummaryReport> getCateWiseAssetSummary()


}