package com.github.haseoo.courier.repositories.adapters;
 import com.github.haseoo.courier.models.AddressModel;
import com.github.haseoo.courier.querydata.AddressQueryData;
import com.github.haseoo.courier.repositories.jpa.AddressJPARepository;
import com.github.haseoo.courier.repositories.ports.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository{

 private  AddressJPARepository addressJPARepository;


@Override
public AddressModel saveAndFlush(AddressModel addressModel){
    return addressJPARepository.saveAndFlush(addressModel);
}


@Override
public Optional<AddressModel> getById(Long id){
    return addressJPARepository.findById(id);
}


@Override
public List<AddressModel> getList(){
    return addressJPARepository.findAll();
}


@Transactional
@Override
public Optional<AddressModel> addressExist(AddressQueryData addressQueryData){
    return addressJPARepository.findByPostalCodeAndCityAndStreetAndBuildingNumberAndFlatNumber(addressQueryData.getPostalCode(), addressQueryData.getCity(), addressQueryData.getStreet(), addressQueryData.getBuildingNumber(), addressQueryData.getFlatNumber());
}


}