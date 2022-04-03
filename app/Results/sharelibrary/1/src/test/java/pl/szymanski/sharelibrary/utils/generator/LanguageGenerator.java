package pl.szymanski.sharelibrary.utils.generator;

import pl.szymanski.sharelibrary.entity.Language;
import pl.szymanski.sharelibrary.requests.LanguageRequest;
import pl.szymanski.sharelibrary.utils.constant.BookConstant;

public class LanguageGenerator {

    public static Language getLanguage() {
        Language language = new Language();
        language.setId(1);
        language.setName(BookConstant.TEST_LANGUAGE_NAME);
        return language;
    }

    public static LanguageRequest getLanguageRequest() {
        return new LanguageRequest(
                1, BookConstant.TEST_LANGUAGE_NAME
        );
    }
}
