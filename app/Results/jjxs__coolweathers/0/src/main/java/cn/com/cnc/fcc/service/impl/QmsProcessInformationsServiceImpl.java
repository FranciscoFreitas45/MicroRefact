package cn.com.cnc.fcc.service.impl;
 import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.domain.QmsEnclosure;
import cn.com.cnc.fcc.domain.QmsPartsAssemblyRelation;
import cn.com.cnc.fcc.domain.QmsProcess;
import cn.com.cnc.fcc.domain.QmsQualityControlDetails;
import cn.com.cnc.fcc.repository.QmsBomTechnologyRepository;
import cn.com.cnc.fcc.repository.QmsEnclosureRepository;
import cn.com.cnc.fcc.repository.QmsPartsAssemblyRelationRepository;
import cn.com.cnc.fcc.repository.QmsProcessRepository;
import cn.com.cnc.fcc.repository.QmsQualityControlDetailsRepository;
import cn.com.cnc.fcc.service.QmsProcessInformationsService;
import cn.com.cnc.fcc.service.dto.BomTechnologGroupDTO;
import cn.com.cnc.fcc.service.dto.DropDowmValueDTO;
import cn.com.cnc.fcc.service.dto.ProcessInfomationsRightListDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsPartsAssemblyRelationOwnerDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
@Service
public class QmsProcessInformationsServiceImpl implements QmsProcessInformationsService{

 private  Logger log;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;

 private  QmsBomTechnologyRepository qmsBomTechnologyRepository;

 private  QmsQualityControlDetailsRepository qmsQualityControlDetailsRepository;

 private  QmsPartsAssemblyRelationRepository qmsPartsAssemblyRelationRepository;

 private  QmsEnclosureRepository qmsEnclosureRepository;

 private  QmsProcessRepository qmsProcessRepository;

public QmsProcessInformationsServiceImpl(EntityManagerFactory emf, QmsBomTechnologyRepository qmsBomTechnologyRepository, QmsQualityControlDetailsRepository qmsQualityControlDetailsRepository, QmsPartsAssemblyRelationRepository qmsPartsAssemblyRelationRepository, QmsEnclosureRepository qmsEnclosureRepository, QmsProcessRepository qmsProcessRepository) {
    this.emf = emf;
    this.qmsBomTechnologyRepository = qmsBomTechnologyRepository;
    this.qmsQualityControlDetailsRepository = qmsQualityControlDetailsRepository;
    this.qmsPartsAssemblyRelationRepository = qmsPartsAssemblyRelationRepository;
    this.qmsEnclosureRepository = qmsEnclosureRepository;
    this.qmsProcessRepository = qmsProcessRepository;
}
@Override
@Transactional
public Integer updateInfo(BomTechnologGroupDTO bomTechnologGroupDTO){
    // 返回结果
    Integer backResult = 0;
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 取得当前时间
    ZonedDateTime nowTime = dateUtil.getDBNowDate();
    try {
        // 根据物料和工艺编码、工序号取值
        List<QmsBomTechnology> existenceCheckQBT = qmsBomTechnologyRepository.findByMaterielIdAndTechnologyCdAndOrderNoAndFlagStatusAndIdNot(bomTechnologGroupDTO.getQmsBomTechnology().getMaterielId(), bomTechnologGroupDTO.getQmsBomTechnology().getTechnologyCd(), bomTechnologGroupDTO.getQmsBomTechnology().getOrderNo(), "0", bomTechnologGroupDTO.getQmsBomTechnology().getId());
        // 判断物料和工艺编码、工序号是否已存在
        if (existenceCheckQBT.size() != 0) {
            // 返回结果 物料和工艺编码已存在
            backResult = 1;
            return backResult;
        }
        // 根据物料ID取得工艺信息
        List<QmsBomTechnology> existenceCheckIsDefault = qmsBomTechnologyRepository.findByMaterielIdAndIsDefaultAndFlagStatus(bomTechnologGroupDTO.getQmsBomTechnology().getMaterielId(), "1", "0");
        // 根据id取得信息
        QmsBomTechnology getqmsBomTechnology = qmsBomTechnologyRepository.findByIdAndFlagStatus(bomTechnologGroupDTO.getQmsBomTechnology().getId(), "0");
        // 判断该物料下是否存在数据
        if (existenceCheckIsDefault.size() == 0) {
            // 无数据默认工艺赋值1
            bomTechnologGroupDTO.getQmsBomTechnology().setIsDefault("1");
        } else {
            List<QmsBomTechnology> gyAndwl = qmsBomTechnologyRepository.findByMaterielIdAndTechnologyCdAndIsDefaultAndFlagStatus(bomTechnologGroupDTO.getQmsBomTechnology().getMaterielId(), bomTechnologGroupDTO.getQmsBomTechnology().getTechnologyCd(), "1", "0");
            if (gyAndwl.size() == 0) {
                // 有数据默认工艺赋值0
                bomTechnologGroupDTO.getQmsBomTechnology().setIsDefault("0");
            } else {
                // 有数据默认工艺赋值0
                bomTechnologGroupDTO.getQmsBomTechnology().setIsDefault("1");
            }
        }
        // 创建时间
        bomTechnologGroupDTO.getQmsBomTechnology().setMakeTime(getqmsBomTechnology.getMakeTime());
        // 创建人
        bomTechnologGroupDTO.getQmsBomTechnology().setMakeUser(getqmsBomTechnology.getMakeUser());
        // 更新时间
        bomTechnologGroupDTO.getQmsBomTechnology().setModifyTime(nowTime);
        // 更新人
        bomTechnologGroupDTO.getQmsBomTechnology().setModifyUser(user.getUsername());
        // 删除标志
        bomTechnologGroupDTO.getQmsBomTechnology().setFlagStatus("0");
        // 保存工艺表数据
        QmsBomTechnology insertBackInfo = qmsBomTechnologyRepository.save(bomTechnologGroupDTO.getQmsBomTechnology());
        // 循环赋值工序质量控制点详细的创建时间和更新时间
        for (int a = 0; a < bomTechnologGroupDTO.getQmsQualityControlDetails().size(); a++) {
            // 根据id取值
            if (null != bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).getId()) {
                QmsQualityControlDetails qmsQualityControlDetailsAdd = new QmsQualityControlDetails();
                // 根据id取值
                qmsQualityControlDetailsAdd = qmsQualityControlDetailsRepository.findByIdAndFlagStatus(bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).getId(), "0");
                // 工艺ID
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setBomTechnologyId(Integer.valueOf(bomTechnologGroupDTO.getQmsBomTechnology().getId().toString()));
                // 判断标准值是否有值 有1无0
                if (null != bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).getStandard()) {
                    bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setInspectionResultDiff("0");
                } else {
                    bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setInspectionResultDiff("1");
                }
                // 创建时间
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setMakeTime(qmsQualityControlDetailsAdd.getMakeTime());
                // 创建人
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setMakeUser(qmsQualityControlDetailsAdd.getMakeUser());
                // 更新时间
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setModifyTime(nowTime);
                // 更新人
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setModifyUser(user.getUsername());
                // 删除标志
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setFlagStatus("0");
                // 保存工序质量控制点详细
                qmsQualityControlDetailsRepository.save(bomTechnologGroupDTO.getQmsQualityControlDetails().get(a));
            } else {
                // 工艺ID
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setBomTechnologyId(Integer.valueOf(bomTechnologGroupDTO.getQmsBomTechnology().getId().toString()));
                // 判断标准值是否有值 有1无0
                if (null != bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).getStandard()) {
                    bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setInspectionResultDiff("0");
                } else {
                    bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setInspectionResultDiff("1");
                }
                // 创建时间
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setMakeTime(nowTime);
                // 创建人
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setMakeUser(user.getUsername());
                // 更新时间
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setModifyTime(nowTime);
                // 更新人
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setModifyUser(user.getUsername());
                // 删除标志
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setFlagStatus("0");
                // 保存工序质量控制点详细
                qmsQualityControlDetailsRepository.save(bomTechnologGroupDTO.getQmsQualityControlDetails().get(a));
            }
        }
        // 循环赋值装配关系的创建时间和更新时间
        for (int b = 0; b < bomTechnologGroupDTO.getQmsPartsAssemblyRelation().size(); b++) {
            if (null != bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getId()) {
                QmsPartsAssemblyRelation partsAssemblyRelationEdit = new QmsPartsAssemblyRelation();
                // 根据id取值
                partsAssemblyRelationEdit = qmsPartsAssemblyRelationRepository.findByIdAndFlagStatus(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getId(), "0");
                // 工艺ID
                partsAssemblyRelationEdit.setBomTechnologyId(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getBomTechnologyId());
                // 装配序号
                partsAssemblyRelationEdit.setAssemblyNum(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyNum());
                // 装配物料ID
                partsAssemblyRelationEdit.setAssemblyMaterielId(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyMaterielId());
                // 数量
                partsAssemblyRelationEdit.setAssemblyCount(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyCount());
                // 备注
                partsAssemblyRelationEdit.setRemark(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getRemark());
                // 工艺ID
                partsAssemblyRelationEdit.setBomTechnologyId(Integer.valueOf(bomTechnologGroupDTO.getQmsBomTechnology().getId().toString()));
                // 更新时间
                partsAssemblyRelationEdit.setModifyTime(nowTime);
                // 更新人
                partsAssemblyRelationEdit.setModifyUser(user.getUsername());
                qmsPartsAssemblyRelationRepository.save(partsAssemblyRelationEdit);
            } else {
                QmsPartsAssemblyRelation partsAssemblyRelationadd = new QmsPartsAssemblyRelation();
                // 工艺ID
                partsAssemblyRelationadd.setBomTechnologyId(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getBomTechnologyId());
                // 装配序号
                partsAssemblyRelationadd.setAssemblyNum(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyNum());
                // 装配物料ID
                partsAssemblyRelationadd.setAssemblyMaterielId(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyMaterielId());
                // 数量
                partsAssemblyRelationadd.setAssemblyCount(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyCount());
                // 备注
                partsAssemblyRelationadd.setRemark(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getRemark());
                // 工艺ID
                partsAssemblyRelationadd.setBomTechnologyId(Integer.valueOf(bomTechnologGroupDTO.getQmsBomTechnology().getId().toString()));
                // 创建时间
                partsAssemblyRelationadd.setMakeTime(nowTime);
                // 创建人
                partsAssemblyRelationadd.setMakeUser(user.getUsername());
                // 更新时间
                partsAssemblyRelationadd.setModifyTime(nowTime);
                // 更新人
                partsAssemblyRelationadd.setModifyUser(user.getUsername());
                // 删除标志
                partsAssemblyRelationadd.setFlagStatus("0");
                // 保存装配关系
                qmsPartsAssemblyRelationRepository.save(partsAssemblyRelationadd);
            }
        }
        // 插入成功返回工艺ID
        backResult = Integer.valueOf(insertBackInfo.getId().toString());
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        backResult = 2;
        return backResult;
    }
    return backResult;
}


@Override
public Boolean deleteEnclosure(Integer inspectionInfoId,String inspectionKbn,String enclosureAddress){
    try {
        qmsEnclosureRepository.deleteById(Long.valueOf(inspectionInfoId));
    } catch (Exception e) {
        System.out.println(e);
        return false;
    }
    // 返回值
    return true;
}


public String getSelectAllInfo(HashMap<String,Object> param){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT qbt.id as \"id\" , ");
    objSqlContent.append(" qbt.order_no as \"orderNo\" , ");
    objSqlContent.append(" qp.process_cd as \"processCd\",  ");
    objSqlContent.append(" qp.process_name as \"processName\"  ");
    objSqlContent.append(" FROM qms_bom_technology qbt ");
    objSqlContent.append(" LEFT JOIN qms_process qp ON qp.id = qbt.process_id AND qp.flag_status = 0");
    objSqlContent.append(" WHERE qbt.flag_status = 0 ");
    objSqlContent.append(" AND qbt.materiel_id = :materielCdVague ");
    objSqlContent.append(" AND qbt.technology_cd = :technologyCd ");
    objSqlContent.append(" ORDER BY qbt.order_no ASC ");
    objSqlContent.append(" LIMIT :start , :end ");
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsPartsAssemblyRelationOwnerDTO> getFlagStatusAndBomTechnologyId(String FlagStatus,Integer BomTechnologyId){
    // 实例化结果集
    List<QmsPartsAssemblyRelationOwnerDTO> QmsPartsAssemblyRelation = new ArrayList<QmsPartsAssemblyRelationOwnerDTO>();
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    try {
        // 查询
        Query query = em.createNativeQuery(getBomTechnologyIdAllInfo(FlagStatus, BomTechnologyId));
        ResultTransformer transformer = Transformers.aliasToBean(QmsPartsAssemblyRelationOwnerDTO.class);
        query.setParameter("BomTechnologyId", BomTechnologyId);
        query.setParameter("FlagStatus", FlagStatus);
        QmsPartsAssemblyRelation = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("bomTechnologyId", StandardBasicTypes.INTEGER).addScalar("assemblyNum", StandardBasicTypes.INTEGER).addScalar("assemblyMaterielId", StandardBasicTypes.INTEGER).addScalar("assemblyMaterielCd", StandardBasicTypes.STRING).addScalar("assemblyMaterielName", StandardBasicTypes.STRING).addScalar("assemblyCount", StandardBasicTypes.INTEGER).addScalar("remark", StandardBasicTypes.STRING).addScalar("flagStatus", StandardBasicTypes.STRING).addScalar("compPkid", StandardBasicTypes.STRING).addScalar("reserveFirst", StandardBasicTypes.STRING).addScalar("reserveSecond", StandardBasicTypes.STRING).addScalar("reserveThird", StandardBasicTypes.STRING).addScalar("makeUser", StandardBasicTypes.STRING).addScalar("modifyUser", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return QmsPartsAssemblyRelation;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsOrganizationInfoDTO> organListInfo(){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoDTO>();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectAllInfo());
        ResultTransformer transformer = Transformers.aliasToBean(QmsOrganizationInfoDTO.class);
        // 赋值给DTO
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).addScalar("vehicleType", StandardBasicTypes.STRING).addScalar("materielCd", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return qmsOrganizationInfoDTO;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<ProcessInfomationsRightListDTO> selectAllInfo(HashMap<String,Object> param){
    List<ProcessInfomationsRightListDTO> processInfomationsRightList = new ArrayList<ProcessInfomationsRightListDTO>();
    // 模糊查询物料编码
    String materielCdVague = param.get("materielCd").toString();
    // 模糊查询物料编码
    String technologyCd = param.get("technologyCd").toString();
    // 取得分页数
    Integer pageSize = (Integer.valueOf(param.get("sizeNumber").toString()) == 0 ? 0 : Integer.valueOf(param.get("sizeNumber").toString()));
    Integer pageNum = Integer.valueOf(param.get("pageNumber").toString());
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectAllInfo(param));
        ResultTransformer transformer = Transformers.aliasToBean(ProcessInfomationsRightListDTO.class);
        query.setParameter("materielCdVague", materielCdVague);
        query.setParameter("technologyCd", technologyCd);
        query.setParameter("start", (pageNum - 1) * pageSize);
        query.setParameter("end", pageSize);
        processInfomationsRightList = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("orderNo", StandardBasicTypes.STRING).addScalar("processCd", StandardBasicTypes.STRING).addScalar("processName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return processInfomationsRightList;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String materielID,String vehicleId){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoDTO>();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectParentNodeInfo(materielID, vehicleId));
        ResultTransformer transformer = Transformers.aliasToBean(QmsOrganizationInfoDTO.class);
        // 判断组织编码是否为空
        if (!"".equals(materielID)) {
            query.setParameter("materielID", "%" + materielID + "%");
        }
        // 判断组织名称是否为空
        if (!"".equals(vehicleId)) {
            query.setParameter("materielName", "%" + vehicleId + "%");
        }
        // 赋值给DTO
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).addScalar("vehicleType", StandardBasicTypes.STRING).addScalar("materielCd", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回值
    return qmsOrganizationInfoDTO;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<DropDowmValueDTO> getWorkTeamInfo(){
    EntityManager em = emf.createEntityManager();
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    try {
        Query query = em.createNativeQuery("SELECT qoi.organization_cd as \"value\" ,qoi.organization_name as \"label\" FROM qms_organization_info qoi where qoi.flag_status = '0' AND qoi.attribute ='4' ");
        ResultTransformer transformer = Transformers.aliasToBean(DropDowmValueDTO.class);
        dropDowmValueDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("label", StandardBasicTypes.STRING).addScalar("value", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回值
    return dropDowmValueDTO;
}


@Transactional
@Override
public Integer updateDefaultProcessInfo(Integer hiddenRightMaterielId,String technologyCd){
    Integer backInfo = 0;
    try {
        // 更新该物料的所有默认工艺为0
        Integer updateMaterielId = qmsBomTechnologyRepository.updatehiddenRightMaterielId(hiddenRightMaterielId);
        if (updateMaterielId == 0) {
            backInfo = 2;
            throw new RuntimeException("更新默认工艺失败");
        } else {
            backInfo = 1;
        }
        // 修改该物料和工艺编码下的所有物料1
        Integer updateMaterielIdAndtechnologyCd = qmsBomTechnologyRepository.updateMaterielIdTechnologyCd(hiddenRightMaterielId, technologyCd);
        if (updateMaterielIdAndtechnologyCd == 0) {
            backInfo = 3;
            throw new RuntimeException("更新默认工艺失败");
        } else {
            backInfo = 0;
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
    }
    // 返回值
    return backInfo;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<DropDowmValueDTO> getSubordinateUnitsInfo(){
    EntityManager em = emf.createEntityManager();
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    try {
        Query query = em.createNativeQuery("SELECT qoi.organization_cd as \"value\" ,qoi.organization_name as \"label\" FROM qms_organization_info qoi where qoi.flag_status = '0' AND qoi.attribute ='2' ");
        ResultTransformer transformer = Transformers.aliasToBean(DropDowmValueDTO.class);
        dropDowmValueDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("label", StandardBasicTypes.STRING).addScalar("value", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回值
    return dropDowmValueDTO;
}


public String getSelectParentNodeInfo(String materielID,String vehicleId){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT t01.id as \"id\" , ");
    objSqlContent.append(" t01.parent_materiel_id as \"parentCd\" , ");
    objSqlContent.append(" t01.materiel_id as \"organizationCd\" , ");
    objSqlContent.append(" t01.vehicle_id as \"vehicleType\" , ");
    objSqlContent.append(" qm.materiel_name as \"organizationName\",  ");
    objSqlContent.append(" qm.materiel_cd as \"materielCd\"  ");
    objSqlContent.append(" FROM qms_bom t01 ");
    objSqlContent.append(" left join  qms_materiel qm ON qm.id = t01.materiel_id");
    objSqlContent.append(" where 1=1 ");
    // 判断组织编码和组织名称是否都为空
    if ("".equals(materielID) && "".equals(vehicleId)) {
        objSqlContent.append(" and t01.parent_materiel_id = '0' or t01.parent_materiel_id = '' or t01.parent_materiel_id is null ");
    }
    // 判断组织编码是否为空
    if (!"".equals(materielID)) {
        objSqlContent.append(" and t01.materiel_id  like :materielID ");
    }
    // 判断组织名称是否为空
    if (!"".equals(vehicleId)) {
        objSqlContent.append(" and t01.vehicle_id like :vehicleId ");
    }
    // 判断组织编码和组织名称是否都为空
    if (!"".equals(materielID) || !"".equals(vehicleId)) {
        objSqlContent.append("and NOT FIND_IN_SET(t01.parent_materiel_id,(select  ");
        objSqlContent.append(" GROUP_CONCAT(t02.materiel_id)  ");
        objSqlContent.append(" FROM qms_bom t02  ");
        objSqlContent.append(" left join  qms_materiel qm ON qm.id = t02.materiel_id");
        objSqlContent.append(" WHERE 1=1  ");
        // 判断组织编码是否为空
        if (!"".equals(materielID)) {
            objSqlContent.append(" and t02.materiel_id  like :materielID ");
        }
        // 判断组织名称是否为空
        if (!"".equals(vehicleId)) {
            objSqlContent.append(" and t02.vehicle_id like :vehicleId ");
        }
        objSqlContent.append("  ))  ");
    }
    // 返回值
    return objSqlContent.toString();
}


public String getBomTechnologyIdAllInfo(String FlagStatus,Integer BomTechnologyId){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT qpar.id as \"id\" , ");
    objSqlContent.append(" qpar.bom_technology_id as \"bomTechnologyId\" , ");
    objSqlContent.append(" qpar.assembly_num as \"assemblyNum\" , ");
    objSqlContent.append(" qpar.assembly_materiel_id as \"assemblyMaterielId\" , ");
    objSqlContent.append(" qm.materiel_cd as \"assemblyMaterielCd\" , ");
    objSqlContent.append(" qm.materiel_name as \"assemblyMaterielName\" , ");
    objSqlContent.append(" qpar.assembly_count as \"assemblyCount\" , ");
    objSqlContent.append(" qpar.remark as \"remark\" , ");
    objSqlContent.append(" qpar.flag_status as \"flagStatus\" , ");
    objSqlContent.append(" qpar.comp_pkid as \"compPkid\" , ");
    objSqlContent.append(" qpar.reserve_first as \"reserveFirst\" , ");
    objSqlContent.append(" qpar.reserve_second as \"reserveSecond\" , ");
    objSqlContent.append(" qpar.reserve_third as \"reserveThird\" , ");
    objSqlContent.append(" qpar.make_user as \"makeUser\" , ");
    objSqlContent.append(" qpar.modify_user as \"modifyUser\" ");
    objSqlContent.append(" FROM qms_parts_assembly_relation qpar ");
    objSqlContent.append(" LEFT JOIN qms_materiel qm ON qm.id = qpar.assembly_materiel_id and qm.flag_status = 0 ");
    objSqlContent.append(" WHERE 1=1");
    objSqlContent.append(" AND qpar.bom_technology_id = :BomTechnologyId ");
    objSqlContent.append(" AND qpar.flag_status = :FlagStatus ");
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<ProcessInfomationsRightListDTO> getAllInfoNumber(HashMap<String,Object> param){
    List<ProcessInfomationsRightListDTO> processInfomationsRightList = new ArrayList<ProcessInfomationsRightListDTO>();
    // 模糊查询物料编码
    String materielCdVague = param.get("materielCd").toString();
    // 模糊查询物料编码
    String technologyCd = param.get("technologyCd").toString();
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectNumberAllInfo(param));
        ResultTransformer transformer = Transformers.aliasToBean(ProcessInfomationsRightListDTO.class);
        query.setParameter("materielCdVague", materielCdVague);
        query.setParameter("technologyCd", technologyCd);
        processInfomationsRightList = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("numberCount", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return processInfomationsRightList;
}


public String getSelectNumberAllInfo(HashMap<String,Object> param){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT count(qbt.id) as \"numberCount\" ");
    objSqlContent.append(" FROM qms_bom_technology qbt ");
    objSqlContent.append(" LEFT JOIN qms_process qp ON qp.id = qbt.process_id AND qp.flag_status = 0");
    objSqlContent.append(" WHERE qbt.flag_status = 0 ");
    objSqlContent.append(" AND qbt.materiel_id = :materielCdVague ");
    objSqlContent.append(" AND qbt.technology_cd = :technologyCd ");
    objSqlContent.append(" ORDER BY qbt.order_no DESC ");
    // 返回值
    return objSqlContent.toString();
}


@Override
@Transactional
public HashMap<String,Object> createCopyProcessInfo(Integer hiddenRightMaterielId,String technologyCd,String technologyName,String copyTechnologyCd){
    HashMap<String, Object> backResult = new HashMap<String, Object>();
    // 实例化复制工艺表接受集合
    List<QmsBomTechnology> copyQmsBomTechnology = new ArrayList<QmsBomTechnology>();
    // 复制工序质量控制点详细表数据获取
    List<QmsQualityControlDetails> copyQmsQualityControlDetailsList = new ArrayList<QmsQualityControlDetails>();
    // 复制工序装配关系表数据获取
    List<QmsPartsAssemblyRelation> copyQmsPartsAssemblyRelation = new ArrayList<QmsPartsAssemblyRelation>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 取得当前时间
    ZonedDateTime nowTime = dateUtil.getDBNowDate();
    String backString = "";
    String backStringOne = "";
    String backStringOrderNo = "";
    try {
        // 取出要复制的工艺表信息
        copyQmsBomTechnology = qmsBomTechnologyRepository.findByMaterielIdAndTechnologyCdAndFlagStatus(hiddenRightMaterielId, copyTechnologyCd, "0");
        // 判断是否获取到数据
        if (copyQmsBomTechnology.size() != 0) {
            for (int o = 0; o < copyQmsBomTechnology.size(); o++) {
                // 重新赋值
                // 新工艺数据保存返回结果
                QmsBomTechnology saveBackQmsBomTechnology = new QmsBomTechnology();
                // 物料id
                saveBackQmsBomTechnology.setMaterielId(copyQmsBomTechnology.get(o).getMaterielId());
                // 工艺编码
                saveBackQmsBomTechnology.setTechnologyCd(technologyCd);
                // 工艺名称
                saveBackQmsBomTechnology.setTechnologyName(technologyName);
                // 序号
                saveBackQmsBomTechnology.setOrderNo(copyQmsBomTechnology.get(o).getOrderNo());
                // 前工序id
                saveBackQmsBomTechnology.setBeforeProcessId(copyQmsBomTechnology.get(o).getBeforeProcessId());
                // 工序id
                saveBackQmsBomTechnology.setProcessId(copyQmsBomTechnology.get(o).getProcessId());
                // 隶属单位
                saveBackQmsBomTechnology.setOrganizationCd(copyQmsBomTechnology.get(o).getOrganizationCd());
                // 调度角色
                saveBackQmsBomTechnology.setSchedulerRole(copyQmsBomTechnology.get(o).getSchedulerRole());
                // 工时定额单位
                saveBackQmsBomTechnology.setWorkUnit(copyQmsBomTechnology.get(o).getWorkUnit());
                // 工时定额
                saveBackQmsBomTechnology.setWorkHours(copyQmsBomTechnology.get(o).getWorkHours());
                // 质检类型
                saveBackQmsBomTechnology.setQcType(copyQmsBomTechnology.get(o).getQcType());
                // 专检质检角色
                saveBackQmsBomTechnology.setSpecialRole(copyQmsBomTechnology.get(o).getSpecialRole());
                // 工序特证
                saveBackQmsBomTechnology.setOperationType(copyQmsBomTechnology.get(o).getOperationType());
                // 互检质检角色
                saveBackQmsBomTechnology.setMutualinRole(copyQmsBomTechnology.get(o).getMutualinRole());
                // 是否产生编号标识
                saveBackQmsBomTechnology.setIsNewCd(copyQmsBomTechnology.get(o).getIsNewCd());
                // 产生编号物料ID
                saveBackQmsBomTechnology.setNewCdMaterielId(copyQmsBomTechnology.get(o).getNewCdMaterielId());
                // 作业班组
                saveBackQmsBomTechnology.setWorkGroupCd(copyQmsBomTechnology.get(o).getWorkGroupCd());
                // 是否检验工序
                saveBackQmsBomTechnology.setIsControlPoint(copyQmsBomTechnology.get(o).getIsControlPoint());
                // 是否试验工序
                saveBackQmsBomTechnology.setIsTest(copyQmsBomTechnology.get(o).getIsTest());
                // 默认工艺
                saveBackQmsBomTechnology.setIsDefault("0");
                // 备注
                saveBackQmsBomTechnology.setRemark(copyQmsBomTechnology.get(o).getRemark());
                // 删除标识
                saveBackQmsBomTechnology.setFlagStatus("0");
                saveBackQmsBomTechnology.setMakeTime(nowTime);
                saveBackQmsBomTechnology.setMakeUser(user.getUsername());
                saveBackQmsBomTechnology.setModifyTime(nowTime);
                saveBackQmsBomTechnology.setModifyUser(user.getUsername());
                // 复制工艺数据保存
                saveBackQmsBomTechnology = qmsBomTechnologyRepository.save(saveBackQmsBomTechnology);
                // 根据复制工艺id取得工序质量种植点详细表信息
                copyQmsQualityControlDetailsList = qmsQualityControlDetailsRepository.findByBomTechnologyIdAndFlagStatus(Integer.valueOf(copyQmsBomTechnology.get(o).getId().toString()), "0");
                // 循环新增复制工序质量控制点详细表信息
                for (int c = 0; c < copyQmsQualityControlDetailsList.size(); c++) {
                    // 复制工序质量控制点详细表数据获取
                    QmsQualityControlDetails saveQmsQualityControlDetails = new QmsQualityControlDetails();
                    saveQmsQualityControlDetails.setBomTechnologyId(Integer.valueOf(saveBackQmsBomTechnology.getId().toString()));
                    saveQmsQualityControlDetails.setInspectionItem(copyQmsQualityControlDetailsList.get(c).getInspectionItem());
                    saveQmsQualityControlDetails.setTechnicalRequirement(copyQmsQualityControlDetailsList.get(c).getTechnicalRequirement());
                    saveQmsQualityControlDetails.setInspectionInstrument(copyQmsQualityControlDetailsList.get(c).getInspectionInstrument());
                    saveQmsQualityControlDetails.setPlaceDiff(copyQmsQualityControlDetailsList.get(c).getPlaceDiff());
                    saveQmsQualityControlDetails.setStandard(copyQmsQualityControlDetailsList.get(c).getStandard());
                    saveQmsQualityControlDetails.setUpperDeviation(copyQmsQualityControlDetailsList.get(c).getUpperDeviation());
                    saveQmsQualityControlDetails.setLowerDeviation(copyQmsQualityControlDetailsList.get(c).getLowerDeviation());
                    saveQmsQualityControlDetails.setInspectionResultDiff(copyQmsQualityControlDetailsList.get(c).getInspectionResultDiff());
                    saveQmsQualityControlDetails.setIsCheckObj(copyQmsQualityControlDetailsList.get(c).getIsCheckObj());
                    saveQmsQualityControlDetails.setAbcType(copyQmsQualityControlDetailsList.get(c).getAbcType());
                    saveQmsQualityControlDetails.setRemark(copyQmsQualityControlDetailsList.get(c).getRemark());
                    saveQmsQualityControlDetails.setMakeTime(nowTime);
                    saveQmsQualityControlDetails.setMakeUser(user.getUsername());
                    saveQmsQualityControlDetails.setModifyTime(nowTime);
                    saveQmsQualityControlDetails.setModifyUser(user.getUsername());
                    saveQmsQualityControlDetails.setFlagStatus("0");
                    saveQmsQualityControlDetails = qmsQualityControlDetailsRepository.save(saveQmsQualityControlDetails);
                    log.info("复制工序质量控制点详细表数据插入" + saveQmsQualityControlDetails);
                }
                // 根据复制工艺id取得工序装配关系表数据
                copyQmsPartsAssemblyRelation = qmsPartsAssemblyRelationRepository.findAllByFlagStatusAndBomTechnologyId("0", Integer.valueOf(copyQmsBomTechnology.get(o).getId().toString()));
                // 循环插入
                for (int a = 0; a < copyQmsPartsAssemblyRelation.size(); a++) {
                    QmsPartsAssemblyRelation saveQmsPartsAssemblyRelation = new QmsPartsAssemblyRelation();
                    saveQmsPartsAssemblyRelation.setBomTechnologyId(Integer.valueOf(saveBackQmsBomTechnology.getId().toString()));
                    saveQmsPartsAssemblyRelation.setAssemblyNum(copyQmsPartsAssemblyRelation.get(a).getAssemblyNum());
                    saveQmsPartsAssemblyRelation.setAssemblyMaterielId(copyQmsPartsAssemblyRelation.get(a).getAssemblyMaterielId());
                    saveQmsPartsAssemblyRelation.setAssemblyCount(copyQmsPartsAssemblyRelation.get(a).getAssemblyCount());
                    saveQmsPartsAssemblyRelation.setRemark(copyQmsPartsAssemblyRelation.get(a).getRemark());
                    saveQmsPartsAssemblyRelation.setFlagStatus("0");
                    saveQmsPartsAssemblyRelation.setMakeTime(nowTime);
                    saveQmsPartsAssemblyRelation.setMakeUser(user.getUsername());
                    saveQmsPartsAssemblyRelation.setModifyTime(nowTime);
                    saveQmsPartsAssemblyRelation.setModifyUser(user.getUsername());
                    saveQmsPartsAssemblyRelation = qmsPartsAssemblyRelationRepository.save(saveQmsPartsAssemblyRelation);
                    log.info("根据复制工艺id取得工序装配关系表插入" + saveQmsPartsAssemblyRelation);
                }
                QmsProcess qmsprocessCd = new QmsProcess();
                qmsprocessCd = qmsProcessRepository.findByFlagStatusAndId("0", Long.valueOf(copyQmsBomTechnology.get(o).getProcessId()));
                if (o == 0) {
                    backString = saveBackQmsBomTechnology.getId().toString();
                    backStringOne = copyQmsBomTechnology.get(o).getId().toString();
                    backStringOrderNo = qmsprocessCd.getProcessCd();
                } else {
                    backString = backString + "," + saveBackQmsBomTechnology.getId().toString();
                    backStringOne = backStringOne + "," + copyQmsBomTechnology.get(o).getId().toString();
                    backStringOrderNo = backStringOrderNo + "," + qmsprocessCd.getProcessCd();
                }
            }
        }
        backResult.put("success", backString);
        backResult.put("successOne", backStringOne);
        backResult.put("backStringOrderNo", backStringOrderNo);
    } catch (Exception e) {
        backResult.put("error", "更细失败");
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    // 返回值
    return backResult;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<DropDowmValueDTO> getCarTypeInfo(){
    EntityManager em = emf.createEntityManager();
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    try {
        Query query = em.createNativeQuery("SELECT r.role_code as \"value\" ,r.role_name as \"label\" FROM rbac_role r where r.del_flag = 0");
        ResultTransformer transformer = Transformers.aliasToBean(DropDowmValueDTO.class);
        dropDowmValueDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("label", StandardBasicTypes.STRING).addScalar("value", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回值
    return dropDowmValueDTO;
}


@SuppressWarnings({ "deprecation", "unchecked" })
@Override
public List<DropDowmValueDTO> getAllTechnology(String masterCd){
    EntityManager em = emf.createEntityManager();
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    try {
        Query query = em.createNativeQuery("SELECT DISTINCT qbt.technology_cd as \"value\" ,qbt.technology_name as \"label\"  FROM qms_bom_technology  qbt WHERE qbt.materiel_id = '" + masterCd + "' AND qbt.flag_status = '0' ORDER BY qbt.is_default desc  ");
        ResultTransformer transformer = Transformers.aliasToBean(DropDowmValueDTO.class);
        dropDowmValueDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("label", StandardBasicTypes.STRING).addScalar("value", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回值
    return dropDowmValueDTO;
}


@Transactional
@Override
public Integer createInfo(BomTechnologGroupDTO bomTechnologGroupDTO){
    // 返回结果
    Integer backResult = 0;
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO);
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsBomTechnology());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsEnclosure());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsPartsAssemblyRelation());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsQualityControlDetails());
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 取得当前时间
    ZonedDateTime nowTime = dateUtil.getDBNowDate();
    try {
        // 根据物料和工艺编码取值
        List<QmsBomTechnology> existenceCheckQBT = qmsBomTechnologyRepository.findByMaterielIdAndTechnologyCdAndOrderNoAndFlagStatus(bomTechnologGroupDTO.getQmsBomTechnology().getMaterielId(), bomTechnologGroupDTO.getQmsBomTechnology().getTechnologyCd(), bomTechnologGroupDTO.getQmsBomTechnology().getOrderNo(), "0");
        // 判断物料和工艺编码是否已存在
        if (existenceCheckQBT.size() != 0) {
            // 返回结果 物料和工艺编码已存在
            backResult = 1;
            return backResult;
        }
        // 根据物料ID取得工艺信息
        List<QmsBomTechnology> existenceCheckIsDefault = qmsBomTechnologyRepository.findByMaterielIdAndIsDefaultAndFlagStatus(bomTechnologGroupDTO.getQmsBomTechnology().getMaterielId(), "1", "0");
        // 判断该物料下是否存在数据
        if (existenceCheckIsDefault.size() == 0) {
            // 无数据默认工艺赋值1
            bomTechnologGroupDTO.getQmsBomTechnology().setIsDefault("1");
        } else {
            List<QmsBomTechnology> gyAndwl = qmsBomTechnologyRepository.findByMaterielIdAndTechnologyCdAndIsDefaultAndFlagStatus(bomTechnologGroupDTO.getQmsBomTechnology().getMaterielId(), bomTechnologGroupDTO.getQmsBomTechnology().getTechnologyCd(), "1", "0");
            if (gyAndwl.size() == 0) {
                // 有数据默认工艺赋值0
                bomTechnologGroupDTO.getQmsBomTechnology().setIsDefault("0");
            } else {
                // 有数据默认工艺赋值0
                bomTechnologGroupDTO.getQmsBomTechnology().setIsDefault("1");
            }
        }
        // 创建时间
        bomTechnologGroupDTO.getQmsBomTechnology().setMakeTime(nowTime);
        // 创建人
        bomTechnologGroupDTO.getQmsBomTechnology().setMakeUser(user.getUsername());
        // 更新时间
        bomTechnologGroupDTO.getQmsBomTechnology().setModifyTime(nowTime);
        // 更新人
        bomTechnologGroupDTO.getQmsBomTechnology().setModifyUser(user.getUsername());
        // 删除标志
        bomTechnologGroupDTO.getQmsBomTechnology().setFlagStatus("0");
        // 保存工艺表数据
        QmsBomTechnology insertBackInfo = qmsBomTechnologyRepository.save(bomTechnologGroupDTO.getQmsBomTechnology());
        // 循环赋值工序质量控制点详细的创建时间和更新时间
        for (int a = 0; a < bomTechnologGroupDTO.getQmsQualityControlDetails().size(); a++) {
            // 判断标准值是否有值 有1无0
            if (null != bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).getStandard()) {
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setInspectionResultDiff("0");
            } else {
                bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setInspectionResultDiff("1");
            }
            // 工艺ID
            bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setBomTechnologyId(Integer.valueOf(insertBackInfo.getId().toString()));
            // 创建时间
            bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setMakeTime(nowTime);
            // 创建人
            bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setMakeUser(user.getUsername());
            // 更新时间
            bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setModifyTime(nowTime);
            // 更新人
            bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setModifyUser(user.getUsername());
            // 删除标志
            bomTechnologGroupDTO.getQmsQualityControlDetails().get(a).setFlagStatus("0");
        }
        // 保存工序质量控制点详细
        qmsQualityControlDetailsRepository.saveAll(bomTechnologGroupDTO.getQmsQualityControlDetails());
        List<QmsPartsAssemblyRelation> qmsPartsAssemblyRelationList = new ArrayList<QmsPartsAssemblyRelation>();
        // 循环赋值装配关系的创建时间和更新时间
        for (int b = 0; b < bomTechnologGroupDTO.getQmsPartsAssemblyRelation().size(); b++) {
            QmsPartsAssemblyRelation partsAssemblyRelation = new QmsPartsAssemblyRelation();
            // 工艺ID
            partsAssemblyRelation.setBomTechnologyId(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getBomTechnologyId());
            // 装配序号
            partsAssemblyRelation.setAssemblyNum(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyNum());
            // 装配物料ID
            partsAssemblyRelation.setAssemblyMaterielId(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyMaterielId());
            // 数量
            partsAssemblyRelation.setAssemblyCount(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getAssemblyCount());
            // 备注
            partsAssemblyRelation.setRemark(bomTechnologGroupDTO.getQmsPartsAssemblyRelation().get(b).getRemark());
            // 工艺ID
            partsAssemblyRelation.setBomTechnologyId(Integer.valueOf(insertBackInfo.getId().toString()));
            // 创建时间
            partsAssemblyRelation.setMakeTime(nowTime);
            // 创建人
            partsAssemblyRelation.setMakeUser(user.getUsername());
            // 更新时间
            partsAssemblyRelation.setModifyTime(nowTime);
            // 更新人
            partsAssemblyRelation.setModifyUser(user.getUsername());
            // 删除标志
            partsAssemblyRelation.setFlagStatus("0");
            qmsPartsAssemblyRelationList.add(partsAssemblyRelation);
        }
        // 保存装配关系
        qmsPartsAssemblyRelationRepository.saveAll(qmsPartsAssemblyRelationList);
        // // 循环赋值附件表的创建时间和更新时间
        // for(int c = 0;c<bomTechnologGroupDTO.getQmsEnclosure().size();c++){
        // 
        // // 创建时间
        // bomTechnologGroupDTO.getQmsEnclosure().get(c).setMakeTime(nowTime);
        // // 创建人
        // bomTechnologGroupDTO.getQmsEnclosure().get(c).setMakeUser(user.getUsername());
        // // 更新时间
        // bomTechnologGroupDTO.getQmsEnclosure().get(c).setModifyTime(nowTime);
        // // 更新人
        // bomTechnologGroupDTO.getQmsEnclosure().get(c).setModifyUser(user.getUsername());
        // }
        // // 附件表赋值
        // qmsEnclosureRepository.saveAll(bomTechnologGroupDTO.getQmsEnclosure());
        // 插入成功返回工艺ID
        backResult = Integer.valueOf(insertBackInfo.getId().toString());
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        // 异常信息
        log.info(e.getMessage());
        // 返回结果 数据异常
        backResult = 0;
        return backResult;
    }
    // 返回值
    return backResult;
}


@Override
@Transactional
public Boolean uploadEnclosure(Integer inspectionInfoId,String inspectionKbn,String enclosureAddress){
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 取得当前时间
    ZonedDateTime nowTime = dateUtil.getDBNowDate();
    QmsEnclosure qmsEnclosure = new QmsEnclosure();
    try {
        qmsEnclosure.setEnclosureAddress(enclosureAddress);
        qmsEnclosure.setInspectionInfoId(inspectionInfoId);
        qmsEnclosure.setInspectionKbn(inspectionKbn);
        qmsEnclosure.setMakeTime(nowTime);
        qmsEnclosure.setMakeUser(user.getUsername());
        qmsEnclosure.setModifyTime(nowTime);
        qmsEnclosure.setModifyUser(user.getUsername());
        qmsEnclosure = qmsEnclosureRepository.save(qmsEnclosure);
        // 判断是否插入成功
        if (null == qmsEnclosure) {
            return false;
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        // 异常信息
        log.info(e.getMessage());
    }
    return true;
}


}