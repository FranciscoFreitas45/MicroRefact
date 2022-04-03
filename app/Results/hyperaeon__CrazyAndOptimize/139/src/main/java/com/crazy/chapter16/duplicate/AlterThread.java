package com.crazy.chapter16.duplicate;
 public class AlterThread {


public void main(String[] args){
    String str = "abc";
    AlterA aa = new AlterA(str, "�߳�A");
    AlterA ab = new AlterA(str, "�߳�B");
    aa.start();
    ab.start();
}


}