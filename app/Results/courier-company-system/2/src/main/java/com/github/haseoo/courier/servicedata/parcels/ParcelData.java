package com.github.haseoo.courier.servicedata.parcels;
 import com.github.haseoo.courier.models.ParcelModel;
import com.github.haseoo.courier.servicedata.places.AddressData;
import com.github.haseoo.courier.servicedata.users.clients.ClientData;
import com.github.haseoo.courier.utilities.ParcelStateRecordComparator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.github.haseoo.courier.enums.ParcelStateType.IN_MAGAZINE;
import com.github.haseoo.courier.models.ParcelStateRecord.defaultParcelStateRecord;
@Value
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ParcelData {

 private  Long id;

 private  ParcelStateData currentState;

 private  List<ParcelStateData> parcelStates;

 private  char[] pin;

 private  ParcelTypeData parcelType;

 private  AddressData deliveryAddress;

 private  AddressData senderAddress;

 private  ClientData sender;

 private  ReceiverInfoData receiverContactData;

 private  LocalDate expectedCourierArrivalDate;

 private  Boolean priority;

 private  BigDecimal parcelFee;

 private  Boolean paid;

 private  Boolean dateMoved;

 private  Boolean toReturn;


public BigDecimal getEffectivePrice(){
    if (priority) {
        return parcelType.getPrice().multiply(BigDecimal.valueOf(1.1)).add(parcelFee);
    }
    return parcelFee;
}


public boolean wasInMagazine(){
    return parcelStates.stream().anyMatch(parcelStateData -> parcelStateData.getState().equals(IN_MAGAZINE));
}


public ParcelData ofWithoutStates(ParcelModel parcelModel){
    return ParcelData.builder().id(parcelModel.getId()).pin(parcelModel.getPin()).parcelType(ParcelTypeData.ofWithoutList(parcelModel.getParcelType())).deliveryAddress(AddressData.of(parcelModel.getDeliveryAddress())).senderAddress(AddressData.of(parcelModel.getSenderAddress())).sender(ClientData.ofWithoutLists(parcelModel.getSender())).receiverContactData(ReceiverInfoData.of(parcelModel.getReceiverContactData())).expectedCourierArrivalDate(parcelModel.getExpectedCourierArrivalDate()).priority(parcelModel.getPriority()).parcelFee(parcelModel.getParcelFee()).paid(parcelModel.getPaid()).dateMoved(parcelModel.getDateMoved()).toReturn(parcelModel.getToReturn()).build();
}


public ParcelData of(ParcelModel parcelModel){
    return ParcelData.builder().id(parcelModel.getId()).parcelStates(((parcelModel.getParcelStates() != null) ? parcelModel.getParcelStates().stream().map(ParcelStateData::of).collect(Collectors.toList()) : new ArrayList<>())).currentState(((parcelModel.getParcelStates() != null) ? ParcelStateData.of(parcelModel.getParcelStates().stream().max(new ParcelStateRecordComparator()).orElse(defaultParcelStateRecord())) : null)).pin(parcelModel.getPin()).parcelType(ParcelTypeData.ofWithoutList(parcelModel.getParcelType())).deliveryAddress(AddressData.of(parcelModel.getDeliveryAddress())).senderAddress(AddressData.of(parcelModel.getSenderAddress())).sender(ClientData.ofWithoutLists(parcelModel.getSender())).receiverContactData(ReceiverInfoData.of(parcelModel.getReceiverContactData())).expectedCourierArrivalDate(parcelModel.getExpectedCourierArrivalDate()).priority(parcelModel.getPriority()).parcelFee(parcelModel.getParcelFee()).paid(parcelModel.getPaid()).dateMoved(parcelModel.getDateMoved()).toReturn(parcelModel.getToReturn()).build();
}


}