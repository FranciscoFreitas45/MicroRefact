package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.NotFoundException;
public class CommentNotFound extends NotFoundException{

public CommentNotFound(Long id) {
    super("komentarz", id);
}
}