package br.com.fatecmogidascruzes.gallery;
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
@Access(AccessType.FIELD)
@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor
@Setter
@Table(name = "_album")
public class Album extends NamedEntity{

 private  long serialVersionUID;

@Basic
@Column(name = "description", nullable = true, length = 100)
 private  String description;

@Transient
 private  Event event;

@JoinTable(name = "_album_images", joinColumns = @JoinColumn(name = "album"), inverseJoinColumns = @JoinColumn(name = "image"))
@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private  Set<File> images;

@JoinColumn(name = "cover", nullable = true)
@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private  File cover;

@Basic
@Column(name = "is_fatec_album", nullable = false)
 private  boolean fatecAlbum;

@Column(name = "idRDB0")
 private Long idRDB0;

@Transient
 private EventRequest eventrequest = new EventRequestImpl();;


public void removeImage(UUID imageHash){
    this.images.removeIf(file -> imageHash.equals(file.getHash()));
}


public void addImage(File image){
    this.images.add(image);
}


@Access(AccessType.PROPERTY)
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_album")
@Id
@Override
@SequenceGenerator(name = "seq_album", initialValue = 1, allocationSize = 1)
public Long getId(){
    return super.getId();
}


public void setImages(File images){
    this.images = new HashSet<>(Arrays.asList(images));
}


public Optional<File> getImage(String imageHash){
    return this.images.parallelStream().filter(image -> imageHash.equals(image.getHashString())).findFirst();
}


}