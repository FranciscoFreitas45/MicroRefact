package com.crazy.chapter17.duplicate;
 public class MultiThreadDown {


public void main(String[] args){
    final DownUtil downUtil = new DownUtil("http://wenku.baidu.com/browse/downloadrec?doc_id=ba3d286a7e21af45b307a879&", "JAVA.doc", 4);
    downUtil.download();
    new Thread() {

        public void run() {
            while (downUtil.getCompleteRate() < 1) {
                System.out.println("已完成：" + downUtil.getCompleteRate());
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }.start();
}


public void run(){
    while (downUtil.getCompleteRate() < 1) {
        System.out.println("已完成：" + downUtil.getCompleteRate());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


}