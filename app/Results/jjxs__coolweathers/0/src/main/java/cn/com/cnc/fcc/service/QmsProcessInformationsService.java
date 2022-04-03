package cn.com.cnc.fcc.service;
 import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.service.dto.BomTechnologGroupDTO;
import cn.com.cnc.fcc.service.dto.DropDowmValueDTO;
import cn.com.cnc.fcc.service.dto.ProcessInfomationsRightListDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsPartsAssemblyRelationOwnerDTO;
@Service
public interface QmsProcessInformationsService {


public Integer updateInfo(BomTechnologGroupDTO bomTechnologGroupDTO)
;

public Boolean deleteEnclosure(Integer inspectionInfoId,String inspectionKbn,String enclosureAddress)
;

public List<QmsPartsAssemblyRelationOwnerDTO> getFlagStatusAndBomTechnologyId(String FlagStatus,Integer BomTechnologyId)
;

public List<QmsOrganizationInfoDTO> organListInfo()
;

public List<ProcessInfomationsRightListDTO> selectAllInfo(HashMap<String,Object> param)
;

public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String materielCd,String materielName)
;

public List<DropDowmValueDTO> getWorkTeamInfo()
;

public Integer updateDefaultProcessInfo(Integer hiddenRightMaterielId,String technologyCd)
;

public List<DropDowmValueDTO> getSubordinateUnitsInfo()
;

public List<ProcessInfomationsRightListDTO> getAllInfoNumber(HashMap<String,Object> param)
;

public HashMap<String,Object> createCopyProcessInfo(Integer hiddenRightMaterielId,String technologyCd,String technologyName,String copyTechnologyCd)
;

public List<DropDowmValueDTO> getCarTypeInfo()
;

public List<DropDowmValueDTO> getAllTechnology(String masterCd)
;

public Integer createInfo(BomTechnologGroupDTO bomTechnologGroupDTO)
;

public Boolean uploadEnclosure(Integer inspectionInfoId,String inspectionKbn,String enclosureAddress)
;

}