package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.ArticleNotFound;
import kielce.tu.weaii.telelearn.models.GlobalNews;
import kielce.tu.weaii.telelearn.repositories.ports.GlobalNewsRepository;
import kielce.tu.weaii.telelearn.requests.GlobalNewsRequest;
import kielce.tu.weaii.telelearn.services.ports.GlobalNewsService;
import kielce.tu.weaii.telelearn.services.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class GlobalNewsServiceImpl implements GlobalNewsService{

 private  GlobalNewsRepository repository;

 private  UserService userService;


@Transactional
@Override
public GlobalNews add(GlobalNewsRequest request){
    GlobalNews globalNews = new GlobalNews();
    BeanUtils.copyProperties(request, globalNews);
    globalNews.setAuthor(userService.getById(request.getAuthorId()));
    return repository.save(globalNews);
}


@Override
public GlobalNews getById(Long id){
    return repository.getById(id).orElseThrow(() -> new ArticleNotFound(id));
}


@Override
public GlobalNews edit(Long id,GlobalNewsRequest request){
    GlobalNews globalNews = getById(id);
    BeanUtils.copyProperties(request, globalNews);
    if (!globalNews.getAuthor().getId().equals(request.getAuthorId())) {
        globalNews.setAuthor(userService.getById(request.getAuthorId()));
    }
    return repository.save(globalNews);
}


public Page<GlobalNews> getPage(int pageSize,int pageNo){
    return repository.getPage(pageSize, pageNo);
}


@Override
@Transactional
public void delete(Long id){
    repository.delete(getById(id));
}


}