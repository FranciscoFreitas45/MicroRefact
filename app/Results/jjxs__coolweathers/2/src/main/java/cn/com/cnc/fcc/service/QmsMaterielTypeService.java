package cn.com.cnc.fcc.service;
 import java.util.List;
import cn.com.cnc.fcc.domain.materialTypeSelectionDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
@Service
public interface QmsMaterielTypeService {


public List<materialTypeSelectionDto> qmsMaterialTypeFindAll(String bianMa,String gongName)
;

public Integer deleteNodeInfos(String id)
;

public List<QmsOrganizationInfoDTO> organListInfo()
;

public JSONObject uploadUserDepart(MultipartFile files)
;

public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String materielTypeCd,String materielTypeName)
;

}