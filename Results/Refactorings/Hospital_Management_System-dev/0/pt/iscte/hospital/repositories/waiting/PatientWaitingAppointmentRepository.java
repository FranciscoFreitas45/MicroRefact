import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import java.util.List;
@Repository
public interface PatientWaitingAppointmentRepository extends JpaRepository<PatientWaitingAppointment, Long> {


public List<PatientWaitingAppointment> findAllByClosed(boolean closed)


public List<PatientWaitingAppointment> findAllByClosedOrderByDate(boolean closed)


public List<PatientWaitingAppointment> findAllByClosedAndRepliedToOffer(boolean closed,boolean repliedToOffer)


public List<PatientWaitingAppointment> findAllByPatientUserIdAndClosed(long userId,boolean closed)


public PatientWaitingAppointment findByPatientWaitingAppointmentId(Long patientWaitingAppointmentId)


public List<PatientWaitingAppointment> findAll()


}