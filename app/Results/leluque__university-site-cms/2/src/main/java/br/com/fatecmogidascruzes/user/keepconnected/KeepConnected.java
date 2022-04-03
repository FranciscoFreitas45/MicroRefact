package br.com.fatecmogidascruzes.user.keepconnected;
 import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.fatecmogidascruzes.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.fatecmogidascruzes.Request.UserRequest;
import br.com.fatecmogidascruzes.Request.Impl.UserRequestImpl;
import br.com.fatecmogidascruzes.DTO.User;
@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor
@Setter
@Table(name = "_keep_connected")
public class KeepConnected {

@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_keep_conn")
@Id
@SequenceGenerator(name = "seq_keep_conn", initialValue = 1, allocationSize = 1)
 private  Long id;

@Basic
@Column(name = "series", nullable = false, length = 100)
 private  String series;

@Basic
@Column(name = "keep_conn_token", nullable = false, length = 100)
 private  String token;

@Column(name = "last_use")
 private  LocalDateTime lastUse;

@Transient
 private  User user;

@Column(name = "idGM9N")
 private Long idGM9N;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;


}