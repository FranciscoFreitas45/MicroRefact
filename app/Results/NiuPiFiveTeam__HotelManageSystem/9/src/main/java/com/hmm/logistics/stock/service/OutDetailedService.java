package com.hmm.logistics.stock.service;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.logistics.stock.entity.OutDetailed;
import com.hmm.logistics.stock.repository.OutDetailedRepository;
@Service
@Transactional
public class OutDetailedService implements IOutDetailedService{

@Autowired
 private  OutDetailedRepository outDetailedRepository;


@Override
public boolean existsById(Long id){
    // TODO Auto-generated method stub
    return outDetailedRepository.existsById(id);
}


@Override
public OutDetailed findById(Long id){
    // TODO Auto-generated method stub
    return outDetailedRepository.findById(id).get();
}


@Override
public OutDetailed save(OutDetailed entity){
    // TODO Auto-generated method stub
    return outDetailedRepository.save(entity);
}


@Override
public long count(){
    // TODO Auto-generated method stub
    return outDetailedRepository.count();
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    outDetailedRepository.deleteById(id);
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
    List<OutDetailed> outDetailed = (List<OutDetailed>) outDetailedRepository.findAllById(idLists);
    if (outDetailed != null) {
        outDetailedRepository.deleteAll(outDetailed);
    }
}


@Override
public Page<OutDetailed> findAll(Specification<OutDetailed> spec,Pageable pageable){
    // TODO Auto-generated method stub
    return outDetailedRepository.findAll(spec, pageable);
}


}