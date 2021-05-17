import javax.persistence;
@Entity
public class AuthorityInformation {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "leave_initial_auth")
 private  String leaveInitialAuth;

@Column(name = "leave_final_auth")
 private  String leaveFinalAuth;

@Column(name = "claim_initial_auth")
 private  String claimInitialAuth;

@Column(name = "claim_final_auth")
 private  String claimFinalAuth;


public void setLeaveFinalAuth(String leaveFinalAuth){
    this.leaveFinalAuth = leaveFinalAuth;
}


public String getClaimFinalAuth(){
    return claimFinalAuth;
}


public void setClaimFinalAuth(String claimFinalAuth){
    this.claimFinalAuth = claimFinalAuth;
}


public String getLeaveInitialAuth(){
    return leaveInitialAuth;
}


public String getClaimInitialAuth(){
    return claimInitialAuth;
}


public String getLeaveFinalAuth(){
    return leaveFinalAuth;
}


@Override
public String toString(){
    return "AuthorityInformation [empId=" + empId + ", leaveInitialAuth=" + leaveInitialAuth + ", leaveFinalAuth=" + leaveFinalAuth + ", claimInitialAuth=" + claimInitialAuth + ", claimFinalAuth=" + claimFinalAuth + "]";
}


public void setClaimInitialAuth(String claimInitialAuth){
    this.claimInitialAuth = claimInitialAuth;
}


public int getEmpId(){
    return empId;
}


public void setLeaveInitialAuth(String leaveInitialAuth){
    this.leaveInitialAuth = leaveInitialAuth;
}


public void setEmpId(int empId){
    this.empId = empId;
}


}