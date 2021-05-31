import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.logistics.loseGoods.entity.LoseGoods;
import com.hmm.logistics.loseGoods.repository.LoseGoodsRepository;
import com.hmm.logistics.stock.repository.InDetailedRepository;
@Service
@Transactional
public class LoseGoodsService implements ILoseGoodsService,com.hmm.logistics.loseGoods.service.ILoseGoodsService{

@Autowired
 private  LoseGoodsRepository loseGoodsRepository;


@Override
public LoseGoods save(LoseGoods entity){
    // TODO Auto-generated method stub
    return loseGoodsRepository.save(entity);
}


@Override
public Page<LoseGoods> findAll(Specification<LoseGoods> spec,Pageable pageable){
    // TODO Auto-generated method stub
    return loseGoodsRepository.findAll(spec, pageable);
}


}