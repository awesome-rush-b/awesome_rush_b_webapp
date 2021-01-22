package rushb.webapp.model;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class Blog {
    private String blogId;
    private String title;
    private Date createDate;
    private Date modifyDate;
    private Set<Tag> hashTag;
    private String status;
    private String authorId;
    private String content;

}
