package goorum.goorum.service;
 import goorum.goorum.domain.Member;
import goorum.goorum.domain.Replylist;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
public interface ReplyService {


public int getListByMemberId(long memberId,int page,int size,ModelAndView mav)
;

public boolean deleteReply(long replyId,long parent,Member member)
;

public boolean writeReply(long boardId,long parent,String content,Member member)
;

public List<Replylist> getRepliesByBoardId(long boardId)
;

}