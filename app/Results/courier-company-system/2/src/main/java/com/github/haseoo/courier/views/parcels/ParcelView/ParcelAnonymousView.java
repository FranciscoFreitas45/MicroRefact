package com.github.haseoo.courier.views.parcels.ParcelView;
 import com.github.haseoo.courier.enums.ParcelStateType;
import com.github.haseoo.courier.enums.UserType;
import com.github.haseoo.courier.exceptions.serviceexceptions.parcelsexceptions.IllegalParcelState;
import com.github.haseoo.courier.servicedata.parcels.ParcelData;
import com.github.haseoo.courier.utilities.ParcelStateDataComparator;
import com.github.haseoo.courier.views.parcels.type.ParcelTypeView;
import com.github.haseoo.courier.views.places.AddressView;
import com.github.haseoo.courier.views.places.PlaceView;
import com.github.haseoo.courier.views.receiver.info.ReceiverInfoView;
import com.github.haseoo.courier.views.users.clients.ClientView;
import lombok;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import com.github.haseoo.courier.enums.ParcelStateType.AT_SENDER;
import com.github.haseoo.courier.enums.PlaceType;
import com.github.haseoo.courier.enums.UserType.COMPANY_CLIENT;
import com.github.haseoo.courier.enums.UserType.LOGISTICIAN;
import com.github.haseoo.courier.utilities.Constants.PRIORITY_MULTILAYER;
import com.github.haseoo.courier.utilities.Utils.isParcelMoveable;
import lombok.AccessLevel.PRIVATE;
@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
@AllArgsConstructor(access = PRIVATE)
public class ParcelAnonymousView extends ParcelClientView{

 private  String sender;

 private  AddressView senderAddress;


public ParcelAnonymousView of(ParcelData parcelData,String sender){
    return ParcelAnonymousView.builder().id(parcelData.getId()).parcelType(ParcelTypeView.of(parcelData.getParcelType())).receiverInfoView(ReceiverInfoView.of(parcelData.getReceiverContactData())).expectedCourierArrivalDate(parcelData.getExpectedCourierArrivalDate()).parcelPrice(calculatePrice(parcelData)).parcelFee(parcelData.getParcelFee()).paid(parcelData.getPaid()).priority(parcelData.getPriority()).toReturn(parcelData.getToReturn()).parcelStatesView(parcelData.getParcelStates().stream().map(parcelStateData -> ParcelStateView.of(parcelStateData, COMPANY_CLIENT)).collect(Collectors.toList())).deliveryAddress(AddressView.of(parcelData.getDeliveryAddress())).moveable(isParcelMoveable(parcelData)).sender(sender).senderAddress(AddressView.of(parcelData.getSenderAddress())).build();
}


}