package rushb.webapp.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Article {
    private int id;
    private String name;
    private Date createDate;
    private Date modifyDate;
    private List<String> HashTag;
    private String status;
    private String author;
}
