import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pt.iscte.hospital.entities.waiting.PatientWaitingAppointment;
import pt.iscte.hospital.security.Role;
import javax.persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Doctor extends Employee {

 private  Long licenseNumber;

 private  Speciality speciality;

 private  Set<Slot> slots;

 private  Set<PatientWaitingAppointment> patientWaitingAppointments;

 private Long specialityId;

 private SpecialityRequestImpl specialityrequestimpl;

 private SlotRequestImpl slotrequestimpl;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public Long getLicenseNumber(){
    return licenseNumber;
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


public Speciality getSpeciality(){
  this.speciality = specialityrequestimpl.getSpeciality(this.Long);
return this.speciality;
}


@Override
public List<GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(Role.ROLE_DOCTOR.name()));
    return roles;
}


public void setSpeciality(Speciality speciality){
 specialityrequestimpl.setSpeciality(speciality,this.Long);


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ userId).concat("/setSpeciality"));

.queryParam("speciality",speciality);
restTemplate.put(builder.toUriString(),null);
}


}