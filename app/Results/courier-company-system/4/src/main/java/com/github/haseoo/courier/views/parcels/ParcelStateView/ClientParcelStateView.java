package com.github.haseoo.courier.views.parcels.ParcelStateView;
 import com.github.haseoo.courier.enums.ParcelStateType;
import com.github.haseoo.courier.enums.UserType;
import com.github.haseoo.courier.servicedata.parcels.ParcelStateData;
import com.github.haseoo.courier.views.places.MagazineWithoutListsView;
import com.github.haseoo.courier.views.users.employees.CourierWithoutListView;
import lombok;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import lombok.AccessLevel.PRIVATE;
@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
@AllArgsConstructor(access = PRIVATE)
public class ClientParcelStateView extends ParcelStateView{

 private  String city;


public ClientParcelStateView of(ParcelStateData data){
    return ClientParcelStateView.builder().stateType(data.getState()).changeTime(data.getChangeDate()).city(((data.getMagazine() != null) ? data.getMagazine().getAddress().getCity() : null)).build();
}


}