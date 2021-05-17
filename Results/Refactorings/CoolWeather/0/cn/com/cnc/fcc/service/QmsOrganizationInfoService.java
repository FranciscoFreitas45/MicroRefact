import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
@Service
public interface QmsOrganizationInfoService {


public Integer deleteNodeInfos(String id)


public List<QmsOrganizationInfoDTO> organListInfo()


public JSONObject uploadUserDepart(MultipartFile files)


public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String organizationCd,String organizationName)


}