import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RoomOrderRepositoryController {

 private RoomOrderRepository roomorderrepository;


@GetMapping
("/findRoomOrderByDay")
public Float findRoomOrderByDay(@RequestParam(name = "dateString") String dateString){
  return roomorderrepository.findRoomOrderByDay(dateString);
}


}