package kielce.tu.weaii.telelearn.exceptions;
 public class InvalidSenderException extends BusinessLogicException{

public InvalidSenderException() {
    super("Próba wysłania wiadomości za innego użytkownika");
}
}