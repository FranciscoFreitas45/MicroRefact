package br.com.fatecmogidascruzes.news;
 import java.time.LocalDate;
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
import lombok.Getter;
import lombok.Setter;
import br.com.fatecmogidascruzes.Request.FileRequest;
import br.com.fatecmogidascruzes.Request.Impl.FileRequestImpl;
import br.com.fatecmogidascruzes.DTO.File;
import br.com.fatecmogidascruzes.Request.FileRequest;
import br.com.fatecmogidascruzes.Request.Impl.FileRequestImpl;
import br.com.fatecmogidascruzes.DTO.File;
import br.com.fatecmogidascruzes.Request.FileRequest;
import br.com.fatecmogidascruzes.Request.Impl.FileRequestImpl;
import br.com.fatecmogidascruzes.DTO.File;
import br.com.fatecmogidascruzes.Request.FileRequest;
import br.com.fatecmogidascruzes.Request.Impl.FileRequestImpl;
import br.com.fatecmogidascruzes.DTO.File;
@Getter
@Entity
@Setter
@Table(name = "_news", indexes = { @Index(name = "ind_news_name", columnList = "name", unique = false) })
public class News extends NamedEntity{

 private  long serialVersionUID;

@Basic
@Column(name = "short_description", nullable = false, length = 150)
 protected  String shortDescription;

@Lob
@Column(name = "long_description", nullable = true)
 protected  String longDescription;

@Basic
@Column(name = "link", nullable = true, length = 200)
 protected  String link;

@Transient
 private  File file;

@Transient
 private  File highlightImage;

@Transient
 private  File image;

@Transient
 private  File bigImage;

@Column(name = "reference_date", nullable = false)
 private  LocalDate referenceDate;

@Basic
@Column(name = "views", nullable = false)
 private  int views;

@Basic
@Column(name = "highlight", nullable = false)
 protected  boolean highlight;

@Basic
@Column(name = "show_only_mobile", nullable = false)
 protected  boolean showNewsOnlyOnMobile;

@Basic
@Column(name = "hide", nullable = false)
 protected  boolean hide;

@Column(name = "id9U03")
 private Long id9U03;

@Transient
 private FileRequest filerequest = new FileRequestImpl();;

@Column(name = "idUIC4")
 private Long idUIC4;

@Transient
 private FileRequest filerequest = new FileRequestImpl();;

@Column(name = "idG1RG")
 private Long idG1RG;

@Transient
 private FileRequest filerequest = new FileRequestImpl();;

@Column(name = "idHQO0")
 private Long idHQO0;

@Transient
 private FileRequest filerequest = new FileRequestImpl();;


@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_news")
@Id
@Override
@SequenceGenerator(name = "seq_news", initialValue = 1, allocationSize = 1)
public Long getId(){
    return super.getId();
}


}