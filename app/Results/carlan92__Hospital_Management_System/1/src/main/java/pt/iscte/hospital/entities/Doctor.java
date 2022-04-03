package pt.iscte.hospital.entities;
 import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.security.Role;
import javax.persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import pt.iscte.hospital.Request.SpecialityRequest;
import pt.iscte.hospital.Request.Impl.SpecialityRequestImpl;
import pt.iscte.hospital.DTO.Speciality;
import pt.iscte.hospital.Request.SlotRequest;
import pt.iscte.hospital.Request.Impl.SlotRequestImpl;
import pt.iscte.hospital.DTO.Slot;
import pt.iscte.hospital.Request.PatientWaitingAppointmentRequest;
import pt.iscte.hospital.Request.Impl.PatientWaitingAppointmentRequestImpl;
import pt.iscte.hospital.DTO.PatientWaitingAppointment;
@Entity
@PrimaryKeyJoinColumn(name = "doctor_id")
public class Doctor extends Employee{

 private  Long licenseNumber;

@Transient
 private  Speciality speciality;

@Transient
 private  Set<Slot> slots;

@Transient
 private  Set<PatientWaitingAppointment> patientWaitingAppointments;

@Column(name = "specialityId")
 private Long specialityId;

@Transient
 private SpecialityRequest specialityrequest = new SpecialityRequestImpl();;

@Transient
 private SlotRequest slotrequest = new SlotRequestImpl();;

@Transient
 private PatientWaitingAppointmentRequest patientwaitingappointmentrequest = new PatientWaitingAppointmentRequestImpl();;

// Constructors
public Doctor() {
    super.setAccount("Médico");
}public Doctor(Long userId, String name, String username, String sex, LocalDate birthday, String address, String postCode, String city, String nationality, String documentType, Long documentNumber, Long nif, Long patientNumber, Long phone, String email, String password, String photoURL, Long licenseNumber, Speciality speciality) {
    super(userId, name, username, sex, birthday, address, postCode, city, nationality, documentType, documentNumber, nif, patientNumber, phone, email, password, photoURL);
    super.setAccount("Médico");
    this.licenseNumber = licenseNumber;
    this.speciality = speciality;
}
public Long getLicenseNumber(){
    return licenseNumber;
}


@Override
public String toString(){
    return "Doctor{" + super.toString() + ", licenseNumber=" + licenseNumber + ", speciality=" + speciality.getName() + '}';
}


public String getTitleAndName(){
    String titleAndName = "";
    if (getSex().equals("Masculino")) {
        titleAndName += "Dr. " + getFirstAndLastName();
    } else {
        titleAndName += "Dra. " + getFirstAndLastName();
    }
    return titleAndName;
}


public void setLicenseNumber(Long licenseNumber){
    this.licenseNumber = licenseNumber;
}


public Speciality getSpeciality(){
  this.speciality = specialityrequest.getSpeciality(this.specialityId);
return this.speciality;
}


public void setSpeciality(Speciality speciality){
 specialityrequest.setSpeciality(speciality,this.specialityId);
}



@Override
public List<GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(Role.ROLE_DOCTOR.name()));
    return roles;
}


}