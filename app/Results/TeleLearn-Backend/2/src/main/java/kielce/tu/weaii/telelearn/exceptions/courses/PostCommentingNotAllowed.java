package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class PostCommentingNotAllowed extends BusinessLogicException{

public PostCommentingNotAllowed() {
    super("Dodawanie komenrarzy do posta zostało wyłączone przez autora.");
}
}