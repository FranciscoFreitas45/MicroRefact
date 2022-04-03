package com.github.haseoo.courier.repositories.adapters;
 import com.github.haseoo.courier.models.ParcelTypeModel;
import com.github.haseoo.courier.repositories.jpa.ParcelTypeJPARepository;
import com.github.haseoo.courier.repositories.ports.ParcelTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class ParcelTypeRepositoryImpl implements ParcelTypeRepository{

 private  ParcelTypeJPARepository parcelTypeJPARepository;


@Override
public ParcelTypeModel saveAndFlush(ParcelTypeModel parcelTypeModel){
    return parcelTypeJPARepository.saveAndFlush(parcelTypeModel);
}


@Override
public Optional<ParcelTypeModel> getById(Long id){
    return parcelTypeJPARepository.findById(id);
}


@Override
public List<ParcelTypeModel> getList(){
    return parcelTypeJPARepository.findAll();
}


@Override
public void delete(Long id){
    parcelTypeJPARepository.deleteById(id);
}


@Override
public List<ParcelTypeModel> getActiveTypes(){
    return parcelTypeJPARepository.getAllByActiveTrue();
}


}