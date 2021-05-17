import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.service.dto.MaterielPopDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Service
public interface QmsMaterielService {


public JSONObject uploadData(MultipartFile files)


public List<MaterielPopDto> qmsMaterielFindAll(String materielCd,String materielName,String supplier,String figureNumber,String type,String supplierId)


}