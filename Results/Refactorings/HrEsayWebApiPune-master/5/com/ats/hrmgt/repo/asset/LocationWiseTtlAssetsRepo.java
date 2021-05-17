import com.ats.hrmgt.model.assets.LocationWiseTtlAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface LocationWiseTtlAssetsRepo extends JpaRepository<LocationWiseTtlAssets, Integer> {


@Query(value = "SELECT\n" + "    UUID() AS id, \n" + "    m_location.loc_name, \n" + "    m_asset_category.cat_name, \n" + "    COUNT(m_assets.asset_id) AS asset_count,\n" + "    m_asset_category.asset_cat_id,\n" + "    m_location.loc_id\n" + "FROM\n" + "    m_assets,\n" + "    m_asset_category,\n" + "    m_location\n" + "WHERE\n" + "    m_asset_category.asset_cat_id = m_assets.asset_cat_id AND " + "m_asset_category.del_status = 1 AND m_assets.del_status = 1 AND " + "m_assets.asset_status IN(0, 1) AND m_location.loc_id = m_assets.loc_id AND " + "m_location.del_status = 1 AND m_location.is_active = 1 AND m_assets.loc_id=:locId\n" + "GROUP BY\n" + "    m_location.loc_id,\n" + "    m_asset_category.asset_cat_id\n" + "ORDER BY\n" + "    m_location.loc_name,\n" + "    m_asset_category.cat_name", nativeQuery = true)
public List<LocationWiseTtlAssets> getLocationWiseTtlAssetsReportByLocId(int locId)


@Query(value = "SELECT\n" + "    UUID() AS id, \n" + "    m_location.loc_name, \n" + "    m_asset_category.cat_name, \n" + "    COUNT(m_assets.asset_id) AS asset_count,\n" + "    m_asset_category.asset_cat_id,\n" + "    m_location.loc_id\n" + "FROM\n" + "    m_assets,\n" + "    m_asset_category,\n" + "    m_location\n" + "WHERE\n" + "    m_asset_category.asset_cat_id = m_assets.asset_cat_id AND " + "m_asset_category.del_status = 1 AND m_assets.del_status = 1 AND " + "m_assets.asset_status IN(0, 1) AND m_location.loc_id = m_assets.loc_id AND " + "m_location.del_status = 1 AND m_location.is_active = 1\n" + "GROUP BY\n" + "    m_location.loc_id,\n" + "    m_asset_category.asset_cat_id\n" + "ORDER BY\n" + "    m_location.loc_name,\n" + "    m_asset_category.cat_name", nativeQuery = true)
public List<LocationWiseTtlAssets> getLocationWiseTtlAssetsReport()


}