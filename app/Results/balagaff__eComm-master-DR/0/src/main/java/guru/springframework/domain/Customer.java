package guru.springframework.domain;
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
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
@Entity
@Table(name = "coats_customers")
public class Customer {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "customer_name")
 private  String customer_name;

@Column(name = "customer_code")
 private  String customer_code;

@Column(name = "sales_org_id")
 private  int sales_org_id;


public String getCustomer_name(){
    return customer_name;
}


public void setCustomer_name(String customer_name){
    this.customer_name = customer_name;
}


public int getSales_org_id(){
    return sales_org_id;
}


public void setCustomer_code(String customer_code){
    this.customer_code = customer_code;
}


public void setSales_org_id(int sales_org_id){
    this.sales_org_id = sales_org_id;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


public String getCustomer_code(){
    return customer_code;
}


}