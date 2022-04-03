package com.boast.chain;
 public class CommonManager extends Manager{

public CommonManager(String name) {
    super(name);
}
@Override
public void requestApplication(Request request){
    if (request.getRequestType() == "请假" && request.getNumber() <= 2) {
        System.out.println("批准");
    } else {
        if (superior != null) {
            superior.requestApplication(request);
        }
    }
}


}