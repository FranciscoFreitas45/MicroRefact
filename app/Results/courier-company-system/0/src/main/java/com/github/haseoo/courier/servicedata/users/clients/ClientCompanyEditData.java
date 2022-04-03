package com.github.haseoo.courier.servicedata.users.clients;
 import com.github.haseoo.courier.commandsdata.users.clients.ClientCompanyEditCommandData;
import lombok.Builder;
import lombok.Value;
import lombok.AccessLevel.PUBLIC;
@Value
@Builder(access = PUBLIC)
public class ClientCompanyEditData {

 private  char[] password;

 private  String emailAddress;

 private  String phoneNumber;

 private  String companyName;

 private  String representativeName;

 private  String representativeSurname;

 private  String representativeEmailAddress;

 private  String representativePhoneNumber;


public ClientCompanyEditData of(ClientCompanyEditCommandData commandData){
    return ClientCompanyEditData.builder().password(commandData.getPassword()).emailAddress(commandData.getEmailAddress()).phoneNumber(commandData.getPhoneNumber()).companyName(commandData.getCompanyName()).representativeName(commandData.getRepresentativeName()).representativeSurname(commandData.getRepresentativeSurname()).representativeEmailAddress(commandData.getRepresentativeEmailAddress()).representativePhoneNumber(commandData.getRepresentativePhoneNumber()).build();
}


}