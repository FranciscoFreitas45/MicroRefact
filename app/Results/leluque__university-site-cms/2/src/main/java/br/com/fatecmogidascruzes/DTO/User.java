package br.com.fatecmogidascruzes.DTO;
 import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.fatecmogidascruzes.domain.NamedEntity;
import br.com.fatecmogidascruzes.user.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
public class User extends NamedEntity{

 private  long serialVersionUID;

 protected  String fullName;

 protected  String accessToken;

 protected  LocalDateTime accessTokenValidity;

 protected  String actionToken;

 protected  String password;

 protected  LocalDateTime actionTokenValidity;

 private  UserStatus status;

 protected  Set<Role> roles;


@Access(AccessType.PROPERTY)
@Basic
@Column(name = "name", nullable = false, unique = true)
@Override
public String getName(){
    return super.getName();
}


@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
@Id
@Override
@SequenceGenerator(name = "seq_user", initialValue = 1, allocationSize = 1)
public Long getId(){
    return super.getId();
}


}