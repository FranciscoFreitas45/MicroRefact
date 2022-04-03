package goorum.goorum.controller;
 import goorum.goorum.domain;
import goorum.goorum.service.BoardService;
import goorum.goorum.service.CategoryService;
import goorum.goorum.service.MemberLikeBoardService;
import goorum.goorum.service.ReplyService;
import goorum.goorum.util.Conversion;
import goorum.goorum.util.CurrentArticle;
import goorum.goorum.util.ErrorPage;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects.isNull;
import goorum.goorum.util.Constants;
import java.util.Objects.isNull;
import goorum.goorum.Interface.MemberLikeBoardService;
import goorum.goorum.Interface.CategoryService;
import goorum.goorum.Interface.ReplyService;
import goorum.goorum.Interface.MemberLikeBoardService;
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

 private  String DEFAULT_CATEGORY;

 private  String DEFAULT_PAGE;

 private  String DEFAULT_LIST_SIZE;

 private  String ON;

 private  int MAIN_PAGE;

 private  int PARENT;

@Autowired
 private  BoardService boardService;

@Autowired
 private  CategoryService categoryService;

@Autowired
 private  ReplyService replyService;

@Autowired
 private  MemberLikeBoardService memberLikeBoardService;


@PostMapping("/modify")
public void modify(long boardId,String title,String content,int category,String sector,String company,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Board article = boardService.getBoardById(boardId);
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(article) || isNull(loginMember) || (loginMember.getMemberId() != article.getWriter())) {
        res.put(RESULT, INVALID_APPROACH);
    } else if (boardService.modify(article, title, content, category, sector, company)) {
        res.put(RESULT, SUCCESS);
    } else {
        res.put(RESULT, FAIL);
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
}


@GetMapping("/{idx}")
public void showArticle(int boardId,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Member member = (Member) request.getSession().getAttribute("loginMember");
    ModelAndView mav = new ModelAndView();
    boolean isLike = false;
    if (!isNull(member)) {
        isLike = memberLikeBoardService.isLike(boardId, member.getMemberId());
        mav.addObject("isLike", isLike);
    }
    res.put("isLike", isLike);
    Boardlist article = boardService.getPostByIdForViewArticle(boardId);
    CurrentArticle currentArticle = boardService.getPrevAndNextArticle(boardId);
    List<Replylist> replies = replyService.getRepliesByBoardId(boardId);
    mav.setViewName("view_article");
    mav.addObject("article", article);
    mav.addObject("replies", replies);
    mav.addObject("current", currentArticle);
    res.put("prev", currentArticle.getPrev());
    res.put("next", currentArticle.getNext());
    res.put("boardId", article.getBoardId());
    res.put("category", article.getCategory());
    res.put("sector", article.getSector());
    res.put("company", article.getCompany());
    res.put("title", article.getTitle());
    res.put("content", article.getContent());
    res.put("profile", article.getProfile());
    res.put("writerId", article.getWriterId());
    res.put("writerNickname", article.getWriterNickname());
    res.put("date", article.getDate());
    res.put("likes", article.getLikes());
    res.put("replies", article.getReplies());
    JSONArray replyArray = new JSONArray();
    for (int i = 0; i < replies.size(); i++) {
        JSONObject data = new JSONObject();
        data.put("boardId", replies.get(i).getBoardId());
        data.put("replyId", replies.get(i).getReplyId());
        data.put("content", replies.get(i).getContent());
        data.put("date", replies.get(i).getDate());
        data.put("memberId", replies.get(i).getMemberId());
        data.put("nickname", replies.get(i).getNickname());
        data.put("profilePhoto", replies.get(i).getProfilePhoto());
        replyArray.add(i, data);
    }
    res.put("reply", replyArray);
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@PostMapping("/like")
public void likeOnOff(long boardId,String flag,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(loginMember)) {
        res.put(RESULT, INVALID_APPROACH);
    } else if (ON.equals(flag)) {
        // like on
        if (memberLikeBoardService.like(loginMember.getMemberId(), boardId)) {
            res.put(RESULT, SUCCESS);
        } else {
            res.put(RESULT, FAIL);
        }
    } else {
        // like off
        memberLikeBoardService.dislike(loginMember.getMemberId(), boardId);
        res.put(RESULT, SUCCESS);
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
}


@GetMapping("/modify/{idx}")
public void showModifyForm(long boardId,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    ModelAndView mav = new ModelAndView();
    Boardlist article = boardService.getPostById(boardId);
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(article) || isNull(loginMember) || (loginMember.getMemberId() != article.getWriterId())) {
        res.put(RESULT, FAIL);
        return;
    }
    mav.setViewName("modify_form");
    mav.addObject("article", article);
    mav.addObject("categories", categoryService.getList());
    res.put("article", article);
    res.put("categories", categoryService.getList());
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@GetMapping
public void showBoard(String category,Integer page,Integer size,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    ModelAndView mav = new ModelAndView();
    Page<Boardlist> boardlistPage = boardService.getList(category, page - 1, size);
    List<Boardlist> boards = boardlistPage.getContent();
    boardService.convertArticleFormat(boards);
    List<Category> categories = categoryService.getList();
    int totalPage = boardlistPage.getTotalPages();
    int startPage = Conversion.calcStartPage(page);
    // 추천글 게시판
    if (category.equals(DEFAULT_CATEGORY) && page == MAIN_PAGE) {
        List<Boardlist> notices = boardService.getNotices();
        List<Boardlist> topLikes = boardService.getTopLikes();
        boardService.convertArticleFormat(notices);
        boardService.convertArticleFormat(topLikes);
        JSONArray likeBoardArray = new JSONArray();
        for (int i = 0; i < topLikes.size(); i++) {
            JSONObject data = new JSONObject();
            data.put("boardId", topLikes.get(i).getBoardId());
            data.put("category", topLikes.get(i).getCategory());
            data.put("sector", topLikes.get(i).getSector());
            data.put("company", topLikes.get(i).getCompany());
            data.put("title", topLikes.get(i).getTitle());
            data.put("content", topLikes.get(i).getContent());
            data.put("writerId", topLikes.get(i).getWriterId());
            data.put("writerNickname", topLikes.get(i).getWriterNickname());
            data.put("date", topLikes.get(i).getDate());
            data.put("likes", topLikes.get(i).getLikes());
            data.put("replies", topLikes.get(i).getReplies());
            likeBoardArray.add(i, data);
        }
        res.put("topLikes", likeBoardArray);
    }
    // 일반글 게시판
    JSONArray boardArray = new JSONArray();
    for (int i = 0; i < boards.size(); i++) {
        JSONObject data = new JSONObject();
        data.put("boardId", boards.get(i).getBoardId());
        data.put("category", boards.get(i).getCategory());
        data.put("sector", boards.get(i).getSector());
        data.put("company", boards.get(i).getCompany());
        data.put("title", boards.get(i).getTitle());
        data.put("content", boards.get(i).getContent());
        data.put("writerId", boards.get(i).getWriterId());
        data.put("writerNickname", boards.get(i).getWriterNickname());
        data.put("date", boards.get(i).getDate());
        data.put("likes", boards.get(i).getLikes());
        data.put("replies", boards.get(i).getReplies());
        boardArray.add(i, data);
    }
    res.put("board", boardArray);
    res.put("category", category);
    res.put("selectSize", size);
    res.put("curPage", page);
    res.put("totalPage", totalPage);
    res.put("startPage", startPage);
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@GetMapping("/write")
public ModelAndView showWriteForm(HttpServletRequest request){
    ModelAndView mav = new ModelAndView();
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(loginMember)) {
        mav.setViewName("redirect:/board");
        return mav;
    }
    mav.setViewName("write_form");
    mav.addObject("categories", categoryService.getList());
    return mav;
}


@PostMapping("/delete/reply")
public void deleteReply(int replyId,int parent,int boardId,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    ModelAndView mav = new ModelAndView();
    Member member = (Member) request.getSession().getAttribute("loginMember");
    boolean result = replyService.deleteReply(replyId, parent, member);
    if (!result) {
        res.put(RESULT, INVALID_APPROACH);
        return;
    }
    mav.setViewName("redirect:/board/" + boardId);
    res.put(RESULT, SUCCESS);
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@PostMapping("/write/reply")
public void writeReply(int boardId,String content,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Member member = (Member) request.getSession().getAttribute("loginMember");
    boolean result = replyService.writeReply(boardId, PARENT, content, member);
    if (!result) {
        res.put(RESULT, INVALID_APPROACH);
    }
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/board/" + boardId);
    res.put(RESULT, SUCCESS);
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@PostMapping("/search")
public void searchKeyword(String category,Integer page,Integer size,String keyword,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    ModelAndView mav = new ModelAndView();
    Page<Boardlist> boardlistPage = boardService.getSearchList(keyword, category, page - 1, size);
    List<Boardlist> boards = boardlistPage.getContent();
    boardService.convertArticleFormat(boards);
    List<Category> categories = categoryService.getList();
    int totalPage = boardlistPage.getTotalPages();
    int startPage = Conversion.calcStartPage(page);
    JSONArray boardArray = new JSONArray();
    for (int i = 0; i < boards.size(); i++) {
        JSONObject data = new JSONObject();
        data.put("boardId", boards.get(i).getBoardId());
        data.put("category", boards.get(i).getCategory());
        data.put("sector", boards.get(i).getSector());
        data.put("company", boards.get(i).getCompany());
        data.put("title", boards.get(i).getTitle());
        data.put("content", boards.get(i).getContent());
        data.put("writerId", boards.get(i).getWriterId());
        data.put("writerNickname", boards.get(i).getWriterNickname());
        data.put("date", boards.get(i).getDate());
        data.put("likes", boards.get(i).getLikes());
        data.put("replies", boards.get(i).getReplies());
        boardArray.add(i, data);
    }
    res.put("board", boardArray);
    res.put("selectSize", size);
    res.put("curPage", page);
    res.put("totalPage", totalPage);
    res.put("startPage", startPage);
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    return;
}


@PostMapping("/write")
public void write(String title,String content,int category,String sector,String company,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(loginMember)) {
        res.put(RESULT, INVALID_APPROACH);
    } else if (boardService.write(loginMember.getMemberId(), title, content, category, sector, company)) {
        res.put(RESULT, SUCCESS);
    } else {
        res.put(RESULT, FAIL);
    }
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
}


@PostMapping("/delete")
public void deleteArticle(long boardId,HttpServletRequest request,HttpServletResponse response){
    JSONObject res = new JSONObject();
    Board article = boardService.getBoardById(boardId);
    Member loginMember = (Member) request.getSession().getAttribute("loginMember");
    if (isNull(article) || isNull(loginMember) || (loginMember.getMemberId() != article.getWriter() && loginMember.getMemberId() != ADMIN_ID)) {
        res.put(RESULT, INVALID_APPROACH);
        return;
    }
    boardService.deleteArticle(boardId);
    res.put(RESULT, SUCCESS);
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().print(res);
    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/board");
    return;
}


}