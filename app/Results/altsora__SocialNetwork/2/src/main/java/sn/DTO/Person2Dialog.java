package sn.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok;
import javax.persistence;
public class Person2Dialog {

 private  long id;

 private  Person person;

 private  Dialog dialog;

// ==================================================================================================================
public Person2Dialog(Person person, Dialog dialog) {
    this.person = person;
    this.dialog = dialog;
}
@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "person_id")
public Person getPerson(){
    return person;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public long getId(){
    return id;
}


@Override
public String toString(){
    return "Person2Dialog{" + "id=" + id + '}';
}


@JsonBackReference
@ManyToOne(fetch = FetchType.LAZY, optional = false)
@JoinColumn(name = "dialog_id")
public Dialog getDialog(){
    return dialog;
}


}