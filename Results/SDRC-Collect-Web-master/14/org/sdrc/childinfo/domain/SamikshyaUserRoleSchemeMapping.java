import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "samikshya_user_role_scheme_mapping")
@NamedQuery(name = "SamikshyaUserRoleSchemeMapping.findAll", query = "SELECT s FROM SamikshyaUserRoleSchemeMapping s")
public class SamikshyaUserRoleSchemeMapping implements Serializable{

 private  long serialVersionUID;

 private  int userRoleSchemeId;

 private  String lastUpdatedBy;

 private  Timestamp lastUpdatedDate;

 private  SamikshyaUser samikshyaUser;


public void setSamikshyaUser(SamikshyaUser samikshyaUser){
    this.samikshyaUser = samikshyaUser;
}


public void setLastUpdatedDate(Timestamp lastUpdatedDate){
    this.lastUpdatedDate = lastUpdatedDate;
}


public void setUserRoleSchemeId(int userRoleSchemeId){
    this.userRoleSchemeId = userRoleSchemeId;
}


public void setLastUpdatedBy(String lastUpdatedBy){
    this.lastUpdatedBy = lastUpdatedBy;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_role_scheme_id")
public int getUserRoleSchemeId(){
    return this.userRoleSchemeId;
}


@Column(name = "last_updated_by")
public String getLastUpdatedBy(){
    return this.lastUpdatedBy;
}


@ManyToOne
@JoinColumn(name = "user_id")
public SamikshyaUser getSamikshyaUser(){
    return this.samikshyaUser;
}


@Column(name = "last_updated_date")
public Timestamp getLastUpdatedDate(){
    return this.lastUpdatedDate;
}


}