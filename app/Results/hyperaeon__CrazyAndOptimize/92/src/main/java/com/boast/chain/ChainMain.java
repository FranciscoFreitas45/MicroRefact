package com.boast.chain;
 public class ChainMain {


public void main(String[] args){
    CommonManager jinli = new CommonManager("金立");
    MajorDemo zongjian = new MajorDemo("宗剑");
    GeneralManager zhongjingli = new GeneralManager("钟经理");
    jinli.setSuperior(zongjian);
    zongjian.setSuperior(zhongjingli);
    Request request1 = new Request("请假", "小米请假", 1);
    jinli.requestApplication(request1);
    Request request2 = new Request("请假", "小米请假", 4);
    jinli.requestApplication(request2);
    Request request3 = new Request("加薪", "小米加薪", 500);
    jinli.requestApplication(request3);
    Request request4 = new Request("加薪", "小米加薪", 1000);
    jinli.requestApplication(request4);
}


}