package pt.iscte.hospital.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.entities.waiting.DoctorWaitingPatient;
@RestController
@CrossOrigin
public class DoctorWaitingPatientAppointmentController {

@Autowired
 private DoctorWaitingPatientAppointmentService doctorwaitingpatientappointmentservice;


@GetMapping
("/Appointment/{id}/DoctorWaitingPatient/getDoctorWaitingPatient")
public DoctorWaitingPatient getDoctorWaitingPatient(@PathVariable(name="id") Long doctorWaitingPatientId){
return doctorwaitingpatientappointmentservice.getDoctorWaitingPatient(doctorWaitingPatientId);
}


@PutMapping
("/Appointment/{id}/DoctorWaitingPatient/setDoctorWaitingPatient")
public void setDoctorWaitingPatient(@PathVariable(name="id") Long doctorWaitingPatientId,@RequestBody DoctorWaitingPatient doctorWaitingPatient){
doctorwaitingpatientappointmentservice.setDoctorWaitingPatient(doctorWaitingPatientId,doctorWaitingPatient);
}


}