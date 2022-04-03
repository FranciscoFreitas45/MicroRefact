package com.github.haseoo.courier.servicedata.parcels;
 import com.github.haseoo.courier.commandsdata.parcels.ReceiverInfoCommandData;
import com.github.haseoo.courier.models.ClientCompanyModel;
import com.github.haseoo.courier.models.ClientIndividualModel;
import com.github.haseoo.courier.models.ReceiverInfoModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotEmpty;
@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiverInfoOperationData {

@NotEmpty
 private  String name;

@NotEmpty
 private  String surname;

@NotEmpty
 private  String emailAddress;

@NotEmpty
 private  String phoneNumber;


public ReceiverInfoOperationData of(ClientCompanyModel clientCompanyModel){
    return ReceiverInfoOperationData.builder().emailAddress(clientCompanyModel.getEmailAddress()).name(clientCompanyModel.getCompanyName()).phoneNumber(clientCompanyModel.getPhoneNumber()).surname("-").build();
}


}