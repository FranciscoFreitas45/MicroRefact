package cn.gson.oasys.Interface;
public interface MailreciverDao {

   public List<Mailreciver> findByReadAndDelAndReciverId(Boolean read,Boolean del,User reciverid);
}