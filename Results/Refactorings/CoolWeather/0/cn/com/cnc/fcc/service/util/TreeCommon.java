import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoLeftDTO;
@Service
public class TreeCommon {


public List<QmsOrganizationInfoLeftDTO> getChildrenInfoBom(String id,String VehicleType,List<QmsOrganizationInfoDTO> organizationList){
    // 实例化返回参数集合
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 循环当前集合
    for (QmsOrganizationInfoDTO organization : organizationList) {
        // 判断是否与父级ID相等
        if (id.equals(organization.getParentCd()) && VehicleType.equals(organization.getVehicleType())) {
            QmsOrganizationInfoLeftDTO QmsOrganizationInfoLeftDTO = new QmsOrganizationInfoLeftDTO();
            // 赋值子集信息
            QmsOrganizationInfoLeftDTO.setChildren(getChildrenInfoBom(organization.getOrganizationCd(), organization.getVehicleType(), organizationList));
            // 赋值当前级信息
            QmsOrganizationInfoDTO QmsOrganizationInfoAllDTO = new QmsOrganizationInfoDTO();
            QmsOrganizationInfoAllDTO.setOrganizationCd(organization.getOrganizationCd());
            QmsOrganizationInfoAllDTO.setOrganizationName(organization.getOrganizationName());
            QmsOrganizationInfoAllDTO.setMaterielCd(organization.getMaterielCd());
            QmsOrganizationInfoAllDTO.setMaterielName(organization.getMaterielName());
            QmsOrganizationInfoAllDTO.setParentCd(organization.getParentCd());
            QmsOrganizationInfoAllDTO.setParentMaterielCd(organization.getParentMaterielCd());
            QmsOrganizationInfoAllDTO.setVehicleType(organization.getVehicleType());
            QmsOrganizationInfoAllDTO.setRootMaterielCd(organization.getRootMaterielCd());
            QmsOrganizationInfoAllDTO.setRootMaterielName(organization.getRootMaterielName());
            QmsOrganizationInfoAllDTO.setId(organization.getId());
            QmsOrganizationInfoLeftDTO.setData(QmsOrganizationInfoAllDTO);
            // 最后样式赋值
            qmsOrganizationInfoDTOBack.add(QmsOrganizationInfoLeftDTO);
        }
    }
    // qmsOrganizationInfoDTOBack.sort(new NodeOrderComparator());
    return qmsOrganizationInfoDTOBack;
}


public List<QmsOrganizationInfoLeftDTO> getChildrenInfo(String id,List<QmsOrganizationInfoDTO> organizationList){
    // 实例化返回参数集合
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 循环当前集合
    for (QmsOrganizationInfoDTO organization : organizationList) {
        // 判断是否与父级ID相等
        if (id.equals(organization.getParentCd())) {
            QmsOrganizationInfoLeftDTO QmsOrganizationInfoLeftDTO = new QmsOrganizationInfoLeftDTO();
            // 赋值子集信息
            QmsOrganizationInfoLeftDTO.setChildren(getChildrenInfo(organization.getOrganizationCd(), organizationList));
            // 赋值当前级信息
            QmsOrganizationInfoDTO QmsOrganizationInfoAllDTO = new QmsOrganizationInfoDTO();
            QmsOrganizationInfoAllDTO.setOrganizationCd(organization.getOrganizationCd());
            QmsOrganizationInfoAllDTO.setOrganizationName(organization.getOrganizationName());
            QmsOrganizationInfoAllDTO.setMaterielCd(organization.getMaterielCd());
            QmsOrganizationInfoAllDTO.setMaterielName(organization.getMaterielName());
            QmsOrganizationInfoAllDTO.setParentCd(organization.getParentCd());
            QmsOrganizationInfoAllDTO.setParentMaterielCd(organization.getParentMaterielCd());
            QmsOrganizationInfoAllDTO.setVehicleType(organization.getVehicleType());
            QmsOrganizationInfoAllDTO.setRootMaterielCd(organization.getRootMaterielCd());
            QmsOrganizationInfoAllDTO.setRootMaterielName(organization.getRootMaterielName());
            QmsOrganizationInfoAllDTO.setId(organization.getId());
            QmsOrganizationInfoLeftDTO.setData(QmsOrganizationInfoAllDTO);
            // 最后样式赋值
            qmsOrganizationInfoDTOBack.add(QmsOrganizationInfoLeftDTO);
        }
    }
    // qmsOrganizationInfoDTOBack.sort(new NodeOrderComparator());
    return qmsOrganizationInfoDTOBack;
}


public List<QmsOrganizationInfoLeftDTO> TreeStructureUtilBom(List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOList,List<QmsOrganizationInfoDTO> parentNodeList){
    // 最终返回
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 循环父级
    for (QmsOrganizationInfoDTO organization : parentNodeList) {
        // 判断父级
        // if("".equals(organization.getParentCd()) || organization.getParentCd() == null || "0".equals(organization.getParentCd())){
        // 实例化参数接受子集
        QmsOrganizationInfoLeftDTO QmsOrganizationInfoLeftDTO = new QmsOrganizationInfoLeftDTO();
        // 赋值子集信息
        QmsOrganizationInfoLeftDTO.setChildren(getChildrenInfoBom(organization.getOrganizationCd(), organization.getVehicleType(), qmsOrganizationInfoDTOList));
        // 赋值父级信息
        QmsOrganizationInfoDTO QmsOrganizationInfoAllDTO = new QmsOrganizationInfoDTO();
        QmsOrganizationInfoAllDTO.setOrganizationCd(organization.getOrganizationCd());
        QmsOrganizationInfoAllDTO.setOrganizationName(organization.getOrganizationName());
        QmsOrganizationInfoAllDTO.setMaterielCd(organization.getMaterielCd());
        QmsOrganizationInfoAllDTO.setMaterielName(organization.getMaterielName());
        QmsOrganizationInfoAllDTO.setParentCd(organization.getParentCd());
        QmsOrganizationInfoAllDTO.setParentMaterielCd(organization.getParentMaterielCd());
        QmsOrganizationInfoAllDTO.setVehicleType(organization.getVehicleType());
        QmsOrganizationInfoAllDTO.setRootMaterielCd(organization.getRootMaterielCd());
        QmsOrganizationInfoAllDTO.setRootMaterielName(organization.getRootMaterielName());
        QmsOrganizationInfoAllDTO.setId(organization.getId());
        QmsOrganizationInfoLeftDTO.setData(QmsOrganizationInfoAllDTO);
        // 最后样式赋值
        qmsOrganizationInfoDTOBack.add(QmsOrganizationInfoLeftDTO);
    // }
    }
    // qmsOrganizationInfoDTOBack.sort(new NodeOrderComparator());
    return qmsOrganizationInfoDTOBack;
}


public List<QmsOrganizationInfoLeftDTO> TreeStructureUtil(List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOList,List<QmsOrganizationInfoDTO> parentNodeList){
    // 最终返回
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 循环父级
    for (QmsOrganizationInfoDTO organization : parentNodeList) {
        // 判断父级
        // if("".equals(organization.getParentCd()) || organization.getParentCd() == null || "0".equals(organization.getParentCd())){
        // 实例化参数接受子集
        QmsOrganizationInfoLeftDTO QmsOrganizationInfoLeftDTO = new QmsOrganizationInfoLeftDTO();
        // 赋值子集信息
        QmsOrganizationInfoLeftDTO.setChildren(getChildrenInfo(organization.getOrganizationCd(), qmsOrganizationInfoDTOList));
        // 赋值父级信息
        QmsOrganizationInfoDTO QmsOrganizationInfoAllDTO = new QmsOrganizationInfoDTO();
        QmsOrganizationInfoAllDTO.setOrganizationCd(organization.getOrganizationCd());
        QmsOrganizationInfoAllDTO.setOrganizationName(organization.getOrganizationName());
        QmsOrganizationInfoAllDTO.setMaterielCd(organization.getMaterielCd());
        QmsOrganizationInfoAllDTO.setMaterielName(organization.getMaterielName());
        QmsOrganizationInfoAllDTO.setParentCd(organization.getParentCd());
        QmsOrganizationInfoAllDTO.setParentMaterielCd(organization.getParentMaterielCd());
        QmsOrganizationInfoAllDTO.setVehicleType(organization.getVehicleType());
        QmsOrganizationInfoAllDTO.setRootMaterielCd(organization.getRootMaterielCd());
        QmsOrganizationInfoAllDTO.setRootMaterielName(organization.getRootMaterielName());
        QmsOrganizationInfoAllDTO.setId(organization.getId());
        QmsOrganizationInfoLeftDTO.setData(QmsOrganizationInfoAllDTO);
        // 最后样式赋值
        qmsOrganizationInfoDTOBack.add(QmsOrganizationInfoLeftDTO);
    // }
    }
    // qmsOrganizationInfoDTOBack.sort(new NodeOrderComparator());
    return qmsOrganizationInfoDTOBack;
}


}