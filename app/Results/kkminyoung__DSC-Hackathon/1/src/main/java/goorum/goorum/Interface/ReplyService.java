package goorum.goorum.Interface;
public interface ReplyService {

   public List<Replylist> getRepliesByBoardId(long boardId);
   public boolean writeReply(long boardId,long parent,String content,Member member);
   public boolean deleteReply(long replyId,long parent,Member member);
}