import cn.com.cnc.fcc.service.dto.SupplierPopDto;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.QmsSupplier;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@Service
public interface QmsSuppliersInfoService {


public JSONObject uploadData(MultipartFile files)


public Page<QmsSupplier> qmsSuppliersInfoFindAll(String bianMa,String gongName,Pageable pageable)


public List<SupplierPopDto> findBySupplierCdAndSupplierName(String supplierCd,String supplierName,String materielId)


}