package cn.com.cnc.fcc.service.impl;
 import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import cn.com.cnc.fcc.domain.QmsSupplierClass;
import cn.com.cnc.fcc.domain.QmsSupplierExcelDto;
import cn.com.cnc.fcc.repository.QmsSupplierClassRepository;
import cn.com.cnc.fcc.service.dto.GroupOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.MaterielPopDto;
import cn.com.cnc.fcc.service.dto.SupplierPopDto;
import cn.com.cnc.fcc.service.util.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import cn.com.cnc.fcc.domain.QmsSupplier;
import cn.com.cnc.fcc.repository.QmsSupplierRepository;
import cn.com.cnc.fcc.service.QmsSuppliersInfoService;
import cn.com.cnc.fcc.service.util.DateUtil;
import org.springframework.web.multipart.MultipartFile;
@Service
public class QmsSuppliersInfoServiceImpl implements QmsSuppliersInfoService{

 private  Logger log;

 private  QmsSupplierRepository qmsSupplierRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  QmsSupplierClassRepository qmsSupplierClassRepository;

 private  EntityManagerFactory emf;

public QmsSuppliersInfoServiceImpl(QmsSupplierRepository qmsSupplierRepository, EntityManagerFactory emf) {
    this.qmsSupplierRepository = qmsSupplierRepository;
    this.emf = emf;
}
@Override
public JSONObject uploadData(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 用于将得到的数据存在真实的QmsSupplier中，之后进行数据插入的操作
    List<QmsSupplier> QmsSupplier = new ArrayList<QmsSupplier>();
    // 获取所有的供应商编码
    QmsSupplier = qmsSupplierRepository.findAll();
    List<QmsSupplier> list = new ArrayList<QmsSupplier>();
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
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), QmsSupplierExcelDto.class, 13);
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
                    // 设置一个记录错误数量的sum
                    // 进行数据的所有Check
                    QmsSupplier qmsSupplier = new QmsSupplier();
                    // 首先进行(供应商编码长度)以及(主键不能重复)以及(不能为空)的Check
                    if ("".equals(data.getJSONObject(i).getString("supplierCd"))) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,供应商编码不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("supplierCd").length() > 10) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,供应商编码长度不能超过10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    List<QmsSupplier> q1 = qmsSupplierRepository.findBySupplierCd(data.getJSONObject(i).getString("supplierCd"));
                    if (q1.size() != 0) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,供应商编码不能重复");
                        returnData.put("flag", "0");
                        continue;
                    }
                    // 供应商编码
                    qmsSupplier.setSupplierCd(data.getJSONObject(i).getString("supplierCd"));
                    // 供应商名称
                    if (data.getJSONObject(i).getString("supplierName").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,供应商名称长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setSupplierName(data.getJSONObject(i).getString("supplierName"));
                    // 供应商分类编码3个check 1.供应商分类编码不能为空 2.供应商分类编码必须能从数据库中得到值 3.长度check
                    if ("".equals(data.getJSONObject(i).getString("suppkierClass"))) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,供应商分类编码不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("suppkierClass").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,供应商分类编码长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    List<QmsSupplierClass> SuppkierClass = qmsSupplierClassRepository.findBySuppkierClass(data.getJSONObject(i).getString("suppkierClass"));
                    if (SuppkierClass.size() == 0) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,供应商分类编码不存在");
                        returnData.put("flag", "0");
                        continue;
                    } else {
                        qmsSupplier.setSupplierClassId(SuppkierClass.get(0).getId().intValue());
                    }
                    // 地址
                    if (data.getJSONObject(i).getString("address").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,地址长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setAddress(data.getJSONObject(i).getString("address"));
                    // 电话1
                    if (data.getJSONObject(i).getString("telNum1").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,电话1长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setTelNum1(data.getJSONObject(i).getString("telNum1"));
                    // 电话2
                    if (data.getJSONObject(i).getString("telNum2").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,电话2长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setTelNum2(data.getJSONObject(i).getString("telNum2"));
                    // 传真
                    if (data.getJSONObject(i).getString("faxNum").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,传真长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setFaxNum(data.getJSONObject(i).getString("faxNum"));
                    // 邮箱
                    if (data.getJSONObject(i).getString("mailAddress").length() > 50) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,邮箱长度不能超过50");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setMailAddress(data.getJSONObject(i).getString("mailAddress"));
                    // 备注
                    if (data.getJSONObject(i).getString("remark").length() > 200) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,备注长度不能超过200");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setRemark(data.getJSONObject(i).getString("remark"));
                    // 网址
                    if (data.getJSONObject(i).getString("urlAddress").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,网址长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setUrlAddress(data.getJSONObject(i).getString("urlAddress"));
                    // 部门
                    if (data.getJSONObject(i).getString("department").length() > 40) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,部门长度不能超过40");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setDepartment(data.getJSONObject(i).getString("department"));
                    // 联系人
                    if (data.getJSONObject(i).getString("linkMan").length() > 40) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,联系人长度不能超过40");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setLinkMan(data.getJSONObject(i).getString("linkMan"));
                    // 评定记录
                    if (data.getJSONObject(i).getString("assRecord").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,评定记录长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsSupplier.setAssRecord(data.getJSONObject(i).getString("assRecord"));
                    // 删除flag
                    qmsSupplier.setFlagStatus("0");
                    // 插入数据人
                    qmsSupplier.setMakeUser(user.getUsername());
                    // 更新数据人
                    qmsSupplier.setModifyUser(user.getUsername());
                    // 插入时间
                    qmsSupplier.setMakeTime(dateUtil.getDBNowDate());
                    // 更新时间
                    qmsSupplier.setModifyTime(dateUtil.getDBNowDate());
                    // 其他项目都设置为""
                    qmsSupplier.setCompPkid("");
                    // list.add(qmsSupplier);
                    qmsSupplierRepository.save(qmsSupplier);
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


@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
@Override
public Page<QmsSupplier> qmsSuppliersInfoFindAll(String bianMa,String gongName,Pageable pageable){
    // 初始化结果集
    Page<QmsSupplier> resultInfo = null;
    Specification querySpecifi = new Specification<QmsSupplier>() {

        @Override
        public Predicate toPredicate(Root<QmsSupplier> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            try {
                // 判断模糊查询供应商编码是否为空
                if (!"".equals(bianMa)) {
                    predicates.add(criteriaBuilder.like(root.get("supplierCd"), "%" + bianMa + "%"));
                }
                // 判断模糊查询供应商名称是否为空
                if (!"".equals(gongName)) {
                    predicates.add(criteriaBuilder.like(root.get("supplierName"), "%" + gongName + "%"));
                }
                // 检索未删除的数据
                predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    };
    // 带检索条件查询数据
    resultInfo = qmsSupplierRepository.findAll(querySpecifi, pageable);
    // 返回结果
    return resultInfo;
}


public String getSupplierSql(String supplierCd,String supplierName,String materielId){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\tDISTINCT\n" + "\tt01.id AS id,\n" + "\tt01.supplier_cd AS supplierCd,\n" + "\tt01.supplier_name AS supplierName,\n" + "\tt01.ass_record AS assRecord,\n" + "\tt01.remark AS remark,\n" + "\tt02.suppkier_class_name as supplierClassName\n" + "\n" + "FROM\n" + "\tqms_supplier t01\n" + "LEFT JOIN qms_supplier_class t02 ON t01.supplier_class_id = t02.id\n" + "LEFT JOIN qms_materiel_supplier t03 ON t01.id = t03.supplier_id \n" + "WHERE\n" + "\t1 = 1\n");
    if (!supplierCd.isEmpty() && supplierCd != null) {
        sql.append("AND t01.supplier_cd LIKE :supplierCd\n");
    }
    if (!supplierName.isEmpty() && supplierName != null) {
        sql.append("AND t01.supplier_name LIKE :supplierName\n");
    }
    if (!materielId.isEmpty() && materielId != null) {
        sql.append("AND t03.materiel_id = :materielId\n");
    }
    return sql.toString();
}


public List<SupplierPopDto> findBySupplierCdAndSupplierName(String supplierCd,String supplierName,String materielId){
    List<SupplierPopDto> qmsSuppliers = new ArrayList<SupplierPopDto>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getSupplierSql(supplierCd, supplierName, materielId);
        Query query = em.createNativeQuery(sql);
        if (!supplierCd.isEmpty() && supplierCd != null) {
            query.setParameter("supplierCd", "%" + supplierCd + "%");
        }
        if (!supplierName.isEmpty() && supplierName != null) {
            query.setParameter("supplierName", "%" + supplierName + "%");
        }
        if (!materielId.isEmpty() && materielId != null) {
            query.setParameter("materielId", materielId);
        }
        ResultTransformer transformer = Transformers.aliasToBean(SupplierPopDto.class);
        qmsSuppliers = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).addScalar("assRecord", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).addScalar("supplierClassName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally {
        em.close();
    }
    return qmsSuppliers;
}


@Override
public Predicate toPredicate(Root<QmsSupplier> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<Predicate>();
    try {
        // 判断模糊查询供应商编码是否为空
        if (!"".equals(bianMa)) {
            predicates.add(criteriaBuilder.like(root.get("supplierCd"), "%" + bianMa + "%"));
        }
        // 判断模糊查询供应商名称是否为空
        if (!"".equals(gongName)) {
            predicates.add(criteriaBuilder.like(root.get("supplierName"), "%" + gongName + "%"));
        }
        // 检索未删除的数据
        predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}


}