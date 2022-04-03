package pl.szymanski.sharelibrary.exceptions.categories;
 import pl.szymanski.sharelibrary.exceptions.ExceptionMessages;
public class CategoryNotExist extends RuntimeException{

public CategoryNotExist(String name) {
    super(String.format(ExceptionMessages.CATEGORY_NOT_EXISTS, name));
}
}