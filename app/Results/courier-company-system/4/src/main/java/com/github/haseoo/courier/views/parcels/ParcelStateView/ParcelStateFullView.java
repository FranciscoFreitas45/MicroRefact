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
public class ParcelStateFullView extends ParcelStateView{

 private  CourierWithoutListView courier;

 private  MagazineWithoutListsView magazine;


public ParcelStateFullView of(ParcelStateData data){
    return ParcelStateFullView.builder().stateType(data.getState()).changeTime(data.getChangeDate()).courier(((data.getCourierData() != null) ? CourierWithoutListView.of(data.getCourierData()) : null)).magazine(((data.getMagazine() != null) ? MagazineWithoutListsView.of(data.getMagazine()) : null)).build();
}


}