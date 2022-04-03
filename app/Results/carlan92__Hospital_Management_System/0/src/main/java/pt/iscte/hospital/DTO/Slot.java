package pt.iscte.hospital.DTO;
 import org.springframework.format.annotation.DateTimeFormat;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import javax.persistence;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import pt.iscte.hospital.objects.utils.Calendar;
public class Slot implements Comparable<Slot>{

 private  Long slotId;

 private  LocalDate date;

 private  LocalTime timeBegin;

 private  LocalTime timeEnd;

 private  Set<Appointment> appointments;

 private  Doctor doctor;

 private  Set<PatientWaitingAppointment> patientWaitingAppointments;

 private  boolean isAvailable;

// Constructors
public Slot() {
}public Slot(Doctor doctor, LocalDate date, LocalTime timeBegin, LocalTime timeEnd) {
    this.doctor = doctor;
    this.date = date;
    this.timeBegin = timeBegin;
    this.timeEnd = timeEnd;
}// Construtor cópia
public Slot(Slot slot, boolean isAvailable) {
    this.doctor = slot.getDoctor();
    this.date = slot.getDate();
    this.timeBegin = slot.getTimeBegin();
    this.timeEnd = slot.getTimeEnd();
    this.isAvailable = isAvailable;
}
public void setTimeEnd(LocalTime timeEnd){
    this.timeEnd = timeEnd;
}


public boolean isAvailable(){
    return isAvailable;
}


public LocalTime getTimeEnd(){
    return timeEnd;
}


public void setAvailable(boolean available){
    isAvailable = available;
}


public void setTimeBegin(LocalTime timeBegin){
    this.timeBegin = timeBegin;
}


@Override
public int compareTo(Slot o){
    if (this.date.isBefore(o.date)) {
        return -1;
    } else if (this.date.isAfter(o.date)) {
        return 1;
    } else {
        // Same date
        if (this.timeBegin.isBefore(o.timeBegin)) {
            return -1;
        } else if (this.timeBegin.isAfter(o.timeBegin)) {
            return 1;
        } else {
            return 0;
        }
    }
}


public String getTimeBeginStr(){
    return timeBegin.format(TIME_FORMATTER);
}


public String getTimeEndStr(){
    return timeEnd.format(TIME_FORMATTER);
}


public Doctor getDoctor(){
    return doctor;
}


public void setSlotId(Long slotId){
    this.slotId = slotId;
}


public Long getSlotId(){
    return slotId;
}


public String getDateStr(){
    return date.format(FORMATTER);
}


public void setDate(LocalDate date){
    this.date = date;
}


public LocalDate getDate(){
    return date;
}


public void setDoctor(Doctor doctor){
    this.doctor = doctor;
}


@Override
public String toString(){
    return "Slot{" + "slotId=" + slotId + ", date=" + date + ", timeBegin=" + timeBegin + ", timeEnd=" + timeEnd + ", doctor= " + doctor.getFirstAndLastName() + '}';
}


public LocalTime getTimeBegin(){
    return timeBegin;
}


}