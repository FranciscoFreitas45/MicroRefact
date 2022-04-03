package run.halo.app.model.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import run.halo.app.model.enums.PostEditorType;
import run.halo.app.model.enums.PostStatus;
@Data
@Entity(name = "BasePost")
@Table(name = "posts", indexes = { @Index(name = "posts_type_status", columnList = "type, status"), @Index(name = "posts_create_time", columnList = "create_time") })
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER, columnDefinition = "int default 0")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BasePost extends BaseEntity{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
@GenericGenerator(name = "custom-id", strategy = "run.halo.app.model.entity.support" + ".CustomIdGenerator")
 private  Integer id;

@Column(name = "title", nullable = false)
 private  String title;

@Column(name = "status")
@ColumnDefault("1")
 private  PostStatus status;

@Deprecated
@Column(name = "url")
 private  String url;

@Column(name = "slug", unique = true)
 private  String slug;

@Column(name = "editor_type")
@ColumnDefault("0")
 private  PostEditorType editorType;

@Column(name = "original_content", nullable = false)
@Lob
 private  String originalContent;

@Column(name = "format_content")
@Lob
 private  String formatContent;

@Column(name = "summary")
@Lob
 private  String summary;

@Column(name = "thumbnail", length = 1023)
 private  String thumbnail;

@Column(name = "visits")
@ColumnDefault("0")
 private  Long visits;

@Column(name = "disallow_comment")
@ColumnDefault("0")
 private  Boolean disallowComment;

@Column(name = "password")
 private  String password;

@Column(name = "template")
 private  String template;

@Column(name = "top_priority")
@ColumnDefault("0")
 private  Integer topPriority;

@Column(name = "likes")
@ColumnDefault("0")
 private  Long likes;

@Column(name = "edit_time")
@Temporal(TemporalType.TIMESTAMP)
 private  Date editTime;

@Column(name = "meta_keywords", length = 511)
 private  String metaKeywords;

@Column(name = "meta_description", length = 1023)
 private  String metaDescription;

@Column(name = "word_count")
@ColumnDefault("0")
 private  Long wordCount;


@Override
public void prePersist(){
    super.prePersist();
    if (editTime == null) {
        editTime = getCreateTime();
    }
    if (status == null) {
        status = PostStatus.DRAFT;
    }
    if (summary == null) {
        summary = "";
    }
    if (thumbnail == null) {
        thumbnail = "";
    }
    if (disallowComment == null) {
        disallowComment = false;
    }
    if (password == null) {
        password = "";
    }
    if (template == null) {
        template = "";
    }
    if (topPriority == null) {
        topPriority = 0;
    }
    if (visits == null || visits < 0) {
        visits = 0L;
    }
    if (likes == null || likes < 0) {
        likes = 0L;
    }
    if (originalContent == null) {
        originalContent = "";
    }
    if (formatContent == null) {
        formatContent = "";
    }
    if (editorType == null) {
        editorType = PostEditorType.MARKDOWN;
    }
    if (wordCount == null || wordCount < 0) {
        wordCount = 0L;
    }
}


}