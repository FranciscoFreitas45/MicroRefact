import cn.com.cnc.fcc.domain.QmsOrganizationInfo;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository;
import org.springframework.stereotype.Repository;
@SuppressWarnings("unused")
@Repository
public interface QmsOrganizationInfoRepository extends JpaRepository<QmsOrganizationInfo, Long> {


public List<QmsOrganizationInfo> findByOrganizationCdAndFlagStatus(String organizationCd,String flagStatus)


}