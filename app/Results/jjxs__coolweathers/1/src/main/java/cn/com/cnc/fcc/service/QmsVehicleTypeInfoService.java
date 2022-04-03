package cn.com.cnc.fcc.service;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
@Service
public interface QmsVehicleTypeInfoService {


public Integer updateVehicleTypeFlag(QmsVehicleTypeInfo qmsVehicleTypeInfo)
;

public Page<QmsVehicleTypeInfo> qmsVehicleTypeFindAll(String carType,String carTypeName,Pageable pageable)
;

}