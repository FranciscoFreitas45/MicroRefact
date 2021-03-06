package run.halo.app.utils;
 import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.emoji.EmojiImageType;
import com.vladsch.flexmark.ext.emoji.EmojiShortcutType;
import com.vladsch.flexmark.ext.escaped.character.EscapedCharacterExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gitlab.GitLabExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import run.halo.app.model.support.HaloConst;
public class MarkdownUtils {

 private  DataHolder OPTIONS;

 private  Parser PARSER;

 private  HtmlRenderer RENDERER;

 private  Pattern FRONT_MATTER;


public String removeFrontMatter(String markdown){
    markdown = markdown.trim();
    Matcher matcher = FRONT_MATTER.matcher(markdown);
    if (matcher.find()) {
        return markdown.replace(matcher.group(), "");
    }
    return markdown;
}


public String renderHtml(String markdown){
    if (StringUtils.isBlank(markdown)) {
        return StringUtils.EMPTY;
    }
    // Render netease music short url.
    if (markdown.contains(HaloConst.NETEASE_MUSIC_PREFIX)) {
        markdown = markdown.replaceAll(HaloConst.NETEASE_MUSIC_REG_PATTERN, HaloConst.NETEASE_MUSIC_IFRAME);
    }
    // Render bilibili video short url.
    if (markdown.contains(HaloConst.BILIBILI_VIDEO_PREFIX)) {
        markdown = markdown.replaceAll(HaloConst.BILIBILI_VIDEO_REG_PATTERN, HaloConst.BILIBILI_VIDEO_IFRAME);
    }
    // Render youtube video short url.
    if (markdown.contains(HaloConst.YOUTUBE_VIDEO_PREFIX)) {
        markdown = markdown.replaceAll(HaloConst.YOUTUBE_VIDEO_REG_PATTERN, HaloConst.YOUTUBE_VIDEO_IFRAME);
    }
    Node document = PARSER.parse(markdown);
    return RENDERER.render(document);
}


public Map<String,List<String>> getFrontMatter(String markdown){
    AbstractYamlFrontMatterVisitor visitor = new AbstractYamlFrontMatterVisitor();
    Node document = PARSER.parse(markdown);
    visitor.visit(document);
    return visitor.getData();
}


}