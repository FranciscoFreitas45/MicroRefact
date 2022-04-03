package com.crazy.chapter5.duplicate;
 public class Sub extends Base{

 private  String name;


public void test(){
    name = "";
    System.out.println("������д����ķ��� ����name�ַ����ĳ����ǣ�" + name.length());
}


public void main(String[] args){
    Sub s = new Sub();
}


}