package run.halo.app.model.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import run.halo.app.model.enums.AttachmentType;
@Data
@Entity
@Table(name = "attachments", indexes = { @Index(name = "attachments_media_type", columnList = "media_type"), @Index(name = "attachments_create_time", columnList = "create_time") })
@ToString
@EqualsAndHashCode(callSuper = true)
public class Attachment extends BaseEntity{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id")
@GenericGenerator(name = "custom-id", strategy = "run.halo.app.model.entity.support" + ".CustomIdGenerator")
 private  Integer id;

@Column(name = "name", nullable = false)
 private  String name;

@Column(name = "path", length = 1023, nullable = false)
 private  String path;

@Column(name = "file_key", length = 2047)
 private  String fileKey;

@Column(name = "thumb_path", length = 1023)
 private  String thumbPath;

@Column(name = "media_type", length = 127, nullable = false)
 private  String mediaType;

@Column(name = "suffix", length = 50)
 private  String suffix;

@Column(name = "width")
@ColumnDefault("0")
 private  Integer width;

@Column(name = "height")
@ColumnDefault("0")
 private  Integer height;

@Column(name = "size", nullable = false)
 private  Long size;

@Column(name = "type")
@ColumnDefault("0")
 private  AttachmentType type;


@Override
public void prePersist(){
    super.prePersist();
    if (fileKey == null) {
        fileKey = "";
    }
    if (thumbPath == null) {
        thumbPath = "";
    }
    if (suffix == null) {
        suffix = "";
    }
    if (width == null) {
        width = 0;
    }
    if (height == null) {
        height = 0;
    }
    if (type == null) {
        type = AttachmentType.LOCAL;
    }
}


}