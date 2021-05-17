import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository;
import cn.com.cnc.fcc.service.QmsMaterielService;
import cn.com.cnc.fcc.service.dto.MaterielPopDto;
import cn.com.cnc.fcc.service.dto.QmsMaterielSupplierDto;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.lang.String;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class QmsMaterielServiceImpl implements cn.com.cnc.fcc.service.QmsMaterielService,QmsMaterielService{

 private  Logger log;

 private  QmsMaterielRepository qmsMaterielRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;

@Autowired
 private  QmsUnitRepository qmsUnitRepository;

@Autowired
 private  RbacRoleRepository rbacRoleRepository;

@Autowired
 private  QmsSupplierRepository qmsSupplierRepository;

@Autowired
 private  QmsMaterielSqlService qmsMaterielSqlService;

@Autowired
 private  QmsMaterielTypeRepository qmsMaterielTypeRepository;


@Override
public JSONObject uploadData(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 用于将得到的数据存在真实的QmsMateriel中，之后进行数据插入的操作
    List<QmsMateriel> QmsMateriel = new ArrayList<QmsMateriel>();
    QmsMateriel = qmsMaterielRepository.findAll();
    // 取得得所有满足条件的物料分类的值
    // 存错误信息的数组
    List<String> errorMess = new ArrayList<String>();
    returnData.put("flag", "1");
    // 判断文件路径
    if (files.isEmpty()) {
        returnData.put("status", "error");
        returnData.put("message", "请重新上传！");
    } else {
        try {
            // 取数据
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), QmsMaterielExcelDto.class, 21);
            System.out.println(data);
            // 判断数据
            if (data.size() <= 0) {
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
                    // 进行数据的所有Check
                    QmsMateriel qmsMateriel = new QmsMateriel();
                    // 首先进行(物料编码长度)以及(主键不能重复)以及(不能为空)的Check
                    if ("".equals(data.getJSONObject(i).getString("materielCd"))) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,物料编码不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("materielCd").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,物料编码长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    List<QmsMateriel> q1 = qmsMaterielRepository.findByMaterielCd(data.getJSONObject(i).getString("materielCd"));
                    if (q1.size() != 0) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,物料编码不能重复");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setMaterielCd(data.getJSONObject(i).getString("materielCd"));
                    // 物料名称
                    if (data.getJSONObject(i).getString("materielName").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,物料名称长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setMaterielName(data.getJSONObject(i).getString("materielName"));
                    // 图号
                    if (data.getJSONObject(i).getString("figureNumber").length() > 30) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,图号长度不能超过30");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setFigureNumber(data.getJSONObject(i).getString("figureNumber"));
                    // ABC码
                    if (data.getJSONObject(i).getString("abcNumber").length() > 1) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,ABC码长度不能超过1");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setAbcNumber(data.getJSONObject(i).getString("abcNumber"));
                    // 生产方式
                    if (data.getJSONObject(i).getString("productMode") != "") {
                        if (!"M".equals(data.getJSONObject(i).getString("productMode")) && !"P".equals(data.getJSONObject(i).getString("productMode")) && !"O".equals(data.getJSONObject(i).getString("productMode"))) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,生产方式必须为M或P或Q");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsMateriel.setProductMode(data.getJSONObject(i).getString("productMode"));
                        }
                    } else {
                        errorMess.add("第" + (i + 1) + "条数据有错误,生产方式不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    // 物料分类#################
                    if (data.getJSONObject(i).getString("materielTypeCd") == "") {
                        qmsMateriel.setMaterielTypeId(0);
                    } else {
                        String wuliao = qmsMaterielSqlService.getMaterielTypeList(data.getJSONObject(i).getString("materielTypeCd"));
                        if (wuliao == "yes") {
                            List<QmsMaterielType> marList = qmsMaterielTypeRepository.findByMaterielTypeCdAndFlagStatus(data.getJSONObject(i).getString("materielTypeCd"), "0");
                            qmsMateriel.setMaterielTypeId(marList.get(0).getId().intValue());
                        } else {
                            errorMess.add("第" + (i + 1) + "条数据有错误,没有输入的物料分类");
                            returnData.put("flag", "0");
                            continue;
                        }
                    }
                    // 物料属性
                    if (data.getJSONObject(i).getString("propertyType") != "") {
                        if (!"V".equals(data.getJSONObject(i).getString("propertyType")) && !"P".equals(data.getJSONObject(i).getString("propertyType"))) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,物料属性必须为V或P或空");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsMateriel.setPropertyType(data.getJSONObject(i).getString("propertyType"));
                        }
                    } else {
                        qmsMateriel.setPropertyType(data.getJSONObject(i).getString("propertyType"));
                    }
                    // 检验类型
                    if (data.getJSONObject(i).getString("checkType") != "") {
                        if (!"1".equals(data.getJSONObject(i).getString("checkType")) && !"2".equals(data.getJSONObject(i).getString("checkType")) && !"3".equals(data.getJSONObject(i).getString("checkType"))) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,检验类型必须为1或2或3或空");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsMateriel.setCheckType(data.getJSONObject(i).getString("checkType"));
                        }
                    } else {
                        qmsMateriel.setCheckType(data.getJSONObject(i).getString("checkType"));
                    }
                    // 抽检比例
                    if ("2".equals(data.getJSONObject(i).getString("checkType"))) {
                        if (data.getJSONObject(i).getString("checkRate") == "") {
                            qmsMateriel.setCheckRate(null);
                        } else {
                            String str = "^([1-9]|[1-9]\\\\d|100)$";
                            try {
                                Integer.parseInt(data.getJSONObject(i).getString("checkRate"));
                                if (!data.getJSONObject(i).getString("checkRate").matches(str)) {
                                    returnData.put("flag", "0");
                                    errorMess.add("第" + (i + 1) + "条数据有错误,抽检比例输入有误");
                                    continue;
                                } else {
                                    qmsMateriel.setCheckRate(Integer.parseInt(data.getJSONObject(i).getString("checkRate")));
                                }
                            } catch (NumberFormatException e) {
                                errorMess.add("第" + (i + 1) + "条数据有错误,抽检比例输入有误");
                                returnData.put("flag", "0");
                                continue;
                            }
                        }
                    } else {
                        if (data.getJSONObject(i).getString("checkRate").length() > 0) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,检验类型不是抽检时抽检比例必须为空");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsMateriel.setCheckRate(null);
                        }
                    }
                    // 使用单位
                    if (data.getJSONObject(i).getString("useUnitCd") == "") {
                        qmsMateriel.setUseUnitId(null);
                    } else {
                        List<QmsUnit> danwei = qmsUnitRepository.findByUnitCd(data.getJSONObject(i).getString("useUnitCd"));
                        if (danwei.size() == 0) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,没有输入的单位");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            List<QmsUnit> unitList = qmsUnitRepository.findByUnitCd(data.getJSONObject(i).getString("useUnitCd"));
                            qmsMateriel.setUseUnitId(unitList.get(0).getId().intValue());
                        }
                    }
                    // 规格
                    if (data.getJSONObject(i).getString("specificationType").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,规格长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setSpecificationType(data.getJSONObject(i).getString("specificationType"));
                    // 重量
                    if (data.getJSONObject(i).getString("weight") == "") {
                        qmsMateriel.setWeight(null);
                    } else {
                        try {
                            Integer.parseInt(data.getJSONObject(i).getString("weight"));
                            qmsMateriel.setWeight(Double.valueOf(Integer.parseInt(data.getJSONObject(i).getString("weight"))));
                        } catch (NumberFormatException e) {
                            try {
                                Double.parseDouble(data.getJSONObject(i).getString("weight"));
                                int dian = data.getJSONObject(i).getString("weight").indexOf(".");
                                if (dian > 14 || (data.getJSONObject(i).getString("weight").length() - 1 - dian) > 6) {
                                    errorMess.add("第" + (i + 1) + "条数据有错误,重量数据位数不对");
                                    returnData.put("flag", "0");
                                    continue;
                                } else {
                                    qmsMateriel.setWeight(Double.parseDouble(data.getJSONObject(i).getString("weight")));
                                }
                            } catch (NumberFormatException d) {
                                errorMess.add("第" + (i + 1) + "条数据有错误,重量数据类型不对");
                                returnData.put("flag", "0");
                                continue;
                            }
                        }
                    }
                    // 密度
                    if (data.getJSONObject(i).getString("density") == "") {
                        qmsMateriel.setDensity(null);
                    } else {
                        try {
                            Integer.parseInt(data.getJSONObject(i).getString("density"));
                            qmsMateriel.setDensity(Double.valueOf(Integer.parseInt(data.getJSONObject(i).getString("density"))));
                        } catch (NumberFormatException e) {
                            try {
                                Double.parseDouble(data.getJSONObject(i).getString("density"));
                                int dian = data.getJSONObject(i).getString("density").indexOf(".");
                                if (dian > 14 || (data.getJSONObject(i).getString("density").length() - 1 - dian) > 6) {
                                    errorMess.add("第" + (i + 1) + "条数据有错误,密度数据位数不对");
                                    returnData.put("flag", "0");
                                    continue;
                                } else {
                                    qmsMateriel.setDensity(Double.parseDouble(data.getJSONObject(i).getString("density")));
                                }
                            } catch (NumberFormatException d) {
                                errorMess.add("第" + (i + 1) + "条数据有错误,密度数据类型不对");
                                returnData.put("flag", "0");
                                continue;
                            }
                        }
                    }
                    // 质量等级
                    if (data.getJSONObject(i).getString("qualityLevel").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,质量等级长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setQualityLevel(data.getJSONObject(i).getString("qualityLevel"));
                    // 材质
                    if (data.getJSONObject(i).getString("texTure").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,材质长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setTexTure(data.getJSONObject(i).getString("texTure"));
                    // 物料员角色
                    if (data.getJSONObject(i).getString("mhandlerRole") == "") {
                        qmsMateriel.setMhandlerRoleId(null);
                    } else {
                        List<RbacRole> juese = rbacRoleRepository.findByRoleCode(data.getJSONObject(i).getString("mhandlerRole"));
                        if (juese.size() == 0) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,没有输入的物料员角色");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            List<RbacRole> roleList = rbacRoleRepository.findByRoleCode(data.getJSONObject(i).getString("mhandlerRole"));
                            qmsMateriel.setMhandlerRoleId(roleList.get(0).getId().intValue());
                        }
                    }
                    // 八防
                    if (data.getJSONObject(i).getString("eightPrevention") != "") {
                        if (!"1".equals(data.getJSONObject(i).getString("eightPrevention")) && !"2".equals(data.getJSONObject(i).getString("eightPrevention")) && !"3".equals(data.getJSONObject(i).getString("eightPrevention")) && !"4".equals(data.getJSONObject(i).getString("eightPrevention")) && !"5".equals(data.getJSONObject(i).getString("eightPrevention")) && !"6".equals(data.getJSONObject(i).getString("eightPrevention")) && !"7".equals(data.getJSONObject(i).getString("eightPrevention")) && !"8".equals(data.getJSONObject(i).getString("eightPrevention"))) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,八防必须为数字1到8或空");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsMateriel.setEightPrevention(data.getJSONObject(i).getString("eightPrevention"));
                        }
                    } else {
                        qmsMateriel.setEightPrevention(data.getJSONObject(i).getString("eightPrevention"));
                    }
                    // 质检关键
                    if (data.getJSONObject(i).getString("ifKey") != "") {
                        if (!"0".equals(data.getJSONObject(i).getString("ifKey")) && !"1".equals(data.getJSONObject(i).getString("ifKey"))) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,质检关键必须为0或1或空");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsMateriel.setIfKey(data.getJSONObject(i).getString("ifKey"));
                        }
                    } else {
                        qmsMateriel.setIfKey(data.getJSONObject(i).getString("ifKey"));
                    }
                    // SAP码
                    if (data.getJSONObject(i).getString("sapCd").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,SAP码长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setSapCd(data.getJSONObject(i).getString("sapCd"));
                    // 是否试验
                    if (data.getJSONObject(i).getString("isCheck") != "") {
                        if (!"0".equals(data.getJSONObject(i).getString("isCheck")) && !"1".equals(data.getJSONObject(i).getString("isCheck"))) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,是否试验必须为0或1或空");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsMateriel.setIsCheck(data.getJSONObject(i).getString("isCheck"));
                        }
                    } else {
                        qmsMateriel.setIsCheck(data.getJSONObject(i).getString("isCheck"));
                    }
                    // 备注
                    if (data.getJSONObject(i).getString("remark").length() > 200) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,材质长度不能超过200");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsMateriel.setRemark(data.getJSONObject(i).getString("remark"));
                    // 删除flag
                    qmsMateriel.setFlagStatus("0");
                    // 插入数据人
                    qmsMateriel.setMakeUser(user.getUsername());
                    // 更新数据人
                    qmsMateriel.setModifyUser(user.getUsername());
                    // 插入时间
                    qmsMateriel.setMakeTime(dateUtil.getDBNowDate());
                    // 更新时间
                    qmsMateriel.setModifyTime(dateUtil.getDBNowDate());
                    // 其他项目为""
                    qmsMateriel.setInnerCd("");
                    qmsMateriel.setOrganizationCd("");
                    qmsMateriel.setInHouseType("");
                    qmsMateriel.setUbiety("");
                    qmsMateriel.setCompPkid("");
                    qmsMateriel.setReserveFirst("");
                    qmsMateriel.setReserveSecond("");
                    qmsMateriel.setReserveThird("");
                    qmsMaterielRepository.save(qmsMateriel);
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
            }
        } catch (Exception e) {
            returnData.put("status", "error");
            returnData.put("message", "数据导入失败！");
        }
    }
    return returnData;
}


public List<MaterielPopDto> qmsMaterielFindAll(String materielCd,String materielName,String supplier,String figureNumber,String type,String supplierId){
    List<MaterielPopDto> qmsMateriels = new ArrayList<MaterielPopDto>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getMaterielSql(materielCd, materielName, supplier, figureNumber, type, supplierId);
        Query query = em.createNativeQuery(sql);
        if (!materielCd.isEmpty() && materielCd != null) {
            query.setParameter("materielCd", "%" + materielCd + "%");
        }
        if (!materielName.isEmpty() && materielName != null) {
            query.setParameter("materielName", "%" + materielName + "%");
        }
        if (!supplier.isEmpty() && supplier != null) {
            query.setParameter("supplier", "%" + supplier + "%");
        }
        if (!figureNumber.isEmpty() && figureNumber != null) {
            query.setParameter("figureNumber", "%" + figureNumber + "%");
        }
        if (!type.isEmpty() && type != null) {
            query.setParameter("type", "%" + type + "%");
        }
        if (!supplierId.isEmpty() && supplierId != null) {
            query.setParameter("supplierId", supplierId);
        }
        ResultTransformer transformer = Transformers.aliasToBean(MaterielPopDto.class);
        qmsMateriels = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("supplierId", StandardBasicTypes.LONG).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).addScalar("type", StandardBasicTypes.STRING).addScalar("figureNumber", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally {
        em.close();
    }
    return qmsMateriels;
}


@Override
public Predicate toPredicate(Root<QmsMateriel> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<Predicate>();
    try {
        // 判断模糊查询物料编码是否为空
        if (!"".equals(bianMa)) {
            predicates.add(criteriaBuilder.like(root.get("materielCd"), "%" + bianMa + "%"));
        }
        // 判断模糊查询物料名称是否为空
        if (!"".equals(gongName)) {
            predicates.add(criteriaBuilder.like(root.get("materielName"), "%" + gongName + "%"));
        }
        // 判断模糊查询图号名称是否为空
        if (!"".equals(tuhao)) {
            predicates.add(criteriaBuilder.like(root.get("figureNumber"), "%" + tuhao + "%"));
        }
        // 判断模糊查规格名称是否为空
        if (!"".equals(guige)) {
            predicates.add(criteriaBuilder.like(root.get("specificationType"), "%" + guige + "%"));
        }
        // 判断模糊查询生产方式名称是否为空
        if (!"null".equals(shengValue)) {
            predicates.add(criteriaBuilder.like(root.get("productMode"), "%" + shengValue + "%"));
        }
        // 判断模糊查物料属性名称是否为空
        if (!"null".equals(shuxingValue)) {
            predicates.add(criteriaBuilder.like(root.get("propertyType"), "%" + shuxingValue + "%"));
        }
        // 检索未删除的数据
        predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}


public String getMaterielSql(String materielCd,String materielName,String supplier,String figureNumber,String type,String supplierId){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\tt01.id,\n" + "\tt01.materiel_cd as materielCd,\n" + "\tt01.materiel_name as materielName,\n" + "\tt01.figure_number as figureNumber,\n" + "\tt01.specification_type as type,\n" + "\tt03.id as supplierId,\n" + "\tt03.supplier_cd as supplierCd,\n" + "\tt03.supplier_name as supplierName\n" + "FROM\n" + "\tqms_materiel t01\n" + "LEFT JOIN qms_materiel_supplier t02 ON t01.id = t02.materiel_id\n" + "LEFT JOIN qms_supplier t03 ON t02.supplier_id = t03.id\n" + "WHERE 1=1\n" + "AND t01.flag_status = '0'\n");
    if (!materielCd.isEmpty() && materielCd != null) {
        sql.append("AND t01.materiel_cd like :materielCd\n");
    }
    if (!materielName.isEmpty() && materielName != null) {
        sql.append("AND t01.materiel_name like :materielName\n");
    }
    if (!supplier.isEmpty() && supplier != null) {
        sql.append("AND t03.supplier_name like :supplier\n");
    }
    if (!figureNumber.isEmpty() && figureNumber != null) {
        sql.append("AND t01.figure_number like :figureNumber\n");
    }
    if (!type.isEmpty() && type != null) {
        sql.append("AND t01.specification_type like :type\n");
    }
    if (!supplierId.isEmpty() && supplierId != null) {
        sql.append("AND t03.id = :supplierId\n");
    }
    sql.append("ORDER BY t01.id\n");
    return sql.toString();
}


}