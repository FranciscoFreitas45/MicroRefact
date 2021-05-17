import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.domain.QmsProductionInspection;
import cn.com.cnc.fcc.repository.QmsBomTechnologyRepository;
import cn.com.cnc.fcc.repository.QmsProductionInspectionRepository;
import cn.com.cnc.fcc.service.QmsProductionInspectionService;
import cn.com.cnc.fcc.service.dto.ProductProcessCheckDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.QmsProductionInspectionController;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.ZonedDateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Service
public class QmsProductionInspectionImpl implements QmsProductionInspectionService,cn.com.cnc.fcc.service.QmsProductionInspectionService{

 private  Logger log;

 private  EntityManagerFactory emf;

 private  QmsProductionInspectionRepository qmsProductionInspectionRepository;

 private  QmsBomTechnologyRepository qmsBomTechnologyRepository;

@Resource
 private  DateUtil dateUtil;


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


@Override
public JSONObject chackPreProcess(String bomTechnologyId,String serialNumber){
    List<ProductProcessCheckDTO> preProcessList = new ArrayList<ProductProcessCheckDTO>();
    JSONObject returnData = new JSONObject();
    EntityManager em = emf.createEntityManager();
    returnData.put("code", 0);
    try {
        String sql = chackPreProcessSql(bomTechnologyId);
        Query query = em.createNativeQuery(sql);
        ResultTransformer transformer = Transformers.aliasToBean(ProductProcessCheckDTO.class);
        preProcessList = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).setResultTransformer(transformer).getResultList();
        // 不检查
        if (preProcessList.size() != 0) {
            Integer preBomId = preProcessList.get(0).getBomTechnologyId();
            List<QmsProductionInspection> preProductInspection = qmsProductionInspectionRepository.findByBomTechnologyIdAndSerialNumber(preBomId, serialNumber);
            String isOk = preProductInspection.get(0).getIsOk();
            if (isOk.equals('2')) {
                returnData.put("code", 1);
            }
        }
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsProductionInspection could not be found");
    } finally {
        em.close();
    }
    return returnData;
}


public String chackPreProcessSql(String bomTechnologyId){
    StringBuffer sql = new StringBuffer();
    sql.append(" select   ");
    sql.append(" qbt.id  ");
    sql.append(" FROM  ");
    sql.append(" qms_bom_technology qbt  ");
    sql.append(" WHERE  ");
    sql.append(" qbt.order_no < (   ");
    sql.append(" SELECT   ");
    sql.append(" a.order_no   ");
    sql.append(" FROM qms_bom_technology a   ");
    sql.append(" WHERE   ");
    sql.append(" a.id = " + bomTechnologyId + " )   ");
    sql.append(" ORDER BY qbt.order_no DESC   ");
    sql.append(" LIMIT 1   ");
    return sql.toString();
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
    sql.append(" qpi.is_ok AS isOk,   ");
    sql.append(" qpi.make_time AS makeTime,   ");
    sql.append(" qpi.make_user AS makeUser   ");
    sql.append(" FROM qms_production_inspection qpi   ");
    sql.append(" INNER JOIN qms_materiel qm   ");
    sql.append(" ON qpi.materiel_id = qm.id   ");
    sql.append(" INNER JOIN qms_bom_technology qbt   ");
    sql.append(" ON qpi.bom_technology_id = qbt.id   ");
    sql.append(" INNER JOIN qms_process   ");
    sql.append(" ON qbt.process_id = qms_process.id   ");
    sql.append(" WHERE qpi.id = " + id + "   ");
    sql.append(" ORDER BY qpi.id DESC ");
    return sql.toString();
}


public String qmsProductProcessFindAll(String materielCd,String materielName,String processName,String serialNumber,String furnace,String isOK,String workno,String inspectionDiff){
    StringBuffer sql = new StringBuffer();
    sql.append(" select   ");
    sql.append(" qpi.id,  ");
    sql.append(" qm.materiel_cd AS materielCd,  ");
    sql.append(" qm.materiel_name AS materielName,  ");
    sql.append(" qpi.bom_technology_id AS bomTechnologyId,  ");
    sql.append(" qms_process.process_name AS processName,  ");
    sql.append(" qpi.serial_number AS serialNumber,   ");
    sql.append(" qpi.furnace,   ");
    sql.append(" qpi.workno,   ");
    sql.append(" qpi.remark,   ");
    sql.append(" qpi.is_ok AS isOk   ");
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
    sql.append(" WHERE 1= 1   ");
    if (!"".equals(serialNumber) && serialNumber != null)
        sql.append(" AND qpi.serial_number LIKE '%" + serialNumber + "%' ");
    if (!"".equals(furnace) && furnace != null)
        sql.append(" AND qpi.furnace LIKE '%" + furnace + "%' ");
    if (!"".equals(workno) && workno != null)
        sql.append(" AND qpi.workno LIKE '%" + workno + "%' ");
    if (!"".equals(isOK) && isOK != null)
        sql.append(" AND qpi.is_ok = '" + isOK + "' ");
    if (!"".equals(inspectionDiff) && inspectionDiff != null)
        sql.append(" AND qpi.inspection_diff = '" + inspectionDiff + "' ");
    sql.append(" ORDER BY qpi.id DESC ");
    return sql.toString();
}


@Override
public String doBatchGeneration(QmsProductionInspection qmsProductionInspection,List<QmsBomTechnology> bomTechnologyList){
    String code = "1";
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 编号
    String serialNumber = qmsProductionInspection.getSerialNumber();
    // 炉批号
    String furnace = qmsProductionInspection.getFurnace();
    // 备注
    String remark = qmsProductionInspection.getRemark();
    // 中梁号
    String workno = qmsProductionInspection.getWorkno();
    Boolean checkFlag = false;
    try {
        for (int i = 0; i < bomTechnologyList.size(); i++) {
            QmsProductionInspection qpi = new QmsProductionInspection();
            Integer materielId = bomTechnologyList.get(i).getMaterielId();
            if (!serialNumber.equals("")) {
                List<QmsProductionInspection> qmsProductionInspectionList = qmsProductionInspectionRepository.findByMaterielIdAndSerialNumber(materielId, serialNumber);
                // 如果物料id+编号，在【生产检验表】中已存在
                if (qmsProductionInspectionList.size() > 0 && checkFlag == false) {
                    code = "2";
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return code;
                }
            } else {
                List<QmsProductionInspection> qmsProductionInspectionList = qmsProductionInspectionRepository.findByMaterielIdAndWorkno(materielId, workno);
                // 如果物料id+中梁号，在【生产检验表】中已存在
                if (qmsProductionInspectionList.size() > 0 && checkFlag == false) {
                    code = "3";
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return code;
                }
            }
            checkFlag = true;
            Integer id = new Long(bomTechnologyList.get(i).getId()).intValue();
            qpi.setBomTechnologyId(id);
            qpi.setMaterielId(materielId);
            qpi.setSerialNumber(serialNumber);
            qpi.setFurnace(furnace);
            qpi.setFinishNumber(1);
            qpi.setQuailfiedNumber(1);
            qpi.setDeffectiveNumber(1);
            qpi.setWorkno(workno);
            qpi.setIsOk("2");
            qpi.setRemark(remark);
            qpi.setMakeUser(user.getUsername());
            qpi.setModifyUser(user.getUsername());
            qpi.setMakeTime(dateUtil.getDBNowDate());
            qpi.setModifyTime(dateUtil.getDBNowDate());
            qmsProductionInspectionRepository.save(qpi);
        }
    } catch (Exception e) {
        code = "0";
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.info(e.getMessage());
    }
    return code;
}


}