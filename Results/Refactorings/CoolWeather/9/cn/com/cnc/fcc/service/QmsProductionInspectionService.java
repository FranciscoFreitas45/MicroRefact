import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.domain.QmsProductionInspection;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public interface QmsProductionInspectionService {


public JSONObject chackPreProcess(String bomTechnologyId,String serialNumber)


@Transactional
public String doBatchGeneration(QmsProductionInspection qmsProductionInspection,List<QmsBomTechnology> list)


}