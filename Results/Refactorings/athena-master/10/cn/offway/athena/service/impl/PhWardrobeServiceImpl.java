import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhWardrobeService;
import cn.offway.athena.domain.PhWardrobe;
import cn.offway.athena.repository.PhWardrobeRepository;
@Service
public class PhWardrobeServiceImpl implements cn.offway.athena.service.PhWardrobeService,PhWardrobeService{

 private  Logger logger;

@Autowired
 private  PhWardrobeRepository phWardrobeRepository;


@Override
public PhWardrobe save(PhWardrobe phWardrobe){
    return phWardrobeRepository.save(phWardrobe);
}


@Override
public PhWardrobe findOne(Long id){
    return phWardrobeRepository.findOne(id);
}


}