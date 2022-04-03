package cn.com.cnc.fcc.service;
 import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.QmsDefect;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
@Service
public interface QmsDefectService {


public Integer deleteNodeInfos(String id)
;

public List<QmsOrganizationInfoDTO> organListInfo()
;

public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String defectCd,String defectName)
;

public List<QmsDefect> getCheckIsUser(String defectCd)
;

}