package main.model;
 import lombok.Getter;
import lombok.Setter;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "captcha_codes")
@Getter
@Setter
public class CaptchaCode {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  int id;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "time", nullable = false)
 private  Date time;

@Column(columnDefinition = "TINYTEXT", name = "code", nullable = false)
 private  String code;

@Column(columnDefinition = "TINYTEXT", name = "secret_code", nullable = false)
 private  String secretCode;


}