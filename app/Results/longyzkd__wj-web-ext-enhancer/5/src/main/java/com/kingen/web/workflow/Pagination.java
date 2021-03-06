package com.kingen.web.workflow;
 public class Pagination {

 protected  String pageStr;

 protected  Integer totalSum;

 protected  Integer totalPage;

 protected  Integer currentPage;

 protected  Integer prePage;

 protected  Integer nextPage;

 protected  Integer pageNumDefault;

 protected  Integer pageNum;

 protected  Integer firstResult;

 protected  Integer maxResult;

 protected  String orderStr;

 protected  String orderColumn;

 protected  String preName;


public void setTotalSum(Integer totalSum){
    this.totalSum = totalSum;
}


public void setFirstResult(Integer firstResult){
    this.firstResult = firstResult;
}


public void setMaxResult(Integer maxResult){
    this.maxResult = maxResult;
}


public String getPageStr(){
    return pageStr;
}


public Integer getFirstResult(){
    return firstResult;
}


public Integer getPageNumDefault(){
    return pageNumDefault;
}


public void setPageNumDefault(Integer pageNumDefault){
    this.pageNumDefault = pageNumDefault;
}


public void setNextPage(Integer nextPage){
    this.nextPage = nextPage;
}


public String getOrderStr(){
    return orderStr;
}


public void setOrderColumn(String orderColumn){
    this.orderColumn = orderColumn;
}


public Integer getCurrentPage(){
    return currentPage;
}


public Integer getPrePage(){
    return prePage;
}


public String getPreName(){
    return preName;
}


public void setOrderStr(String orderStr){
    this.orderStr = orderStr;
}


public Integer getPageNum(){
    if (this.pageNum == null) {
        this.pageNum = this.getPageNumDefault();
    }
    pageNum = pageNum < 1 || pageNum > 1000 ? 10 : pageNum;
    return pageNum;
}


public void setPrePage(Integer prePage){
    this.prePage = prePage;
}


public Integer getNextPage(){
    return nextPage;
}


public void setTotalPage(Integer totalPage){
    this.totalPage = totalPage;
}


public void setCurrentPage(Integer currentPage){
    this.currentPage = currentPage;
}


public Integer getMaxResult(){
    return maxResult;
}


public void setPageNum(Integer pageNum){
    this.pageNum = pageNum;
}


public Integer getTotalSum(){
    return totalSum;
}


public void processTotalPage(){
    this.setTotalPage(new Integer((this.getTotalSum() / this.getPageNum()) + (this.getTotalSum() % this.getPageNum() != 0 ? 1 : 0)));
    this.setPrePage(this.getCurrentPage() - 1 > 1 ? this.getCurrentPage() - 1 : 1);
    this.setNextPage(this.getCurrentPage() + 1 < this.getTotalPage() ? this.getCurrentPage() + 1 : this.getTotalPage());
    this.setFirstResult(this.getCurrentPage() == 1 ? 0 : this.getPrePage() * this.getPageNum());
    this.setMaxResult(this.getPageNum() > this.getTotalSum() ? this.getTotalSum() : this.getPageNum());
    StringBuffer str = new StringBuffer();
    str.append("<table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"box6\">");
    str.append("<tr><td width=\"100%\" height=\"24\"  class=\"bt\">");
    str.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" background=\"\">");
    str.append("<input type=\"hidden\" name=\"current\" value=\"\">");
    str.append("<tr>");
    str.append("<td align=\"left\" valign=\"center\" class=\"bt\" width=\"30%\">???" + this.getTotalPage() + "???&nbsp;");
    str.append("???" + this.getCurrentPage() + "???&nbsp;");
    str.append("??????<input id=\"pageNum\" name=\"" + this.preName + "pageNum\" type=\"text\" class=\"bt\" value=\"" + this.getPageNum() + "\" onChange=\"doSearch(1);\"  maxlength=\"3\" size=\"2\">?????????</td>");
    str.append("<td align=\"right\" valign=\"center\" class=\"bt\" width=\"70%\">");
    if (this.getTotalPage().longValue() > 1) {
        str.append("<a href=\"javascript:doSearch(1);\" class=\"bt\" onClick=\"\">??????</a>&nbsp;&nbsp;");
        str.append("<a href=\"javascript:doSearch(" + this.getPrePage() + ");\" class=\"bt\" onClick=\"\" style=\"cursor:hand\">?????????</a>&nbsp;&nbsp;");
        str.append("<a href=\"javascript:doSearch(" + this.getNextPage() + ");\" class=\"bt\" onClick=\"\" style=\"cursor:hand\">?????????</a>&nbsp;");
        str.append("<a href=\"javascript:doSearch(" + this.getTotalPage() + ");\" class=\"bt\" onClick=\"\" style=\"cursor:hand\"> ?????? </a>&nbsp;");
    } else {
        str.append("??????&nbsp;&nbsp;");
        str.append("?????????&nbsp;&nbsp;");
        str.append("?????????&nbsp;");
        str.append("??????&nbsp;");
    }
    str.append("???" + this.getTotalSum() + "?????????<input type=\"hidden\" id=\"currentPage\" name=\"" + this.preName + "currentPage\" value=\"" + this.getCurrentPage() + "\">");
    str.append("</td>");
    str.append("</tr>");
    str.append("</table>");
    str.append("</td></tr>");
    str.append("</table>");
    this.setPageStr(str.toString());
}


public void setPreName(String preName){
    this.preName = preName;
}


public void setPageStr(String pageStr){
    this.pageStr = pageStr;
}


public String getOrderColumn(){
    return orderColumn;
}


public Integer getTotalPage(){
    return totalPage;
}


}