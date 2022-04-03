package cn.com.cnc.fcc.service.impl;
 import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import cn.com.cnc.fcc.domain.materialTypeSelectionDto;
import cn.com.cnc.fcc.service.dto.QmsMaterielSupplierDto;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
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
import cn.com.cnc.fcc.domain.QmsMaterielType;
import cn.com.cnc.fcc.repository.QmsMaterielTypeRepository;
import cn.com.cnc.fcc.service.QmsMaterielTypeService;
import cn.com.cnc.fcc.service.dto.GroupMaterielTypeInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
@Service
public class QmsMaterielTypeServiceImpl implements QmsMaterielTypeService{

 private  Logger log;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;

 private  QmsMaterielTypeRepository qmsMaterielTypeRepository;

public QmsMaterielTypeServiceImpl(EntityManagerFactory emf, QmsMaterielTypeRepository qmsMaterielTypeRepository) {
    this.qmsMaterielTypeRepository = qmsMaterielTypeRepository;
    this.emf = emf;
}
@Override
public List<materialTypeSelectionDto> qmsMaterialTypeFindAll(String bianMa,String gongName){
    List<materialTypeSelectionDto> materialTypeSelectionDto = new ArrayList<materialTypeSelectionDto>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getfindAllSql(bianMa, gongName);
        Query query = em.createNativeQuery(sql);
        if (!"".equals(bianMa) && bianMa != null)
            query.setParameter("bianMa", "%" + bianMa + "%");
        if (!"".equals(gongName) && gongName != null)
            query.setParameter("gongName", "%" + gongName + "%");
        ResultTransformer transformer = Transformers.aliasToBean(materialTypeSelectionDto.class);
        materialTypeSelectionDto = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("materielTypeCd", StandardBasicTypes.STRING).addScalar("materielTypeName", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsMaterielSupplier could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return materialTypeSelectionDto;
}


public String getSelectParentNodeInfo(String materielTypeCd,String materielTypeName){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT t01.id as \"id\" , ");
    objSqlContent.append(" t01.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" t01.materiel_type_cd as \"organizationCd\" , ");
    objSqlContent.append(" t01.materiel_type_name as \"organizationName\"  ");
    objSqlContent.append(" FROM qms_materiel_type t01 ");
    objSqlContent.append(" where 1=1 ");
    // 判断物料分类编码和物料分类名称是否都为空
    if ("".equals(materielTypeCd) && "".equals(materielTypeName)) {
        objSqlContent.append(" and t01.parent_cd = '0' or t01.parent_cd = '' or t01.parent_cd is null ");
    }
    // 判断物料分类编码是否为空
    if (!"".equals(materielTypeCd)) {
        objSqlContent.append(" and t01.materiel_type_cd  like :materielTypeCd ");
    }
    // 判断物料分类名称是否为空
    if (!"".equals(materielTypeName)) {
        objSqlContent.append(" and t01.materiel_type_name like :materielTypeName ");
    }
    // 判断物料分类编码和物料分类名称是否都为空
    if (!"".equals(materielTypeCd) || !"".equals(materielTypeName)) {
        objSqlContent.append("and NOT FIND_IN_SET(t01.parent_cd,(select  ");
        objSqlContent.append(" GROUP_CONCAT(t02.materiel_type_cd)  ");
        objSqlContent.append(" FROM qms_materiel_type t02  ");
        objSqlContent.append(" WHERE 1=1  ");
        // 判断物料分类编码是否为空
        if (!"".equals(materielTypeCd)) {
            objSqlContent.append(" and t02.materiel_type_cd  like :materielTypeCd ");
        }
        // 判断物料分类名称是否为空
        if (!"".equals(materielTypeName)) {
            objSqlContent.append(" and t02.materiel_type_name like :materielTypeName ");
        }
        objSqlContent.append("  ))  ");
    }
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
@Transactional
public Integer deleteNodeInfos(String id){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    Integer resultNumber = 0;
    List<QmsMaterielType> organizationInfo = new ArrayList<QmsMaterielType>();
    try {
        // 查询
        Query query = em.createNativeQuery(deleteParentNodeInfo(id));
        ResultTransformer transformer = Transformers.aliasToBean(QmsMaterielType.class);
        // 赋值给DTO
        organizationInfo = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("materielTypeCd", StandardBasicTypes.STRING).addScalar("materielTypeName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
        qmsMaterielTypeRepository.deleteAll(organizationInfo);
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
    objSqlContent.append(" WITH RECURSIVE sub1 (id,materiel_type_cd,materiel_type_name,parent_cd) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.materiel_type_cd, ");
    objSqlContent.append(" t01.materiel_type_name, ");
    objSqlContent.append(" t01.parent_cd ");
    objSqlContent.append(" FROM qms_materiel_type t01 ");
    objSqlContent.append(" WHERE t01.parent_cd = '0' or t01.parent_cd = '' or t01.parent_cd is null ");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.materiel_type_cd, ");
    objSqlContent.append(" t02.materiel_type_name, ");
    objSqlContent.append(" t02.parent_cd ");
    objSqlContent.append(" FROM qms_materiel_type t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_cd = sub1.materiel_type_cd ");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" sub1.materiel_type_cd as \"organizationCd\" , ");
    objSqlContent.append(" sub1.materiel_type_name as \"organizationName\"  ");
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
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    return qmsOrganizationInfoDTO;
}


@SuppressWarnings({ "rawtypes", "unchecked" })
@Override
@Transactional
public JSONObject uploadUserDepart(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // 实例化接受结果集
    List<QmsMaterielType> organizationInfoList = new ArrayList<QmsMaterielType>();
    // 实例化结果集parentCd是否存在
    List<QmsMaterielType> parentCdListInfo = new ArrayList<QmsMaterielType>();
    // 实例化结果集organizationCd是否已存在
    List<QmsMaterielType> organizationCdListInfo = new ArrayList<QmsMaterielType>();
    // 存错误信息的数组
    List<String> errorMess = new ArrayList<String>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 取得用户ID
    String userCd = user.getUsername();
    // 取得当前时间
    ZonedDateTime nowTime = dateUtil.getDBNowDate();
    List listInfo = new ArrayList<>();
    // 判断文件路径
    if (files.isEmpty()) {
        returnData.put("status", "error");
        returnData.put("message", "请重新上传！");
    } else {
        try {
            // 取数据
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), GroupMaterielTypeInfoDTO.class, 3);
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
                    QmsMaterielType organizationInfo = new QmsMaterielType();
                    // 上级编码最大长度check
                    if (data.getJSONObject(i).getString("parentCd").length() > 10) {
                        // 返回错误信息：表格内无数据
                        returnData.put("status", "error");
                        returnData.put("message", "上级分类字数超长,最长10个字符！超长的上级分类是：" + data.getJSONObject(i).getString("parentCd"));
                        return returnData;
                    }
                    // 物料分类编码最大长度check
                    if (data.getJSONObject(i).getString("materielTypeCd").length() > 10) {
                        // 返回错误信息：表格内无数据
                        returnData.put("status", "error");
                        returnData.put("message", "物料分类编码字数超长,最长10个字符！超长的物料分类是：" + data.getJSONObject(i).getString("materielTypeCd"));
                        return returnData;
                    }
                    // 物料分类名称最大长度check
                    if (data.getJSONObject(i).getString("materielTypeName").length() > 100) {
                        // 返回错误信息：表格内无数据
                        returnData.put("status", "error");
                        returnData.put("message", "物料分类名称字数超长,最长100个字符！超长的物料分类名称是：" + data.getJSONObject(i).getString("materielTypeName"));
                        return returnData;
                    }
                    // 上级编码赋值
                    organizationInfo.setParentCd(data.getJSONObject(i).getString("parentCd"));
                    // 物料分类编码赋值
                    organizationInfo.setMaterielTypeCd(data.getJSONObject(i).getString("materielTypeCd"));
                    // 物料分类名称赋值
                    organizationInfo.setMaterielTypeName(data.getJSONObject(i).getString("materielTypeName"));
                    if (!"".equals(data.getJSONObject(i).getString("parentCd"))) {
                        parentCdListInfo = qmsMaterielTypeRepository.findByMaterielTypeCdAndFlagStatus(data.getJSONObject(i).getString("parentCd"), "0");
                        if (parentCdListInfo.size() == 0) {
                            returnData.put("status", "error");
                            returnData.put("message", "上级分类不存在;不存在的上级分类是:" + data.getJSONObject(i).getString("parentCd"));
                            return returnData;
                        }
                    }
                    // 判断物料分类编码是否为空
                    if (!"".equals(data.getJSONObject(i).getString("materielTypeCd"))) {
                        organizationCdListInfo = qmsMaterielTypeRepository.findByMaterielTypeCdAndFlagStatus(data.getJSONObject(i).getString("materielTypeCd"), "0");
                        // 判断物料分类编码是否已经存在
                        if (organizationCdListInfo.size() != 0) {
                            // 返回错误信息：物料分类编码已存在
                            returnData.put("status", "error");
                            returnData.put("message", "物料分类编码已存在;存在的物料分类编码是:" + data.getJSONObject(i).getString("materielTypeCd"));
                            return returnData;
                        }
                    } else {
                        // 返回错误信息：物料分类编码为空
                        returnData.put("status", "error");
                        returnData.put("message", "物料分类编码为空:");
                        return returnData;
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
                    // 用于判断上传的表格中数据的物料分类ID是否有相同 的
                    listInfo.add(data.getJSONObject(i).getString("materielTypeCd"));
                }
                // 实例化结果集
                HashSet set = new HashSet<>(listInfo);
                // 判断表格中的物料分类ID是否有重复
                if (listInfo.size() != set.size()) {
                    // 返回错误信息：表格中数据的物料分类ID有相同的
                    returnData.put("status", "error");
                    returnData.put("message", "导入的数据中物料分类编码有重复！");
                    return returnData;
                }
                // 数据插入
                qmsMaterielTypeRepository.saveAll(organizationInfoList);
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
            // 返回错误信息：数据插入异常
            returnData.put("status", "error");
            returnData.put("message", "数据插入异常！");
        }
    }
    // 返回结果
    return returnData;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsOrganizationInfoDTO> getParentNodeListInfo(String materielTypeCd,String materielTypeName){
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoDTO>();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectParentNodeInfo(materielTypeCd, materielTypeName));
        ResultTransformer transformer = Transformers.aliasToBean(QmsOrganizationInfoDTO.class);
        // 判断物料分类编码是否为空
        if (!"".equals(materielTypeCd)) {
            query.setParameter("materielTypeCd", "%" + materielTypeCd + "%");
        }
        // 判断物料分类名称是否为空
        if (!"".equals(materielTypeName)) {
            query.setParameter("materielTypeName", "%" + materielTypeName + "%");
        }
        // 赋值给DTO
        qmsOrganizationInfoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.BIG_INTEGER).addScalar("parentCd", StandardBasicTypes.STRING).addScalar("organizationCd", StandardBasicTypes.STRING).addScalar("organizationName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回值
    return qmsOrganizationInfoDTO;
}


public String deleteParentNodeInfo(String id){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" WITH RECURSIVE sub1 (id,materiel_type_cd,materiel_type_name,parent_cd) AS ( ");
    objSqlContent.append(" select	t01.id, ");
    objSqlContent.append(" t01.materiel_type_cd, ");
    objSqlContent.append(" t01.materiel_type_name, ");
    objSqlContent.append(" t01.parent_cd ");
    objSqlContent.append(" FROM qms_materiel_type t01 ");
    objSqlContent.append(" WHERE t01.materiel_type_cd =  '" + id + "'");
    objSqlContent.append(" UNION ALL ");
    objSqlContent.append(" SELECT t02.id, ");
    objSqlContent.append(" t02.materiel_type_cd, ");
    objSqlContent.append(" t02.materiel_type_name, ");
    objSqlContent.append(" t02.parent_cd ");
    objSqlContent.append(" FROM qms_materiel_type t02 ");
    objSqlContent.append(" INNER JOIN sub1 ON t02.parent_cd = sub1.materiel_type_cd ");
    objSqlContent.append(" ) ");
    objSqlContent.append(" SELECT sub1.id as \"id\" , ");
    objSqlContent.append(" sub1.parent_cd as \"parentCd\" , ");
    objSqlContent.append(" sub1.materiel_type_cd as \"materielTypeCd\" , ");
    objSqlContent.append(" sub1.materiel_type_name as \"materielTypeName\"  ");
    objSqlContent.append(" FROM sub1 ");
    objSqlContent.append(" where 1=1 ");
    return objSqlContent.toString();
}


public String getfindAllSql(String bianMa,String gongName){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT t01.id as id,\n" + "t01.materiel_type_cd as materielTypeCd,t01.materiel_type_name as materielTypeName,t01.remark as remark\n" + "FROM \n" + "qms_materiel_type t01 \n" + "WHERE \n" + "NOT EXISTS ( \n" + "SELECT \n" + "t02.parent_cd FROM qms_materiel_type t02 \n" + "WHERE \n" + "t01.materiel_type_cd = t02.parent_cd)");
    if (!"".equals(bianMa) && bianMa != null)
        sql.append("and t01.materiel_type_cd  LIKE :bianMa\n");
    if (!"".equals(gongName) && gongName != null)
        sql.append("and t01.materiel_type_name  LIKE :gongName\n");
    return sql.toString();
}


}