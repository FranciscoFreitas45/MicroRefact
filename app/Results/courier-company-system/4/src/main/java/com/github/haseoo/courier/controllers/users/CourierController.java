package com.github.haseoo.courier.controllers.users;
 import com.github.haseoo.courier.commandsdata.parcels.ParcelChangeStateMultipleCommandData;
import com.github.haseoo.courier.commandsdata.parcels.ParcelPickupCommandData;
import com.github.haseoo.courier.services.ports.CourierService;
import com.github.haseoo.courier.services.ports.ParcelStateService;
import com.github.haseoo.courier.views.users.employees.CourierView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/api/employee/courier")
@RequiredArgsConstructor
public class CourierController {

 private  CourierService courierService;

 private  ParcelStateService parcelStateService;


@GetMapping("{id}")
@PreAuthorize("hasAnyRole({'ADMIN', 'LOGISTICIAN', 'COURIER'})")
public ResponseEntity<CourierView> getById(Long id){
    return new ResponseEntity<>(CourierView.of(courierService.getById(id)), OK);
}


@PostMapping("/{id}/parcelPickup")
@PreAuthorize("hasAnyRole({'ADMIN', 'COURIER'})")
public ResponseEntity<CourierView> pickupParcels(Long id,ParcelPickupCommandData commandData){
    return new ResponseEntity<>(CourierView.of(parcelStateService.setAsPickedByCourier(id, commandData.getParcelsId(), commandData.isWasPaid())), OK);
}


@GetMapping
@PreAuthorize("hasAnyRole({'ADMIN', 'LOGISTICIAN', 'COURIER'})")
public ResponseEntity<List<CourierView>> getList(){
    return new ResponseEntity<>(courierService.getList().stream().map(CourierView::of).collect(Collectors.toList()), OK);
}


@PostMapping("/{id}/parcelAssign")
@PreAuthorize("hasAnyRole({'ADMIN', 'LOGISTICIAN'})")
public ResponseEntity<CourierView> assignParcels(Long id,ParcelChangeStateMultipleCommandData commandData){
    return new ResponseEntity<>(CourierView.of(parcelStateService.assignParcelsToCourier(id, commandData.getParcelsIds())), OK);
}


}