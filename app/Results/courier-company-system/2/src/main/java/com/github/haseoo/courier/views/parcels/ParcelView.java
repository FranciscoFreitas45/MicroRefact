package com.github.haseoo.courier.views.parcels;
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
@Getter
@SuperBuilder
@NoArgsConstructor(access = PRIVATE)
public class ParcelView {

 private  Long id;

 private  ParcelTypeView parcelType;

 private  ReceiverInfoView receiverInfoView;

 private  LocalDate expectedCourierArrivalDate;

 private  BigDecimal parcelPrice;

 private  BigDecimal parcelFee;

 private  Boolean paid;

 private  Boolean priority;

 private  Boolean toReturn;

 private  List<ParcelStateView> parcelStatesView;

 private  AddressView deliveryAddress;

 private  Boolean moveable;

 private  String sender;

 private  AddressView senderAddress;

 private  PlaceView source;

 private  PlaceView destination;

 private  ParcelStateType currentState;

 private  List<ParcelStateView> parcelStatesView;

 private  PlaceView source;

 private  PlaceView destination;

 private  ParcelStateView currentState;

 private  ClientView clientView;

 private  Boolean dateMoved;

 private  char[] pin;

 private  List<ParcelStateView> parcelStatesView;

 private  PlaceView source;

 private  PlaceView destination;

 private  ParcelStateView currentState;

 private  ClientView clientView;

 private  Boolean dateMoved;


public BigDecimal calculatePrice(ParcelData parcelData){
    BigDecimal price = parcelData.getParcelType().getPrice();
    if (parcelData.getPriority()) {
        price = price.multiply(PRIORITY_MULTILAYER);
    }
    return price;
}


public PlaceView getDestination(ParcelData parcelData){
    if (!parcelData.wasInMagazine()) {
        return PlaceView.builder().placeType(MAGAZINE).address(AddressView.of(parcelData.getParcelStates().stream().filter(parcelStateData -> parcelStateData.getState().equals(AT_SENDER)).findFirst().orElseThrow(IllegalParcelState::new).getMagazine().getAddress())).build();
    }
    return PlaceView.builder().placeType(RECEIVER).address(AddressView.of(parcelData.getDeliveryAddress())).build();
}


public PlaceView getSource(ParcelData parcelData){
    if (!parcelData.wasInMagazine()) {
        return PlaceView.builder().address(AddressView.of(parcelData.getSenderAddress())).placeType(SENDER).build();
    }
    return PlaceView.builder().placeType(MAGAZINE).address(AddressView.of(parcelData.getParcelStates().stream().filter(parcelStateData -> parcelStateData.getState().equals(ParcelStateType.IN_MAGAZINE)).max(new ParcelStateDataComparator()).orElseThrow(IllegalParcelState::new).getMagazine().getAddress())).build();
}


public ParcelView of(ParcelData parcelData,UserType userType,String sender){
    switch(userType) {
        case COMPANY_CLIENT:
        case INDIVIDUAL_CLIENT:
            return ParcelClientView.of(parcelData);
        case LOGISTICIAN:
            return ParcelLogisticianView.of(parcelData);
        case COURIER:
            return ParcelCourierView.of(parcelData);
        case ADMIN:
            return ParcelAdminView.of(parcelData);
        case ANONYMOUS:
        default:
            return ParcelAnonymousView.of(parcelData, sender);
    }
}


}