import javax.persistence;
@Entity
public class MailByUsername {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "email")
 private  String email;


public void setEmail(String email){
    this.email = email;
}


public String getEmail(){
    return email;
}


@Override
public String toString(){
    return "MailByUsername [empId=" + empId + ", email=" + email + "]";
}


public int getEmpId(){
    return empId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}