package kielce.tu.weaii.telelearn.exceptions;
 public class AttachmentNotFound extends NotFoundException{

public AttachmentNotFound(Long id) {
    super("załącznik", id);
}
}