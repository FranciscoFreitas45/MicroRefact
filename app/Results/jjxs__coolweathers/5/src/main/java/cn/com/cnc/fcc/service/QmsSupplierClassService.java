package cn.com.cnc.fcc.service;
 import cn.com.cnc.fcc.domain.QmsSupplierClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public interface QmsSupplierClassService {


public Page<QmsSupplierClass> qmsSupplierClassFindAll(String bianMa,String gongName,Pageable pageable)
;

}