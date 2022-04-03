package goorum.goorum.service;
 import goorum.goorum.domain.Board;
import goorum.goorum.domain.Boardlist;
import goorum.goorum.util.CurrentArticle;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
public interface BoardService {


public List<Boardlist> getListByMemberId(long memberId,int page,int size,ModelAndView mav)
;

public List<Boardlist> getTopLikes()
;

public Board getBoardById(long boardId)
;

public Boardlist getBoardListById(long boardId)
;

public CurrentArticle getPrevAndNextArticle(long boardId)
;

public Boardlist getPostByIdForViewArticle(long boardId)
;

public void deleteArticle(long boardId)
;

public boolean modify(Board article,String title,String content,long category,String sector,String company)
;

public Page<Boardlist> getList(String category,int page,int size)
;

public Page<Boardlist> getSearchList(String keyword,String category,int page,int size)
;

public Boardlist getPostById(long boardId)
;

public void convertArticleFormat(List<Boardlist> articles)
;

public boolean write(Long memberId,String title,String content,long category,String sector,String company)
;

public List<Boardlist> getNotices()
;

}