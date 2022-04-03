package com.github.haseoo.courier.repositories.adapters;
 import com.github.haseoo.courier.models.MagazineModel;
import com.github.haseoo.courier.repositories.jpa.MagazineJPARepository;
import com.github.haseoo.courier.repositories.ports.MagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Repository
public class MagazineRepositoryImpl implements MagazineRepository{

 private  MagazineJPARepository magazineJPARepository;


@Override
public MagazineModel saveAndFlush(MagazineModel magazineModel){
    return magazineJPARepository.saveAndFlush(magazineModel);
}


@Override
public List<MagazineModel> getActiveMagazines(){
    return magazineJPARepository.findAllByActiveTrue();
}


@Override
public Optional<MagazineModel> getById(Long id){
    return magazineJPARepository.findById(id);
}


@Override
public List<MagazineModel> getList(){
    return magazineJPARepository.findAll();
}


}