package br.com.fatecmogidascruzes.DTO;
 import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.fatecmogidascruzes.domain.NamedEntity;
import br.com.fatecmogidascruzes.event.Event;
import br.com.fatecmogidascruzes.file.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.fatecmogidascruzes.Request.EventRequest;
import br.com.fatecmogidascruzes.Request.Impl.EventRequestImpl;
import br.com.fatecmogidascruzes.DTO.Event;
public class Album extends NamedEntity{

 private  long serialVersionUID;

 private  String description;

 private  Event event;

 private  Set<File> images;

 private  File cover;

 private  boolean fatecAlbum;

 private Long idRDB0;


@Access(AccessType.PROPERTY)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_album")
@Id
@Override
@SequenceGenerator(name = "seq_album", initialValue = 1, allocationSize = 1)
public Long getId(){
    return super.getId();
}


public Optional<File> getImage(String imageHash){
    return this.images.parallelStream().filter(image -> imageHash.equals(image.getHashString())).findFirst();
}


}