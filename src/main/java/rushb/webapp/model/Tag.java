package rushb.webapp.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Blog Tag Entity")
public class Tag {
    private String tagId;
    private String name;
    private int count;
}
