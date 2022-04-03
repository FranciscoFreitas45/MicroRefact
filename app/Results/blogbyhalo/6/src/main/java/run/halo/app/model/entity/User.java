package run.halo.app.model.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import run.halo.app.model.enums.MFAType;
import run.halo.app.utils.DateUtils;
@Data
@Entity
@Table(name = "users")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Integer id;

@Column(name = "username", length = 50, nullable = false)
 private  String username;

@Column(name = "nickname", nullable = false)
 private  String nickname;

@Column(name = "password", nullable = false)
 private  String password;

@Column(name = "email", length = 127)
 private  String email;

@Column(name = "avatar", length = 1023)
 private  String avatar;

@Column(name = "description", length = 1023)
 private  String description;

@Column(name = "expire_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date expireTime;

@Column(name = "mfa_type", nullable = false)
@ColumnDefault("0")
 private  MFAType mfaType;

@Column(name = "mfa_key", length = 64)
 private  String mfaKey;


@Override
public void prePersist(){
    super.prePersist();
    if (email == null) {
        email = "";
    }
    if (avatar == null) {
        avatar = "";
    }
    if (description == null) {
        description = "";
    }
    if (expireTime == null) {
        expireTime = DateUtils.now();
    }
}


}