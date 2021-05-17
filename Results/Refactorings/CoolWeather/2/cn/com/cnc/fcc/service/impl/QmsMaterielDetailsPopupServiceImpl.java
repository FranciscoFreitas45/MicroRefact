import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.service.QmsMaterielDetailsPopupService;
import cn.com.cnc.fcc.service.dto.QmsMaterielDetailsPopupDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
@Service
public class QmsMaterielDetailsPopupServiceImpl implements QmsMaterielDetailsPopupService,cn.com.cnc.fcc.service.QmsMaterielDetailsPopupService{

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;


@SuppressWarnings({ "unchecked", "deprecation" })
@Override
public List<QmsMaterielDetailsPopupDTO> qmsMaterialTypeFindAll(String materielId,String supplierName){
    List<QmsMaterielDetailsPopupDTO> materialTypeSelectionDto = new ArrayList<QmsMaterielDetailsPopupDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        Query query = em.createNativeQuery(deleteParentNodeInfo(materielId, supplierName));
        // if (!"".equals(materielId) && materielId != null)
        // query.setParameter("materielId", "%" + materielId + "%");
        if (!"".equals(supplierName) && supplierName != null) {
            query.setParameter("supplierName", "%" + supplierName + "%");
        }
        ResultTransformer transformer = Transformers.aliasToBean(QmsMaterielDetailsPopupDTO.class);
        materialTypeSelectionDto = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("entryId", StandardBasicTypes.INTEGER).addScalar("goodsCd", StandardBasicTypes.STRING).addScalar("madeFactoryCd", StandardBasicTypes.STRING).addScalar("madeYMD", StandardBasicTypes.STRING).addScalar("entryQuantity", StandardBasicTypes.INTEGER).addScalar("entryType", StandardBasicTypes.STRING).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsMaterielSupplier could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return materialTypeSelectionDto;
}


public String deleteParentNodeInfo(String materielId,String supplierName){
    StringBuffer objSqlContent = new StringBuffer();
    objSqlContent.append(" SELECT  qmd.id as \"id\", ");
    objSqlContent.append(" qmd.entry_id as \"entryId\", ");
    objSqlContent.append(" qmd.goods_cd as \"goodsCd\", ");
    objSqlContent.append(" qme.made_ymd as \"madeYMD\", ");
    objSqlContent.append(" qme.made_factory_cd \"madeFactoryCd\", ");
    objSqlContent.append(" qmd.goods_quantity \"entryQuantity\", ");
    objSqlContent.append(" qme.entry_type \"entryType\", ");
    objSqlContent.append(" qm.materiel_cd as \"materielCd\", ");
    objSqlContent.append(" qm.materiel_name as \"materielName\", ");
    objSqlContent.append(" qs.supplier_cd as \"supplierCd\", ");
    objSqlContent.append(" qs.supplier_name as \"supplierName\" ");
    objSqlContent.append(" FROM  qms_materiel_details qmd, qms_materiel_entry qme ");
    objSqlContent.append(" LEFT JOIN qms_materiel qm ON qm.id = qme.materiel_id AND qm.flag_status = '0' ");
    objSqlContent.append(" LEFT JOIN qms_supplier qs ON qs.id = qme.supplier_id AND qs.flag_status = '0' ");
    objSqlContent.append(" WHERE qmd.entry_id = qme.id ");
    objSqlContent.append(" AND qmd.flag_status = '0' ");
    objSqlContent.append(" AND qme.flag_status = '0' ");
    objSqlContent.append(" AND ((SELECT COUNT(t.id) FROM qms_production_relation t where t.assembly_materiel_id = qmd.id)<qmd.goods_quantity) ");
    objSqlContent.append(" AND qme.materiel_id =  '" + materielId + "'");
    if (!"".equals(supplierName) && supplierName != null) {
        objSqlContent.append(" AND qs.supplier_name LIKE :supplierName");
    }
    return objSqlContent.toString();
}


}