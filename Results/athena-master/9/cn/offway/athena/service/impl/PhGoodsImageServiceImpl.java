import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.offway.athena.service.PhGoodsImageService;
import cn.offway.athena.domain.PhGoodsImage;
import cn.offway.athena.repository.PhGoodsImageRepository;
@Service
public class PhGoodsImageServiceImpl implements cn.offway.athena.service.PhGoodsImageService,PhGoodsImageService{

 private  Logger logger;

@Autowired
 private  PhGoodsImageRepository phGoodsImageRepository;


@Override
public int deleteByGoodsIds(List<Long> goodsIds){
    return phGoodsImageRepository.deleteByGoodsIds(goodsIds);
}


@Override
public List<PhGoodsImage> save(List<PhGoodsImage> phGoodsImages){
    return phGoodsImageRepository.save(phGoodsImages);
}


@Override
public PhGoodsImage findOne(Long id){
    return phGoodsImageRepository.findOne(id);
}


@Override
public List<PhGoodsImage> findByGoodsId(Long goodsId){
    return phGoodsImageRepository.findByGoodsId(goodsId);
}


@Override
public void delete(List<PhGoodsImage> phGoodsImages){
    phGoodsImageRepository.delete(phGoodsImages);
}


}