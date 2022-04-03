package run.halo.app.utils;
 import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
public class SlugUtils {

 private  Pattern NON_LATIN;

 private  Pattern WHITESPACE;


@NonNull
@Deprecated
public String slugify(String input){
    Assert.hasText(input, "Input string must not be blank");
    String withoutWhitespace = WHITESPACE.matcher(input).replaceAll("-");
    String normalized = Normalizer.normalize(withoutWhitespace, Normalizer.Form.NFKD);
    String slug = NON_LATIN.matcher(normalized).replaceAll("");
    return slug.toLowerCase(Locale.ENGLISH);
}


public String slug(String input){
    Assert.hasText(input, "Input string must not be blank");
    String slug = input.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5\\.\\-)]", "").replaceAll("[\\?\\\\/:|<>\\*\\[\\]\\(\\)\\$%\\{\\}@~\\.]", "").replaceAll("\\s", "").toLowerCase(Locale.ENGLISH);
    return StringUtils.isNotEmpty(slug) ? slug : String.valueOf(System.currentTimeMillis());
}


}