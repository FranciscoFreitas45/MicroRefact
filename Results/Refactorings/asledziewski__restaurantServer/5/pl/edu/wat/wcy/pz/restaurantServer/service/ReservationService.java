import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.wcy.pz.restaurantServer.email.MailService;
import pl.edu.wat.wcy.pz.restaurantServer.entity.RTable;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import pl.edu.wat.wcy.pz.restaurantServer.repository.ReservationRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class ReservationService {

 private  ReservationRepository reservationRepository;

 private  UserService userService;

 private  MailService mailService;

 private  RTableService rTableService;


public Optional<Reservation> getReservationById(Long id){
    Optional<Reservation> reservation = reservationRepository.findById(id);
    if (reservation.isPresent()) {
        return reservation;
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
    }
}


public void addReservation(Reservation reservation){
    Long tabId = getTableId(reservation);
    if (tabId.equals(Long.valueOf(-1))) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Couldn't find suitable table for this date.");
    }
    reservation.setRTableId(tabId);
    List<Reservation> reservationList = reservationRepository.findAll();
    if (reservationList.stream().map(Reservation::getRTableId).anyMatch(reservation.getRTableId()::equals) && reservationList.stream().map(Reservation::getDateTime).anyMatch(reservation.getDateTime()::equals) && reservationList.stream().map(Reservation::getDateDays).anyMatch(reservation.getDateDays()::equals)) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Reservation for this table and date already exists.");
    } else {
        if (reservation.getUserId() != null) {
            User user = userService.getUserById(reservation.getUserId()).get();
            String mail = user.getMail();
            String sb = "Hello " + user.getFirstName() + " you've made reservation for " + reservation.getAttendees() + " people on " + reservation.getDateDays() + " " + reservation.getDateTime() + ". Table " + reservation.getRTableId() + " is waiting for you";
        // mailService.sendEmail(mail, "Reservation has been made.", sb);
        }
        reservation.setrTableNumber(rTableService.getRTableById(tabId).get().getNumber());
        reservation.setStatus("PENDING");
        reservationRepository.save(reservation);
    }
}


public void deleteReservationById(Long id){
    Optional<Reservation> res = reservationRepository.findById(id);
    if (res.isPresent()) {
        Reservation reservation = res.get();
        if (reservation.getUserId() != null) {
            User user = userService.getUserById(reservation.getUserId()).get();
            String mail = user.getMail();
            String sb = "Hello " + user.getFirstName() + " your reservation " + " on " + reservation.getDateDays() + " " + reservation.getDateTime() + " has been cancelled." + " You're welcome another time";
            mailService.sendEmail(mail, "Reservation has been cancelled.", sb);
        }
        reservationRepository.deleteById(id);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
    }
}


public void updateReservation(Long id,Reservation reservation){
    Optional<Reservation> oldReservation = reservationRepository.findById(id);
    if (!oldReservation.isPresent())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found.");
    else {
        reservation.setReservationId(id);
        reservationRepository.save(reservation);
    }
}


public List<Reservation> getReservations(){
    return reservationRepository.findAll();
}


public List<Reservation> getCurrentReservations(){
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String dateDays = dateFormatter.format(date);
    List<Reservation> reservationList = reservationRepository.findByDateDays(dateDays);
    List<Reservation> returnList = new ArrayList<>();
    Reservation temp;
    for (Reservation aReservationList : reservationList) {
        temp = aReservationList;
        if (temp.getStatus().equals("PENDING")) {
            returnList.add(temp);
        }
    }
    return returnList;
}


public Long getTableId(Reservation reservation){
    System.out.println(reservation.toString());
    List<RTable> tableList = rTableService.getRTablesBySize(reservation.getAttendees());
    List<RTable> reservable = new ArrayList<>();
    String dateDays = reservation.getDateDays();
    String dateTime = reservation.getDateTime();
    RTable tempTable;
    Reservation tempReservation;
    boolean free;
    for (int i = 0; i < tableList.size(); i++) {
        free = true;
        tempTable = tableList.get(i);
        List<Reservation> tempReservations = tempTable.getReservations();
        for (int j = 0; j < tempReservations.size(); j++) {
            tempReservation = tempReservations.get(j);
            if (tempReservation.getDateDays().equals(dateDays) && tempReservation.getDateTime().equals(dateTime)) {
                free = false;
            }
        }
        if (free) {
            reservable.add(tempTable);
        }
    }
    if (reservable.size() == 0) {
        return Long.valueOf(-1);
    } else {
        return reservable.get(0).getRTableId();
    }
}


}