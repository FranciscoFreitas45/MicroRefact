import cn.offway.athena.domain.PhGoodsType;
import cn.offway.athena.repository.PhGoodsTypeRepository;
import cn.offway.athena.service.PhGoodsTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PhGoodsTypeServiceImpl implements PhGoodsTypeService,cn.offway.athena.service.PhGoodsTypeService{

 private  Logger logger;

@Autowired
 private  PhGoodsTypeRepository phGoodsTypeRepository;


@Override
public PhGoodsType save(PhGoodsType phGoodsType){
    return phGoodsTypeRepository.save(phGoodsType);
}


@Override
public PhGoodsType findOne(Long id){
    return phGoodsTypeRepository.findOne(id);
}


@Override
public void del(Long id){
    phGoodsTypeRepository.delete(id);
}


@Override
public List<PhGoodsType> findAll(){
    return phGoodsTypeRepository.findAll();
}


}