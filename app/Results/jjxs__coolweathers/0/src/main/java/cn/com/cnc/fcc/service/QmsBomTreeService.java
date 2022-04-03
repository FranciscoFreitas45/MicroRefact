package cn.com.cnc.fcc.service;
 import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import cn.com.cnc.fcc.service.dto.DropDowmValueDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
@Service
public interface QmsBomTreeService {


public Integer deleteNodeInfos(String id,String hidVehicleType)
;

public List<QmsOrganizationInfoDTO> organListInfo()
;

public JSONObject uploadUserDepart(MultipartFile files)
;

public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String materielCd,String materielName)
;

public List<DropDowmValueDTO> getCarTypeInfo()
;

}