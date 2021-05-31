import com.ats.hrmgt.model.assets.CatWiseAssetCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface CatWiseAssetCountRepo extends JpaRepository<CatWiseAssetCount, Integer> {


@Query(value = "SELECT\n" + "    b.asset_cat_id,\n" + "    b.asset_count,\n" + "    m_asset_category.cat_name\n" + "FROM\n" + "    (\n" + "    SELECT\n" + "        m_assets.asset_cat_id,\n" + "        COUNT(m_assets.asset_id) AS asset_count\n" + "    FROM\n" + "        m_assets\n" + "    WHERE\n" + "        m_assets.del_status = 1 AND m_assets.asset_status IN(0, 1)\n" + "    GROUP BY\n" + "        m_assets.asset_cat_id\n" + ") b,\n" + "m_asset_category\n" + "WHERE\n" + "    b.asset_cat_id = m_asset_category.asset_cat_id\n" + "ORDER BY\n" + "    m_asset_category.cat_name ASC", nativeQuery = true)
public List<CatWiseAssetCount> getCatWiseAssetCount()


}