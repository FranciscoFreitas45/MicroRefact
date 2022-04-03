package com.github.haseoo.courier.utilities.ClosestMagazineChain;
 import com.github.haseoo.courier.models.MagazineModel;
import com.github.haseoo.courier.servicedata.places.AddressData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel.PRIVATE;
public class FilterByPostalCode extends ClosestMagazineChain{

private FilterByPostalCode(ClosestMagazineChain nextSteep) {
    super(nextSteep);
}
@Override
public List<MagazineModel> filterList(List<MagazineModel> magazineList,AddressData address){
    return magazineList.stream().filter(magazine -> magazine.getAddress().getPostalCode().equals(address.getPostalCode())).collect(Collectors.toList());
}


}