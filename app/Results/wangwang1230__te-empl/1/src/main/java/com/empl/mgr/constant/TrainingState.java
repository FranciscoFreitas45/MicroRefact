package com.empl.mgr.constant;
 public class TrainingState {

 public  int NOT_STARTED;

 public  int HAVE_BEGUN;

 public  int FINISH;

 public  int FORCED_END;


public String findVal(int key){
    switch(key) {
        case 0:
            return "未开始";
        case 1:
            return "已经开始";
        case 2:
            return "完成培训";
        case 3:
            return "强制停止";
    }
    return "";
}


}