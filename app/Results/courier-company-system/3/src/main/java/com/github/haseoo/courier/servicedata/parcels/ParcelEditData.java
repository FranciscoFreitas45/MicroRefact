package com.github.haseoo.courier.servicedata.parcels;
 import com.github.haseoo.courier.commandsdata.parcels.ParcelCommandEditData;
import com.github.haseoo.courier.servicedata.places.AddressOperationData;
import lombok.Builder;
import lombok.Value;
import java.math.BigDecimal;
import lombok.AccessLevel.PUBLIC;
@Value
@Builder(access = PUBLIC)
public class ParcelEditData {

 private  AddressOperationData deliveryAddress;

 private  AddressOperationData senderAddress;

 private  ReceiverInfoOperationData receiverContactData;

 private  BigDecimal parcelFee;

 private  Boolean priority;


public ParcelEditData of(ParcelCommandEditData commandEditData){
    return ParcelEditData.builder().deliveryAddress(AddressOperationData.of(commandEditData.getDeliveryAddress())).senderAddress(AddressOperationData.of(commandEditData.getSenderAddress())).receiverContactData(ReceiverInfoOperationData.of(commandEditData.getReceiverContactData())).build();
}


}