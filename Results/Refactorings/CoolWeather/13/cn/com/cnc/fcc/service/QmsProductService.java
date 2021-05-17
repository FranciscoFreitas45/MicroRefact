import cn.com.cnc.fcc.domain.QmsProductRelationTabDTO;
import cn.com.cnc.fcc.domain.QmsProductRelationTabThreeDTO;
import cn.com.cnc.fcc.domain.QmsProductRelationTabTwoDTO;
import cn.com.cnc.fcc.service.dto.ProductDTO;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Service
public interface QmsProductService {


public JSONObject uploadData(MultipartFile files)


public List<QmsProductRelationTabDTO> qmsProductFindTabData(String productRelation)


public List<QmsProductRelationTabTwoDTO> qmsProductFindTabDataTwo(String productRelation)


public List<ProductDTO> qmsProductFindAll(String productNumIn,String materielCdIn,String materielNameIn,String productBatchIn,String materielId,String sp)


public List<QmsProductRelationTabThreeDTO> qmsProductFindTabDataThree(String productRelation)


}