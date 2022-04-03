package org.jeecgframework.core.common.hibernate.qbc;
 import java.util.Map;
public class Pager {

 private  int curPageNO;

 private  int pageSize;

 private  int rowsCount;

 private  int pageCount;

 private  Map<String,Object> map;

/**
 * @param allCount
 *            记录行数
 * @param offset
 *            记录开始数目
 * @param pageSize
 *            每页显示的记录数
 */
public Pager(int allCount, int curPagerNo, int pageSize, Map<String, Object> map) {
    this.curPageNO = curPagerNo;
    this.pageSize = pageSize;
    this.rowsCount = allCount;
    this.map = map;
    this.pageCount = (int) Math.ceil((double) allCount / pageSize);
}public Pager() {
}
public int getRowsCount(){
    return rowsCount;
}


public int next(){
    return (curPageNO + 1 > pageCount) ? pageCount : curPageNO + 1;
}


public boolean isFirst(){
    return (curPageNO == 1) ? true : false;
}


public int getPageSize(){
    return pageSize;
}


public int last(){
    return pageCount;
}


public int previous(){
    return (curPageNO - 1 < 1) ? 1 : curPageNO - 1;
}


public int getPageCount(){
    return pageCount;
}


public boolean isLast(){
    return (curPageNO == pageCount) ? true : false;
}


public String toString(){
    return "Pager的值为 " + " curPageNO = " + curPageNO + " limit = " + pageSize + " rowsCount = " + rowsCount + " pageCount = " + pageCount;
}


public String getToolBar(String myaction,String myform){
    String str = "";
    str += "<script language='javascript'>" + "\n";
    str += "function commonSubmit(val){" + "\n";
    // 校验是否全由数字组成
    str += "var patrn=/^[0-9]{1,20}$/;" + "\n";
    str += "if (!patrn.exec(val)){" + "\n";
    str += " alert(\"请输入有效页号！\");" + "\n";
    str += " return false ;" + "\n";
    str += " }else{" + "\n";
    str += "    document." + myform + ".action='" + myaction + "&curPageNO='+val;" + "\n";
    str += "    document." + myform + ".submit();" + "\n";
    str += "    return true ;" + "\n";
    str += "} " + "\n";
    str += " }" + "\n";
    str += "</script>" + "\n";
    str += "&nbsp;<DIV class=pageArea id=pageArea>共<b>" + rowsCount + "</b>条&nbsp;当前第" + curPageNO + "/" + pageCount + "页&nbsp;&nbsp;&nbsp;";
    if (curPageNO == 1 || curPageNO == 0)
        str += "<a class=pageFirstDisable title=首页 onMouseMove=\"style.cursor='hand'\">&nbsp;<a class=pagePreviousDisable title=上一页 onMouseMove=\"style.cursor='hand'\"></a>";
    else {
        str += "<a class=pageFirst title=首页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(1)\"></a>";
        str += "<a class=pagePrevious title=上一页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + (curPageNO - 1) + ")\"></a>";
    }
    if (curPageNO - pageCount == 0 || pageCount == 0 || pageCount == 1)
        str += "<a class=pageNextDisable  title=下一页 onMouseMove=\"style.cursor='hand'\">&nbsp;<a class=pageLastDisable title=尾页 onMouseMove=\"style.cursor='hand'\"></a>&nbsp;";
    else {
        str += "<a class=pageNext title=下一页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + (curPageNO + 1) + ")\"></a>";
        str += "<a class=pageLast title=尾页 onMouseMove=\"style.cursor='hand'\" onclick=\"commonSubmit(" + pageCount + ")\"></a>";
    }
    if (pageCount == 1 || pageCount == 0) {
        str += " &nbsp;转到:<input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=5 name=\"pageroffsetll\" size=3 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;";
        str += "<A class=pageGoto id=pageGoto title=转到 onclick='return commonSubmit()'></A></DIV>";
    } else {
        str += " &nbsp;转到:<input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=5 name=\"pageroffsetll\" size=3 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;";
        str += "<A class=pageGoto id=pageGoto title=转到 onclick='commonSubmit(document.all.pageroffsetll.value)'></A></DIV>";
    }
    return str;
}


public int first(){
    return 1;
}


}