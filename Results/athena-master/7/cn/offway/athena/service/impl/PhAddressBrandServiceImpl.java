import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhAddressBrandService;
import cn.offway.athena.domain.PhAddressBrand;
import cn.offway.athena.repository.PhAddressBrandRepository;
@Service
public class PhAddressBrandServiceImpl implements PhAddressBrandService,cn.offway.athena.service.PhAddressBrandService{

 private  Logger logger;

@Autowired
 private  PhAddressBrandRepository phAddressBrandRepository;


@Override
public List<PhAddressBrand> save(List<PhAddressBrand> entities){
    return phAddressBrandRepository.save(entities);
}


@Override
public PhAddressBrand findOne(Long id){
    return phAddressBrandRepository.findOne(id);
}


@Override
public void delete(Long id){
    phAddressBrandRepository.delete(id);
}


}