import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.repository.QmsProductRepository;
import cn.com.cnc.fcc.service.QmsProductService;
import cn.com.cnc.fcc.service.dto.ProductDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Service
public class QmsProductServiceImpl implements QmsProductService,cn.com.cnc.fcc.service.QmsProductService{

@Autowired
 private  EntityManagerFactory emf;

@Autowired
 private  QmsProductRepository qmsProductRepository;

@Autowired
 private  QmsMaterielRepository qmsMaterielRepository;

@Resource
 private  DateUtil dateUtil;


@Override
public JSONObject uploadData(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 用于将得到的数据存在真实的QmsSupplier中，之后进行数据插入的操作
    List<QmsProduct> QmsProduct = new ArrayList<QmsProduct>();
    // 获取所有的供应商编码
    QmsProduct = qmsProductRepository.findAll();
    List<QmsProduct> list = new ArrayList<QmsProduct>();
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
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), QmsproductDTO.class, 4);
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
                    QmsProduct qmsProduct = new QmsProduct();
                    // 首先进行(供应商编码长度)以及(主键不能重复)以及(不能为空)的Check
                    if ("".equals(data.getJSONObject(i).getString("productNum"))) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,产品编号不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("productNum").length() > 10) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,产品编号长度不能超过10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    List<QmsProduct> q1 = qmsProductRepository.findByProductNum(data.getJSONObject(i).getString("productNum"));
                    if (q1.size() != 0) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,产品编号不能重复");
                        returnData.put("flag", "0");
                        continue;
                    }
                    // 产品编号
                    qmsProduct.setProductNum(data.getJSONObject(i).getString("productNum"));
                    // 物料编号
                    // 生产方式
                    if (data.getJSONObject(i).getString("materielCd") != "") {
                        List<QmsMateriel> SuppkierClass = qmsMaterielRepository.findByMaterielCd(data.getJSONObject(i).getString("materielCd"));
                        if (SuppkierClass.size() == 0) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,物料编码不存在");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsProduct.setMaterielId(SuppkierClass.get(0).getId().intValue());
                        }
                    } else {
                        errorMess.add("第" + (i + 1) + "条数据有错误,物料编码不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    // 批次
                    if (data.getJSONObject(i).getString("productBatch").length() > 20) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,批号长度不能超过20");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsProduct.setProductBatch(data.getJSONObject(i).getString("productBatch"));
                    // 备注
                    if (data.getJSONObject(i).getString("remark").length() > 200) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,备注长度不能超过200");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsProduct.setRemark(data.getJSONObject(i).getString("remark"));
                    // 删除flag
                    qmsProduct.setFlagStatus("0");
                    // 插入数据人
                    qmsProduct.setMakeUser(user.getUsername());
                    // 更新数据人
                    qmsProduct.setModifyUser(user.getUsername());
                    // 插入时间
                    qmsProduct.setMakeTime(dateUtil.getDBNowDate());
                    // 更新时间
                    qmsProduct.setModifyTime(dateUtil.getDBNowDate());
                    // 其他项目都设置为""
                    qmsProduct.setCompPkid("");
                    // list.add(qmsSupplier);
                    qmsProductRepository.save(qmsProduct);
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


public String getfindTabThreeSql(String productRelation){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\ta.id as id,\n" + "\td.supplier_cd as supplierCd,\n" + "\te.prj_name AS prjName,\n" + "\tc.purchase_order_number AS purchaseOrderNumber,\n" + "\tc.made_ymd AS madeYmd,\n" + "\td.supplier_name AS supplierName,\n" + "\tc.entry_quantity AS entryQuantity,\n" + "\tc.batch_number AS batchNumber,\n" + "\tc.made_factory_cd AS madeFactoryCd\n" + "FROM\n" + "\tqms_production_relation a\n" + "LEFT JOIN qms_materiel_details b ON a.assembly_materiel_id = b.id\n" + "LEFT JOIN qms_materiel_entry c ON b.entry_id = c.id\n" + "LEFT JOIN qms_supplier d ON c.supplier_id = d.id\n" + "LEFT JOIN qms_master e ON c.entry_type = e.prj_cd\n" + "AND e.kbn_cd = 'M20'\n" + "WHERE\n" + "\t1 = 1");
    if (!"".equals(productRelation) && productRelation != null)
        sql.append(" and a.id = " + productRelation);
    return sql.toString();
}


public List<QmsProductRelationTabDTO> qmsProductFindTabData(String productRelation){
    List<QmsProductRelationTabDTO> QmsProductRelationTabDTO = new ArrayList<QmsProductRelationTabDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getfindTabSql(productRelation);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(QmsProductRelationTabDTO.class);
        QmsProductRelationTabDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("productBatch", StandardBasicTypes.STRING).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("figureNumber", StandardBasicTypes.STRING).addScalar("weight", StandardBasicTypes.STRING).addScalar("texTure", StandardBasicTypes.STRING).addScalar("prjName", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("specificationType", StandardBasicTypes.STRING).addScalar("density", StandardBasicTypes.STRING).addScalar("assemblyCount", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        throw new InternalServerErrorException("qmsProductFindAll could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return QmsProductRelationTabDTO;
}


public List<QmsProductRelationTabTwoDTO> qmsProductFindTabDataTwo(String productRelation){
    List<QmsProductRelationTabTwoDTO> QmsProductRelationTabTwoDTO = new ArrayList<QmsProductRelationTabTwoDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getfindTabTwoSql(productRelation);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(QmsProductRelationTabTwoDTO.class);
        QmsProductRelationTabTwoDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("organizationName", StandardBasicTypes.STRING).addScalar("technologyCd", StandardBasicTypes.STRING).addScalar("processCd", StandardBasicTypes.STRING).addScalar("prjName", StandardBasicTypes.STRING).addScalar("orderNo", StandardBasicTypes.STRING).addScalar("workGroupCd", StandardBasicTypes.STRING).addScalar("technologyName", StandardBasicTypes.STRING).addScalar("processName", StandardBasicTypes.STRING).addScalar("isControlPoint", StandardBasicTypes.STRING).addScalar("isTest", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        throw new InternalServerErrorException("qmsProductFindAll could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return QmsProductRelationTabTwoDTO;
}


public String getfindTabSql(String productRelation){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\ta.id as id,\n" + "\tc.product_batch as productBatch,\n" + "\td.materiel_cd AS materielCd,\n" + "\td.figure_number AS figureNumber,\n" + "\td.weight AS weight,\n" + "\td.tex_ture AS texTure,\n" + "\te.prj_name AS prjName,\n" + "\td.materiel_name AS materielName,\n" + "\td.specification_type AS specificationType,\n" + "\td.density AS density,\n" + "\tb.assembly_count AS assemblyCount\n" + "FROM\n" + "\tqms_production_relation a\n" + "LEFT JOIN qms_parts_assembly_relation b ON a.assembly_id = b.id\n" + "LEFT JOIN qms_product c ON a.do_product_id = c.id\n" + "LEFT JOIN qms_materiel d ON c.materiel_id = d.id\n" + "LEFT JOIN qms_master e ON a.from_diff = e.prj_cd\n" + "AND e.kbn_cd = 'M01'\n" + "WHERE\n" + "\t1 = 1");
    if (!"".equals(productRelation) && productRelation != null)
        sql.append(" and a.id = " + productRelation);
    return sql.toString();
}


@Override
public List<ProductDTO> qmsProductFindAll(String productNumIn,String materielCdIn,String materielNameIn,String productBatchIn,String materielId,String sp){
    List<ProductDTO> ProductDTO = new ArrayList<ProductDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getfindAllSql(productNumIn, materielCdIn, materielNameIn, productBatchIn, materielId, sp);
        Query query = em.createNativeQuery(sql);
        if (!"".equals(productNumIn) && productNumIn != null)
            query.setParameter("productNumIn", "%" + productNumIn + "%");
        if (!"".equals(materielCdIn) && materielCdIn != null)
            query.setParameter("materielCdIn", "%" + materielCdIn + "%");
        if (!"".equals(materielNameIn) && materielNameIn != null)
            query.setParameter("materielNameIn", "%" + materielNameIn + "%");
        if (!"".equals(productBatchIn) && productBatchIn != null)
            query.setParameter("productBatchIn", "%" + productBatchIn + "%");
        ResultTransformer transformer = Transformers.aliasToBean(ProductDTO.class);
        ProductDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("productNum", StandardBasicTypes.STRING).addScalar("productBatch", StandardBasicTypes.STRING).addScalar("materielId", StandardBasicTypes.LONG).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        throw new InternalServerErrorException("qmsProductFindAll could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return ProductDTO;
}


public List<QmsProductRelationTabThreeDTO> qmsProductFindTabDataThree(String productRelation){
    List<QmsProductRelationTabThreeDTO> QmsProductRelationTabThreeDTO = new ArrayList<QmsProductRelationTabThreeDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getfindTabThreeSql(productRelation);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(QmsProductRelationTabThreeDTO.class);
        QmsProductRelationTabThreeDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("prjName", StandardBasicTypes.STRING).addScalar("purchaseOrderNumber", StandardBasicTypes.STRING).addScalar("madeYmd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).addScalar("entryQuantity", StandardBasicTypes.STRING).addScalar("batchNumber", StandardBasicTypes.STRING).addScalar("madeFactoryCd", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        throw new InternalServerErrorException("qmsProductFindTabDataThree could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return QmsProductRelationTabThreeDTO;
}


public String getfindTabTwoSql(String productRelation){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\ta.id AS id,\n" + "\tf.organization_name AS organizationName,\n" + "\td.technology_cd AS technologyCd,\n" + "\tg.process_cd AS processCd,\n" + "\ti.prj_name AS prjName,\n" + "\td.order_no AS orderNo,\n" + "\td.work_group_cd AS workGroupCd,\n" + "\td.technology_name AS technologyName,\n" + "\tg.process_name AS processName,\n" + "\td.is_control_point AS isControlPoint,\n" + "\td.is_test AS isTest\n" + "FROM\n" + "\tqms_production_relation a\n" + "LEFT JOIN qms_production_inspection_value b ON a.production_inspection_id = b.id\n" + "LEFT JOIN qms_production_inspection c ON b.inspection_id = c.id\n" + "LEFT JOIN qms_bom_technology d ON c.bom_technology_id = d.id\n" + "LEFT JOIN qms_master e ON d.is_control_point = e.prj_cd\n" + "AND e.kbn_cd = 'M04'\n" + "LEFT JOIN qms_master h ON d.is_test = h.prj_cd\n" + "AND h.kbn_cd = 'M04'\n" + "LEFT JOIN qms_master i ON d.operation_type = i.prj_cd\n" + "AND i.kbn_cd = 'M12'\n" + "LEFT JOIN qms_organization_info f ON d.organization_cd = f.organization_cd\n" + "LEFT JOIN qms_process g ON d.process_id = g.id\n" + "WHERE\n" + "\t1 = 1\n" + "AND a.id = '1'");
    if (!"".equals(productRelation) && productRelation != null)
        sql.append(" and a.id = " + productRelation);
    return sql.toString();
}


public String getfindAllSql(String productNumIn,String materielCdIn,String materielNameIn,String productBatchIn,String materielId,String sp){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT a.id as id,b.id AS materielId,a.product_num as productNum,a.product_batch AS productBatch,\n" + "b.materiel_cd AS materielCd,\n" + "b.materiel_name AS materielName,\n" + "a.remark AS remark \n" + "from qms_product a\n" + "inner join qms_materiel b\n" + "on a.materiel_id = b.id where 1=1\n");
    if (!"".equals(productNumIn) && productNumIn != null)
        sql.append("and a.product_num  LIKE :productNumIn\n");
    if (!"".equals(materielCdIn) && materielCdIn != null)
        sql.append("and b.materiel_cd  LIKE :materielCdIn\n");
    if (!"".equals(materielNameIn) && materielNameIn != null)
        sql.append("and b.materiel_name  LIKE :materielNameIn\n");
    if (!"".equals(productBatchIn) && productBatchIn != null)
        sql.append("and a.product_batch  LIKE :productBatchIn\n");
    if ("sp1.1".equals(sp) && materielId != null)
        sql.append("and b.id =" + materielId);
    if ("sp1.2".equals(sp) && materielId != null)
        sql.append("AND NOT EXISTS (\n" + "\tSELECT\n" + "\t\tt.id\n" + "\tFROM\n" + "\t\tqms_production_relation t\n" + "\tWHERE\n" + "\t\tt.use_product_id = a.id\n" + ")\n" + "AND b.id = " + materielId);
    sql = sql.append(" order by a.id");
    return sql.toString();
}


}