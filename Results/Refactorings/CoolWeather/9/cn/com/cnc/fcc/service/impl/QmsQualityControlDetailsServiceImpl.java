import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository;
import cn.com.cnc.fcc.service.QmsQualityControlDetailsService;
import cn.com.cnc.fcc.service.dto.ProductProcessCheckDTO;
import cn.com.cnc.fcc.service.dto.QmsPartsAssemblyRelationDto;
import cn.com.cnc.fcc.service.dto.QmsQualityControlDetailsDto;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.ZonedDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class QmsQualityControlDetailsServiceImpl implements QmsQualityControlDetailsService,cn.com.cnc.fcc.service.QmsQualityControlDetailsService{

@Autowired
 private  EntityManagerFactory emf;

 private  QmsProductionInspectionValueRepository qmsProductionInspectionValueRepository;

@Resource
 private  QmsUnqualifiedProductDetailsRepository qmsUnqualifiedProductDetailRepository;

@Resource
 private  QmsProductionInspectionResultRepository qmsProductionInspectionResultRepository;

@Resource
 private  QmsMaterielDetailsRepository qmsMaterielDetailsRepository;

@Resource
 private  QmsProductionInspectionRepository qmsProductionInspectionRepository;

@Resource
 private  QmsProductionRelationRepository qmsProductionRelationRepository;

@Resource
 private  QmsUnqualifiedProductRepository qmsUnqualifiedProductRepository;

@Resource
 private  QmsEnclosureRepository qmsEnclosureRepository;

@Resource
 private  QmsBomTechnologyRepository qmsBomTechnologyRepository;

@Resource
 private  RbacUserRepository rbacUserRepository;

@Resource
 private  DateUtil dateUtil;


public String updateQmsProductionInspectionResult(JSONArray qqcd,JSONObject qpiv,String pid){
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    String isOk = qpiv.getString("isOk");
    String checkNumber = qpiv.getString("checkNumber");
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(pid), "C");
    qmsProductionInspectionValueOptional.get().setCheckNumber(checkNumber);
    qmsProductionInspectionValueOptional.get().setIsOk(isOk);
    qmsProductionInspectionValueOptional.get().setModifyTime(dateUtil.getDBNowDate());
    qmsProductionInspectionValueOptional.get().setModifyUser(user.getUsername());
    Long qmsProductionInspectionValueId = qmsProductionInspectionValueRepository.save(qmsProductionInspectionValueOptional.get()).getId();
    for (int i = 0; i < qqcd.size(); i++) {
        JSONObject requestObject = qqcd.getJSONObject(i);
        String testValue = requestObject.getString("testValue");
        String resultId = requestObject.getString("resultId");
        Optional<QmsProductionInspectionResult> qpir_obj = qmsProductionInspectionResultRepository.findById(Long.valueOf(resultId));
        qpir_obj.get().setTestValue(testValue);
        qpir_obj.get().setModifyUser(user.getUsername());
        qpir_obj.get().setModifyTime(dateUtil.getDBNowDate());
        qmsProductionInspectionResultRepository.save(qpir_obj.get());
    }
    return qmsProductionInspectionValueId.toString();
}


@Transactional
@Override
public JSONObject saveAll(JSONObject jsonObject){
    JSONObject result = new JSONObject();
    result.put("code", "1");
    result.put("valueId", "");
    try {
        // 质量检验项目
        String valueId = savePIR(jsonObject);
        result.put("valueId", valueId);
        // 装配
        saveProductionRelation(jsonObject);
    } catch (InternalServerErrorException e) {
        result.put("code", "0");
        // 超出入厂单件数量
        if (e.getTitle() == "2") {
            result.put("code", "2");
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    return result;
}


public String findByBomTechnologyIdSql(Integer pId,String isDetails){
    StringBuffer sql = new StringBuffer();
    sql = sql.append(" SELECT ");
    sql = sql.append(" T1.id, ");
    sql = sql.append(" T6.id AS controlId, ");
    sql = sql.append(" T6.bom_technology_id AS bomTechnologyId, ");
    sql = sql.append(" T6.standard, ");
    sql = sql.append(" T6.inspection_instrument AS inspectionInstrument, ");
    sql = sql.append(" T6.technical_requirement AS technicalRequirement, ");
    sql = sql.append(" T6.inspection_item AS inspectionItem, ");
    sql = sql.append(" T6.abc_type AS abcType, ");
    sql = sql.append(" T6.upper_deviation AS upperDeviation, ");
    sql = sql.append(" T6.lower_deviation AS lowerDeviation, ");
    sql = sql.append(" T6.inspection_result_diff AS inspectionResultDiff, ");
    sql = sql.append(" T6.place_diff AS placeDiff, ");
    sql = sql.append(" T5.place_diff AS resultPlaceDiff, ");
    sql = sql.append(" T5.id AS resultId, ");
    if (isDetails.equals("1")) {
        sql = sql.append(" CASE WHEN T5.id IS NULL THEN '' ELSE T5.test_value END AS testValue, ");
    } else {
        sql = sql.append(" CASE WHEN T5.id IS NULL THEN CASE WHEN T6.inspection_result_diff = '0' THEN  ");
        sql = sql.append(" T6.standard ELSE 'Y' END ELSE T5.test_value END AS testValue, ");
    }
    sql = sql.append(" CASE WHEN T5.modify_user IS NULL THEN '' ELSE T5.modify_user END AS modifyUser ");
    sql = sql.append(" FROM QMS_PRODUCTION_INSPECTION T1 ");
    sql = sql.append(" INNER JOIN QMS_MATERIEL T2 ");
    sql = sql.append(" ON T1.materiel_id = T2.id ");
    sql = sql.append(" INNER JOIN QMS_BOM_TECHNOLOGY T3 ");
    sql = sql.append(" ON T1.bom_technology_id = T3.id ");
    sql = sql.append(" INNER JOIN QMS_process T4 ");
    sql = sql.append(" ON T3.process_id = T4.id ");
    sql = sql.append(" INNER JOIN QMS_QUALITY_CONTROL_DETAILS T6 ");
    sql = sql.append(" ON T3.id = T6.bom_technology_id ");
    sql = sql.append(" LEFT JOIN QMS_PRODUCTION_INSPECTION_result T5 ");
    sql = sql.append(" ON T5.inspection_id = T1.id ");
    sql = sql.append(" AND T5.inspection_diff = 'C' ");
    sql = sql.append(" AND T5.control_id = T6.id ");
    sql = sql.append(" WHERE T1.id = " + pId);
    sql.append(" ORDER BY T1.id DESC ");
    return sql.toString();
}


@Override
public List<QmsPartsAssemblyRelationDto> findAssemblyRelationByPid(Integer pid){
    List<QmsPartsAssemblyRelationDto> newResult = new ArrayList<QmsPartsAssemblyRelationDto>();
    List<QmsPartsAssemblyRelationDto> result = new ArrayList<QmsPartsAssemblyRelationDto>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getAssemblyRelationSql(pid);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(QmsPartsAssemblyRelationDto.class);
        result = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("materielId", StandardBasicTypes.INTEGER).addScalar("productMode", StandardBasicTypes.STRING).addScalar("assemblyCount", StandardBasicTypes.INTEGER).addScalar("materielName", StandardBasicTypes.STRING).addScalar("madeYmd", StandardBasicTypes.STRING).addScalar("madeFactoryCd", StandardBasicTypes.STRING).addScalar("productionRelationId", StandardBasicTypes.INTEGER).addScalar("partsAssemblyRelationId", StandardBasicTypes.INTEGER).addScalar("HID", StandardBasicTypes.INTEGER).addScalar("bianHao", StandardBasicTypes.STRING).addScalar("actualUseLocation", StandardBasicTypes.STRING).addScalar("entryType", StandardBasicTypes.STRING).addScalar("modifyUser", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        QmsPartsAssemblyRelationDto resultDto = null;
        for (int i = 0; i < result.size(); i++) {
            // 拷贝一条
            resultDto = (QmsPartsAssemblyRelationDto) result.get(i).clone();
            // 设置管理人
            List<RbacUser> userList = rbacUserRepository.findByUserCode(resultDto.getModifyUser());
            if (userList.size() != 0) {
                resultDto.setModifyUser(userList.get(0).getUserName());
            }
            // 根据数量复制
            if (resultDto.getProductionRelationId() == null && resultDto.getAssemblyCount() > 0) {
                for (int j = 0; j < resultDto.getAssemblyCount(); j++) {
                    newResult.add(resultDto);
                }
            } else {
                newResult.add(resultDto);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsPartsAssemblyRelation could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return newResult;
}


@Override
public String checkProductionRelation(JSONObject jsonObject){
    String code = "1";
    JSONArray requestList = jsonObject.getJSONArray("params");
    String pid = jsonObject.getString("pid");
    // 上工序检验合格再判断审批人
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(pid), "C");
    if (qmsProductionInspectionValueOptional.isPresent()) {
        Long valueId = qmsProductionInspectionValueOptional.get().getId();
        List<QmsUnqualifiedProduct> qmsUnqualifiedProductList = qmsUnqualifiedProductRepository.findByInspectionValueIdAndApproveUserIdIsNotNull(Integer.valueOf(valueId.toString()));
        if (qmsUnqualifiedProductList.size() > 0) {
            code = "3";
            return code;
        }
    }
    // 产品Id集合
    ArrayList<String> pList = Lists.newArrayList();
    // 物料进场Id集合
    ArrayList<String> mList = Lists.newArrayList();
    for (int i = 0; i < requestList.size(); i++) {
        // 隐藏Id 产品？物料进场
        String hid = requestList.getJSONObject(i).getString("hid");
        // 来源区分
        String fromDiff = requestList.getJSONObject(i).getString("productMode");
        // 生产关系Id
        String productionRelationId = requestList.getJSONObject(i).getString("productionRelationId");
        // 到货类型 0：单件，1：批次
        String entryType = requestList.getJSONObject(i).getString("entryType");
        // 判断是否重复使用产品自制
        if ("M".equals(fromDiff)) {
            // 新规
            if (productionRelationId == null || "".equals(productionRelationId)) {
                if (pList.stream().anyMatch(hid::equalsIgnoreCase)) {
                    code = "2";
                    break;
                }
                pList.add(hid);
            } else {
                // 更新
                if (pList.stream().anyMatch(hid::equalsIgnoreCase)) {
                    code = "2";
                    break;
                }
                pList.add(hid);
                List<QmsProductionRelation> qmsProductionRelationList = qmsProductionRelationRepository.findAllByUseProductIdAndIdIsNot(Integer.valueOf(hid), Long.valueOf(productionRelationId));
                if (qmsProductionRelationList.size() > 0) {
                    code = "2";
                    break;
                }
            }
        } else if ("P".equals(fromDiff) || "0".equals(fromDiff)) {
            // 外购，外协
            if ("0".equals(entryType)) {
                if (productionRelationId == null || productionRelationId.equals("")) {
                    if (mList.stream().anyMatch(hid::equalsIgnoreCase)) {
                        code = "2";
                        break;
                    }
                    mList.add(hid);
                } else {
                    // 更新
                    if (mList.stream().anyMatch(hid::equalsIgnoreCase)) {
                        code = "2";
                        break;
                    }
                    mList.add(hid);
                    List<QmsProductionRelation> qmsProductionRelationList = qmsProductionRelationRepository.findAllByAssemblyMaterielIdAndIdIsNot(Integer.valueOf(hid), Long.valueOf(productionRelationId));
                    if (qmsProductionRelationList.size() > 0) {
                        code = "2";
                        break;
                    }
                }
            }
        }
    }
    return code;
}


@Transactional
@Override
public String deleteAll(String pid){
    String code = "1";
    try {
        // 删除生产检验
        Optional<QmsProductionInspection> inspection = qmsProductionInspectionRepository.findById(Long.valueOf(pid));
        // 设置成待检验
        if (inspection.isPresent()) {
            // session取得用户信息
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 取得用户信息
            UserDetails user = (UserDetails) authentication.getPrincipal();
            inspection.get().setIsOk("2");
            inspection.get().setModifyUser(user.getUsername());
            inspection.get().setModifyTime(dateUtil.getDBNowDate());
            qmsProductionInspectionRepository.save(inspection.get());
        }
        // 删除生产检验结果
        Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(pid), "C");
        if (qmsProductionInspectionValueOptional.isPresent()) {
            // 删除生产检验结果
            QmsProductionInspectionValue qmsProductionInspectionValue = qmsProductionInspectionValueOptional.get();
            Integer qpivId = Integer.valueOf(qmsProductionInspectionValue.getId().toString());
            qmsProductionInspectionValueRepository.delete(qmsProductionInspectionValue);
            // 删除生产检验项结果表
            List<QmsProductionInspectionResult> qmsProductionInspectionResultList = qmsProductionInspectionResultRepository.findByInspectionIdAndInspectionValueIdAndInspectionDiff(Integer.valueOf(pid), qpivId, "C");
            if (qmsProductionInspectionResultList.size() > 0) {
                for (int i = 0; i < qmsProductionInspectionResultList.size(); i++) {
                    qmsProductionInspectionResultRepository.delete(qmsProductionInspectionResultList.get(i));
                }
            }
            // 删除生产检验装配关系表
            List<QmsProductionRelation> productionRelationList = qmsProductionRelationRepository.findByProductionInspectionId(Integer.valueOf(pid));
            if (productionRelationList.size() > 0) {
                for (int i = 0; i < productionRelationList.size(); i++) {
                    qmsProductionRelationRepository.delete(productionRelationList.get(i));
                }
            }
            // 删除附件表
            List<QmsEnclosure> qmsEnclosureList = qmsEnclosureRepository.findAllByInspectionInfoIdAndInspectionKbn(qpivId, "2");
            if (qmsEnclosureList.size() > 0) {
                for (int i = 0; i < qmsEnclosureList.size(); i++) {
                    qmsEnclosureRepository.delete(qmsEnclosureList.get(i));
                }
            }
        }
    } catch (Exception e) {
        code = "0";
        // 回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    return code;
}


public String qmsChackPreProcessSql(JSONObject jsonObject){
    StringBuffer sql = new StringBuffer();
    JSONObject productionInspection = jsonObject.getJSONObject("productionInspection");
    String serialNumber = productionInspection.getString("serialNumber");
    String workno = productionInspection.getString("workno");
    String materielId = productionInspection.getString("materielId");
    String bomTechnologyId = productionInspection.getString("bomTechnologyId");
    sql.append(" select   ");
    sql.append(" qpi.id,   ");
    sql.append(" qpv.is_ok AS isOk,   ");
    sql.append(" qmu.approve_result_diff AS approveResultDiff  ");
    sql.append(" FROM  qms_production_inspection qpi ");
    sql.append(" LEFT JOIN qms_production_inspection_value qpv ON qpi.id = qpv.inspection_id AND qpv.inspection_diff = 'S'   ");
    sql.append(" LEFT JOIN qms_unqualified_product qmu ON qpv.id = qmu.inspection_value_id   ");
    sql.append(" WHERE 1=1   ");
    if (!"".equals(serialNumber) && serialNumber != null) {
        sql.append(" AND qpi.serial_number = '" + serialNumber + "'");
    }
    if (!"".equals(workno) && workno != null) {
        sql.append(" AND qpi.workno = '" + workno + "'");
    }
    sql.append(" AND qpi.materiel_id = " + materielId);
    sql.append(" AND qpi.bom_technology_id = (   ");
    sql.append(" SELECT   ");
    sql.append(" id    ");
    sql.append(" FROM   ");
    sql.append(" qms_bom_technology qbt   ");
    sql.append(" WHERE   ");
    sql.append(" qbt.materiel_id = " + materielId);
    sql.append(" and qbt.is_default = '1' ");
    sql.append(" AND qbt.order_no = (   ");
    sql.append(" SELECT   ");
    sql.append(" max(qbc.order_no)   ");
    sql.append(" FROM   ");
    sql.append(" qms_bom_technology qbc   ");
    sql.append(" WHERE   ");
    sql.append(" qbc.materiel_id = " + materielId);
    sql.append(" AND qbc.is_default = '1' ");
    sql.append(" AND qbc.order_no < (   ");
    sql.append(" SELECT   ");
    sql.append(" qb.order_no   ");
    sql.append(" FROM   ");
    sql.append(" qms_bom_technology qb   ");
    sql.append(" WHERE   ");
    sql.append(" qb.id = " + bomTechnologyId);
    sql.append(" )))    ");
    return sql.toString();
}


@Override
public String deleteCheck(String pid){
    String code = "1";
    // 当前数据的互检（M）没有数据
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(pid), "M");
    if (qmsProductionInspectionValueOptional.isPresent()) {
        code = "2";
        return code;
    }
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptionalM = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(pid), "C");
    // 没有不良记录
    if (qmsProductionInspectionValueOptionalM.isPresent()) {
        Long pivId = qmsProductionInspectionValueOptionalM.get().getId();
        List<QmsUnqualifiedProduct> qmsUnqualifiedProductOptional = qmsUnqualifiedProductRepository.findByInspectionValueId(Integer.valueOf(pivId.toString()));
        if (qmsUnqualifiedProductOptional.size() > 0) {
            code = "3";
            return code;
        }
    }
    return code;
}


@Override
public String updateQmsProductionInspectionValues(String inspectionId,String checkNumber,String isOk,String inspectionDiff){
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    Optional<QmsProductionInspectionValue> productionInspectionValues = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(inspectionId), inspectionDiff);
    productionInspectionValues.get().setCheckNumber(checkNumber);
    productionInspectionValues.get().setIsOk(isOk);
    productionInspectionValues.get().setMakeUser(user.getUsername());
    productionInspectionValues.get().setModifyUser(user.getUsername());
    productionInspectionValues.get().setMakeTime(dateUtil.getDBNowDate());
    productionInspectionValues.get().setModifyTime(dateUtil.getDBNowDate());
    qmsProductionInspectionValueRepository.save(productionInspectionValues.get());
    return "success_update";
}


public List<ProductProcessCheckDTO> qmsProductProcessSelfFindAll(HttpServletRequest request){
    List<ProductProcessCheckDTO> qmsProductProcessDto = new ArrayList<ProductProcessCheckDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        String materielCd = request.getParameter("materielCd");
        String materielName = request.getParameter("materielName");
        String processName = request.getParameter("processName");
        String serialNumber = request.getParameter("serialNumber");
        String furnace = request.getParameter("furnace");
        String isOK = request.getParameter("isOK");
        String workno = request.getParameter("workno");
        String inspectionDiff = request.getParameter("inspectionDiff");
        String sql = qmsProductProcessFindAll(materielCd, materielName, processName, serialNumber, furnace, isOK, workno, inspectionDiff);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(ProductProcessCheckDTO.class);
        qmsProductProcessDto = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielId", StandardBasicTypes.INTEGER).addScalar("bomTechnologyId", StandardBasicTypes.INTEGER).addScalar("workno", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("processName", StandardBasicTypes.STRING).addScalar("serialNumber", StandardBasicTypes.STRING).addScalar("furnace", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).addScalar("isOk", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsProductionInspection could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return qmsProductProcessDto;
}


public String createQmsProductionInspectionResult(JSONArray qqcd,JSONObject qpiv){
    // 新增生产检验结果表
    QmsProductionInspectionValue newQpiv = new QmsProductionInspectionValue();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    String isOk = qpiv.getString("isOk");
    String inspectionDiff = qpiv.getString("inspectionDiff");
    String inspectionId = qpiv.getString("inspectionId");
    String checkNumber = qpiv.getString("checkNumber");
    String flagStatus = qpiv.getString("flagStatus");
    newQpiv.setIsOk(isOk);
    newQpiv.setInspectionDiff(inspectionDiff);
    newQpiv.setInspectionId(Integer.valueOf(inspectionId));
    newQpiv.setCheckNumber(checkNumber);
    newQpiv.setFlagStatus(flagStatus);
    newQpiv.setMakeTime(dateUtil.getDBNowDate());
    newQpiv.setModifyTime(dateUtil.getDBNowDate());
    newQpiv.setMakeUser(user.getUsername());
    newQpiv.setModifyUser(user.getUsername());
    Long pivId = qmsProductionInspectionValueRepository.save(newQpiv).getId();
    // 向生产检验项点结果表插入数据
    for (int i = 0; i < qqcd.size(); i++) {
        QmsProductionInspectionResult qmsProductionInspectionResult = new QmsProductionInspectionResult();
        qmsProductionInspectionResult.setInspectionValueId(Integer.valueOf(pivId.toString()));
        qmsProductionInspectionResult.setTestValue(qqcd.getJSONObject(i).getString("testValue"));
        qmsProductionInspectionResult.setFlagStatus("0");
        qmsProductionInspectionResult.setInspectionDiff("C");
        qmsProductionInspectionResult.setPlaceDiff(qqcd.getJSONObject(i).getString("placeDiff"));
        qmsProductionInspectionResult.setControlId(Integer.valueOf(qqcd.getJSONObject(i).getString("controlId")));
        qmsProductionInspectionResult.setInspectionId(Integer.valueOf(qqcd.getJSONObject(i).getString("id")));
        qmsProductionInspectionResult.setMakeTime(dateUtil.getDBNowDate());
        qmsProductionInspectionResult.setModifyTime(dateUtil.getDBNowDate());
        qmsProductionInspectionResult.setMakeUser(user.getUsername());
        qmsProductionInspectionResult.setModifyUser(user.getUsername());
        qmsProductionInspectionResultRepository.save(qmsProductionInspectionResult);
    }
    return pivId.toString();
}


@Override
public ProductProcessCheckDTO findById(Long id){
    ProductProcessCheckDTO productProcessCheck = new ProductProcessCheckDTO();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = qmsProductProcessFindById(id);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(ProductProcessCheckDTO.class);
        productProcessCheck = (ProductProcessCheckDTO) query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("bomTechnologyId", StandardBasicTypes.INTEGER).addScalar("processId", StandardBasicTypes.INTEGER).addScalar("materielId", StandardBasicTypes.INTEGER).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("workno", StandardBasicTypes.STRING).addScalar("processName", StandardBasicTypes.STRING).addScalar("serialNumber", StandardBasicTypes.STRING).addScalar("furnace", StandardBasicTypes.STRING).addScalar("isOk", StandardBasicTypes.STRING).addScalar("makeTime", ZonedDateTimeType.INSTANCE).addScalar("makeUser", StandardBasicTypes.STRING).setResultTransformer(transformer).getSingleResult();
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsProductionInspection could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return productProcessCheck;
}


public String getAssemblyRelationSql(Integer pid){
    StringBuffer sql = new StringBuffer();
    sql = sql.append(" SELECT ");
    sql = sql.append(" T4.product_mode AS productMode, ");
    sql = sql.append(" T2.assembly_count AS assemblyCount, ");
    sql = sql.append(" T4.id AS materielId, ");
    sql = sql.append(" T4.materiel_name AS materielName, ");
    sql = sql.append(" T2.id AS partsAssemblyRelationId, ");
    sql = sql.append(" T3.id productionRelationId, ");
    sql = sql.append(" T6.entry_type AS entryType, ");
    sql = sql.append(" CASE WHEN T3.id IS NULL THEN '' ");
    sql = sql.append(" ELSE T3.actual_use_location END AS actualUseLocation, ");
    sql = sql.append(" CASE WHEN T4.product_mode = 'M' THEN T5.id ");
    sql = sql.append(" ELSE T7.id END AS HID, ");
    sql = sql.append(" CASE WHEN T4.product_mode = 'M' THEN T5.product_num ");
    sql = sql.append(" ELSE T7.goods_cd END AS bianHao, ");
    sql = sql.append(" CASE WHEN T4.product_mode = 'M' THEN '' ");
    sql = sql.append(" ELSE T6.made_factory_cd END AS madeFactoryCd, ");
    sql = sql.append(" CASE WHEN T4.product_mode = 'M' THEN '' ");
    sql = sql.append(" ELSE T6.made_ymd END AS madeYmd, ");
    sql = sql.append(" CASE WHEN T3.modify_user IS NULL THEN '' ELSE T3.modify_user END AS modifyUser ");
    sql = sql.append(" FROM QMS_PRODUCTION_INSPECTION T1 ");
    sql = sql.append(" INNER JOIN QMS_PARTS_ASSEMBLY_RELATION T2 ");
    sql = sql.append(" ON T2.bom_technology_id = T1.bom_technology_id ");
    sql = sql.append(" LEFT JOIN QMS_PRODUCTION_RELATION T3 ON T3.production_inspection_id = T1.id\n ");
    sql = sql.append(" AND T3.assembly_id = T2.id ");
    sql = sql.append(" INNER JOIN QMS_MATERIEL T4 ON T4.id = T2.assembly_materiel_id ");
    sql = sql.append(" LEFT JOIN QMS_product T5 ON T5.id = T3.use_product_id ");
    sql = sql.append(" LEFT JOIN QMS_MATERIEL_DETAILS T7 ON T7.id = T3.assembly_materiel_id ");
    sql = sql.append(" LEFT JOIN QMS_MATERIEL_entry T6 ON T6.ID = T7.entry_id ");
    sql = sql.append(" WHERE T1.id = " + pid);
    sql = sql.append(" ORDER BY T2.assembly_num ASC ");
    return sql.toString();
}


@Override
public List<QmsQualityControlDetailsDto> findByBomTechnologyId(Integer pId,String isDetails){
    List<QmsQualityControlDetailsDto> newResult = new ArrayList<QmsQualityControlDetailsDto>();
    List<QmsQualityControlDetailsDto> result = new ArrayList<QmsQualityControlDetailsDto>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = findByBomTechnologyIdSql(pId, isDetails);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(QmsQualityControlDetailsDto.class);
        result = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("bomTechnologyId", StandardBasicTypes.INTEGER).addScalar("controlId", StandardBasicTypes.INTEGER).addScalar("resultId", StandardBasicTypes.INTEGER).addScalar("inspectionItem", StandardBasicTypes.STRING).addScalar("inspectionInstrument", StandardBasicTypes.STRING).addScalar("technicalRequirement", StandardBasicTypes.STRING).addScalar("resultPlaceDiff", StandardBasicTypes.STRING).addScalar("placeDiff", StandardBasicTypes.STRING).addScalar("standard", StandardBasicTypes.DOUBLE).addScalar("upperDeviation", StandardBasicTypes.DOUBLE).addScalar("lowerDeviation", StandardBasicTypes.DOUBLE).addScalar("inspectionResultDiff", StandardBasicTypes.STRING).addScalar("abcType", StandardBasicTypes.STRING).addScalar("testValue", StandardBasicTypes.STRING).addScalar("modifyUser", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        QmsQualityControlDetailsDto resultDto = null;
        String placeDiff = "";
        for (int i = 0; i < result.size(); i++) {
            // 拷贝一条
            resultDto = (QmsQualityControlDetailsDto) result.get(i).clone();
            // 设置管理人
            List<RbacUser> userList = rbacUserRepository.findByUserCode(resultDto.getModifyUser());
            if (userList.size() != 0) {
                resultDto.setModifyUser(userList.get(0).getUserName());
            }
            // 位别
            if (null == resultDto.getResultId() && resultDto.getPlaceDiff() != null) {
                placeDiff = resultDto.getPlaceDiff();
                String[] placeList = placeDiff.split(",");
                for (int j = 0; j < placeList.length; j++) {
                    resultDto = (QmsQualityControlDetailsDto) result.get(i).clone();
                    resultDto.setPlaceDiff(placeList[j]);
                    newResult.add(resultDto);
                }
            } else {
                newResult.add(resultDto);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsQualityControlDetails could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return newResult;
}


@Override
public JSONObject chackPreProcess(JSONObject jsonObject){
    JSONObject result = new JSONObject();
    // 首工序不合格
    result.put("code", "0");
    JSONObject productionInspection = jsonObject.getJSONObject("productionInspection");
    String bomTechnologyId = productionInspection.getString("bomTechnologyId");
    String pid = productionInspection.getString("id");
    Optional<QmsBomTechnology> bomTechnologyOptional = qmsBomTechnologyRepository.findById(Long.valueOf(bomTechnologyId));
    // 首工序则不检验
    // if(bomTechnologyOptional.isPresent()){
    // String operationType = bomTechnologyOptional.get().getOperationType();
    // if(operationType.equals("F")){
    // result.put("code", "1");
    // return result;
    // }
    // }
    EntityManager em = emf.createEntityManager();
    try {
        List<ProductProcessCheckDTO> productProcessCheckList = new ArrayList<ProductProcessCheckDTO>();
        String sql = qmsChackPreProcessSql(jsonObject);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(ProductProcessCheckDTO.class);
        productProcessCheckList = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("isOk", StandardBasicTypes.STRING).addScalar("approveResultDiff", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        if (productProcessCheckList.size() == 0) {
            result.put("code", "1");
        } else {
            ProductProcessCheckDTO productProcessCheckDTO = productProcessCheckList.get(0);
            String isOk = productProcessCheckDTO.getIsOk();
            String approveResultDiff = productProcessCheckDTO.getApproveResultDiff();
            if (isOk != null) {
                if (isOk.equals("1")) {
                    result.put("code", "1");
                }
            }
            if (approveResultDiff != null) {
                if (approveResultDiff.equals("3")) {
                    result.put("code", "1");
                }
            }
        }
        // 上工序检验合格再判断审批人
        if (result.getString("code") == "1") {
            Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(pid), "C");
            if (qmsProductionInspectionValueOptional.isPresent()) {
                Long valueId = qmsProductionInspectionValueOptional.get().getId();
                List<QmsUnqualifiedProduct> qmsUnqualifiedProductList = qmsUnqualifiedProductRepository.findByInspectionValueIdAndApproveUserIdIsNotNull(Integer.valueOf(valueId.toString()));
                if (qmsUnqualifiedProductList.size() == 0) {
                    result.put("code", "1");
                } else {
                    result.put("code", "2");
                }
            }
        }
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsProductionInspection could not be found");
    } finally {
        em.close();
    }
    return result;
}


public String savePIR(JSONObject jsonObject){
    // 不合格集合
    JSONArray errorList = jsonObject.getJSONArray("errorList");
    // 工序质量控制点数据
    JSONArray qqcd = jsonObject.getJSONArray("qqcd");
    // 生产检验结果数据
    JSONObject qpiv = jsonObject.getJSONObject("qpiv");
    // 生成检验数据
    JSONObject qpi = jsonObject.getJSONObject("qpi");
    // 装配关系
    JSONArray qpar = jsonObject.getJSONArray("qpar");
    // 不合格等级
    String controlLevel = jsonObject.getString("controlLevel");
    // 生产检验项Id
    String resultId_0 = qqcd.getJSONObject(0).getString("resultId");
    // 生产检验结果表Id
    String qmsProductionInspectionValueId = "";
    // 生产检验Id
    String pid = qpi.getString("id");
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 任务表实例获取
    Optional<QmsProductionInspection> qmsProductionInspectionOptional = qmsProductionInspectionRepository.findById(Long.valueOf(pid));
    QmsProductionInspection qmsProductionInspection = qmsProductionInspectionOptional.get();
    if (qqcd.size() > 0) {
        // 新增生产检验
        if (resultId_0 == null || resultId_0 == "") {
            qmsProductionInspectionValueId = createQmsProductionInspectionResult(qqcd, qpiv);
        } else {
            // 更新生产检验
            qmsProductionInspectionValueId = updateQmsProductionInspectionResult(qqcd, qpiv, pid);
        }
        // 不合格检验
        if (errorList.size() > 0) {
            createQmsUnqualifiedProduct(errorList, controlLevel, qpi, qmsProductionInspectionValueId);
            qmsProductionInspection.setIsOk("0");
        } else {
            qmsProductionInspection.setIsOk("1");
        }
        // 更新任务表
        qmsProductionInspection.setModifyTime(dateUtil.getDBNowDate());
        qmsProductionInspection.setModifyUser(user.getUsername());
        qmsProductionInspectionRepository.save(qmsProductionInspection);
    }
    return qmsProductionInspectionValueId;
}


public String qmsProductProcessFindById(Long id){
    StringBuffer sql = new StringBuffer();
    sql.append(" select   ");
    sql.append(" qpi.id,  ");
    sql.append(" qpi.bom_technology_id As bomTechnologyId,  ");
    sql.append(" qm.id AS materielId,  ");
    sql.append(" qm.materiel_cd AS materielCd,  ");
    sql.append(" qm.materiel_name AS materielName,  ");
    sql.append(" qms_process.id AS processId,  ");
    sql.append(" qms_process.process_name AS processName,  ");
    sql.append(" qpi.serial_number AS serialNumber,   ");
    sql.append(" qpi.workno AS workno,   ");
    sql.append(" qpi.furnace,   ");
    sql.append(" qpi.workno,   ");
    sql.append(" qpi.remark,    ");
    sql.append(" qpi.make_time AS makeTime,   ");
    sql.append(" qpi.make_user AS makeUser,   ");
    sql.append(" CASE WHEN qpiv.id IS NULL THEN 2 ELSE qpi.is_ok END AS isOK,   ");
    sql.append(" qpiv.id   ");
    sql.append(" FROM qms_production_inspection qpi   ");
    sql.append(" INNER JOIN qms_materiel qm   ");
    sql.append(" ON qpi.materiel_id = qm.id   ");
    sql.append(" INNER JOIN qms_bom_technology qbt   ");
    sql.append(" ON qpi.bom_technology_id = qbt.id   ");
    sql.append(" INNER JOIN qms_process   ");
    sql.append(" ON qbt.process_id = qms_process.id   ");
    sql.append(" LEFT JOIN qms_production_inspection_value qpiv   ");
    sql.append(" ON qpiv.inspection_id = qpi.id   ");
    sql.append(" AND qpiv.inspection_diff = 'C'   ");
    sql.append(" WHERE qpi.id = " + id + "   ");
    sql.append(" ORDER BY qpi.id DESC ");
    return sql.toString();
}


public String qmsProductProcessFindAll(String materielCd,String materielName,String processName,String serialNumber,String furnace,String isOK,String workno,String inspectionDiff){
    StringBuffer sql = new StringBuffer();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    sql.append(" select   ");
    sql.append(" qpi.id,  ");
    sql.append(" qm.id AS materielId,  ");
    sql.append(" qm.materiel_cd AS materielCd,  ");
    sql.append(" qm.materiel_name AS materielName,  ");
    sql.append(" qpi.bom_technology_id AS bomTechnologyId,  ");
    sql.append(" qms_process.process_name AS processName,  ");
    sql.append(" qpi.serial_number AS serialNumber,   ");
    sql.append(" qpi.furnace,   ");
    sql.append(" qpi.workno,   ");
    sql.append(" qpi.remark,   ");
    sql.append(" CASE WHEN qpiv.id IS NULL THEN 2 ELSE qpi.is_ok END AS isOK,   ");
    sql.append(" qpiv.id   ");
    sql.append(" FROM qms_production_inspection qpi   ");
    sql.append(" INNER JOIN qms_materiel qm   ");
    sql.append(" ON qpi.materiel_id = qm.id   ");
    if (!"".equals(materielCd) && materielCd != null)
        sql.append(" AND qm.materiel_cd LIKE '%" + materielCd + "%' ");
    if (!"".equals(materielName) && materielName != null)
        sql.append(" AND qm.materiel_name LIKE '%" + materielName + "%' ");
    sql.append(" INNER JOIN qms_bom_technology qbt   ");
    sql.append(" ON qpi.bom_technology_id = qbt.id   ");
    sql.append(" INNER JOIN qms_process   ");
    sql.append(" ON qbt.process_id = qms_process.id   ");
    if (!"".equals(processName) && processName != null)
        sql.append(" AND qms_process.process_name LIKE '%" + processName + "%' ");
    sql.append(" LEFT JOIN qms_production_inspection_value qpiv   ");
    sql.append(" ON qpiv.inspection_id = qpi.id   ");
    sql.append(" AND qpiv.inspection_diff = 'C'   ");
    // 关联班组
    sql.append(" INNER JOIN rbac_user rUser   ");
    sql.append(" ON qbt.work_group_cd = rUser.organization_cd And rUser.user_code = '" + user.getUsername() + "' ");
    sql.append(" WHERE 1= 1   ");
    if (!"".equals(serialNumber) && serialNumber != null)
        sql.append(" AND qpi.serial_number LIKE '%" + serialNumber + "%' ");
    if (!"".equals(furnace) && furnace != null)
        sql.append(" AND qpi.furnace LIKE '%" + furnace + "%' ");
    if (!"".equals(workno) && workno != null)
        sql.append(" AND qpi.workno LIKE '%" + workno + "%' ");
    if (isOK.equals("2")) {
        sql.append(" AND qpiv.id IS NULL ");
    } else {
        sql.append(" AND qpi.is_ok = " + isOK);
        sql.append(" AND qpiv.id IS NOT NULL ");
    }
    sql.append(" ORDER BY qpi.id DESC ");
    return sql.toString();
}


public void saveProductionRelation(JSONObject jsonObject){
    // 生成检验数据
    JSONObject qpi = jsonObject.getJSONObject("qpi");
    // 装配关系
    JSONArray qpar = jsonObject.getJSONArray("qpar");
    // 生产检验Id
    String pid = qpi.getString("id");
    // 物料Id
    String mid = qpi.getString("materielId");
    // 编码
    String serialNumber = qpi.getString("serialNumber");
    // 编辑区分
    Boolean edit = false;
    if (qpar.size() > 0) {
        String productionRelationId = qpar.getJSONObject(0).getString("productionRelationId");
        if (productionRelationId != null && productionRelationId != "") {
            edit = true;
        }
    }
    QmsProductionInspection piObject = qmsProductionInspectionRepository.findByMaterielIdAndSerialNumber(Integer.valueOf(mid), serialNumber).get(0);
    Long productId = piObject.getId();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 更新OR新增
    if (edit) {
        for (int i = 0; i < qpar.size(); i++) {
            // 隐藏Id 产品？物料进场
            String hid = qpar.getJSONObject(i).getString("hid");
            // 生产检验装配关系Id
            String productionRelationId = qpar.getJSONObject(i).getString("productionRelationId");
            // 位别
            String actualUseLocation = qpar.getJSONObject(i).getString("actualUseLocation");
            // 来源区分
            String fromDiff = qpar.getJSONObject(i).getString("productMode");
            // 到货类型 0：单件，1：批次
            String entryType = qpar.getJSONObject(i).getString("entryType");
            // 获取生产检验装配实例
            Optional<QmsProductionRelation> qmsProductionRelation = qmsProductionRelationRepository.findById(Long.valueOf(productionRelationId));
            qmsProductionRelation.get().setActualUseLocation(actualUseLocation);
            qmsProductionRelation.get().setModifyUser(user.getUsername());
            qmsProductionRelation.get().setModifyTime(dateUtil.getDBNowDate());
            if ("M".equals(fromDiff)) {
                qmsProductionRelation.get().setUseProductId(Integer.valueOf(hid));
            } else {
                qmsProductionRelation.get().setAssemblyMaterielId(Integer.valueOf(hid));
                // 批次
                if ("1".equals(entryType)) {
                    // 入厂单件数量
                    QmsMaterielDetails qmsMaterielDetails = qmsMaterielDetailsRepository.findById(Long.valueOf(hid)).get();
                    Integer goodQuantity = qmsMaterielDetails.getGoodsQuantity();
                    // 更新排除自己
                    Integer prSize = qmsProductionRelationRepository.findAllByAssemblyMaterielIdAndIdIsNot(Integer.valueOf(hid), Long.valueOf(productionRelationId)).size();
                    if (prSize >= goodQuantity) {
                        throw new InternalServerErrorException("2");
                    }
                }
            }
            qmsProductionRelationRepository.save(qmsProductionRelation.get());
        }
    } else {
        for (int i = 0; i < qpar.size(); i++) {
            QmsProductionRelation qmsProductionRelation = new QmsProductionRelation();
            // 隐藏Id 产品？物料进场
            String hid = qpar.getJSONObject(i).getString("hid");
            // 工序装配关系表Id
            String partsAssemblyRelationId = qpar.getJSONObject(i).getString("partsAssemblyRelationId");
            // 位别
            String actualUseLocation = qpar.getJSONObject(i).getString("actualUseLocation");
            // 来源区分
            String fromDiff = qpar.getJSONObject(i).getString("productMode");
            // 生产关系Id
            // String productionRelationId = qpar.getJSONObject(i).getString("productionRelationId");
            // 到货类型 0：单件，1：批次
            String entryType = qpar.getJSONObject(i).getString("entryType");
            qmsProductionRelation.setProductionInspectionId(Integer.valueOf(pid));
            qmsProductionRelation.setAssemblyId(Integer.valueOf(partsAssemblyRelationId));
            qmsProductionRelation.setDoProductId(Integer.valueOf(productId.toString()));
            qmsProductionRelation.setActualUseLocation(actualUseLocation);
            qmsProductionRelation.setFromDiff(fromDiff);
            qmsProductionRelation.setFlagStatus("0");
            qmsProductionRelation.setMakeUser(user.getUsername());
            qmsProductionRelation.setModifyUser(user.getUsername());
            qmsProductionRelation.setMakeTime(dateUtil.getDBNowDate());
            qmsProductionRelation.setModifyTime(dateUtil.getDBNowDate());
            if (fromDiff.equals("M")) {
                qmsProductionRelation.setUseProductId(Integer.valueOf(hid));
            } else {
                qmsProductionRelation.setAssemblyMaterielId(Integer.valueOf(hid));
                // 批次
                if (entryType.equals("1")) {
                    // 入厂单件数量
                    QmsMaterielDetails qmsMaterielDetails = qmsMaterielDetailsRepository.findById(Long.valueOf(hid)).get();
                    Integer goodQuantity = qmsMaterielDetails.getGoodsQuantity();
                    Integer prSize = qmsProductionRelationRepository.findByAssemblyMaterielId(Integer.valueOf(hid)).size();
                    if (prSize >= goodQuantity) {
                        throw new InternalServerErrorException("2");
                    }
                }
            }
            qmsProductionRelationRepository.save(qmsProductionRelation);
        }
    }
}


public void createQmsUnqualifiedProduct(JSONArray errorList,String controlLevel,JSONObject qpi,String qmsProductionInspectionValueId){
    QmsUnqualifiedProduct qmsUnqualifiedProduct = new QmsUnqualifiedProduct();
    // 得到传过来的值
    String bomTechnologyId = qpi.getString("bomTechnologyId");
    String processId = qpi.getString("processId");
    String materielId = qpi.getString("materielId");
    String serialNumber = qpi.getString("serialNumber");
    String furnace = qpi.getString("furnace");
    qmsUnqualifiedProduct.setInspectionValueId(Integer.valueOf(qmsProductionInspectionValueId));
    qmsUnqualifiedProduct.setBomTechnologyId(Integer.valueOf(bomTechnologyId));
    qmsUnqualifiedProduct.setProcessId(Integer.valueOf(processId));
    qmsUnqualifiedProduct.setFlagStatus("0");
    qmsUnqualifiedProduct.setMaterielId(Integer.valueOf(materielId));
    qmsUnqualifiedProduct.setControlLevel(controlLevel);
    if (serialNumber.equals(null)) {
        serialNumber = "";
    }
    if (furnace.equals(null)) {
        furnace = "";
    }
    qmsUnqualifiedProduct.setSeriNumber(serialNumber);
    qmsUnqualifiedProduct.setFurnace(furnace);
    qmsUnqualifiedProduct.setApproveStepNum(0);
    qmsUnqualifiedProduct.setInspectionDiff("C");
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsUnqualifiedProduct.setMakeUser(user.getUsername());
    qmsUnqualifiedProduct.setModifyUser(user.getUsername());
    qmsUnqualifiedProduct.setMakeTime(dateUtil.getDBNowDate());
    qmsUnqualifiedProduct.setModifyTime(dateUtil.getDBNowDate());
    QmsUnqualifiedProduct newObj = qmsUnqualifiedProductRepository.save(qmsUnqualifiedProduct);
    Long unqualifiedProductId = newObj.getId();
    for (int i = 0; i < errorList.size(); i++) {
        QmsUnqualifiedProductDetails qmsUnqualifiedProductDetails = new QmsUnqualifiedProductDetails();
        // 检查项目
        String inspectionItem = errorList.getJSONObject(i).getString("inspectionItem");
        // 检查器具
        String inspectionInstrument = errorList.getJSONObject(i).getString("inspectionInstrument");
        // 标准值
        Double standard = errorList.getJSONObject(i).getDouble("standard");
        // 上偏差
        Double upperDeviation = errorList.getJSONObject(i).getDouble("upperDeviation");
        // 下偏差
        Double lowerDeviation = errorList.getJSONObject(i).getDouble("lowerDeviation");
        // 质量检查项目级别
        String abcType = errorList.getJSONObject(i).getString("abcType");
        // 检查结果
        String testValue = errorList.getJSONObject(i).getString("testValue");
        // 技术要求
        String technicalRequirement = errorList.getJSONObject(i).getString("technicalRequirement");
        qmsUnqualifiedProductDetails.setUnqualifiedProductId(Integer.valueOf(unqualifiedProductId.toString()));
        qmsUnqualifiedProductDetails.setInspectionItem(inspectionItem);
        qmsUnqualifiedProductDetails.setInspectionInstrument(inspectionInstrument);
        qmsUnqualifiedProductDetails.setTechnicalRequirement(technicalRequirement);
        qmsUnqualifiedProductDetails.setStandard(standard);
        qmsUnqualifiedProductDetails.setUpperDeviation(upperDeviation);
        qmsUnqualifiedProductDetails.setLowerDeviation(lowerDeviation);
        qmsUnqualifiedProductDetails.setControlLevel(abcType);
        qmsUnqualifiedProductDetails.setCheckResult(testValue);
        qmsUnqualifiedProductDetails.setFlagStatus("0");
        qmsUnqualifiedProductDetails.setMakeUser(user.getUsername());
        qmsUnqualifiedProductDetails.setModifyUser(user.getUsername());
        qmsUnqualifiedProductDetails.setMakeTime(dateUtil.getDBNowDate());
        qmsUnqualifiedProductDetails.setModifyTime(dateUtil.getDBNowDate());
        qmsUnqualifiedProductDetailRepository.save(qmsUnqualifiedProductDetails);
    }
}


}