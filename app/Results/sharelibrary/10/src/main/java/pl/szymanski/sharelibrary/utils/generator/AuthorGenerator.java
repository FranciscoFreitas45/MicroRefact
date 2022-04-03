package pl.szymanski.sharelibrary.utils.generator;
 import pl.szymanski.sharelibrary.entity.Author;
import pl.szymanski.sharelibrary.requests.AuthorRequest;
import pl.szymanski.sharelibrary.utils.constant.AuthorConstant;
public class AuthorGenerator {


public Author getAuthor(){
    Author author = new Author();
    author.setId(TEST_AUTHOR_ID);
    author.setName(TEST_AUTHOR_NAME);
    author.setSurname(TEST_AUTHOR_SURNAME);
    return author;
}


public AuthorRequest getAuthorRequest(){
    return new AuthorRequest(TEST_AUTHOR_NAME, TEST_AUTHOR_SURNAME);
}


}