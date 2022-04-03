package pl.szymanski.sharelibrary.exceptions.requirements;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class RequirementAlreadyExists extends RuntimeException{

public RequirementAlreadyExists() {
    super(ExceptionMessages.REQUIREMENT_ALREADY_EXISTS);
}
}