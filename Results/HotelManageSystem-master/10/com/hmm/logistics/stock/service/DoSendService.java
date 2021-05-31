import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.logistics.stock.entity.DoSend;
import com.hmm.logistics.stock.repository.DoSendRepository;
@Service
@Transactional
public class DoSendService implements com.hmm.logistics.stock.service.IDoSendService,IDoSendService{

@Autowired
 private  DoSendRepository doSendRepository;


@Override
public boolean existsById(Long id){
    // TODO Auto-generated method stub
    return doSendRepository.existsById(id);
}


@Override
public DoSend findById(Long id){
    // TODO Auto-generated method stub
    return doSendRepository.findById(id).get();
}


@Override
public DoSend save(DoSend entity){
    // TODO Auto-generated method stub
    return doSendRepository.save(entity);
}


@Override
public long count(){
    // TODO Auto-generated method stub
    return doSendRepository.count();
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    doSendRepository.deleteById(id);
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
    List<DoSend> doSend = (List<DoSend>) doSendRepository.findAllById(idLists);
    if (doSend != null) {
        doSendRepository.deleteAll(doSend);
    }
}


@Override
public List<DoSend> findAll(){
    // TODO Auto-generated method stub
    Iterable<DoSend> getd = doSendRepository.findAll();
    List<DoSend> listd = new ArrayList<DoSend>();
    getd.forEach(single -> {
        listd.add(single);
    });
    return listd;
}


}