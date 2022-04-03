package cn.com.cnc.fcc.service;
 import cn.com.cnc.fcc.domain.QmsBomTechnologyDTO;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface QmsBomTechnologyService {


public List<QmsBomTechnologyDTO> qmsTechnologyFindAll(String materielCdIn,String materielNameIn,String technologyNameIn)
;

}