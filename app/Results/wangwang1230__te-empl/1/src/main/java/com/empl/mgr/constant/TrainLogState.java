package com.empl.mgr.constant;
 public class TrainLogState {

 public  int APPLY;

 public  int START;

 public  int FINISH;

 public  int EXIT;

 public  int EVALUATION;


public String findStatus(int key){
    switch(key) {
        case 1:
            return "已报名";
        case 2:
            return "培训中";
        case 3:
            return "培训完成";
        case 4:
            return "停止培训";
        case 5:
            return "已评分";
    }
    return "";
}


}