package net.shangtech.ssh.core.base;
 import java.util.List;
public class Page {

 private  int start;

 private  int limit;

 private  int pageNo;

 private  int pageSize;

 private  int totalCount;

 private  List<T> result;

 private  boolean leftPoints;

 private  boolean rightPoints;

 private  int pageStart;

 private  int pageEnd;

 private  String hql;

 private  Object[] values;

public Page(int limit) {
    if (limit == 0) {
        limit = 15;
    }
    this.limit = limit;
}
public void setPageNo(int pageNo){
    this.pageNo = pageNo;
}


public void setTotalCount(int totalCount){
    this.totalCount = totalCount;
    // 计算总页数
    this.pageSize = (int) (totalCount / limit);
    pageSize = totalCount % limit == 0 ? pageSize : pageSize + 1;
    // 如果start为0就认为是用pageNo+limit查询,否则视为start+limit查询
    if (start == 0) {
        // 当前页码范围应该要大于0且不大于总页数
        if (pageNo > pageSize)
            pageNo = pageSize;
        if (pageNo <= 0)
            pageNo = 1;
        // 计算起始位置
        start = (pageNo - 1) * limit;
    } else {
        // start应该大于0且不大于记录总数
        if (start > totalCount)
            start = totalCount;
        if (start < 0)
            start = 0;
    }
    pageStart = 1;
    pageEnd = pageSize;
    if (pageSize > 10) {
        if (pageNo > 5) {
            pageStart = pageNo - 4;
            if (pageStart > pageSize - 10) {
                pageStart = pageSize - 10;
            }
            leftPoints = true;
        }
        if (pageSize - pageNo > 5) {
            pageEnd = pageStart + 9;
            rightPoints = true;
        }
    }
}


public Object[] getValues(){
    return values;
}


public int getStart(){
    return start;
}


public void setRightPoints(boolean rightPoints){
    this.rightPoints = rightPoints;
}


public String getHql(){
    return hql;
}


public int getLimit(){
    return limit;
}


public void setResult(List<T> result){
    this.result = result;
}


public boolean isRightPoints(){
    return rightPoints;
}


public long getTotalCount(){
    return totalCount;
}


public int getPageStart(){
    return pageStart;
}


public void setLeftPoints(boolean leftPoints){
    this.leftPoints = leftPoints;
}


public int getPageEnd(){
    return pageEnd;
}


public int getPageSize(){
    return pageSize;
}


public void setPageEnd(int pageEnd){
    this.pageEnd = pageEnd;
}


public void setStart(int start){
    this.start = start;
}


public void setLimit(int limit){
    this.limit = limit;
}


public boolean isLeftPoints(){
    return leftPoints;
}


public List<T> getResult(){
    return result;
}


public void setPageStart(int pageStart){
    this.pageStart = pageStart;
}


public int getPageNo(){
    return pageNo;
}


public void setQuery(String hql,Object values){
    this.hql = hql;
    this.values = values;
}


public void setPageSize(int pageSize){
    this.pageSize = pageSize;
}


}