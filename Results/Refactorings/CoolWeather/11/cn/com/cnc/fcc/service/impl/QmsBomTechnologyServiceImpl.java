import cn.com.cnc.fcc.domain.QmsBomTechnologyDTO;
import cn.com.cnc.fcc.service.QmsBomTechnologyService;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Service
public class QmsBomTechnologyServiceImpl implements QmsBomTechnologyService,cn.com.cnc.fcc.service.QmsBomTechnologyService{

@Autowired
 private  EntityManagerFactory emf;


@Override
public List<QmsBomTechnologyDTO> qmsTechnologyFindAll(String materielCdIn,String materielNameIn,String technologyNameIn){
    List<QmsBomTechnologyDTO> QmsBomTechnologyDTO = new ArrayList<QmsBomTechnologyDTO>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getfindAllSql(materielCdIn, materielNameIn, technologyNameIn);
        Query query = em.createNativeQuery(sql);
        if (!"".equals(materielCdIn) && materielCdIn != null)
            query.setParameter("materielCdIn", "%" + materielCdIn + "%");
        if (!"".equals(materielNameIn) && materielNameIn != null)
            query.setParameter("materielNameIn", "%" + materielNameIn + "%");
        if (!"".equals(technologyNameIn) && technologyNameIn != null)
            query.setParameter("technologyNameIn", "%" + technologyNameIn + "%");
        ResultTransformer transformer = Transformers.aliasToBean(QmsBomTechnologyDTO.class);
        QmsBomTechnologyDTO = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("technologyId", StandardBasicTypes.LONG).addScalar("materielId", StandardBasicTypes.LONG).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("technologyCd", StandardBasicTypes.STRING).addScalar("technologyName", StandardBasicTypes.STRING).addScalar("processName", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).addScalar("jhiDescribe", StandardBasicTypes.STRING).addScalar("operationType", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        throw new InternalServerErrorException("qmsProductFindAll could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return QmsBomTechnologyDTO;
}


public String getfindAllSql(String materielCdIn,String materielNameIn,String technologyNameIn){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT \n" + "a.id as technologyId,\n" + "b.id as materielId,\n" + "b.materiel_cd as materielCd,\n" + "b.materiel_name as materielName,\n" + "qms_process.process_name AS processName, \n" + "a.technology_cd as technologyCd,\n" + "a.technology_name AS technologyName,\n" + "a.remark AS remark,\n" + "a.jhi_describe AS jhiDescribe,\n" + "a.operation_type AS operationType\n" + "from qms_bom_technology a\n" + "LEFT JOIN qms_materiel b\n" + "on b.id = a.materiel_id \n");
    sql.append(" INNER JOIN qms_process ");
    sql.append(" ON a.process_id = qms_process.id ");
    sql.append(" where 1=1 ");
    if (!"".equals(materielCdIn) && materielCdIn != null)
        sql.append("and b.materiel_cd  LIKE :materielCdIn\n");
    if (!"".equals(materielNameIn) && materielNameIn != null)
        sql.append("and b.materiel_name  LIKE :materielNameIn\n");
    if (!"".equals(technologyNameIn) && technologyNameIn != null)
        sql.append("and a.technology_name  LIKE :technologyNameIn\n");
    return sql.toString();
}


}