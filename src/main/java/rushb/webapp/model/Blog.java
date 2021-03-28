package rushb.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@ApiModel(description = "Blog Entity")
public class Blog {
    @Null(message = "blogId should not be input by request!")
    private String blogId;

    @NotBlank(message = "blog title should not be blank!")
    private String title;

    @NotNull(message = "blog should always have a create date")
    private Date createDate;

    @NotNull(message = "blog should always have a modify date")
    private Date modifyDate;

    private Set<Tag> hashTag = new HashSet<>();

    @Pattern(regexp = "")
    private String status;

    @NotBlank
    private String authorId;

    private String content;

    private int views;

}
