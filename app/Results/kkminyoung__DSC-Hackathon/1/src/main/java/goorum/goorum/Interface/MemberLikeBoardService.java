package goorum.goorum.Interface;
public interface MemberLikeBoardService {

   public boolean isLike(long boardId,long memberId);
   public void dislike(long memberId,long boardId);
   public boolean like(long memberId,long boardId);
}