package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
public class EkmKnowledgeMaster {

 private  long serialVersionUID;

 private  String id;

 private  String title;

 private  String summary;

 private  String content;

 private  String tags;

 private  String keyword;

 private  String dimenid;

 private  String dimentypeid;

 private  String organ;

 private  String knowledgetypeid;

 private  String knowbaseid;

 private  String pubstatus;

 private  boolean datastatus;

 private  int version;

 private  String knowledgetype;

 private  Date begintime;

 private  Date endtime;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  EkmKnowledgeTimes knowledgetimes;

 private  EkmKnowledgeCollect knowledgecollect;

 private  String knowledgeid;

 private  String kbid;

 private  String auditor;

 private  String nlpnr;

 private  String nlpns;

 private  String nlpnt;

 private  String nlpnz;

 private  String keyphrase;

 private  Date updatetime;

 private  String attr;


public String getTags(){
    return tags;
}


@Transient
public EkmKnowledgeTimes getKnowledgetimes(){
    return knowledgetimes;
}


public String getDimentypeid(){
    return dimentypeid;
}


public String getTitle(){
    return title;
}


public String getAttr(){
    return attr;
}


@Transient
public String getKnowledgeid(){
    return knowledgeid;
}


public String getKeyphrase(){
    return keyphrase;
}


@Transient
public EkmKnowledgeCollect getKnowledgecollect(){
    return knowledgecollect;
}


public String getKnowbaseid(){
    return knowbaseid;
}


public Date getUpdatetime(){
    return updatetime;
}


@Transient
public String getKbid(){
    return kbid;
}


public String getKnowledgetypeid(){
    return knowledgetypeid;
}


public String getAuditor(){
    return auditor;
}


public String getOrgan(){
    return organ;
}


public String getPubstatus(){
    return pubstatus;
}


public String getContent(){
    return content;
}


public Date getBegintime(){
    return begintime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getDimenid(){
    return dimenid;
}


public Date getCreatetime(){
    return createtime;
}


public String getNlpnz(){
    return nlpnz;
}


public String getCreater(){
    return creater;
}


public String getNlpnt(){
    return nlpnt;
}


public String getNlpnr(){
    return nlpnr;
}


public String getNlpns(){
    return nlpns;
}


public int getVersion(){
    return version;
}


public String getSummary(){
    return summary;
}


public String getKeyword(){
    return keyword;
}


public String getKnowledgetype(){
    return knowledgetype;
}


public String getOrgi(){
    return orgi;
}


public Date getEndtime(){
    return endtime;
}


}