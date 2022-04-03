package goorum.goorum.Interface;
public interface BoardService {

   public List<Boardlist> getListByMemberId(long memberId,int page,int size,ModelAndView mav);
   public Board getBoardById(long boardId);
   public Boardlist getBoardListById(long boardId);
}