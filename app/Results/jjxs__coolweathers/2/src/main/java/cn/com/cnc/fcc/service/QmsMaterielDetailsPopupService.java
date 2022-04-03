package cn.com.cnc.fcc.service;
 import java.util.List;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.service.dto.QmsMaterielDetailsPopupDTO;
@Service
public interface QmsMaterielDetailsPopupService {


public List<QmsMaterielDetailsPopupDTO> qmsMaterialTypeFindAll(String materielId,String supplierName)
;

}