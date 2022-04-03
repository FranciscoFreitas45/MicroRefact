package DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
public class MetadataTable {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String dbid;

 private  String tabledirid;

 private  String tablename;

 private  String code;

 private  String secure;

 private  String tabletype;

 private  String datasql;

 private  int startindex;

 private  Date updatetime;

 private  long updatetimenumber;

 private  String tabtype;

 private  String pid;

 private  String secmenuid;

 private  String reportid;

 private  boolean timeline;

 private  String eventname;

 private  int tbversion;

 private  Date lastupdate;

 private  String taskname;

 private  String taskplan;

 private  String taskstatus;

 private  String tasktype;

 private  Date createtime;

 private  String configure;

 private  String secureconf;

 private  String userid;

 private  String groupid;

 private  String previewtemplet;

 private  String listblocktemplet;

 private  String orgi;

 private  String creater;

 private  String creatername;

 private  boolean userpage;

 private  boolean fromdb;

 private  boolean workflow;

 private  List<TableProperties> tableproperty;


public String getName(){
    return name;
}


public String getListblocktemplet(){
    return listblocktemplet;
}


public String getTabledirid(){
    return tabledirid;
}


public String getTaskstatus(){
    return taskstatus;
}


// 不载入 设置为 禁用 导入导出的字段
@Where(clause = "impfield=0")
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name = "dbtableid")
@OrderBy("sortindex")
public List<TableProperties> getTableproperty(){
    return tableproperty;
}


public String getCode(){
    return code;
}


public int getStartindex(){
    return startindex;
}


public String getReportid(){
    return reportid;
}


public String getTabletype(){
    return tabletype != null ? tabletype : "1";
}


public Date getUpdatetime(){
    return updatetime;
}


@Transient
public String getType(){
    return "table";
}


public String getEventname(){
    return eventname;
}


public String getCreatername(){
    return creatername;
}


public long getUpdatetimenumber(){
    return updatetimenumber;
}


public String getTasktype(){
    return tasktype;
}


public String getSecureconf(){
    return secureconf;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getConfigure(){
    return configure;
}


public String getTaskplan(){
    return taskplan;
}


public String getPreviewtemplet(){
    return previewtemplet;
}


public String getTablename(){
    return tablename;
}


public Date getCreatetime(){
    return createtime;
}


public String getTabtype(){
    return tabtype;
}


public String getCreater(){
    return creater;
}


public String getSecmenuid(){
    return secmenuid;
}


public String getPid(){
    return pid;
}


public String getUserid(){
    return userid;
}


public int getTbversion(){
    return tbversion;
}


public String getGroupid(){
    return groupid;
}


public Date getLastupdate(){
    return lastupdate;
}


public String getDbid(){
    return dbid;
}


public String getTaskname(){
    return taskname != null ? taskname : tablename;
}


public String getOrgi(){
    return orgi;
}


public String getDatasql(){
    return datasql;
}


public String getSecure(){
    return secure;
}


}