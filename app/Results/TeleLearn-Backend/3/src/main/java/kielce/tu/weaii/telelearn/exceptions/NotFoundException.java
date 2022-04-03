package kielce.tu.weaii.telelearn.exceptions;
 public class NotFoundException extends RuntimeException{

public NotFoundException(String message) {
    super(message);
}public NotFoundException(String resourceName, Long id) {
    super(String.format("Zasób %s z id %s nie został znaleziony.", resourceName, id));
}
}