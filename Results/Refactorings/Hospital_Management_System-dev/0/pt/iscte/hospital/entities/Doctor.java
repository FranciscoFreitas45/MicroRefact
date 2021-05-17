import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.security.Role;
import javax.persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@PrimaryKeyJoinColumn(name = "doctor_id")
public class Doctor extends Employee {

 private  Long licenseNumber;

@Transient
 private  Speciality speciality;

@Transient
 private  Set<Slot> slots;

@OneToMany(mappedBy = "doctor")
 private  Set<PatientWaitingAppointment> patientWaitingAppointments;

@Column(name = specialityId)
 private Long specialityId;

@Transient
 private SpecialityRequestImpl specialityrequestimpl;

@Transient
 private SlotRequestImpl slotrequestimpl;


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
  this.speciality = specialityrequestimpl.getSpeciality(this.Long);
return this.speciality;
}


public void setSpeciality(Speciality speciality){
 specialityrequestimpl.setSpeciality(speciality,this.Long);



@Override
public List<GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(Role.ROLE_DOCTOR.name()));
    return roles;
}


}