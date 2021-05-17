import cn.com.cnc.fcc.domain.QmsQualityControlDetails;
import cn.com.cnc.fcc.service.dto.ProductDTO;
import cn.com.cnc.fcc.service.dto.ProductProcessCheckDTO;
import cn.com.cnc.fcc.service.dto.QmsPartsAssemblyRelationDto;
import cn.com.cnc.fcc.service.dto.QmsQualityControlDetailsDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public interface QmsQualityControlDetailsService {


public List<ProductProcessCheckDTO> qmsProductProcessSelfFindAll(HttpServletRequest request)


public JSONObject saveAll(JSONObject jsonObject)


public List<QmsPartsAssemblyRelationDto> findAssemblyRelationByPid(Integer bid)


public String checkProductionRelation(JSONObject jsonObject)


public ProductProcessCheckDTO findById(Long id)


public String deleteAll(String pid)


public List<QmsQualityControlDetailsDto> findByBomTechnologyId(Integer pId,String isDetails)


public JSONObject chackPreProcess(JSONObject jsonObject)


public String updateQmsProductionInspectionValues(String inspectionId,String checkNumber,String isOk,String inspectionDiff)


public String deleteCheck(String pid)


}