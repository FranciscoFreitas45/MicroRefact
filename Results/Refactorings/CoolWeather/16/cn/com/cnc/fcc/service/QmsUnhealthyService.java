import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.QmsUnhealthy;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
@Service
public interface QmsUnhealthyService {


public Integer deleteNodeInfos(String id)


public List<QmsOrganizationInfoDTO> organListInfo()


public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String organizationCd,String organizationName)


public List<QmsUnhealthy> getCheckIsUser(String id)


}