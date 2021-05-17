import cn.com.cnc.fcc.domain.QmsVehicleTypeClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface QmsVehicTypeClassService {


public Page<QmsVehicleTypeClass> qmsVtcFindAll(String vehicleClass,String vehicleClassName,Pageable pageable)


}