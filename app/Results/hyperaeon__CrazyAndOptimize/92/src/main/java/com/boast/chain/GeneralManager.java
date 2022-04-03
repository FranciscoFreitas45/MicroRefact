package com.boast.chain;
 public class GeneralManager extends Manager{

public GeneralManager(String name) {
    super(name);
}
@Override
public void requestApplication(Request request){
    if (request.getRequestType() == "请假") {
        System.out.println("批准");
    } else if (request.getRequestType() == "加薪" && request.getNumber() <= 500) {
        System.out.println("批准");
    } else if (request.getRequestType() == "加薪" && request.getNumber() > 500) {
        System.out.println("再说吧");
    }
}


}