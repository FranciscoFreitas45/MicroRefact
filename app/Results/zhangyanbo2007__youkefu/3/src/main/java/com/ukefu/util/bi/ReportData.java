package com.ukefu.util.bi;
 import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.lucene.queryparser.flexible.core.nodes.PathQueryNode.QueryText;
import com.ukefu.util.bi.model.Level;
import com.ukefu.util.bi.model.RequestData;
import com.ukefu.util.bi.model.ValueData;
public interface ReportData extends Serializable{


public void setTotal(long total)
;

public Level getCol()
;

public long getQueryTime()
;

public int getPage()
;

public void setRequestData(RequestData data)
;

public void merge(ReportData data)
;

public void setOptions(Map<String,Object> options)
;

public void setRow(Level level)
;

public Date getDate()
;

public void setException(Exception ex)
;

public void setQueryText(QueryText queryText)
;

public List<List<ValueData>> getData()
;

public void setPage(int page)
;

public void setViewData(String viewData)
;

public Exception getException()
;

public QueryText getQueryText()
;

public void exchangeColRow()
;

public Level getRow()
;

public int getPageSize()
;

public RequestData getRequestData()
;

public void setDate(Date createtime)
;

public long getTotal()
;

public Map<String,Object> getOptions()
;

public void setQueryTime(long queryTime)
;

public void setPageSize(int pageSize)
;

public String getViewData()
;

}