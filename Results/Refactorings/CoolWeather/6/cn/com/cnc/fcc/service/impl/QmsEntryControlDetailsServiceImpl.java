import java.time.ZonedDateTime;
import java.util;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import cn.com.cnc.fcc.service.dto.GroupEntryControlDetailsDTO;
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
import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.domain.QmsEntryControlDetails;
import cn.com.cnc.fcc.repository.QmsEntryControlDetailsRepository;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.service.QmsEntryControlDetailsService;
import cn.com.cnc.fcc.service.dto.GroupEntryControlDetailsInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsEntryControlDetailsDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
@Service
public class QmsEntryControlDetailsServiceImpl implements QmsEntryControlDetailsService,cn.com.cnc.fcc.service.QmsEntryControlDetailsService{

 private  Logger log;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;

 private  QmsMaterielRepository qmsMaterielRepository;

 private  QmsEntryControlDetailsRepository qmsEntryControlDetailsRepository;


@SuppressWarnings({ "unchecked", "rawtypes" })
@Override
@Transactional
public JSONObject uploadData(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // 实例化接受结果集
    List<QmsEntryControlDetails> organizationInfoList = new ArrayList<QmsEntryControlDetails>();
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
    // 异常信息判断
    String errorInfo = "0";
    returnData.put("flag", "1");
    List listInfo = new ArrayList<>();
    // 判断文件路径
    if (files.isEmpty()) {
        returnData.put("status", "error");
        returnData.put("message", "请重新上传！");
    } else {
        try {
            // 取数据
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), GroupEntryControlDetailsDTO.class, 7);
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
                    QmsEntryControlDetails qmsEntryControlDetails = new QmsEntryControlDetails();
                    if ("".equals(data.getJSONObject(i).getString("materielCd"))) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + (i + 1) + "条数据;物料编码不能为空！");
                        continue;
                    }
                    // 物料编码最大长度check
                    if (data.getJSONObject(i).getString("materielCd").length() > 10) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + (i + 1) + "条数据;物料编码字数超长,最长10！");
                        continue;
                    }
                    String materielCd = data.getJSONObject(i).getString("materielCd");
                    Optional<QmsMateriel> qmsMateriel1 = qmsMaterielRepository.findQmsMaterielByMaterielCdAndFlagStatus(materielCd, "0");
                    if (!qmsMateriel1.isPresent()) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + (i + 1) + "条数据;此物料不存在！");
                        continue;
                    }
                    QmsMateriel qmsMateriel = qmsMateriel1.get();
                    // 检查项目最大长度check
                    if ("".equals(data.getJSONObject(i).getString("inspectionItem"))) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + (i + 1) + "条数据;检查项目不能为空！");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("inspectionItem").length() > 200) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + (i + 1) + "条数据;检查项目字数超长,最长200！");
                        continue;
                    }
                    // 检查器具最大长度check
                    if (data.getJSONObject(i).getString("inspectionInstrument").length() > 100) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + (i + 1) + "条数据;检查器具字数超长,最长100！");
                        continue;
                    }
                    // 标准值负号check
                    if (!"".equals(data.getJSONObject(i).getString("standard"))) {
                        if (-1 != data.getJSONObject(i).getString("standard").indexOf("-")) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;标准值不能为负数");
                            continue;
                        }
                        if (data.getJSONObject(i).getString("standard").length() > 20) {
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;标准值不是有效数字！");
                            continue;
                        }
                    }
                    String str = "(([0-9]+)([.]([0-9]+))?|(([0-9]+))?)$";
                    // 标准值最大长度check 标准值入力 上偏差和下偏差必须入力
                    if (!"".equals(data.getJSONObject(i).getString("standard"))) {
                        if (!data.getJSONObject(i).getString("standard").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;标准值不是有效数字！");
                            continue;
                        }
                        if ("".equals(data.getJSONObject(i).getString("upperDeviation"))) {
                            // 返回错误信息：上偏差必须输入
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;标准值不为空上偏差必须输入！");
                            continue;
                        }
                        if ("".equals(data.getJSONObject(i).getString("lowerDeviation"))) {
                            // 返回错误信息：下偏差必须输入
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;标准值不为空下偏差必须输入！");
                            continue;
                        }
                    }
                    // 上偏差负号check
                    if (!"".equals(data.getJSONObject(i).getString("upperDeviation"))) {
                        if (-1 != data.getJSONObject(i).getString("upperDeviation").indexOf("-")) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;上偏差不能为负数！");
                            continue;
                        }
                        if (!data.getJSONObject(i).getString("upperDeviation").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;上偏差不是有效数字！");
                            continue;
                        }
                        if (data.getJSONObject(i).getString("upperDeviation").length() > 20) {
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;上偏差不是有效数字！");
                            continue;
                        }
                    }
                    // 下偏差负号check
                    if (!"".equals(data.getJSONObject(i).getString("lowerDeviation"))) {
                        if (-1 != data.getJSONObject(i).getString("lowerDeviation").indexOf("-")) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;下偏差不能为负数");
                            continue;
                        }
                        if (!data.getJSONObject(i).getString("lowerDeviation").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;下偏差不能为负数！");
                            continue;
                        }
                        if (data.getJSONObject(i).getString("lowerDeviation").length() > 20) {
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;下偏差不是有效数字！");
                            continue;
                        }
                    }
                    // 上偏差最大长度check
                    if (!"".equals(data.getJSONObject(i).getString("upperDeviation"))) {
                        if (!data.getJSONObject(i).getString("upperDeviation").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;上偏差不是有效数字！");
                            continue;
                        }
                    }
                    // 下偏差最大长度check
                    if (!"".equals(data.getJSONObject(i).getString("lowerDeviation"))) {
                        if (!data.getJSONObject(i).getString("lowerDeviation").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + (i + 1) + "条数据;下偏差不是有效数字！");
                            continue;
                        }
                    }
                    // 技术要求最大长度check
                    if (data.getJSONObject(i).getString("technicalRequirement").length() > 1000) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + (i + 1) + "条数据;技术要求字数超长,最长1000！");
                        continue;
                    }
                    List<QmsEntryControlDetails> qmsEntryControlDetails1 = qmsEntryControlDetailsRepository.findAllByMaterielIdAndFlagStatusAndIsValidOrderByItemNumber(qmsMateriel.getId().intValue(), "0", "1");
                    // 物料编码
                    qmsEntryControlDetails.setMaterielId(qmsMateriel.getId().intValue());
                    // 检查项目
                    qmsEntryControlDetails.setInspectionItem(data.getJSONObject(i).getString("inspectionItem"));
                    // 检查器具
                    qmsEntryControlDetails.setInspectionInstrument(data.getJSONObject(i).getString("inspectionInstrument"));
                    // 标准值
                    if (!"".equals(data.getJSONObject(i).getString("standard"))) {
                        qmsEntryControlDetails.setStandard(Double.valueOf(data.getJSONObject(i).getString("standard")));
                    }
                    // 上偏差
                    if (!"".equals(data.getJSONObject(i).getString("upperDeviation"))) {
                        qmsEntryControlDetails.setUpperDeviation(Double.valueOf(data.getJSONObject(i).getString("upperDeviation")));
                    }
                    // 下偏差
                    if (!"".equals(data.getJSONObject(i).getString("lowerDeviation"))) {
                        qmsEntryControlDetails.setLowerDeviation(Double.valueOf(data.getJSONObject(i).getString("lowerDeviation")));
                    }
                    // 技术要求
                    qmsEntryControlDetails.setTechnicalRequirement(data.getJSONObject(i).getString("technicalRequirement"));
                    // 项目编号
                    qmsEntryControlDetails.setItemNumber(qmsEntryControlDetails1.size() + 1);
                    // 检验结果区分
                    if ("".equals(data.getJSONObject(i).getString("standard"))) {
                        qmsEntryControlDetails.setInspectionResultDiff("1");
                    } else {
                        qmsEntryControlDetails.setInspectionResultDiff("0");
                    }
                    // 判断物料编码是否为空
                    if (!"".equals(data.getJSONObject(i).getString("materielCd"))) {
                        // 实例化结果集
                        List<QmsEntryControlDetails> entryControlDetails = new ArrayList<QmsEntryControlDetails>();
                    // // 取得物料编码和检查项目是否有数据
                    // entryControlDetails = qmsEntryControlDetailsRepository.findByMaterielIdAndInspectionItemAndFlagStatus(data.getJSONObject(i).getInteger("materielCd"),data.getJSONObject(i).getString("inspectionItem"),"0");
                    // 
                    // // 判断物料编码是否已经存在
                    // if(entryControlDetails.size() != 0){
                    // // 返回错误信息：物料编码已存在
                    // returnData.put("flag", "0");
                    // errorMess.add("第"+i+"条数据;物料编码和检查项目已存在;");
                    // continue;
                    // }
                    } else {
                        // 返回错误信息：物料编码为空
                        returnData.put("status", "error");
                        returnData.put("message", "物料编码为空:");
                        continue;
                    }
                    // 删除标识赋值
                    qmsEntryControlDetails.setFlagStatus("0");
                    // 创建人赋值
                    qmsEntryControlDetails.setMakeUser(userCd);
                    // 创建时间赋值
                    qmsEntryControlDetails.setMakeTime(nowTime);
                    // 更新人赋值
                    qmsEntryControlDetails.setModifyUser(userCd);
                    // 更新时间赋值
                    qmsEntryControlDetails.setModifyTime(nowTime);
                    // 添加到接收集合中
                    organizationInfoList.add(qmsEntryControlDetails);
                    // 数据插入
                    qmsEntryControlDetailsRepository.save(qmsEntryControlDetails);
                    // 用于判断上传的表格中数据的物料ID是否有相同 的
                    listInfo.add(data.getJSONObject(i).getString("materielCd"));
                }
                // 实例化结果集
                HashSet set = new HashSet<>(listInfo);
            // 判断表格中的物料ID是否有重复
            // if(listInfo.size() != set.size()){
            // // 返回错误信息：表格中数据的物料ID有相同的
            // returnData.put("status", "error");
            // returnData.put("message", "导入的数据中数据有重复！");
            // errorInfo = "1";
            // throw new RuntimeException("导入的数据中数据有重复！");
            // }
            }
            if (returnData.getString("flag") == "1") {
                returnData.put("status", "success");
                returnData.put("message", "操作成功!本次一共处理" + data.size() + "条数据");
            } else {
                // 将所有的错误信息拼接成一个字符串
                String error = "";
                for (int i = 0; i < errorMess.size(); i++) {
                    error = error + errorMess.get(i) + "。";
                }
                returnData.put("status", "success");
                returnData.put("message1", "成功件数:" + (data.size() - errorMess.size()) + "件。" + "失败件数:" + errorMess.size() + "件。");
                returnData.put("message2", "失败原因:" + error);
            }
        } catch (Exception e) {
            // 事物回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // 打印错误信息
            log.info(e.getMessage());
            if ("0".equals(errorInfo)) {
                // 返回错误信息：数据插入异常
                returnData.put("status", "error");
                returnData.put("message", "数据插入异常！");
            }
        }
    }
    // 返回结果
    return returnData;
}


public String getSelectAllInfo(HashMap<String,Object> param){
    // 模糊查询物料编码
    String materielCdVague = param.get("materielCdVague").toString();
    // 模糊查询物料名称
    String materielNameVague = param.get("materielNameVague").toString();
    // 模糊查询检查项目
    String inspectionItemVague = param.get("inspectionItemVague").toString();
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT qecd.id as \"id\" , ");
    objSqlContent.append(" qecd.materiel_id as \"materielId\" , ");
    objSqlContent.append(" qm.materiel_name as \"materielName\" , ");
    objSqlContent.append(" qecd.inspection_item as \"inspectionItem\" , ");
    objSqlContent.append(" qecd.technical_requirement as \"technicalRequirement\" , ");
    objSqlContent.append(" qecd.standard as \"standard\" , ");
    objSqlContent.append(" qecd.remark as \"remark\" ");
    objSqlContent.append(" FROM  qms_entry_control_details qecd ");
    objSqlContent.append(" LEFT JOIN qms_materiel qm ON qecd.materiel_id = qm.materiel_cd AND qm.flag_status = 0");
    objSqlContent.append(" WHERE qecd.flag_status = 0 ");
    // 物料编码是否为空
    if (!"".equals(materielCdVague)) {
        objSqlContent.append(" AND qecd.materiel_id LIKE :materielCdVague ");
    }
    // 物料名称是否为空
    if (!"".equals(materielNameVague)) {
        objSqlContent.append(" AND qm.materiel_name LIKE :materielNameVague ");
    }
    // 检查项目是否为空
    if (!"".equals(inspectionItemVague)) {
        objSqlContent.append(" AND qecd.inspection_item LIKE :inspectionItemVague ");
    }
    objSqlContent.append(" ORDER BY qecd.id DESC ");
    objSqlContent.append(" LIMIT :start , :end ");
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsEntryControlDetailsDTO> getAllInfoNumber(HashMap<String,Object> param){
    List<QmsEntryControlDetailsDTO> entryControlDetails = new ArrayList<QmsEntryControlDetailsDTO>();
    // 模糊查询物料编码
    String materielCdVague = param.get("materielCdVague").toString();
    // 模糊查询物料名称
    String materielNameVague = param.get("materielNameVague").toString();
    // 模糊查询检查项目
    String inspectionItemVague = param.get("inspectionItemVague").toString();
    // 取得分页数
    Integer pageSize = (Integer.valueOf(param.get("sizeNumber").toString()) == 0 ? 0 : Integer.valueOf(param.get("sizeNumber").toString()));
    Integer pageNum = Integer.valueOf(param.get("pageNumber").toString());
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectNumberAllInfo(param));
        ResultTransformer transformer = Transformers.aliasToBean(QmsEntryControlDetailsDTO.class);
        // 物料编码是否为空
        if (!"".equals(materielCdVague)) {
            query.setParameter("materielCdVague", "%" + materielCdVague + "%");
        }
        // 物料名称是否为空
        if (!"".equals(materielNameVague)) {
            query.setParameter("materielNameVague", "%" + materielNameVague + "%");
        }
        // 检查项目是否为空
        if (!"".equals(inspectionItemVague)) {
            query.setParameter("inspectionItemVague", "%" + inspectionItemVague + "%");
        }
        query.setParameter("start", (pageNum - 1) * pageSize);
        query.setParameter("end", pageSize);
        entryControlDetails = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("numberCount", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回结果
    return entryControlDetails;
}


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsEntryControlDetailsDTO> selectAllInfo(HashMap<String,Object> param){
    List<QmsEntryControlDetailsDTO> entryControlDetails = new ArrayList<QmsEntryControlDetailsDTO>();
    // 模糊查询物料编码
    String materielCdVague = param.get("materielCdVague").toString();
    // 模糊查询物料名称
    String materielNameVague = param.get("materielNameVague").toString();
    // 模糊查询检查项目
    String inspectionItemVague = param.get("inspectionItemVague").toString();
    // 取得分页数
    Integer pageSize = (Integer.valueOf(param.get("sizeNumber").toString()) == 0 ? 0 : Integer.valueOf(param.get("sizeNumber").toString()));
    Integer pageNum = Integer.valueOf(param.get("pageNumber").toString());
    // 实例化工厂类
    EntityManager em = emf.createEntityManager();
    try {
        // 查询
        Query query = em.createNativeQuery(getSelectAllInfo(param));
        ResultTransformer transformer = Transformers.aliasToBean(QmsEntryControlDetailsDTO.class);
        // 物料编码是否为空
        if (!"".equals(materielCdVague)) {
            query.setParameter("materielCdVague", "%" + materielCdVague + "%");
        }
        // 物料名称是否为空
        if (!"".equals(materielNameVague)) {
            query.setParameter("materielNameVague", "%" + materielNameVague + "%");
        }
        // 检查项目是否为空
        if (!"".equals(inspectionItemVague)) {
            query.setParameter("inspectionItemVague", "%" + inspectionItemVague + "%");
        }
        query.setParameter("start", (pageNum - 1) * pageSize);
        query.setParameter("end", pageSize);
        entryControlDetails = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("materielId", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("inspectionItem", StandardBasicTypes.STRING).addScalar("technicalRequirement", StandardBasicTypes.STRING).addScalar("standard", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
    } finally {
        em.close();
    }
    // 返回结果
    return entryControlDetails;
}


public String getSelectNumberAllInfo(HashMap<String,Object> param){
    // 模糊查询物料编码
    String materielCdVague = param.get("materielCdVague").toString();
    // 模糊查询物料名称
    String materielNameVague = param.get("materielNameVague").toString();
    // 模糊查询检查项目
    String inspectionItemVague = param.get("inspectionItemVague").toString();
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT count(qecd.id) as \"numberCount\" ");
    objSqlContent.append(" FROM  qms_entry_control_details qecd ");
    objSqlContent.append(" LEFT JOIN qms_materiel qm ON qecd.materiel_id = qm.materiel_cd AND qm.flag_status = 0");
    objSqlContent.append(" WHERE qecd.flag_status = 0 ");
    // 物料编码是否为空
    if (!"".equals(materielCdVague)) {
        objSqlContent.append(" AND qecd.materiel_id LIKE :materielCdVague ");
    }
    // 物料名称是否为空
    if (!"".equals(materielNameVague)) {
        objSqlContent.append(" AND qm.materiel_name LIKE :materielNameVague ");
    }
    // 检查项目是否为空
    if (!"".equals(inspectionItemVague)) {
        objSqlContent.append(" AND qecd.inspection_item LIKE :inspectionItemVague ");
    }
    objSqlContent.append(" ORDER BY qecd.id DESC ");
    objSqlContent.append(" LIMIT :start , :end ");
    // 返回值
    return objSqlContent.toString();
}


@SuppressWarnings({ "unchecked", "rawtypes" })
@Override
@Transactional
public JSONObject uploadUserDepart(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // 实例化接受结果集
    List<QmsEntryControlDetails> organizationInfoList = new ArrayList<QmsEntryControlDetails>();
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
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), GroupEntryControlDetailsInfoDTO.class, 9);
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
                    QmsEntryControlDetails organizationInfo = new QmsEntryControlDetails();
                    if ("".equals(data.getJSONObject(i).getString("materielCd"))) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + i + "条数据;物料编码字数超长,最长10！");
                        continue;
                    }
                    // 物料编码最大长度check
                    if (data.getJSONObject(i).getString("materielCd").length() > 10) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + i + "条数据;物料编码字数超长,最长10！");
                        continue;
                    }
                    // 检查项目最大长度check
                    if (data.getJSONObject(i).getString("inspectionItem").length() > 100) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + i + "条数据;检查项目字数超长,最长10！");
                        continue;
                    }
                    // 检查器具最大长度check
                    if (data.getJSONObject(i).getString("inspectionInstrument").length() > 100) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + i + "条数据;检查器具字数超长,最长10！");
                        continue;
                    }
                    String str = "^[0-9]{1,14}+(.[0-9]{1,6})?$";
                    // 标准值最大长度check
                    if (!"".equals(data.getJSONObject(i).getString("standard"))) {
                        if (!data.getJSONObject(i).getString("standard").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + i + "条数据;标准值字数超长,最长20位整数6位小数！");
                            continue;
                        }
                    }
                    // 上偏差最大长度check
                    if (!"".equals(data.getJSONObject(i).getString("upperDeviation"))) {
                        if (!data.getJSONObject(i).getString("upperDeviation").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + i + "条数据;上偏差字数超长,最长20位整数6位小数！");
                            continue;
                        }
                    }
                    // 下偏差最大长度check
                    if (!"".equals(data.getJSONObject(i).getString("lowerDeviation"))) {
                        if (!data.getJSONObject(i).getString("lowerDeviation").matches(str)) {
                            // 返回错误信息：表格内无数据
                            returnData.put("flag", "0");
                            errorMess.add("第" + i + "条数据;下偏差字数超长,最长20位整数6位小数！");
                            continue;
                        }
                    }
                    // 结果区分最大长度check
                    if (data.getJSONObject(i).getString("inspectionResultDiff").length() > 1) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + i + "条数据;生产方式字数超长,最长1！");
                        continue;
                    }
                    // 技术要求最大长度check
                    if (data.getJSONObject(i).getString("technicalRequirement").length() > 200) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + i + "条数据;生产方式字数超长,最长1！");
                        continue;
                    }
                    // 备注最大长度check
                    if (data.getJSONObject(i).getString("remark").length() > 200) {
                        // 返回错误信息：表格内无数据
                        returnData.put("flag", "0");
                        errorMess.add("第" + i + "条数据;备注字数超长,最长200！");
                        continue;
                    }
                    // 物料编码
                    organizationInfo.setMaterielId(data.getJSONObject(i).getInteger("materielCd"));
                    // 检查项目
                    organizationInfo.setInspectionItem(data.getJSONObject(i).getString("inspectionItem"));
                    // 检查器具
                    organizationInfo.setInspectionInstrument(data.getJSONObject(i).getString("inspectionInstrument"));
                    // 标准值
                    organizationInfo.setStandard(Double.valueOf(data.getJSONObject(i).getString("standard")));
                    // 上偏差
                    organizationInfo.setUpperDeviation(Double.valueOf(data.getJSONObject(i).getString("upperDeviation")));
                    // 下偏差
                    organizationInfo.setLowerDeviation(Double.valueOf(data.getJSONObject(i).getString("lowerDeviation")));
                    // 检验结果区分
                    organizationInfo.setInspectionResultDiff(data.getJSONObject(i).getString("inspectionResultDiff"));
                    // 技术要求
                    organizationInfo.setTechnicalRequirement(data.getJSONObject(i).getString("technicalRequirement"));
                    // 备注
                    organizationInfo.setRemark(data.getJSONObject(i).getString("remark"));
                    // 判断物料编码是否为空
                    if (!"".equals(data.getJSONObject(i).getString("materielCd"))) {
                        // 实例化结果集
                        List<QmsEntryControlDetails> entryControlDetails = new ArrayList<QmsEntryControlDetails>();
                        // 取得物料编码和检查项目是否有数据
                        entryControlDetails = qmsEntryControlDetailsRepository.findByMaterielIdAndInspectionItemAndFlagStatus(data.getJSONObject(i).getInteger("materielCd"), data.getJSONObject(i).getString("inspectionItem"), "0");
                        // 判断物料编码是否已经存在
                        if (entryControlDetails.size() != 0) {
                            // 返回错误信息：物料编码已存在
                            returnData.put("flag", "0");
                            errorMess.add("第" + i + "条数据;物料编码和检查项目已存在;");
                            continue;
                        }
                    } else {
                        // 返回错误信息：物料编码为空
                        returnData.put("status", "error");
                        returnData.put("message", "物料编码为空:");
                        continue;
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
                    qmsEntryControlDetailsRepository.save(organizationInfo);
                    // 用于判断上传的表格中数据的物料ID是否有相同 的
                    listInfo.add(data.getJSONObject(i).getString("vehicleType") + data.getJSONObject(i).getString("parentMaterielCd") + data.getJSONObject(i).getString("materielCd"));
                }
                // 实例化结果集
                HashSet set = new HashSet<>(listInfo);
            // 判断表格中的物料ID是否有重复
            // if(listInfo.size() != set.size()){
            // // 返回错误信息：表格中数据的物料ID有相同的
            // returnData.put("status", "error");
            // returnData.put("message", "导入的数据中数据有重复！");
            // errorInfo = "1";
            // throw new RuntimeException("导入的数据中数据有重复！");
            // }
            }
            if ("1".equals(returnData.getString("flag"))) {
                // 返回成功结果
                returnData.put("status", "success");
                returnData.put("flag", "1");
                returnData.put("message", "导入成功！一共导入" + data.size() + "数据");
            } else {
                // 返回成功结果
                returnData.put("status", "success");
                returnData.put("message", "导入成功数据！" + (data.size() - errorMess.size()) + "条。导入失败数据" + errorMess.size() + "条。");
                returnData.put("messageError", errorMess);
            }
        } catch (Exception e) {
            // 事物回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // 打印错误信息
            log.info(e.getMessage());
            if ("0".equals(errorInfo)) {
                // 返回错误信息：数据插入异常
                returnData.put("status", "error");
                returnData.put("message", "数据插入异常！");
            }
        }
    }
    // 返回结果
    return returnData;
}


}