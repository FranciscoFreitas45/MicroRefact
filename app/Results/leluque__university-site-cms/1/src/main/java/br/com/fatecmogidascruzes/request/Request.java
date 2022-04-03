package br.com.fatecmogidascruzes.request;
 import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.fatecmogidascruzes.course.Course;
import br.com.fatecmogidascruzes.domain.NamedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Entity
@Setter
@Table(name = "_request")
public class Request extends NamedEntity{

 private  long serialVersionUID;

@Basic
@Column(name = "registry", nullable = false, length = 30)
 private  String registry;

@Basic
@Column(name = "email", nullable = false, length = 50)
 private  String email;

@Basic
@Column(name = "phone", nullable = false, length = 50)
 private  String phone;

 private  String name;

@Column(name = "learner_situation", nullable = false)
@Enumerated(EnumType.STRING)
 private  LearnerSituation learnerSituation;

@Column(name = "course", nullable = false)
@Enumerated(EnumType.STRING)
 private  Course course;

 private  String name;

@Column(name = "document_type", nullable = false)
@Enumerated(EnumType.STRING)
 private  DocumentType documentType;

@Basic
@Column(name = "comments", nullable = true, length = 400)
 private  String comments;

@Basic
@Column(name = "protocol", nullable = false)
 private  Long protocol;

 private  String name;

@Column(name = "request_situation", nullable = false)
@Enumerated(EnumType.STRING)
 private  RequestSituation requestSituation;


@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_request")
@Id
@Override
@SequenceGenerator(name = "seq_request", initialValue = 1, allocationSize = 1)
public Long getId(){
    return super.getId();
}


}