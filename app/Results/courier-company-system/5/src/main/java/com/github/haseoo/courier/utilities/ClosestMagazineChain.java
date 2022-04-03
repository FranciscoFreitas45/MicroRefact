package com.github.haseoo.courier.utilities;
 import com.github.haseoo.courier.models.MagazineModel;
import com.github.haseoo.courier.servicedata.places.AddressData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel.PRIVATE;
@Getter
@AllArgsConstructor(access = PRIVATE)
public class ClosestMagazineChain {

 private  ClosestMagazineChain nextSteep;


public ClosestMagazineChain prepareChain(boolean isPostalCodeInCity){
    if (isPostalCodeInCity) {
        return new FilterByCity(new FilterByPostalCode(new FilterByStreet(null)));
    } else {
        return new FilterByPostalCode(null);
    }
}


@Override
public List<MagazineModel> filterList(List<MagazineModel> magazineList,AddressData address){
    return magazineList.stream().filter(magazine -> magazine.getAddress().getPostalCode().equals(address.getPostalCode())).collect(Collectors.toList());
}


}