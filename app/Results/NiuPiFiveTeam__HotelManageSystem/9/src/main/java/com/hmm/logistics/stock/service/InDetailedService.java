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
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.logistics.stock.repository.InDetailedRepository;
@Service
@Transactional
public class InDetailedService implements IInDetailedService{

@Autowired
 private  InDetailedRepository inDetailedRepository;


@Override
public boolean existsById(Long id){
    // TODO Auto-generated method stub
    return inDetailedRepository.existsById(id);
}


@Override
public InDetailed findById(Long id){
    // TODO Auto-generated method stub
    return inDetailedRepository.findById(id).get();
}


@Override
public InDetailed save(InDetailed entity){
    // TODO Auto-generated method stub
    return inDetailedRepository.save(entity);
}


@Override
public long count(){
    // TODO Auto-generated method stub
    return inDetailedRepository.count();
}


@Override
public void deleteById(Long id){
    // TODO Auto-generated method stub
    inDetailedRepository.deleteById(id);
}


@Override
public void deleteAll(Long[] ids){
    // TODO Auto-generated method stub
    List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
    List<InDetailed> inDetailed = (List<InDetailed>) inDetailedRepository.findAllById(idLists);
    if (inDetailed != null) {
        inDetailedRepository.deleteAll(inDetailed);
    }
}


@Override
public List<InDetailed> findAll(Specification<InDetailed> spec){
    // TODO Auto-generated method stub
    return inDetailedRepository.findAll(spec);
}


}