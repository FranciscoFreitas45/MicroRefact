package br.com.fatecmogidascruzes.DTO;
 import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.fatecmogidascruzes.domain.NamedEntity;
import br.com.fatecmogidascruzes.file.File;
import br.com.fatecmogidascruzes.gallery.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
public class Event extends NamedEntity{

 private  long serialVersionUID;

 private  LocalDate startDate;

 private  LocalDate endDate;

 private  LocalTime startTime;

 private  LocalTime endTime;

 private  String link;

 private  String shortDescription;

 private  String longDescription;

 private  String place;

 private  Album album;

 private  File image;

 private  File bigImage;

 private  int views;


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_event")
@Override
@SequenceGenerator(name = "seq_event", initialValue = 1, allocationSize = 1)
public Long getId(){
    return super.getId();
}


}