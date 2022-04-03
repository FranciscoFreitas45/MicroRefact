package com.github.haseoo.courier.views.users.employees;
 import com.github.haseoo.courier.servicedata.users.employees.CourierData;
import com.github.haseoo.courier.views.parcels.ParcelView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel.PRIVATE;
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Builder
public class CourierView {

 private  Long id;

 private  String pesel;

 private  String name;

 private  String surname;

 private  String phoneNumber;

 private  List<ParcelView.ParcelCourierView> assignedParcels;


public CourierView of(CourierData courierData){
    return CourierView.builder().id(courierData.getId()).name(courierData.getName()).surname(courierData.getSurname()).pesel(courierData.getPesel()).phoneNumber(courierData.getPhoneNumber()).assignedParcels((courierData.getAssignedParcels() != null) ? courierData.getAssignedParcels().stream().map(ParcelView.ParcelCourierView::of).collect(Collectors.toList()) : new ArrayList<>()).build();
}


}