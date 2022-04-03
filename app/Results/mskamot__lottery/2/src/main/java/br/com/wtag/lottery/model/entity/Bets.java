package br.com.wtag.lottery.model.entity;
 import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bets {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotNull
@NotBlank
@Size(max = 255)
@Email
 private  String email;

@NotNull
 private  LocalDateTime registered;

@OneToMany(mappedBy = "bets", fetch = FetchType.LAZY)
@Fetch(FetchMode.JOIN)
@JsonManagedReference
 private  List<RandomNumbers> randomNumbers;


}