package com.boast.chain;
 public class MajorDemo extends Manager{

public MajorDemo(String name) {
    super(name);
}
@Override
public void requestApplication(Request request){
    if (request.getRequestType() == "请假" && request.getNumber() <= 5) {
        System.out.println("批准");
    } else {
        if (superior != null) {
            superior.requestApplication(request);
        }
    }
}


}