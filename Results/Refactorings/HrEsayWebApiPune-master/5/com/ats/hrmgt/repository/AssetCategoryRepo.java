import com.ats.hrmgt.model.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface AssetCategoryRepo extends JpaRepository<AssetCategory, Integer> {


@Query(value = "SELECT COUNT(asset_cat_id) FROM `m_asset_category` WHERE del_status=1 AND asset_cat_id!=:assetCatId AND cat_name=:assetCat", nativeQuery = true)
public int findAssetCat(int assetCatId,String assetCat)


public AssetCategory findByCatNameAndDelStatus(String assetCat,int del)


public List<AssetCategory> findByDelStatusOrderByAssetCatIdDesc(int del)


@Transactional
@Modifying
@Query(value = "UPDATE m_asset_category SET del_status=0 WHERE asset_cat_id=:assetCatId", nativeQuery = true)
public int deleteAssetCatId(int assetCatId)


public AssetCategory findByAssetCatId(int assetCatId)


}