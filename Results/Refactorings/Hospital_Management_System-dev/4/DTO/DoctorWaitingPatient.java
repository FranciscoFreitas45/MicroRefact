import pt.iscte.hospital.entities.Appointment;
import pt.iscte.hospital.objects.utils.Calendar;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
public class DoctorWaitingPatient implements Comparable<DoctorWaitingPatient>{

 private  Long doctorWaitingPatientId;

 private  LocalDate date;

 private  LocalTime timeFirstCall;

 private  LocalTime timeLatestCall;

 private  Appointment appointment;


public Appointment getAppointment(){
    return appointment;
}


public LocalTime getTimeLatestCall(){
    return timeLatestCall;
}


public LocalTime getTimeFirstCall(){
    return timeFirstCall;
}


public String getTimeLatestCallStr(){
    return timeLatestCall.format(Calendar.TIME_FORMATTER);
}


public LocalDate getDate(){
    return date;
}


}