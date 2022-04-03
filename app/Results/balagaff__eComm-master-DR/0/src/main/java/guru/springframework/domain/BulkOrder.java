package guru.springframework.domain;
 import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "coats_bulk_orders")
public class BulkOrder implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "source_id")
 private  int source_id;

@Column(name = "order_no")
 private  String order_no;

@Column(name = "sales_org_id")
 private  int sales_org_id;

@Column(name = "customer_id")
 private  int customer_id;

@Column(name = "requester_id")
 private  int requester_id;

@Column(name = "po_number")
 private  String po_number;

@Column(name = "created_user_id")
 private  int created_user_id;

@CreationTimestamp
@Column(name = "created")
@JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
 private  Timestamp created;


public int getRequester_id(){
    return requester_id;
}


public int getCreated_user_id(){
    return created_user_id;
}


public String getOrder_no(){
    return order_no;
}


public int getSales_org_id(){
    return sales_org_id;
}


public void setCustomer_id(int customer_id){
    this.customer_id = customer_id;
}


public void setSales_org_id(int sales_org_id){
    this.sales_org_id = sales_org_id;
}


public Timestamp getCreated(){
    return created;
}


public int getId(){
    return id;
}


public void setCreated(Timestamp created){
    java.time.LocalDateTime.now();
    this.created = created;
}


public void setCreated_user_id(int created_user_id){
    this.created_user_id = created_user_id;
}


public void setOrder_no(String order_no){
    Random rand = new Random();
    int n = rand.nextInt(5000) + 1;
    order_no = "MANUA" + n;
    this.order_no = order_no;
}


public int getCustomer_id(){
    return customer_id;
}


public int getSource_id(){
    return source_id;
}


public void setSource_id(int source_id){
    this.source_id = source_id;
}


public void setId(int id){
    this.id = id;
}


public String getPo_number(){
    return po_number;
}


public void setPo_number(String po_number){
    this.po_number = po_number;
}


public void setRequester_id(int requester_id){
    this.requester_id = requester_id;
}


}