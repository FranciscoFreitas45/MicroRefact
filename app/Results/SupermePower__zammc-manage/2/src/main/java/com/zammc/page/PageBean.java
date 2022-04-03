package com.zammc.page;
 import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;
public class PageBean {

 private  long totalRecorder;

 private  int pageSize;

 private  int pageNum;

 private  int totalPage;

 private  List<?> content;

 private  List<Map<String,Object>> data;


public void setContent(List<?> content){
    this.content = content;
}


public void setData(List<Map<String,Object>> data){
    this.data = data;
}


public void setTotalRecorder(long totalRecorder){
    this.totalRecorder = totalRecorder;
}


public int getPageNum(){
    return pageNum;
}


public void setTotalPage(int totalPage){
    this.totalPage = totalPage;
}


public List<?> getContent(){
    return content;
}


public void setPageNum(int pageNum){
    this.pageNum = pageNum;
}


public int getPageSize(){
    return pageSize;
}


public void load(JdbcTemplate jdbcTemplate,StringBuffer sql,List<Object> param){
    StringBuffer countSql = new StringBuffer();
    countSql.append("select count(*) from (");
    countSql.append(sql);
    countSql.append(") as total");
    totalRecorder = jdbcTemplate.queryForObject(countSql.toString(), param.toArray(), Integer.class);
    if (totalRecorder > 0) {
        sql.append(" limit ?,?");
        param.add((pageNum - 1) * pageSize);
        param.add(pageSize);
        data = jdbcTemplate.queryForList(sql.toString(), param.toArray());
    }
}


public void setPageInfo(long tr,int tp){
    setTotalRecorder(tr);
    setTotalPage(tp);
}


@Override
public String toString(){
    return "PageBean{" + "总记录数=" + totalRecorder + ", 每页的记录数=" + pageSize + ", 当前第几页=" + pageNum + ", 总页数=" + totalPage + '}';
}


public long getTotalRecorder(){
    return totalRecorder;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


public List<Map<String,Object>> getData(){
    return data;
}


public int getTotalPage(){
    if (totalRecorder % pageSize == 0) {
        totalPage = (int) (totalRecorder / pageSize);
    } else {
        totalPage = (int) ((totalRecorder / pageSize) + 1);
    }
    return totalPage;
}


}