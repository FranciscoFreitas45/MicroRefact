package com.github.haseoo.courier.servicedata.places;
 import com.github.haseoo.courier.commandsdata.places.MagazineAddCommandData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.AccessLevel.PRIVATE;
import lombok.AccessLevel.PUBLIC;
@Value
@Builder(access = PUBLIC)
@AllArgsConstructor(access = PRIVATE)
public class MagazineAddOperationData {

@NonNull
 private  AddressOperationData address;


public MagazineAddOperationData of(MagazineAddCommandData commandData){
    return MagazineAddOperationData.builder().address(AddressOperationData.of(commandData.getAddress())).build();
}


}