package pt.iscte.hospital.DTO;
 import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.security.Role;
import javax.persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Doctor extends Employee{

 private  Long licenseNumber;

 private  Speciality speciality;

 private  Set<Slot> slots;

 private  Set<PatientWaitingAppointment> patientWaitingAppointments;

 private Long specialityId;

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
    return speciality;
}


public void setSpeciality(Speciality speciality){
    this.speciality = speciality;
}


@Override
public List<GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(Role.ROLE_DOCTOR.name()));
    return roles;
}


}