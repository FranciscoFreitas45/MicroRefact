package com.crazy.chapter16.duplicate.synchronize;
 public class DrawThread2 extends Thread{

 private  Account account;

 private  double drawAmount;

public DrawThread2(String name, Account account, double drawAmount) {
    super(name);
    this.account = account;
    this.drawAmount = drawAmount;
}
public void run(){
    synchronized (account) {
        if (account.getBalance() >= drawAmount) {
            System.out.println(getName() + "ȡǮ�ɹ���ȡǮ��" + drawAmount);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.setBalance(account.getBalance() - drawAmount);
            System.out.println("�˻���" + account.getBalance());
        } else {
            System.out.println(getName() + "ȡǮʧ�ܣ�����");
        }
    }
}


}