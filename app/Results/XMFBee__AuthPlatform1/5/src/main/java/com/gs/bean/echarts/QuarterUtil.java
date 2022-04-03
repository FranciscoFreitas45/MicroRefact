package com.gs.bean.echarts;
 public class QuarterUtil {


public String quarter(String ag){
    if (ag == "01月" || ag == "02月" || ag == "03月") {
        ag = "1-3月";
    } else if (ag == "04月" || ag == "05月" || ag == "06月") {
        ag = "4-6月";
    } else if (ag == "07月" || ag == "8月" || ag == "9月") {
        ag = "7-9月";
    } else if (ag == "10月" || ag == "11月" || ag == "12月") {
        ag = "10-12月";
    }
    return ag;
}


}