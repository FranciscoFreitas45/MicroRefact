import cn.com.cnc.fcc.domain.HstServer;
import cn.com.cnc.fcc.domain.HstServerInfo;
import cn.com.cnc.fcc.domain.PapiToken;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface HstServerInfoRepository extends JpaRepository<HstServerInfo, Long> {


@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
public Long getHostInfoMaxId()


@Query(value = "select new cn.com.cnc.fcc.domain.HstServer(hsi, hsid) from HstServerInfo as hsi, HstServerInfoDetails as hsid " + "where hsi.id = hsid.infoId and hsi.delFlag = 0 and hsi.stopFlag = 0 and " + "hsid.stopFlag = 0 and hsid.delFlag = 0 and hsi.hostSlaveFlag = :hostSlaveFlag and hsi.nodeId = :nodeId")
public List<HstServer> findByNodeId(String nodeId,int hostSlaveFlag)


@Query(value = "select new cn.com.cnc.fcc.domain.HstServer(hsi, hsid) from HstServerInfo as hsi, HstServerInfoDetails as hsid " + "where hsi.id = hsid.infoId and hsi.delFlag = 0 and hsi.stopFlag = 0 and " + "hsid.stopFlag = 0 and hsid.delFlag = 0 and hsi.hostSlaveFlag = :hostSlaveFlag")
public List<HstServer> findHostServer(int hostSlaveFlag)


}