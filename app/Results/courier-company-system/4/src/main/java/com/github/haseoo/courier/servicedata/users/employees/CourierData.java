package com.github.haseoo.courier.servicedata.users.employees;
 import com.github.haseoo.courier.models.CourierModel;
import com.github.haseoo.courier.models.ParcelStateRecord;
import com.github.haseoo.courier.servicedata.parcels.ParcelData;
import com.github.haseoo.courier.utilities.UserUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.github.haseoo.courier.enums.ParcelStateType.ASSIGNED;
import com.github.haseoo.courier.enums.ParcelStateType.AT_COURIER;
import com.github.haseoo.courier.utilities.Utils.distinctByKey;
import lombok.AccessLevel.PRIVATE;
@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
@AllArgsConstructor(access = PRIVATE)
public class CourierData extends EmployeeData{

 private  List<ParcelData> assignedParcels;


public CourierData ofWithoutList(CourierModel courierModel){
    if (courierModel != null) {
        return CourierData.builder().id(courierModel.getId()).userName(courierModel.getUserName()).password(courierModel.getPassword()).active(courierModel.getActive()).name(courierModel.getName()).surname(courierModel.getSurname()).phoneNumber(courierModel.getPhoneNumber()).pesel(courierModel.getPesel()).employeeType(UserUtils.getEmployeeType(courierModel)).userType(UserUtils.getUserType(courierModel)).build();
    }
    return null;
}


public CourierData of(CourierModel courierModel){
    return CourierData.builder().id(courierModel.getId()).userName(courierModel.getUserName()).password(courierModel.getPassword()).active(courierModel.getActive()).name(courierModel.getName()).surname(courierModel.getSurname()).phoneNumber(courierModel.getPhoneNumber()).pesel(courierModel.getPesel()).employeeType(UserUtils.getEmployeeType(courierModel)).userType(UserUtils.getUserType(courierModel)).assignedParcels(((courierModel.getParcelStates() != null) ? courierModel.getParcelStates().stream().map(ParcelStateRecord::getParcel).map(ParcelData::of).filter(parcelData -> parcelData.getCurrentState().getState() == AT_COURIER || parcelData.getCurrentState().getState() == ASSIGNED).filter(distinctByKey(ParcelData::getId)).collect(Collectors.toList()) : new ArrayList<>())).build();
}


}