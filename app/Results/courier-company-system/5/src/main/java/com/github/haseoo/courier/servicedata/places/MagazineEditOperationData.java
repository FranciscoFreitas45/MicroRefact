package com.github.haseoo.courier.servicedata.places;
 import com.github.haseoo.courier.commandsdata.places.MagazineEditCommandData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.AccessLevel.PRIVATE;
import lombok.AccessLevel.PUBLIC;
@Value
@Builder(access = PUBLIC)
@AllArgsConstructor(access = PRIVATE)
public class MagazineEditOperationData {

 private  AddressOperationData address;

 private  Boolean active;


public MagazineEditOperationData of(MagazineEditCommandData commandData){
    return MagazineEditOperationData.builder().active(commandData.getActive()).address(AddressOperationData.of(commandData.getAddress())).build();
}


}