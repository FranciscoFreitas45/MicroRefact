package cn.com.cnc.fcc.service.impl;
 import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.com.cnc.fcc.domain.QmsBom;
import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
import cn.com.cnc.fcc.repository.QmsBomRepository;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.repository.QmsVehicleTypeInfoRepository;
import cn.com.cnc.fcc.service.QmsBomTreeService;
import cn.com.cnc.fcc.service.dto.DropDowmValueDTO;
import cn.com.cnc.fcc.service.dto.GroupBOMInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
@Service
public class QmsBomTreeServiceImpl implements QmsBomTreeService{

 private  Logger log;

@Resource
 private  DateUtil dateUtil;

 private  QmsBomRepository qmsBomRepository;

 private  EntityManagerFactory emf;

 private  QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository;

 private  QmsMaterielRepository qmsMaterielRepository;

public QmsBomTreeServiceImpl(EntityManagerFactory emf, QmsBomRepository qmsBomRepository, QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository, QmsMaterielRepository qmsMaterielRepository) {
    this.qmsBomRepository = qmsBomRepository;
    this.emf = emf;
    this.qmsVehicleTypeInfoRepository = qmsVehicleTypeInfoRepository;
    this.qmsMaterielRepository = qmsMaterielRepository;
}
public String getSelectParentNodeInfo(String materielCd,String materielName){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT t01.id as \"id\" , ");
    objSqlContent.append(" t01.parent_materiel_id as \"parentCd\" , ");
    objSqlContent.append(" t01.vehicle_id as \"vehicleType\" , ");
    objSqlContent.append(" t01.materiel_id as \"organizationCd\" , ");
    objSqlContent.append(" t01.root_materiel_id as \"rootMaterielCd\" , ");
    objSqlContent.append(" qm.materiel_cd as \"materielCd\" , ");
    objSqlContent.append(" qm1.materiel_name as \"rootMaterielName\" , ");
    objSqlContent.append(" qm.materiel_name as \"organizationName\"  ");
    objSqlContent.append(" FROM qms_bom t01 ");
    objSqlContent.append(" left join  qms_materiel qm ON qm.id = t01.materiel_id");
    objSqlContent.append(" left join  qms_materiel qm1 ON qm1.id = t01.root_materiel_id");
    objSqlContent.append(" where 1=1 ");
    // 判断组织编码和组织名称是否都为空
    if ("".equals(materielCd) && "".equals(materielName)) {
        objSqlContent.append(" and t01.parent_materiel_id = '0' or t01.parent_materiel_id = '' or t01.parent_materiel_id is null ");
    }
    // 判断组织编码是否为空
    if (!"".equals(materielCd)) {
        objSqlContent.append(" and qm.materiel_cd  like :materielCd ");
    }
    // 判断组织名称是否为空
    if (!"".equals(materielName)) {
        objSqlContent.append(" and qm.materiel_name like :materielName ");
    }
    // 判断组织编码和组织名称是否都为空
    if (!"".equals(materielCd) || !"".equals(materielName)) {
        objSqlContent.append("and NOT FIND_IN_SET(t01.parent_materiel_id,(select  ");
        objSqlContent.append(" GROUP_CONCAT(t02.materiel_id)  ");
        objSqlContent.append(" FROM qms_bom t02  ");
        objSqlContent.append(" left join  qms_materiel qm ON qm.id = t02.materiel_id");
        objSqlContent.append(" left join  qms_materiel qm1 ON qm1.id = t02.root_materiel_id");
        objSqlContent.append(" WHERE 1=1  ");
        // 判断组织编码是否为空
        if (!"".equals(materielCd)) {
            objSqlContent.append(" and qm.materiel_cd  like :materielCd ");
        }
        // 判断组织名称是否为空
        if (!"".equals(materielName)) {
            objSqlContent.append(" and qm.materiel_name like :materielName ");
        }
        objSqlContent.append("  ))  ");
    }
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
@Transactional
public Integer deleteNodeInfos(String id,String hidVehicleType){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    Integer resultNumber = 0;
    List<QmsBom> organizationInfo = new ArrayList<QmsBom>();
    try {
        // 查询
        Query query = em.createNativeQuery(deleteParentNodeInfo(id, hidVehicleType));
        ResultTransformer transformer = Transformers.aliasToBean(QmsBom.class);
        // 赋值给DTO
        organizationInfo = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("parentMaterielID", StandardBasicTypes.INTEGER).addScalar("materielId", StandardBasicTypes.INTEGER).addScalar("vehicleId", StandardBasicTypes.INTEGER).setResultTransformer(transformer).getResultList();
        query = null;
        qmsBomRepository.deleteAll(organizationInfo);
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        resultNumber = 1;
    } finally {
        em.close();
    }
    // 返回值
    return resultNumber;
}


public String getSelectAllInfo(){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" WITH RECURSIVE sub1 (id,materiel_id,materiel_name,root_materiel_id,materiel_cd,vehicleType,rootMaterielName,parent_materiel_id) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.materiel_id, ");
    objSqlContent.append(" qm.materiel_name, ");
    objSqlContent.append(" t01.root_materiel_id , ");
    objSqlContent.append(" qm.materiel_cd  , ");
    objSqlContent.append(" t01.vehicle_id as \"vehicleType\" , ");
    objSqlContent.append(" qm1.materiel_name as \"rootMaterielName\" , ");
    objSqlContent.append(" t01.parent_materiel_id ");
    objSqlContent.append(" FROM qms_bom t01 ");
    objSqlContent.append(" left join  qms_materiel qm ON qm.id = t01.materiel_id");
    objSqlContent.append(" left join  qms_materiel qm1 ON qm1.id = t01.root_materiel_id");
    objSqlContent.append(" WHERE t01.parent_materiel_id = '0' or t01.parent_materiel_id = '' or t01.parent_materiel_id is null ");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.materiel_id, ");
    objSqlContent.append(" qm.materiel_name, ");
    objSqlContent.append(" t02.root_materiel_id , ");
    objSqlContent.append(" qm.materiel_cd , ");
    objSqlContent.append(" t02.vehicle_id as \"vehicleType\" , ");
    objSqlContent.append(" qm1.materiel_name as \"rootMaterielName\" , ");
    objSqlContent.append(" t02.parent_materiel_id ");
    objSqlContent.append(" FROM qms_bom t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_materiel_id = sub1.materiel_id and t02.vehicle_id = sub1.vehicleType");
    objSqlContent.append(" left join  qms_materiel qm ON qm.id = t02.materiel_id");
    objSqlContent.append(" left join  qms_materiel qm1 ON qm1.id = t02.root_materiel_id");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_materiel_id as \"parentCd\" , ");
    objSqlContent.append(" sub1.materiel_id as \"organizationCd\" , ");
    objSqlContent.append(" sub1.root_materiel_id as \"rootMaterielCd\" , ");
    objSqlContent.append(" sub1.materiel_cd as \"materielCd\" , ");
    objSqlContent.append(" sub1.vehicleType as \"vehicleType\" , ");
    objSqlContent.append(" sub1.rootMaterielName as \"rootMaterielName\" , ");
    objSqlContent.append(" sub1.materiel_name as \"organizationName\"  ");
    objSqlContent.append(" FROM sub1 ");
    objSqlContent.append(" where 1=1 ");
    // 返回值
    return objSqlContent.toString();
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
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).addScalar("rootMaterielName", StandardBasicTypes.STRING).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("vehicleType", StandardBasicTypes.STRING).addScalar("rootMaterielCd", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return qmsOrganizationInfoDTO;
}


@SuppressWarnings({ "unchecked", "rawtypes" })
@Transactional
@Override
public JSONObject uploadUserDepart(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // 实例化接受结果集
    List<QmsBom> organizationInfoList = new ArrayList<QmsBom>();
    // 存错误信息的数组
    // List<String> errorMess = new ArrayList<String>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 取得用户ID
    String userCd = user.getUsername();
    // 取得当前时间
    ZonedDateTime nowTime = dateUtil.getDBNowDate();
    // 异常信息判断
    String errorInfo = "0";
    List listInfo = new ArrayList<>();
    // 判断文件路径
    if (files.isEmpty()) {
        returnData.put("status", "error");
        returnData.put("message", "请重新上传！");
    } else {
        try {
            // 取数据
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), GroupBOMInfoDTO.class, 4);
            // 判断数据
            if (data.size() <= 0) {
                // 返回错误信息：表格内无数据
                returnData.put("status", "error");
                returnData.put("message", "表格内无数据！");
                return returnData;
            } else {
                if ("longColumns".equals(data.getJSONObject(0).getString("errorColumns"))) {
                    // 返回错误信息：表格内无数据
                    returnData.put("status", "error");
                    returnData.put("message", "列数不对！");
                    return returnData;
                }
                for (int i = 0; i < data.size(); i++) {
                    // 实例化单挑数据接收实体
                    QmsBom organizationInfo = new QmsBom();
                    // 车型最大长度check
                    if (data.getJSONObject(i).getString("parentMaterielCd").length() > 20) {
                        // 返回错误信息：表格内无数据
                        returnData.put("status", "error");
                        returnData.put("message", "第" + (i + 1) + "条，车型字数超长,最长20个字符！");
                        throw new RuntimeException("车型最大长度check！");
                    }
                    // 上级物料最大长度check
                    if (data.getJSONObject(i).getString("parentMaterielCd").length() > 10) {
                        // 返回错误信息：表格内无数据
                        returnData.put("status", "error");
                        returnData.put("message", "第" + (i + 1) + "条，上级物料字数超长,最长10个字符！");
                        throw new RuntimeException("上级物料最大长度check！");
                    }
                    // 物料编码最大长度check
                    if (data.getJSONObject(i).getString("materielCd").length() > 10) {
                        // 返回错误信息：表格内无数据
                        returnData.put("status", "error");
                        returnData.put("message", "第" + (i + 1) + "条，物料编码字数超长,最长10个字符！");
                        throw new RuntimeException("物料编码最大长度check！");
                    }
                    // 备注最大长度check
                    if (data.getJSONObject(i).getString("remark").length() > 200) {
                        // 返回错误信息：表格内无数据
                        returnData.put("status", "error");
                        returnData.put("message", "第" + (i + 1) + "条，备注字数超长,最长200个字符！");
                        throw new RuntimeException("备注最大长度check！");
                    }
                    // 备注
                    organizationInfo.setRemark(data.getJSONObject(i).getString("remark"));
                    // 判断车型是否
                    if (!"".equals(data.getJSONObject(i).getString("vehicleType"))) {
                        // 初始化结果集
                        List<QmsVehicleTypeInfo> vehicleTypeExistence = new ArrayList<QmsVehicleTypeInfo>();
                        // 判断车型是否存在
                        vehicleTypeExistence = qmsVehicleTypeInfoRepository.findByFlagStatusAndVehicleType("0", data.getJSONObject(i).getString("vehicleType"));
                        // 判断是否取到值
                        if (vehicleTypeExistence.size() == 0) {
                            returnData.put("status", "error");
                            returnData.put("message", "第" + (i + 1) + "条，车型不存在;");
                            throw new RuntimeException("车型不存在！");
                        }
                        // 车型id赋值
                        organizationInfo.setVehicleId(Integer.valueOf(vehicleTypeExistence.get(0).getId().toString()));
                    } else {
                        // 返回错误信息：物料编码为空
                        returnData.put("status", "error");
                        returnData.put("message", "第" + (i + 1) + "条，车型为空");
                        throw new RuntimeException("车型为空！");
                    }
                    // 取得车型ID
                    QmsVehicleTypeInfo qmsVehicleTypeInfo = qmsVehicleTypeInfoRepository.findByVehicleTypeAndFlagStatus(data.getJSONObject(i).getString("vehicleType"), "0");
                    // 根据上级ID取得上级名字
                    List<QmsMateriel> qmsOrgaNameParentMaterielID = new ArrayList<QmsMateriel>();
                    // 判断上级物料是否存在
                    if (!"".equals(data.getJSONObject(i).getString("parentMaterielCd"))) {
                        // 实例化结果集parentCd是否存在
                        List<QmsBom> parentCdListInfo = new ArrayList<QmsBom>();
                        qmsOrgaNameParentMaterielID = qmsMaterielRepository.findByMaterielCdAndFlagStatus(data.getJSONObject(i).getString("parentMaterielCd"), "0");
                        if (qmsOrgaNameParentMaterielID.size() == 0) {
                            returnData.put("status", "error");
                            returnData.put("message", "第" + (i + 1) + "条，上级物料不存在;");
                            throw new RuntimeException("上级物料不存在！");
                        }
                        parentCdListInfo = qmsBomRepository.findByVehicleIdAndMaterielIdAndFlagStatus(Integer.valueOf(qmsVehicleTypeInfo.getId().toString()), Integer.valueOf(qmsOrgaNameParentMaterielID.get(0).getId().toString()), "0");
                        if (parentCdListInfo.size() == 0) {
                            returnData.put("status", "error");
                            returnData.put("message", "第" + (i + 1) + "条，上级物料不存在;");
                            throw new RuntimeException("上级物料不存在！");
                        }
                        // 上级物料赋值
                        organizationInfo.setParentMaterielID(Integer.valueOf(qmsOrgaNameParentMaterielID.get(0).getId().toString()));
                        organizationInfo.setRootMaterielId(parentCdListInfo.get(0).getRootMaterielId());
                    }
                    // 判断物料编码是否为空
                    if (!"".equals(data.getJSONObject(i).getString("materielCd"))) {
                        // 根据物料CD取得物料信息
                        List<QmsMateriel> qmsOrgaNameMaterielId = qmsMaterielRepository.findByMaterielCdAndFlagStatus(data.getJSONObject(i).getString("materielCd"), "0");
                        // 判断物料编码是否已经存在
                        if (qmsOrgaNameMaterielId.size() == 0) {
                            // 返回错误信息：物料编码已存在
                            returnData.put("status", "error");
                            returnData.put("message", "第" + (i + 1) + "条，物料编码不存在;");
                            throw new RuntimeException("物料编码不存在！");
                        }
                        // 判断上级物料是否为空
                        if ("".equals(data.getJSONObject(i).getString("parentMaterielCd"))) {
                            // 实例化结果集
                            List<QmsBom> organizationInfoCheck = new ArrayList<QmsBom>();
                            // 根据组织编码取得数据
                            organizationInfoCheck = qmsBomRepository.findByVehicleIdAndMaterielIdAndParentMaterielIDAndFlagStatus(Integer.valueOf(qmsVehicleTypeInfo.getId().toString()), Integer.valueOf(qmsOrgaNameMaterielId.get(0).getId().toString()), 0, "0");
                            // 判断组织编码是否重复
                            if (organizationInfoCheck.size() > 0) {
                                // 编码重复返回错误信息
                                returnData.put("status", "error");
                                returnData.put("message", "第" + (i + 1) + "条，车型+物料已存在;");
                                throw new RuntimeException("车型+物料已存在！");
                            }
                            organizationInfo.setParentMaterielID(0);
                            // 根物料
                            organizationInfo.setRootMaterielId(Integer.valueOf(qmsOrgaNameMaterielId.get(0).getId().toString()));
                        } else {
                            // 实例化结果集
                            List<QmsBom> organizationInfoCheck = new ArrayList<QmsBom>();
                            // 根据组织编码取得数据
                            organizationInfoCheck = qmsBomRepository.findByVehicleIdAndParentMaterielIDAndMaterielIdAndFlagStatus(Integer.valueOf(qmsVehicleTypeInfo.getId().toString()), Integer.valueOf(qmsOrgaNameParentMaterielID.get(0).getId().toString()), Integer.valueOf(qmsOrgaNameMaterielId.get(0).getId().toString()), "0");
                            if (organizationInfoCheck.size() != 0) {
                                // 编码重复返回错误信息
                                returnData.put("status", "error");
                                returnData.put("message", "第" + (i + 1) + "条，该上级物料下已存在此物料;");
                                throw new RuntimeException("车型+物料已存在！");
                            }
                        }
                        // 物料编码赋值
                        organizationInfo.setMaterielId(Integer.valueOf(qmsOrgaNameMaterielId.get(0).getId().toString()));
                    } else {
                        // 返回错误信息：物料编码为空
                        returnData.put("status", "error");
                        returnData.put("message", "物料编码为空:");
                        throw new RuntimeException("物料编码为空！");
                    }
                    // 删除标识赋值
                    organizationInfo.setFlagStatus("0");
                    // 创建人赋值
                    organizationInfo.setMakeUser(userCd);
                    // 创建时间赋值
                    organizationInfo.setMakeTime(nowTime);
                    // 更新人赋值
                    organizationInfo.setModifyUser(userCd);
                    // 更新时间赋值
                    organizationInfo.setModifyTime(nowTime);
                    // 添加到接收集合中
                    organizationInfoList.add(organizationInfo);
                    // 数据插入
                    qmsBomRepository.save(organizationInfo);
                    // 用于判断上传的表格中数据的物料ID是否有相同 的
                    listInfo.add(data.getJSONObject(i).getString("vehicleType") + data.getJSONObject(i).getString("parentMaterielCd") + data.getJSONObject(i).getString("materielCd"));
                }
                // 实例化结果集
                HashSet set = new HashSet<>(listInfo);
                // 判断表格中的物料ID是否有重复
                if (listInfo.size() != set.size()) {
                    // 返回错误信息：表格中数据的物料ID有相同的
                    returnData.put("status", "error");
                    returnData.put("message", "导入的数据中数据有重复！");
                    errorInfo = "1";
                    throw new RuntimeException("导入的数据中数据有重复！");
                }
            }
            // 返回成功结果
            returnData.put("status", "success");
            returnData.put("flag", "0");
            returnData.put("message", "导入成功！");
        } catch (Exception e) {
            // 事物回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // 打印错误信息
            log.info(e.getMessage());
        }
    }
    // 返回结果
    return returnData;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String materielCd,String materielName){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoDTO>();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectParentNodeInfo(materielCd, materielName));
        ResultTransformer transformer = Transformers.aliasToBean(QmsOrganizationInfoDTO.class);
        // 判断组织编码是否为空
        if (!"".equals(materielCd)) {
            query.setParameter("materielCd", "%" + materielCd + "%");
        }
        // 判断组织名称是否为空
        if (!"".equals(materielName)) {
            query.setParameter("materielName", "%" + materielName + "%");
        }
        // 赋值给DTO
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).addScalar("rootMaterielName", StandardBasicTypes.STRING).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("vehicleType", StandardBasicTypes.STRING).addScalar("rootMaterielCd", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
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
public List<DropDowmValueDTO> getCarTypeInfo(){
    EntityManager em = emf.createEntityManager();
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    try {
        Query query = em.createNativeQuery("SELECT qvti.id as \"value\" ,qvti.vehicle_type_name as \"label\" FROM qms_vehicle_type_info qvti where qvti.flag_status = 0");
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


public String deleteParentNodeInfo(String id,String hidVehicleType){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" WITH RECURSIVE sub1 (id,materiel_id,vehicle_id,parent_materiel_id) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.materiel_id, ");
    objSqlContent.append(" t01.vehicle_id, ");
    objSqlContent.append(" t01.parent_materiel_id ");
    objSqlContent.append(" FROM qms_bom t01 ");
    objSqlContent.append(" WHERE t01.materiel_id =  '" + id + "'");
    objSqlContent.append(" AND t01.vehicle_id =  '" + hidVehicleType + "'");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.materiel_id, ");
    objSqlContent.append(" t02.vehicle_id, ");
    objSqlContent.append(" t02.parent_materiel_id ");
    objSqlContent.append(" FROM qms_bom t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_materiel_id = sub1.materiel_id and t02.vehicle_id = sub1.vehicle_id ");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_materiel_id as \"parentMaterielID\" , ");
    objSqlContent.append(" sub1.materiel_id as \"materielId\" , ");
    objSqlContent.append(" sub1.vehicle_id as \"vehicleId\"  ");
    objSqlContent.append(" FROM sub1 ");
    objSqlContent.append(" where 1=1 ");
    return objSqlContent.toString();
}


}