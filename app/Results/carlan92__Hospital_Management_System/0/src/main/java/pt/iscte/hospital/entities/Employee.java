package pt.iscte.hospital.entities;
 import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pt.iscte.hospital.security.Role;
import javax.persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends User{

// Attributes
// Constructors
public Employee() {
    super.setAccount("Funcionário");
}public Employee(Long userId, String name, String username, String sex, LocalDate birthday, String address, String postCode, String city, String nationality, String documentType, Long documentNumber, Long nif, Long patientNumber, Long phone, String email, String password, String photoURL) {
    super(userId, name, username, sex, birthday, address, postCode, city, nationality, documentType, documentNumber, nif, patientNumber, phone, email, password, photoURL);
    super.setAccount("Funcionário");
}
@Override
public String toString(){
    return "Employee{" + super.toString() + '}';
}


@Override
public List<GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> roles = new ArrayList<>();
    roles.add(new SimpleGrantedAuthority(Role.ROLE_EMPLOYEE.name()));
    return roles;
}


}