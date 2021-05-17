import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class IGuestServiceController {

 private IGuestService iguestservice;


@GetMapping
("/findGuestByIdCard")
public Guest findGuestByIdCard(@RequestParam(name = "idCard") String idCard){
  return iguestservice.findGuestByIdCard(idCard);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "guest") Guest guest){
iguestservice.save(guest);
}


@GetMapping
("/findGuestByRoomNo")
public List<Guest> findGuestByRoomNo(@RequestParam(name = "roomNo") String roomNo){
  return iguestservice.findGuestByRoomNo(roomNo);
}


}