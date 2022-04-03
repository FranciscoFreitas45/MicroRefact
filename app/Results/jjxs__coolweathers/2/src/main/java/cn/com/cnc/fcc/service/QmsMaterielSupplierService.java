package cn.com.cnc.fcc.service;
 import cn.com.cnc.fcc.service.dto.QmsMaterielSupplierDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public interface QmsMaterielSupplierService {


public QmsMaterielSupplierDto findById(Long id)
;

public List<QmsMaterielSupplierDto> qmsMaterielSupplierFindAll(String materielCd,String materielName,String supplierCd,String supplierName)
;

}