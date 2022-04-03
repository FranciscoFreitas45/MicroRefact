package run.halo.app.DTO;
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
public class User extends BaseEntity{

 private  Integer id;

 private  String username;

 private  String nickname;

 private  String password;

 private  String email;

 private  String avatar;

 private  String description;

 private  Date expireTime;

 private  MFAType mfaType;

 private  String mfaKey;


}