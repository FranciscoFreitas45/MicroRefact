import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import java.util.List;
public interface PatientWaitingAppointmentService {


public List<PatientWaitingAppointment> findAllByClosed(boolean closed)


public List<PatientWaitingAppointment> findAllByClosedOrderByDate(boolean closed)


public List<PatientWaitingAppointment> findAllByClosedAndRepliedToOffer(boolean closed,boolean repliedToOffer)


public List<PatientWaitingAppointment> findAllByPatientUserIdAndClosed(long userId,boolean closed)


public void save(PatientWaitingAppointment patientWaitingAppointment)


public void saveWaitingListRequest(PatientWaitingAppointment patientWaitingAppointment)


public PatientWaitingAppointment findByPatientWaitingAppointmentId(Long patientWaitingAppointmentId)


public List<PatientWaitingAppointment> findAll()


}