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
    @ApiModelProperty(hidden = true)
    @Null(message = "blogId should not be input by request!")
    private String blogId;

    @NotBlank(message = "blog title should not be blank!")
    private String title;

    @ApiModelProperty(hidden = true)
    @NotNull(message = "blog should always have a create date")
    private Date createDate;

    @ApiModelProperty(hidden = true)
    @NotNull(message = "blog should always have a modify date")
    private Date modifyDate;

    @ApiModelProperty(required = false)
    private Set<Tag> hashTag = new HashSet<>();

    @ApiModelProperty(required = true)
    @Pattern(regexp = "")
    private String status;

    @NotBlank
    @ApiModelProperty(required = true)
    private String authorId;

    @ApiModelProperty(required = false)
    private String content;

    @ApiModelProperty(required = false)
    private int views;

}
