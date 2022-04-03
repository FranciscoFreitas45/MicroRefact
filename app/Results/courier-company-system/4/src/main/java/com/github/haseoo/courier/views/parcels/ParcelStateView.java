package com.github.haseoo.courier.views.parcels;
 import com.github.haseoo.courier.enums.ParcelStateType;
import com.github.haseoo.courier.enums.UserType;
import com.github.haseoo.courier.servicedata.parcels.ParcelStateData;
import com.github.haseoo.courier.views.places.MagazineWithoutListsView;
import com.github.haseoo.courier.views.users.employees.CourierWithoutListView;
import lombok;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import lombok.AccessLevel.PRIVATE;
@Getter
@SuperBuilder
@NoArgsConstructor(access = PRIVATE)
public class ParcelStateView {

 private  ParcelStateType stateType;

 private  LocalDateTime changeTime;

 private  String city;

 private  CourierWithoutListView courier;

 private  MagazineWithoutListsView magazine;


public ParcelStateView of(ParcelStateData data,UserType userType){
    switch(userType) {
        case LOGISTICIAN:
        case ADMIN:
        case COURIER:
            return ParcelStateFullView.of(data);
        case COMPANY_CLIENT:
        case INDIVIDUAL_CLIENT:
        default:
            return ClientParcelStateView.of(data);
    }
}


}