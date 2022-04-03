package goorum.goorum.service;
 import goorum.goorum.domain.Board;
import goorum.goorum.domain.Boardlist;
import goorum.goorum.repository.BoardRepository;
import goorum.goorum.repository.BoardlistRepository;
import goorum.goorum.util.Conversion;
import goorum.goorum.util.CurrentArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Objects;
@Service
@Slf4j
public class BoardServiceImpl implements BoardService{

 private  String ALL_POSTS;

 private  String NOTICE;

 private  int PREV_OR_NEXT;

 private  int PREV_ARTICLE;

 private  int NEXT_ARTICLE;

@Autowired
 private  BoardRepository boardRepository;

@Autowired
 private  BoardlistRepository boardlistRepository;


@Override
public List<Boardlist> getListByMemberId(long memberId,int page,int size,ModelAndView mav){
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Boardlist> boardlistPage = boardlistRepository.findAllByWriterId(memberId, pageRequest);
    List<Boardlist> boards = boardlistPage.getContent();
    boards.forEach(Conversion::convertDateFormatForArticleList);
    Conversion.convertTitleLength(boards);
    int totalPages = boardlistPage.getTotalPages();
    mav.addObject("boards", boards);
    mav.addObject("totalPages", totalPages);
    return boards;
}


@Override
public List<Boardlist> getTopLikes(){
    return boardlistRepository.findTopLikes();
}


@Override
public Board getBoardById(long boardId){
    return boardRepository.findById(boardId).orElse(null);
}


@Override
public Boardlist getBoardListById(long boardId){
    return boardlistRepository.findById(boardId).orElse(null);
}


@Override
public CurrentArticle getPrevAndNextArticle(long boardId){
    CurrentArticle currentArticle;
    List<Board> boards = boardRepository.findPrevAndNextBoardIdByBoardId(boardId);
    switch(boards.size()) {
        case 0:
            currentArticle = new CurrentArticle();
            break;
        case 1:
            long prevOrNext = boards.get(PREV_OR_NEXT).getBoardId();
            if (prevOrNext > boardId) {
                currentArticle = CurrentArticle.builder().next(prevOrNext).build();
                break;
            }
            currentArticle = CurrentArticle.builder().prev(prevOrNext).build();
            break;
        case 2:
            currentArticle = CurrentArticle.builder().prev(boards.get(PREV_ARTICLE).getBoardId()).next(boards.get(NEXT_ARTICLE).getBoardId()).build();
            break;
        default:
            currentArticle = null;
            break;
    }
    return currentArticle;
}


@Override
public void deleteArticle(long boardId){
    try {
        boardRepository.deleteById(boardId);
    } catch (Exception e) {
        log.error("\n >> " + e.toString() + "\n >> There isn't a board no." + boardId);
    }
}


@Override
public Boardlist getPostByIdForViewArticle(long boardId){
    Boardlist board = getPostById(boardId);
    Conversion.convertContent(board);
    Conversion.convertDateFormatForArticle(board);
    return board;
}


@Override
public boolean modify(Board article,String title,String content,long category,String sector,String company){
    article.setTitle(title);
    article.setContent(content);
    article.setCategory(category);
    article.setSector(sector);
    article.setCompany(company);
    return !Objects.isNull(boardRepository.save(article));
}


@Override
public Page<Boardlist> getList(String category,int page,int size){
    Page<Boardlist> boardlistPage;
    PageRequest pageRequest = PageRequest.of(page, size);
    if (ALL_POSTS.equals(category)) {
        boardlistPage = boardlistRepository.findAll(pageRequest);
    } else {
        boardlistPage = boardlistRepository.findAllByCategory(category, pageRequest);
    }
    return boardlistPage;
}


@Override
public Page<Boardlist> getSearchList(String keyword,String category,int page,int size){
    Page<Boardlist> boardlistPage;
    PageRequest pageRequest = PageRequest.of(page, size);
    boardlistPage = boardlistRepository.findAllSearch(keyword, pageRequest);
    return boardlistPage;
}


@Override
public void convertArticleFormat(List<Boardlist> articles){
    articles.forEach(Conversion::convertDateFormatForArticleList);
    Conversion.convertTitleLength(articles);
}


@Override
public Boardlist getPostById(long boardId){
    return boardlistRepository.findById(boardId).orElse(null);
}


@Override
public boolean write(Long memberId,String title,String content,long category,String sector,String company){
    Board board = Board.builder().writer(memberId).title(title).content(content).category(category).sector(sector).company(company).build();
    return !Objects.isNull(boardRepository.save(board));
}


@Override
public List<Boardlist> getNotices(){
    return boardlistRepository.findAllByCategory(NOTICE);
}


}