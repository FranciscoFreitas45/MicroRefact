package Interface;
public interface SNSAccountRepository {

   public SNSAccount findBySnsid(String snsid);
   public SNSAccount findBySnsidAndOrgi(String snsid,String orgi);
}