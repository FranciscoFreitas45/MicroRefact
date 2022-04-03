package DTO;
 import javax.persistence.Transient;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;
import com.ukefu.util.UKTools;
import com.ukefu.util.task.ESData;
public class EkmKnowledgeTimes implements ESData{

 private  long serialVersionUID;

 private  String id;

 private  int viewtimes;

 private  int commentstimes;

 private  int collectimes;

 private  int version;

 private  String knowledgeid;

 private  String kbid;

 private  EkmKnowledgeMaster knowledge;

 private  Date createtime;

 private  String orgi;


public int getCommentstimes(){
    return commentstimes;
}


public int getVersion(){
    return version;
}


public int getViewtimes(){
    return viewtimes;
}


@Transient
public EkmKnowledgeMaster getKnowledge(){
    return knowledge;
}


public String getKbid(){
    return kbid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
public String getId(){
    return id;
}


public int getCollectimes(){
    return collectimes;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public String getKnowledgeid(){
    return knowledgeid;
}


}