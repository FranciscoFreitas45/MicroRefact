package com.github.haseoo.courier.views.places;
 import com.github.haseoo.courier.servicedata.places.MagazineData;
import lombok.Builder;
import lombok.Getter;
import lombok.AccessLevel.PRIVATE;
@Builder(access = PRIVATE)
@Getter
public class MagazineWoLogisticiansView {

 private  Long id;

 private  AddressView address;

 private  Boolean active;


public MagazineWoLogisticiansView of(MagazineData magazineData){
    return MagazineWoLogisticiansView.builder().id(magazineData.getId()).address(AddressView.of(magazineData.getAddress())).active(magazineData.getActive()).build();
}


}