import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.service.RTableService;
import java.util.Collection;
import java.util.Optional;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RTableController {

 private  RTableService rTableService;


@PostMapping("/rTables")
public void addRTable(RTable rTable){
    rTableService.addRTable(rTable);
}


@PutMapping("/rTables/{id}")
public void updateRTable(Long id,RTable rTable){
    rTableService.updateRTable(id, rTable);
}


@DeleteMapping("/rTables/{id}")
public void deleteRTable(Long id){
    Optional<RTable> rTable = rTableService.getRTableById(id);
    rTableService.deleteRTableById(id);
}


@GetMapping(value = "/rTables/{id}")
public RTable getRTableById(Long id){
    Optional<RTable> rTable = rTableService.getRTableById(id);
    return rTable.orElse(null);
}


@GetMapping(value = "/rTables/{id}/bills")
public Collection<Bill> getRTableBills(Long id){
    return rTableService.getRTableBills(id);
}


@GetMapping(value = "/rTables/{id}/reservations")
public Collection<Reservation> getRTableReservations(Long id){
    return rTableService.getRTableReservations(id);
}


@GetMapping("/rTables")
public Collection<Bill> getRTables(){
    return rTableService.getRTables();
}


@GetMapping(value = "/rTables/{id}/currentBill")
public Bill getCurrentBill(Long id){
    return rTableService.getCurrentBill(id);
}


}