import com.ats.hrmgt.model.AssetTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface AssetTransRepo extends JpaRepository<AssetTrans, Integer> {


@Transactional
@Modifying
@Query(value = "UPDATE\n" + "    t_asset_trans\n" + "SET\n" + "    is_lost = :status,\n" + "    asset_trans_status = :assetStatus,\n" + "    lost_remark =:lostAssetRemark,\n" + "    maker_user_id =:userUpdateId,\n" + "    update_datetime =:updateTime\n" + "WHERE\n" + "    asset_trans_id =:transactnId", nativeQuery = true)
public int updateStatusAssetsLost(int transactnId,int status,int userUpdateId,String updateTime,int assetStatus,String lostAssetRemark)


public List<AssetTrans> findByDelStatusOrderByAssetTransIdDesc(int del)


@Query(value = "SELECT * FROM t_asset_trans WHERE t_asset_trans.asset_id=:assetId and t_asset_trans.asset_trans_status=1 and t_asset_trans.del_status=1 AND is_lost=0", nativeQuery = true)
public AssetTrans findByAssetTransId(int assetId)


@Transactional
@Modifying
@Query(value = "UPDATE t_asset_trans SET return_date=:returnDate, asset_trans_status=:assetTransStatus, return_remark=:returnRemark," + " return_img_file=:assetReturnImg, update_datetime=:updateDateTime, maker_user_id=:updateUserId  WHERE asset_trans_id=:assetTransId", nativeQuery = true)
public int updateAssetsStatus(int assetTransId,int assetTransStatus,String returnDate,String returnRemark,String assetReturnImg,String updateDateTime,int updateUserId)


public AssetTrans findByAssetTransIdAndDelStatus(int assetTransId,int del)


@Query(value = "SELECT assign_img_file FROM t_asset_trans WHERE emp_id=:empId AND asset_id=:assetId AND del_status=1 AND asset_trans_status=1", nativeQuery = true)
public String getImgByEmpId(int assetId,int empId)


}