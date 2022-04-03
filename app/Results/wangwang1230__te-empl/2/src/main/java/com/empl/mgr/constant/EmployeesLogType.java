package com.empl.mgr.constant;
 import java.io.Serializable;
public class EmployeesLogType implements Serializable{

 private  long serialVersionUID;

 public  int RECORDING_INFORMATION;

 public  int MODIFY_INFORMATION;

 public  int INDUCTION;

 public  int ELIMINATE;

 public  int DEPARTURE;

 public  int AGAIN_INDUCTION;


public String findStatus(int key){
    switch(key) {
        case 1:
            return "录入信息";
        case 2:
            return "修改信息";
        case 3:
            return "入职";
        case 4:
            return "淘汰";
        case 5:
            return "离职";
        case 6:
            return "重新入职";
    }
    return "";
}


}