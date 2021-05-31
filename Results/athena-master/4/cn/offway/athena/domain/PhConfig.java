import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_config")
public class PhConfig implements Serializable{

 private  Long id;

 private  String name;

 private  String content;

 private  Date createTime;

 private  String remark;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "name", length = 200)
public String getName(){
    return name;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "content", length = 200)
public String getContent(){
    return content;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


}