import com.ats.hrmgt.model.assets.CatWiseAssetDistributn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface CatWiseAssetDistributnRepo extends JpaRepository<CatWiseAssetDistributn, Integer> {


@Query(value = "SELECT\n" + "    b.asset_cat_id,\n" + "    b.asset_count,\n" + "    b.asset_status,\n" + "    m_asset_category.cat_name,\n" + "    dm_status_mst.status_text\n" + "FROM\n" + "    (\n" + "    SELECT\n" + "        m_assets.asset_cat_id,\n" + "        COUNT(m_assets.asset_id) AS asset_count,\n" + "        m_assets.asset_status\n" + "    FROM\n" + "        m_assets\n" + "    WHERE\n" + "        m_assets.del_status = 1\n" + "    GROUP BY\n" + "        m_assets.asset_status,\n" + "        m_assets.asset_cat_id\n" + ") b,\n" + "m_asset_category,\n" + "dm_status_mst\n" + "WHERE\n" + "    b.asset_cat_id = m_asset_category.asset_cat_id AND b.asset_status = dm_status_mst.status_value\n" + "ORDER BY\n" + "    b.asset_cat_id", nativeQuery = true)
public List<CatWiseAssetDistributn> getCatWiseAssetDistributn()


}