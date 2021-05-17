import cn.com.cnc.fcc.service.dto.LableValueDto;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Service
public class QmsMaterielSqlService {

 private  EntityManagerFactory emf;

 private  Logger log;


@SuppressWarnings("unchecked")
public String getMaterielTypeList(String cd){
    EntityManager em = emf.createEntityManager();
    String result = "no";
    List<LableValueDto> supplier = new ArrayList<LableValueDto>();
    try {
        Query query = em.createNativeQuery("SELECT\n" + "\tt01.materiel_type_cd as value,t01.materiel_type_name as label\n" + "FROM\n" + "\tqms_materiel_type t01\n" + "WHERE\n" + "\tNOT EXISTS (\n" + "\t\tSELECT\n" + "\t\t\tt02.parent_cd\n" + "\t\tFROM\n" + "\t\t\tqms_materiel_type t02\n" + "\t\tWHERE\n" + "\t\t\tt01.materiel_type_cd = t02.parent_cd\n" + "\t)");
        ResultTransformer transformer = Transformers.aliasToBean(LableValueDto.class);
        supplier = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("label", StandardBasicTypes.STRING).addScalar("value", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
        query = null;
    } catch (Exception e) {
        // 异常信息
        log.info(e.getMessage());
        throw new InternalServerErrorException("qmsEntryInspection could not be found");
    } finally {
        em.close();
    }
    if (cd == null) {
        result = "yes";
    } else {
        for (int i = 0; i < supplier.size(); i++) {
            if (cd.equals(supplier.get(i).getValue())) {
                result = "yes";
            }
        }
    }
    // 返回值
    return result;
}


}