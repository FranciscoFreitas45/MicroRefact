package com.crazy.chapter16;
 public class DrawThread extends Thread{

 private  Accounts accounts;

 private  double drawAmount;

public DrawThread(String name, Accounts accounts, double drawAmount) {
    super(name);
    this.accounts = accounts;
    this.drawAmount = drawAmount;
}
public void run(){
    synchronized (accounts) {
        if (drawAmount <= accounts.getBalance()) {
            System.out.println(getName() + "ȡǮ�ɹ�,��ȡǮ��" + drawAmount);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            accounts.setBalance(accounts.getBalance() - drawAmount);
            System.out.println("\t�����" + accounts.getBalance());
        } else {
            System.out.println(getName() + "ȡǮ����");
        }
    }
}


}