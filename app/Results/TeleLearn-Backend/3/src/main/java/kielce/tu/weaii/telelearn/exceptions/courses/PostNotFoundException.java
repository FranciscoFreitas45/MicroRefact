package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.NotFoundException;
public class PostNotFoundException extends NotFoundException{

public PostNotFoundException(Long id) {
    super("post", id);
}
}