package com.ukefu.util;
 import java.util.Date;
import com.ukefu.webim.web.model.TableProperties;
import Interface.TableProperties;
public class TabelSql {

 private  String sql;

 private  boolean parame;

 private  int paramnum;

 private  Date updatetime;

 private  long startindex;

 private  TableProperties moditp;

public TabelSql(String sql, boolean param, int paramnum, Date updatetime, TableProperties moditp) {
    this.sql = sql;
    this.parame = param;
    this.paramnum = paramnum;
    this.updatetime = updatetime;
    this.moditp = moditp;
}public TabelSql(String sql, boolean param, int paramnum, long startindex, TableProperties moditp) {
    this.sql = sql;
    this.parame = param;
    this.paramnum = paramnum;
    this.startindex = startindex;
    this.moditp = moditp;
}
public String getSql(){
    return sql;
}


public long getStartindex(){
    return startindex;
}


public void setModitp(TableProperties moditp){
    this.moditp = moditp;
}


public int getParamnum(){
    return paramnum;
}


public boolean isParame(){
    return parame;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setStartindex(long startindex){
    this.startindex = startindex;
}


public TableProperties getModitp(){
    return moditp;
}


public void setParame(boolean parame){
    this.parame = parame;
}


public void setSql(String sql){
    this.sql = sql;
}


public void setParamnum(int paramnum){
    this.paramnum = paramnum;
}


}