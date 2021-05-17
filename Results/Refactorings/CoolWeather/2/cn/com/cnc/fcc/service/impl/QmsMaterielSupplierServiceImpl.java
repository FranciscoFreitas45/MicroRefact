import cn.com.cnc.fcc.repository.QmsMaterielSupplierRepository;
import cn.com.cnc.fcc.service.QmsMaterielSupplierService;
import cn.com.cnc.fcc.service.dto.QmsMaterielSupplierDto;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class QmsMaterielSupplierServiceImpl implements cn.com.cnc.fcc.service.QmsMaterielSupplierService,QmsMaterielSupplierService{

 private  Logger log;

 private  QmsMaterielSupplierRepository qmsMaterielSupplierRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;


@Override
public QmsMaterielSupplierDto findById(Long id){
    QmsMaterielSupplierDto qmsMaterielSupplierDto = new QmsMaterielSupplierDto();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = findByIdSql(id);
        Query query = em.createNativeQuery(sql);
        if (id != null)
            query.setParameter("id", id);
        ResultTransformer transformer = Transformers.aliasToBean(QmsMaterielSupplierDto.class);
        qmsMaterielSupplierDto = (QmsMaterielSupplierDto) query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("materielId", StandardBasicTypes.LONG).addScalar("supplierId", StandardBasicTypes.LONG).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).setResultTransformer(transformer).getSingleResult();
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsMaterielSupplier could not be found");
    } finally {
        em.close();
    }
    return qmsMaterielSupplierDto;
}


public String findByIdSql(Long id){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\tt01.id as id,\n" + "\tt01.materiel_id as materielId,\n" + "\tt01.supplier_id as supplierId,\n" + "\tt02.materiel_cd as materielCd,\n" + "\tt02.materiel_name as materielName,\n" + "\tt03.supplier_cd as supplierCd,\n" + "\tt03.supplier_name as supplierName,\n" + "\tt01.remark as remark\n" + "FROM\n" + "\tqms_materiel_supplier t01\n" + "LEFT JOIN qms_materiel t02 ON t01.materiel_id = t02.id\n" + "LEFT JOIN qms_supplier t03 ON t01.supplier_id = t03.id\n" + "WHERE 1=1 \n");
    if (id != null) {
        sql.append("AND t01.id = :id");
    }
    return sql.toString();
}


@Override
public List<QmsMaterielSupplierDto> qmsMaterielSupplierFindAll(String materielCd,String materielName,String supplierCd,String supplierName){
    List<QmsMaterielSupplierDto> qmsMaterielSupplierDtos = new ArrayList<QmsMaterielSupplierDto>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getfindAllSql(materielCd, materielName, supplierCd, supplierName);
        Query query = em.createNativeQuery(sql);
        if (!"".equals(materielCd) && materielCd != null)
            query.setParameter("materielCd", "%" + materielCd + "%");
        if (!"".equals(materielName) && materielName != null)
            query.setParameter("materielName", "%" + materielName + "%");
        if (!"".equals(supplierCd) && supplierCd != null)
            query.setParameter("supplierCd", "%" + supplierCd + "%");
        if (!"".equals(supplierName) && supplierName != null)
            query.setParameter("supplierName", "%" + supplierName + "%");
        ResultTransformer transformer = Transformers.aliasToBean(QmsMaterielSupplierDto.class);
        qmsMaterielSupplierDtos = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("materielId", StandardBasicTypes.LONG).addScalar("supplierId", StandardBasicTypes.LONG).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).addScalar("remark", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e);
        throw new InternalServerErrorException("QmsMaterielSupplier could not be found");
    } finally {
        em.close();
    }
    // 返回值
    return qmsMaterielSupplierDtos;
}


public String getfindAllSql(String materielCd,String materielName,String supplierCd,String supplierName){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\tt01.id as id,\n" + "\tt01.materiel_id as materielId,\n" + "\tt01.supplier_id as supplierId,\n" + "\tt02.materiel_cd as materielCd,\n" + "\tt02.materiel_name as materielName,\n" + "\tt03.supplier_cd as supplierCd,\n" + "\tt03.supplier_name as supplierName,\n" + "\tt01.remark as remark\n" + "FROM\n" + "\tqms_materiel_supplier t01\n" + "LEFT JOIN qms_materiel t02 ON t01.materiel_id = t02.id\n" + "LEFT JOIN qms_supplier t03 ON t01.supplier_id = t03.id\n" + "WHERE 1=1 \n");
    if (!"".equals(materielCd) && materielCd != null)
        sql.append("AND t02.materiel_cd LIKE :materielCd\n");
    if (!"".equals(materielName) && materielName != null)
        sql.append("AND t02.materiel_name LIKE :materielName\n");
    if (!"".equals(supplierCd) && supplierCd != null)
        sql.append("AND t03.supplier_cd LIKE :supplierCd\n");
    if (!"".equals(supplierName) && supplierName != null)
        sql.append("AND t03.supplier_name LIKE :supplierName");
    return sql.toString();
}


}