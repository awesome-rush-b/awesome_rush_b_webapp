package rushb.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel(description = "Blog Editor User Entity")
public class User {
    @ApiModelProperty(required = true)
    @NotBlank(message = "Username is mandatory")
    private String username;

    @ApiModelProperty(required = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email's pattern should be valid")
    private String email;

    @ApiModelProperty(required = true)
    @Size(min = 6, message = "Password should be longer than 6!")
    @Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$\"",message = "Password should contain both alphabets and numbers!")
    private String password;

    @ApiModelProperty(hidden = true)
    @Null(message = "userId should not be input by request!")
    private String userId;
}
