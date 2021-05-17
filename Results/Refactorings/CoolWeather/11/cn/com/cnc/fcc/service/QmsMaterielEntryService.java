import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.service.dto.MaterielPopDto;
import cn.com.cnc.fcc.service.dto.QmsMaterielEntryDto;
import cn.com.cnc.fcc.service.dto.QmsMaterielEntryEditDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Service
public interface QmsMaterielEntryService {


public JSONObject uploadData(MultipartFile files)


public JSONObject getEditHeader(String Id)


public List<QmsMaterielEntryDto> qmsMaterielEntryFindAll(String materielCd,String materielName,String figureNumber,String supplierCd,String supplierName,String specificationType,String purchaseOrderNumber,String flagInspect,String enclosure)


public JSONObject saveData(JSONObject body)


}