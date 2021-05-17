public class OpbalAndId {

 private  int balId;

 private  float opBal;


public void setOpBal(float opBal){
    this.opBal = opBal;
}


public int getBalId(){
    return balId;
}


public void setBalId(int balId){
    this.balId = balId;
}


@Override
public String toString(){
    return "OpbalAndId [balId=" + balId + ", opBal=" + opBal + "]";
}


public float getOpBal(){
    return opBal;
}


}