package com.github.haseoo.courier.servicedata.places;
 import com.github.haseoo.courier.commandsdata.places.AddressCommandData;
import com.github.haseoo.courier.models.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel.PRIVATE;
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class AddressOperationData {

@NotEmpty
 private  String city;

@NotEmpty
 private  String street;

@NotEmpty
 private  String postalCode;

@NotEmpty
 private  String buildingNumber;

@NonNull
 private  String flatNumber;


public AddressOperationData of(AddressModel addressModel){
    return AddressOperationData.builder().city(addressModel.getCity()).street(addressModel.getStreet()).postalCode(addressModel.getPostalCode()).buildingNumber(addressModel.getBuildingNumber()).flatNumber(addressModel.getFlatNumber()).build();
}


}