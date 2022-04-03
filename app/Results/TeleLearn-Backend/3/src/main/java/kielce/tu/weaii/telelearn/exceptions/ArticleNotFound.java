package kielce.tu.weaii.telelearn.exceptions;
 public class ArticleNotFound extends NotFoundException{

public ArticleNotFound(Long id) {
    super("artykuł", id);
}
}