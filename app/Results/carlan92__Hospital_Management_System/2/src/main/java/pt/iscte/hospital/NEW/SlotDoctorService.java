package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.SlotRepository;
import pt.iscte.hospital.entities.Slot;
@Service
public class SlotDoctorService {

@Autowired
 private SlotRepository slotrepository;


}