import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "samikshya_user")
@NamedQuery(name = "SamikshyaUser.findAll", query = "SELECT s FROM SamikshyaUser s")
public class SamikshyaUser implements Serializable{

 private  long serialVersionUID;

 private  int userId;

 private  String lastUpdatedBy;

 private  Date lastUpdatedDate;

 private  String userContactNo;

 private  String userEmailId;

 private  String userName;

 private  boolean activationStatus;

 private  List<SamikshyaUserRoleSchemeMapping> samikshyaUserRoleSchemeMappings;


@Column(name = "user_email_id")
public String getUserEmailId(){
    return this.userEmailId;
}


public SamikshyaUserRoleSchemeMapping removeSamikshyaUserRoleSchemeMapping(SamikshyaUserRoleSchemeMapping samikshyaUserRoleSchemeMapping){
    getSamikshyaUserRoleSchemeMappings().remove(samikshyaUserRoleSchemeMapping);
    samikshyaUserRoleSchemeMapping.setSamikshyaUser(null);
    return samikshyaUserRoleSchemeMapping;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "samikshyaUser", cascade = CascadeType.ALL)
public List<SamikshyaUserRoleSchemeMapping> getSamikshyaUserRoleSchemeMappings(){
    return this.samikshyaUserRoleSchemeMappings;
}


public void setLastUpdatedDate(Date lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


public SamikshyaUserRoleSchemeMapping addSamikshyaUserRoleSchemeMapping(SamikshyaUserRoleSchemeMapping samikshyaUserRoleSchemeMapping){
    getSamikshyaUserRoleSchemeMappings().add(samikshyaUserRoleSchemeMapping);
    samikshyaUserRoleSchemeMapping.setSamikshyaUser(this);
    return samikshyaUserRoleSchemeMapping;
}


@Column(name = "user_contact_no")
public String getUserContactNo(){
    return this.userContactNo;
}


public void setUserContactNo(String userContactNo){
    this.userContactNo = userContactNo;
}


public void setUserEmailId(String userEmailId){
    this.userEmailId = userEmailId;
}


public void setUserName(String userName){
    this.userName = userName;
}


public void setSamikshyaUserRoleSchemeMappings(List<SamikshyaUserRoleSchemeMapping> samikshyaUserRoleSchemeMappings){
    this.samikshyaUserRoleSchemeMappings = samikshyaUserRoleSchemeMappings;
}


@Column(name = "is_active")
public boolean isActivationStatus(){
    return activationStatus;
}


@Column(name = "user_name")
public String getUserName(){
    return this.userName;
}


@Column(name = "last_updated_by")
public String getLastUpdatedBy(){
    return this.lastUpdatedBy;
}


public void setActivationStatus(boolean activationStatus){
    this.activationStatus = activationStatus;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
public int getUserId(){
    return this.userId;
}


public void setUserId(int userId){
    this.userId = userId;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "last_updated_date")
public Date getLastUpdatedDate(){
    return this.lastUpdatedDate;
}


}